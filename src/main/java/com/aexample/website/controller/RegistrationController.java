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
import org.springframework.core.env.Environment;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
import com.aexample.event.OnRegistrationCompleteEvent;
import com.aexample.persistence.model.UserAccount;
import com.aexample.persistence.model.UserPasswordResetToken;
import com.aexample.persistence.model.UserVerificationToken;
import com.aexample.security.service.RegistrationUserDetailsService;
import com.aexample.website.dto.UserDto;
import com.aexample.website.exception.RegistrationFailedException;
import com.aexample.website.exception.UserAlreadyExistsException;
import com.aexample.website.service.IUserService;

@Controller
public class RegistrationController {
//    private final Logger logger = LoggerFactory.getLogger(getClass());
	private static @ILogger Logger logger;	
	
    @Autowired
    private IUserService userService;

    @Autowired
    private MessageSource messages;

    private JavaMailSender mailSender;

    @Autowired
    private ApplicationEventPublisher eventPublisher;
    
    @Autowired
 //   private UserDetailsService userDetailsService;
    private RegistrationUserDetailsService userDetailsService;

    @Autowired
    private Environment env;
    
    
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
   public ModelAndView registerUserAccount(@ModelAttribute("userDto") @Valid final UserDto userDto, BindingResult result, final HttpServletRequest request, final HttpServletResponse response) {
       logger.debug("Registering user account with information: {}", userDto);
       UserAccount registered = new UserAccount();
       final Locale locale = request.getLocale();
       StringBuffer appUrlBuff = request.getRequestURL();  //returns the URL up to the query ?
       String appUrl = appUrlBuff.toString();
       
       //validation passed, now save the record
       //returning UserAccountExists exception if account exists
       //exceptionhandler is at end of controller code
       if (!result.hasErrors()) {
    	   
		   registered = userService.registerNewUserAccount(userDto);
		   
    	   try{
               //the event publisher sends registration confirmation email
    		   //via com.aexample.listener.RegistrationListener
    		   logger.debug("Triggering OnRegistrationCompleteEvent");
               eventPublisher.publishEvent(new OnRegistrationCompleteEvent
                 (registered, request.getLocale(), appUrl));
           }catch(Exception me) {
        	   logger.debug("Email error exception--" + me);
               return new ModelAndView("emailError", "user", userDto);
           }
       }
       if (registered == null) {
    	   //registerNewUserAccount routine throws the UserAlreadyExistsException
    	   //so if processing reaches this point with registered being null
    	   //there is a system outage!
           throw new RegistrationFailedException();
       }
       if (result.hasErrors()) {
           return new ModelAndView("registration", "user", userDto);
       } 
       else {
           return new ModelAndView("successRegister", "user", userDto);
       }
   }

 
    @RequestMapping(value = "/user/registrationConfirm", method = RequestMethod.GET)
    public String confirmRegistration(final HttpServletRequest request, final Model model, @RequestParam("token") final String token) {
        final Locale locale = request.getLocale();

        final UserVerificationToken verificationToken = userService.getVerificationToken(token);
        if (verificationToken == null) {
            final String message = messages.getMessage("auth.message.invalidToken", null, locale);
            model.addAttribute("message", message);
            return "redirect:/badUser.html?lang=" + locale.getLanguage();
        }

        final UserAccount user = verificationToken.getUser();
        
        if(user.getEnabled().booleanValue()){  //bypass checks and updating tokens-just return page
            return "registrationCompleted";         	
        }
        final Calendar cal = Calendar.getInstance();
        if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
            model.addAttribute("message", messages.getMessage("auth.message.expired", null, locale));
            model.addAttribute("expired", true);
            model.addAttribute("token", token);
            return "redirect:/badUser.html?lang=" + locale.getLanguage();
        }
        
        user.setEnabled(Boolean.TRUE);
   
