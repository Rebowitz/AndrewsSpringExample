/**
 * 
 */
package com.aexample.security;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

import com.aexample.event.OnRegistrationCompleteEvent;
import com.aexample.event.OnRegistrationNotConfirmedEvent;
import com.aexample.persistence.repositories.IUserLoginAttemptsRepository;
import com.aexample.persistence.repositories.IUserVerificationTokenRepository;
import com.aexample.security.service.CustomSpringSecurityUserDetailsService;
import com.aexample.security.service.UserLoginAttemptsServiceImpl;
import com.aexample.website.exception.RegistrationNotCompletedException;

/**
 * @author Main Login
 * $Rev$
 * $Date$
 *
 */
//@Component
public class LimitLoginAttemptsAuthenticationProvider extends DaoAuthenticationProvider{
	Logger logger = LoggerFactory.getLogger(LimitLoginAttemptsAuthenticationProvider.class);
//	@ILogger Logger logger;
	
	@Autowired
	IUserLoginAttemptsRepository userLoginAttemptsRepository;
	
	@Autowired
	IUserVerificationTokenRepository userVerificationTokenRepository;
	
	@Autowired
	UserLoginAttemptsServiceImpl userLoginAttemptsServiceImpl;
	
    @Autowired
    private ApplicationEventPublisher eventPublisher;	
	
	CustomSpringSecurityUserDetailsService customSpringSecurityUserDetailsService;
    
	private IEncryptionService encryptionService;
    
	@Autowired
    public void setEncryptionService(IEncryptionService encryptionService) {
        this.encryptionService = encryptionService;
    }
	
	//private UserDetailsService userDetailsService;
	
	String outcome = null;
	
//	@Autowired
//	@Qualifier("userDetailsService")
	public void setUserDetailsService(CustomSpringSecurityUserDetailsService customSpringSecurityUserDetailsService) {
//		customSpringSecurityUserDetailsService = userDetailsService;
		super.setUserDetailsService(customSpringSecurityUserDetailsService);
	}
	
//	@Transactional
	@Override
	public Authentication authenticate(Authentication authentication)
          throws AuthenticationException {
		
		String username = authentication.getName();
		String password = (String) authentication.getCredentials();
		
		logger.debug("Using userLoginAttemptsRepository reset fail attempts");
		logger.debug("Email address: " + authentication.getName());
		logger.debug("Password: " + authentication.getCredentials());
		
		//need to encode password for comparison attempt
		
		
		UserDetails theUser = super.getUserDetailsService().loadUserByUsername(username);
		//UserDetails theUser = customSpringSecurityUserDetailsService.loadUserByUsername(username);  //this is an email address
		
			  
			  boolean passwordsEqual = encryptionService.checkPassword(password, theUser.getPassword());
		
			  //get number of login failures for test 
			  
			  
		  if(!passwordsEqual && theUser.isAccountNonLocked()){
			  logger.debug("Authentication failed-throwing bad credentials exception");
			  userLoginAttemptsServiceImpl.userUpdateFailedLoginAttempts(authentication.getName(), new Date());			  
			  throw new BadCredentialsException("Bad Credentials");
		  }		
	
		  
		  if(!theUser.isAccountNonLocked()){
			  logger.debug("Account is locked- throwing LockedException");
			  throw new LockedException("User account is locked!");
		  }
		  
  
		  if(!theUser.isEnabled()){
			  
			  logger.debug("Account is not enabled - throwing NotConfirmedException");
			  throw new DisabledException(theUser.getUsername());
		  }

		  userLoginAttemptsServiceImpl.clearFailedLoginAttempts(authentication.getName(), new Date());
		  
		  return new UsernamePasswordAuthenticationToken(theUser, null, theUser.getAuthorities()) ;  
	
	}
	
}
