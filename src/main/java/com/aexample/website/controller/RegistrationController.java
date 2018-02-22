package com.aexample.website.controller;


import java.util.Calendar;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.aexample.annotations.ILogger;
import com.aexample.event.OnRegistrationEnteredEvent;
import com.aexample.event.OnRegistrationConfirmEvent;
import com.aexample.event.OnResendRegistrationTokenEvent;
import com.aexample.event.OnResetPasswordRequestEvent;
import com.aexample.persistence.model.UserAccount;
import com.aexample.persistence.model.UserPasswordResetToken;
import com.aexample.persistence.model.UserVerificationToken;
import com.aexample.security.IEncryptionService;
import com.aexample.website.dto.EmailOnlyDto;
import com.aexample.website.dto.PasswordDto;
import com.aexample.website.dto.UserDto;
import com.aexample.website.exception.RegistrationFailedException;
import com.aexample.website.exception.UserAlreadyExistsException;
import com.aexample.website.service.IUserService;

/*
 * In order to support AOP creation of activity log records
 * each method must have an id and email populated in its declared fields.
 * These fields are accessed via reflection and used to populated fields in
 * the activity log records.  This is done to eliminate more dbase access requests
 * to pull the same information multiple times.
 * 
 */


@Controller
public class RegistrationController {
//    private final Logger logger = LoggerFactory.getLogger(getClass());
	private static @ILogger Logger logger;	
	
    @Autowired
    private IUserService userService;
    
    @Autowired
    private IEncryptionService encryptionService;

    @Autowired
    private MessageSource messages;

    @Autowired
    private ApplicationEventPublisher eventPublisher;
    
    
 /*   @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(new RegistrationValidator());
    }    
*/
    
    public RegistrationController() {
        super();
    }

    
   @RequestMapping(value = "/user/registration", method = RequestMethod.GET)
    public ModelAndView showRegistrationForm(HttpServletRequest request, Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("userDto", userDto);
        logger.debug("Serving registration page");
        return new ModelAndView("registration", "user", model);
    }

   
	@RequestMapping(value = "/user/registration", method = RequestMethod.POST)
	public ModelAndView registerUserAccount(@ModelAttribute("userDto") @Valid final UserDto userDto,
			BindingResult result, final HttpServletRequest request, final HttpServletResponse response) {
		logger.debug("Registering user account with information: {}", userDto);
		UserAccount registered = new UserAccount();
		final Locale locale = request.getLocale();
		StringBuffer appUrlBuff = request.getRequestURL(); // returns the URL up
															// to the query ?
		String appUrl = appUrlBuff.toString();

		// validation passed, now save the record
		// returning UserAccountExists exception if account exists
		// exceptionhandler is at end of controller code
		if (!result.hasErrors()) {

			registered = userService.registerNewUserAccount(userDto);

//			try {
				// the event publisher sends registration confirmation email
				// via com.aexample.listener.OnRegistrationEnteredListener
				logger.debug("Triggering OnRegistrationEnteredEvent");
				eventPublisher.publishEvent(new OnRegistrationEnteredEvent(registered, locale, appUrl));
//			} catch (Exception me) {
//				logger.debug("Email error exception--" + me);
//				return new ModelAndView("emailError", "user", userDto);
//			}
		}
		if (registered == null) {
			// registerNewUserAccount routine throws the
			// UserAlreadyExistsException
			// so if processing reaches this point with registered being null
			// there is a system outage!
			throw new RegistrationFailedException();
		}
		if (result.hasErrors()) {
			return new ModelAndView("registration", "user", userDto);
		} else {

			return new ModelAndView("successRegister", "user", userDto);
		}
	}

 
	@RequestMapping(value = "/user/registrationConfirm", method = RequestMethod.GET)
	public String confirmRegistration(final HttpServletRequest request, final Model model,
			@RequestParam("token") final String token) {
		final Locale locale = request.getLocale();
		StringBuffer appUrlBuff = request.getRequestURL(); // returns the URL up
															// to the query ?
		String appUrl = appUrlBuff.toString();

		final UserVerificationToken verificationToken = userService.getVerificationToken(token);
		if (verificationToken == null) {
			final String message = messages.getMessage("auth.message.invalidToken", null, locale);
			model.addAttribute("message", message);
			return "redirect:/badRegistrationToken?lang=" + locale.getLanguage();
		}

		final UserAccount user = verificationToken.getUser();

		if (user.getEnabled().booleanValue()) {
			// bypass checks and updating tokens-just return page
			// this handles a second confirmation attempt on the same token
			return "registrationCompleted";
		}
		final Calendar cal = Calendar.getInstance();
		if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
			model.addAttribute("message", messages.getMessage("auth.message.expired", null, locale));
			model.addAttribute("expired", true);
			model.addAttribute("token", token);
			return "redirect:/badRegistrationToken?lang=" + locale.getLanguage();
		}

