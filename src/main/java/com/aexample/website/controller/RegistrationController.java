package com.aexample.website.controller;

import java.io.UnsupportedEncodingException;
import java.util.Locale;
import java.util.UUID;

import javax.security.auth.login.AccountNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.aexample.captcha.ICaptchaService;
import com.aexample.persistence.model.Accounts;
import com.aexample.persistence.model.RegistrationVerificationToken;
import com.aexample.persistence.model.UserVerificationToken;
import com.aexample.registration.OnRegistrationCompleteEvent;
import com.aexample.security.ISecurityRegService;
import com.aexample.website.service.IRegistrationService;
import com.aexample.website.viewBean.RegistrationBean;
import com.aexample.website.error.InvalidOldPasswordException;
import com.aexample.website.dto.PasswordDto;
import com.aexample.website.error.AccountsNotFoundException;
import com.aexample.website.util.GenericResponse;




import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RegistrationController {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    private IRegistrationService regService;

    @Autowired
    private ISecurityRegService securityregService;

    @Autowired
    private ICaptchaService captchaService;

    @Autowired
    private MessageSource messages;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Autowired
    private Environment env;

    public RegistrationController() {
        super();
    }

    // Registration

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    @ResponseBody
    public GenericResponse registerNewAccount(@Valid final RegistrationBean regBean, final HttpServletRequest request) {
        LOGGER.debug("Registering account with information: {}", regBean);

        final Accounts registered = regService.registerNewAccount(regBean);
        eventPublisher.publishEvent(new OnRegistrationCompleteEvent(registered, request.getLocale(), getAppUrl(request)));
        return new GenericResponse("success");
    }

    @RequestMapping(value = "/registrationConfirm", method = RequestMethod.GET)
    public String confirmRegistration(final Locale locale, final Model model, @RequestParam("token") final String token) throws UnsupportedEncodingException {
        final String result = regService.validateVerificationToken(token);
        if (result.equals("valid")) {
            final Accounts accounts = regService.getAccount(token);
            System.out.println(accounts);
            if (accounts.isUsing2FA()) {
                model.addAttribute("qr", regService.generateQRUrl(accounts));
                return "redirect:/qrcode.html?lang=" + locale.getLanguage();
            }
            model.addAttribute("message", messages.getMessage("message.accountVerified", null, locale));
            return "redirect:/login?lang=" + locale.getLanguage();
        }

        model.addAttribute("message", messages.getMessage("auth.message." + result, null, locale));
        model.addAttribute("expired", "expired".equals(result));
        model.addAttribute("token", token);
        return "redirect:/badAccounts.html?lang=" + locale.getLanguage();
    }

    // Accounts activation - verification

    @RequestMapping(value = "/Accounts/resendRegistrationToken", method = RequestMethod.GET)
    @ResponseBody
    public GenericResponse resendRegistrationToken(final HttpServletRequest request, @RequestParam("token") final String existingToken) {
        final RegistrationVerificationToken newToken = regService.generateNewVerificationToken(existingToken);
        final Accounts Accounts = regService.getAccount(newToken.getToken());
        mailSender.send(constructResendVerificationTokenEmail(getAppUrl(request), request.getLocale(), newToken, Accounts));
        return new GenericResponse(messages.getMessage("message.resendToken", null, request.getLocale()));
    }

    // Reset password
    @RequestMapping(value = "/Accounts/resetPassword", method = RequestMethod.POST)
    @ResponseBody
    public GenericResponse resetPassword(final HttpServletRequest request, @RequestParam("email") final String accountsEmail) {
        final Accounts accounts = regService.findAccountByEmail(accountsEmail);
        if (accounts == null) {
            throw new AccountsNotFoundException();
        }
        final String token = UUID.randomUUID()
            .toString();
        regService.createPasswordResetTokenForAccount(accounts, token);
        mailSender.send(constructResetTokenEmail(getAppUrl(request), request.getLocale(), token, accounts));
        return new GenericResponse(messages.getMessage("message.resetPasswordEmail", null, request.getLocale()));
    }

    @RequestMapping(value = "/Accounts/changePassword", method = RequestMethod.GET)
    public String showChangePasswordPage(final Locale locale, final Model model, @RequestParam("id") final long id, @RequestParam("token") final String token) {
        final String result = securityregService.validatePasswordResetToken(id, token);
        if (result != null) {
            model.addAttribute("message", messages.getMessage("auth.message." + result, null, locale));
            return "redirect:/login?lang=" + locale.getLanguage();
        }
        return "redirect:/updatePassword.html?lang=" + locale.getLanguage();
    }

    @RequestMapping(value = "/Accounts/savePassword", method = RequestMethod.POST)
    @ResponseBody
    public GenericResponse savePassword(final Locale locale, @Valid PasswordDto passwordDto) {
        final Accounts Accounts = (Accounts) SecurityContextHolder.getContext()
            .getAuthentication()
            .getPrincipal();
        regService.changeAccountPassword(Accounts, passwordDto.getNewPassword());
        return new GenericResponse(messages.getMessage("message.resetPasswordSuc", null, locale));
    }

    // change Accounts password
    @RequestMapping(value = "/Accounts/updatePassword", method = RequestMethod.POST)
    @ResponseBody
    public GenericResponse changeAccountsPassword(final Locale locale, @Valid PasswordDto passwordDto) {
        final Accounts Accounts = regService.findAccountByEmail(((Accounts) SecurityContextHolder.getContext()
            .getAuthentication()
            .getPrincipal()).getEmail());
        if (!regService.checkIfValidOldPassword(Accounts, passwordDto.getOldPassword())) {
            throw new InvalidOldPasswordException();
        }
        regService.changeAccountPassword(Accounts, passwordDto.getNewPassword());
        return new GenericResponse(messages.getMessage("message.updatePasswordSuc", null, locale));
    }

    @RequestMapping(value = "/Accounts/update/2fa", method = RequestMethod.POST)
    @ResponseBody
    public GenericResponse modifyAccounts2FA(@RequestParam("use2FA") final boolean use2FA) throws UnsupportedEncodingException {
        final Accounts Accounts = regService.updateAccount2FA(use2FA);
        if (use2FA) {
            return new GenericResponse(regService.generateQRUrl(Accounts));
        }
        return null;
    }

    // ============== NON-API ============

    private SimpleMailMessage constructResendVerificationTokenEmail(final String contextPath, final Locale locale, final RegistrationVerificationToken newToken, final Accounts Accounts) {
        final String confirmationUrl = contextPath + "/registrationConfirm.html?token=" + newToken.getToken();
        final String message = messages.getMessage("message.resendToken", null, locale);
        return constructEmail("Resend Registration Token", message + " \r\n" + confirmationUrl, Accounts);
    }

    private SimpleMailMessage constructResetTokenEmail(final String contextPath, final Locale locale, final String token, final Accounts Accounts) {
        final String url = contextPath + "/Accounts/changePassword?id=" + Accounts.getId() + "&token=" + token;
        final String message = messages.getMessage("message.resetPassword", null, locale);
        return constructEmail("Reset Password", message + " \r\n" + url, Accounts);
    }

    private SimpleMailMessage constructEmail(String subject, String body, Accounts Accounts) {
        final SimpleMailMessage email = new SimpleMailMessage();
        email.setSubject(subject);
        email.setText(body);
        email.setTo(Accounts.getEmail());
        email.setFrom(env.getProperty("support.email"));
        return email;
    }

    private String getAppUrl(HttpServletRequest request) {
        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }

}



