/**
 * 
 */
package com.aexample.handler;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.transaction.annotation.Transactional;

import com.aexample.security.service.UserLoginAttemptsServiceImpl;


/**
 * @author Main Login
 * $Rev$
 * $Date$
 *
 */
@Transactional
public class SiteAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	private static final Logger logger = LoggerFactory.getLogger(SiteAuthenticationSuccessHandler.class);
//	private static @ILogger Logger logger;	
	
	@Autowired
	UserLoginAttemptsServiceImpl userLoginAttemptsServiceImpl;
	
	String outcome = null;
	
	
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    public SiteAuthenticationSuccessHandler() {
        super();
    }
    
    //API
    @Transactional
    @Override
    public void onAuthenticationSuccess(final HttpServletRequest request, final HttpServletResponse response, 
    		final Authentication authentication) throws IOException {
    
//		Authentication auth = super.authenticate(authentication);

		logger.debug("Using userLoginAttemptsRepository reset fail attempts");
		logger.debug("Email address: " + authentication.getName());
		logger.debug("Password: " + authentication.getCredentials());		
		//if reach here, means login success, else an exception will be thrown
		//reset the user_attempts   name actually contains email
		userLoginAttemptsServiceImpl.clearFailedLoginAttempts(authentication.getName(), new Date());  	
    	
    	handle(request, response, authentication);        
        clearAuthenticationAttributes(request);
    }
 
    //IMPL
    protected void handle(final HttpServletRequest request, final HttpServletResponse response, 
    		final Authentication authentication) throws IOException {
        final String targetUrl = determineTargetUrl(authentication);
 
        if (response.isCommitted()) {
            logger.debug("Response has already been committed. Unable to redirect to " + targetUrl);
            return;
        }
        
        redirectStrategy.sendRedirect(request, response, targetUrl);
    }
 
    /** Builds the target URL according to the logic defined in the main class Javadoc. */
    protected String determineTargetUrl(Authentication authentication) {
        boolean isUser = false;
        boolean isAdmin = false;
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (GrantedAuthority grantedAuthority : authorities) {
            if (grantedAuthority.getAuthority().equals("ROLE_USER")) {
                isUser = true;
                logger.info("granted authority is ROLE_USER");
                break;
            } else if (grantedAuthority.getAuthority().equals("ROLE_ADMIN")) {
                isAdmin = true;
                logger.info("granted authority is ROLE_ADMIN");                
                break;
            }
        }
 
        if (isUser) {
            return "/dashboard.html";
        } else if (isAdmin) {
            return "/admin.html";
        } else {
            throw new IllegalStateException();
        }
    }
 
    protected final void clearAuthenticationAttributes(final HttpServletRequest request) {
        final HttpSession session = request.getSession(false);
        if (session == null) {
            return;
        }
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }
 
    public void setRedirectStrategy(final RedirectStrategy redirectStrategy) {
        this.redirectStrategy = redirectStrategy;
    }
    protected RedirectStrategy getRedirectStrategy() {
        return redirectStrategy;
    }
}