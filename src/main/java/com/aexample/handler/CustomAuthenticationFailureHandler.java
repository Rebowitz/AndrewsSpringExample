package com.aexample.handler;

import java.io.IOException;
import java.util.Date;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.web.servlet.LocaleResolver;

import com.aexample.security.service.UserLoginAttemptsServiceImpl;

//@Component
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
	
	Logger logger = LoggerFactory.getLogger(CustomAuthenticationFailureHandler.class);

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private LocaleResolver localeResolver;
    
	@Autowired
	UserLoginAttemptsServiceImpl userLoginAttemptsServiceImpl;    

 //   @Transactional
    @Override
    public void onAuthenticationFailure(final HttpServletRequest request, final HttpServletResponse response, final AuthenticationException exception) throws IOException, ServletException {
        
    	logger.debug("Entered onAuthenticationFailure");
    	
    	setDefaultFailureUrl("/login?error=true");

        String email = request.getParameter("email");
  //      String notifier = null;
        
        final Locale locale = localeResolver.resolveLocale(request);

        String errorMessage = messageSource.getMessage("message.badCredentials", null, locale);

        if (exception.getMessage().equalsIgnoreCase("User is disabled")) {
            errorMessage = messageSource.getMessage("auth.message.disabled", null, locale);
        } else if (exception.getMessage().equalsIgnoreCase("User account has expired")) {
            errorMessage = messageSource.getMessage("auth.message.expired", null, locale);
        } else if (exception.getMessage().equalsIgnoreCase("blocked")) {
            errorMessage = messageSource.getMessage("auth.message.blocked", null, locale);
        }else{
        	logger.debug("onAuthenticationFailureHandler calling userUpdateFailedLoginAttempts");
        	userLoginAttemptsServiceImpl.userUpdateFailedLoginAttempts(email, new Date());
        }
        request.getSession().setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION, errorMessage);
    }
}