/* ORIGINAL CONTROLLER CODE HANGING ONTO IT  FOR RIGHT NOW
@Controller
public class RegistrationController {

	private static final Logger logger = LoggerFactory.getLogger(RegistrationController.class);	
	
    @Autowired
    @Qualifier("registrationValidator")
    private RegistrationValidator registrationValidator;
    
    @Autowired
    private IRegistrationService rsi;
    
    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(registrationValidator);
    }
    
    @ModelAttribute("registrationBean")
    public RegistrationBean createRegistrationBean(){
    	// Model Attribute must be the same name as used in the registration.jsp form header
    	return new RegistrationBean();
    }
    
	@RequestMapping(value="/registration",method=RequestMethod.GET)
	public ModelAndView displayRegistration(HttpServletRequest request, HttpServletResponse response)

	{
		logger.info("Entered Registration Controller GET");
		ModelAndView model = new ModelAndView("registration");			
		RegistrationBean regBean = new RegistrationBean();
		model.addObject("regBean", regBean);
		logger.info("Created modelandview GET\n");
		
		return model;
	}	
	
	@RequestMapping(value="/registration",method = RequestMethod.POST)
	public String submitForm(Model model, @ModelAttribute("registrationBean") @Valid RegistrationBean registrationBean, BindingResult result) {
		logger.info("Processing registration POST");
		
		model.addAttribute("registration", registrationBean);
		String returnVal = "registration";
//		RegistrationServiceImpl rsi = new RegistrationServiceImpl();

		
		if(result.hasErrors()) {
			logger.info("Result has errors");
			logger.info("Result errors are:");
			
			for(ObjectError error : result.getAllErrors()){
				logger.info(error.getDefaultMessage());
			}
			
//			initModelList(model);
			returnVal = "registration";
		} else {
			model.addAttribute("registration", registrationBean);
			//persist the record to the database
			logger.info("Validation with no errors");
			logger.info("persisting account info");
			rsi.create(registrationBean);
			
			returnVal = "Registered";
		}		
		return returnVal;
	}


}	

*/