		user.setEnabled(Boolean.TRUE);
		userService.saveOrUpdate(user);

		logger.debug("Triggering OnRegistrationConfirmEvent");
		eventPublisher.publishEvent(new OnRegistrationConfirmEvent(user, request.getLocale(), appUrl));

		return "registrationCompleted";
	}
   
    @RequestMapping(value = "/user/resendRegistrationToken", method = RequestMethod.POST)
    public ModelAndView resendRegistrationToken(@ModelAttribute("emailOnlyDto") @Valid final EmailOnlyDto emailOnlyDto,
			BindingResult result, final HttpServletRequest request, final HttpServletResponse response){
    		
        final Locale locale = request.getLocale();
        StringBuffer appUrlBuff = request.getRequestURL();  //returns the URL up to the query ?
        String appUrl = appUrlBuff.toString();        
        
        //look up token in database
        //if exists and not expired, send regconfirm email
        //if not, generate token and send regconfirm email
        //if email not found, throw error
        //else error out
        
        String emailAddy = emailOnlyDto.getEmail();
        
		UserAccount aUser = userService.findUserByEmail(emailAddy);
		ModelAndView mav = new ModelAndView();
		
		if(aUser == null){
			mav.addObject("msg", messages.getMessage("auth.message.usernotfound", null, locale));
			mav.setViewName("registrationIncomplete");
			return mav;
		}
		
		
		//check for user not found here and throw error
		//TODO: check for user not found here and throw error
	
		//if the token is still valid or if a new token must be generated
		//it is handled by the CustomHTMLMailer which is called by
		//the OnResendRegistrationTokenListener triggered by the below event call
	
		 logger.debug("Triggering OnResendRegistrationTokenEvent");
         eventPublisher.publishEvent(new OnResendRegistrationTokenEvent
             (aUser, request.getLocale(), appUrl));           
  
//        model.addAttribute("message", messages.getMessage("message.resendToken", null, locale));
         mav.setViewName("registrationTokenResent");
        return mav;		
		
    }	
		

    /*
     * The resetPassword process mirrors the registrationToken process.
     * The requestor fills out the form with their current password(Old Password) and the new password(entered twice)
     * The new password is validated as being correctly entered, a token is generated, the new password is encrypted
     * and the password and token are stored in the userPasswordResetToken table with a time stamp.
     * 
     * An email with the token embedded in a link is sent to the email address on record.  When the user clicks on the url,
     * the token is validated, the date checked and if ok the encrypted password in the userPasswordResetToken table is then
     * entered into the main user table. 
     * 
     */
    
    
    
    
    @RequestMapping(value = "/user/resetPassword", method = RequestMethod.GET)
    public ModelAndView showResetPasswordForm(HttpServletRequest request, Model model) {
    	PasswordDto passwordDto = new PasswordDto();
        model.addAttribute("passwordDto", passwordDto);
        logger.debug("Serving reset password page");
        return new ModelAndView("resetPassword", "password", model);
        
        
    }

    
    @RequestMapping(value = "/user/resetPassword", method = RequestMethod.POST)
    public String resetPassword(final HttpServletRequest request,@ModelAttribute("passwordDto") @Valid final PasswordDto passwordDto, Model model)
    		{
    	
        final Locale locale = request.getLocale();
        StringBuffer appUrlBuff = request.getRequestURL();  //returns the URL up to the query ?
        String appUrl = appUrlBuff.toString();        
        
    	final UserAccount user = userService.findUserByEmail(passwordDto.getEmail());
        if (user == null) {
        	logger.debug("User not found! " + passwordDto.getEmail());
            model.addAttribute("message", messages.getMessage("message.userNotFound", null, locale));
            return "redirect:/login?lang=" + request.getLocale().getLanguage();
        }
        
        //encrypt old password entered and compare to one in the user account        
        if(!encryptionService.checkPassword(passwordDto.getOldPassword(), user.getEncryptedPassword())){
        
        	logger.debug("Encrypted passwords do not match!");
        	logger.debug("origPassword is: " + passwordDto.getOldPassword());
        	logger.debug("Encrypted password is: " + user.getEncryptedPassword());
        	
            model.addAttribute("message", messages.getMessage("message.userNotFound", null, request.getLocale()));
            return "redirect:/login?lang=" + request.getLocale().getLanguage();
        }
                
        //check that the two new passwords entered match
        //this check is done by the @Valid annotation associated code

            //store new password encrypted in the userPasswordResetToken table
        	//when the valid token is returned, the encrypted password will be moved to the UserAccount record
            //send that token       	
        	
        	String newEncryptPassword = encryptionService.encryptString(passwordDto.getPassword());      	     	
        	logger.debug("new encrypted password is: " + newEncryptPassword);
        	logger.debug("Creating new password change token.");
            
        	final String token = userService.generateTokenValue();
            
        	logger.debug("Generated token is: " + token);
            logger.debug("Creating PasswordResetTokenForUser");
        	
            userService.createPasswordResetTokenForUser(user, token, newEncryptPassword);        	
        	//create email and include token link
            
        
		logger.debug("Triggering OnResetPasswordRequestEvent");
		eventPublisher.publishEvent(new OnResetPasswordRequestEvent(user, request.getLocale(), appUrl, token));

		return "resetPasswordTokenSent";
    }

    //we get a valid response via clicked link with token and we move the password from the token table to the user account table
    //then dump the user at the login page.
       
	

    @RequestMapping(value = "/user/resetPasswordTokenConfirm", method = RequestMethod.GET)
    public String submitPasswordResetToken(final HttpServletRequest request, final Model model,
			@RequestParam("token") final String token) {
		final Locale locale = request.getLocale();

		final UserPasswordResetToken verificationToken = userService.getPasswordResetToken(token);
		if (verificationToken == null) {
			final String message = messages.getMessage("auth.message.invalidToken", null, locale);
			model.addAttribute("message", message);
			return "redirect:/badRegistrationToken?lang=" + locale.getLanguage();
		}


		final Calendar cal = Calendar.getInstance();
		if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
			model.addAttribute("message", messages.getMessage("auth.message.expired", null, locale));
			model.addAttribute("expired", true);
			model.addAttribute("token", token);
			return "redirect:/badRegistrationToken?lang=" + locale.getLanguage();
		}


		final UserAccount user = verificationToken.getUser();
		
		String encryptedPassword = verificationToken.getNewEncryptPassword();
		
		user.setEncryptedPassword(encryptedPassword);
		userService.saveOrUpdate(user);

		logger.debug("Password updated!");

		return "passwordUpdated";
		
    }
    
    // NON-API
    
	@ExceptionHandler(UserAlreadyExistsException.class)
	public ModelAndView handleUserAlreadyExistsException(UserAlreadyExistsException e,  HttpServletRequest request, Model model) {
		logger.debug("User already exists", e);
		UserDto userDto = new UserDto();

		userDto.setFirstName(request.getParameter("firstName"));
		userDto.setLastName(request.getParameter("lastName"));
		userDto.setEmail(request.getParameter("email"));
		model.addAttribute(userDto);
		ModelAndView mav = new ModelAndView("registration","user",model);
		mav.addObject("errorMessage","There is a current account with that email address!");
		return mav;		
		
	}
    @ExceptionHandler(RegistrationFailedException.class)
    public String handleRegistrationFailedException(HttpServletRequest request){
        final Locale locale = request.getLocale();
        
        return "redirect:/SystemError.html?lang=" + locale.getLanguage();
    }
}