        userService.saveOrUpdate(user);
        return "registrationCompleted";       
    }
   
    @RequestMapping(value = "/user/resendRegistrationToken", method = RequestMethod.GET)
    public String resendRegistrationToken(final HttpServletRequest request, final Model model, @RequestParam("token") final String existingToken) {
        final Locale locale = request.getLocale();
        final UserVerificationToken newToken = userService.generateNewVerificationToken(existingToken);
        final UserAccount user = userService.getUser(newToken.getToken());
        try {
            final String appUrl = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
            final SimpleMailMessage email = constructResetVerificationTokenEmail(appUrl, request.getLocale(), newToken, user);
            mailSender.send(email);
        } catch (final MailAuthenticationException e) {
            logger.debug("MailAuthenticationException", e);
            return "redirect:/emailError.html?lang=" + locale.getLanguage();
        } catch (final Exception e) {
            logger.debug(e.getLocalizedMessage(), e);
            model.addAttribute("message", e.getLocalizedMessage());
            return "redirect:/login.html?lang=" + locale.getLanguage();
        }
        model.addAttribute("message", messages.getMessage("message.resendToken", null, locale));
        return "redirect:/login.html?lang=" + locale.getLanguage();
    }

    @RequestMapping(value = "/user/resetPassword", method = RequestMethod.POST)
    public String resetPassword(final HttpServletRequest request, final Model model, @RequestParam("email") final String userEmail) {
        final UserAccount user = userService.findUserByEmail(userEmail);
        if (user == null) {
            model.addAttribute("message", messages.getMessage("message.userNotFound", null, request.getLocale()));
            return "redirect:/login.html?lang=" + request.getLocale().getLanguage();
        }

        final String token = userService.generateTokenValue();
        userService.createPasswordResetTokenForUser(user, token);
        try {
            final String appUrl = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
            final SimpleMailMessage email = constructResetTokenEmail(appUrl, request.getLocale(), token, user);
            mailSender.send(email);
        } catch (final MailAuthenticationException e) {
            logger.debug("MailAuthenticationException", e);
            return "redirect:/emailError.html?lang=" + request.getLocale().getLanguage();
        } catch (final Exception e) {
            logger.debug(e.getLocalizedMessage(), e);
            model.addAttribute("message", e.getLocalizedMessage());
            return "redirect:/login.html?lang=" + request.getLocale().getLanguage();
        }
        model.addAttribute("message", messages.getMessage("message.resetPasswordEmail", null, request.getLocale()));
        return "redirect:/login.html?lang=" + request.getLocale().getLanguage();
    }

    @RequestMapping(value = "/user/changePassword", method = RequestMethod.GET)
    public String changePassword(final HttpServletRequest request, final Model model, @RequestParam("id") final long id, @RequestParam("token") final String token) {
        final Locale locale = request.getLocale();

        final UserPasswordResetToken passToken = userService.getPasswordResetToken(token);
        final UserAccount user = passToken.getUser();
        if ((passToken == null) || (user.getId() != id)) {
            final String message = messages.getMessage("auth.message.invalidToken", null, locale);
            model.addAttribute("message", message);
            return "redirect:/login.html?lang=" + locale.getLanguage();
        }

        final Calendar cal = Calendar.getInstance();
        if ((passToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
            model.addAttribute("message", messages.getMessage("auth.message.expired", null, locale));
            return "redirect:/login.html?lang=" + locale.getLanguage();
        }

        final Authentication auth = new UsernamePasswordAuthenticationToken(user, null, userDetailsService.loadUserByUsername(user.getEmail()).getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);

        return "redirect:/updatePassword.html?lang=" + locale.getLanguage();
    }

    @RequestMapping(value = "/user/savePassword", method = RequestMethod.POST)
    @PreAuthorize("hasRole('READ_PRIVILEGE')")
    public String savePassword(final HttpServletRequest request, final Model model, @RequestParam("password") final String password) {
        final Locale locale = request.getLocale();

        final UserAccount user = (UserAccount) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        userService.changeUserPassword(user, password);
        model.addAttribute("message", messages.getMessage("message.resetPasswordSuc", null, locale));
        return "redirect:/login.html?lang=" + locale;
    }

    // NON-API

    private final SimpleMailMessage constructResetVerificationTokenEmail(final String contextPath, final Locale locale, final UserVerificationToken newToken, final UserAccount user) {
        final String confirmationUrl = contextPath + "/registrationConfirm.html?token=" + newToken.getToken();
        final String message = messages.getMessage("message.resendToken", null, locale);
        final SimpleMailMessage email = new SimpleMailMessage();
        email.setSubject("Resend Registration Token");
        email.setText(message + " \r\n" + confirmationUrl);
        email.setTo(user.getEmail());
        email.setFrom(env.getProperty("support.email"));
        return email;
    }

    private final SimpleMailMessage constructResetTokenEmail(final String contextPath, final Locale locale, final String token, final UserAccount user) {
        final String url = contextPath + "/old/user/changePassword?id=" + user.getId() + "&token=" + token;
        final String message = messages.getMessage("message.resetPassword", null, locale);
        final SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(user.getEmail());
        email.setSubject("Reset Password");
        email.setText(message + " \r\n" + url);
        email.setFrom(env.getProperty("support.email"));
        return email;
    }

    public static String getFullURL(HttpServletRequest request) {
        StringBuffer requestURL = request.getRequestURL();
        String queryString = request.getQueryString();

        if (queryString == null) {
            return requestURL.toString();
        } else {
            return requestURL.append('?').append(queryString).toString();
        }
    }
    
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