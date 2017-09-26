package com.aexample.security.service;

import java.util.Date;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.transaction.annotation.Transactional;

import com.aexample.persistence.model.UserAccount;
import com.aexample.persistence.model.UserLoginAttempts;
import com.aexample.persistence.repositories.IUserLoginAttemptsRepository;
import com.aexample.persistence.repositories.IUserRepository;
import com.aexample.security.IUserLoginAttemptsService;


/*
 * Classes calling these methods must implement @Transactional annotation
 * on the calling methods in those classes. Failure to do so results in a
 * null pointer exception.
 */
@Transactional
@PropertySource({"classpath:application.properties"})
@Service
public class UserLoginAttemptsServiceImpl implements IUserLoginAttemptsService{
	Logger logger = LoggerFactory.getLogger(UserLoginAttemptsServiceImpl.class);
//	@ILogger Logger logger;
		
	@Autowired
	Environment env;
		
	int MAX_ATTEMPTS = 0;

	@Autowired
	IUserLoginAttemptsRepository userLoginAttemptsRepository;
	
//	@Autowired
	IUserRepository userRepository;

	UserLoginAttempts userLoginAttempts = null;
	
    @Autowired
    public void setIUserRepository(IUserRepository userRepository){
    	this.userRepository = userRepository;
    }
    
    @Autowired
    public void setIUserLoginAttemptsRepository(IUserLoginAttemptsRepository userLoginAttemptsRepository){
       	this.userLoginAttemptsRepository = userLoginAttemptsRepository;    	
    }
        
	@PostConstruct
	public void init(){
		MAX_ATTEMPTS = Integer.parseInt(env.getRequiredProperty("website.max.login.attempts"));
		logger.debug("MAX_ATTEMPTS is set to: " + MAX_ATTEMPTS);
	}
	
//should only be called internally inside of a current transaction
//was marked private, but that caused a persistence error	
    public String addUserLoginAttempts(String email, Date date){
		
		UserLoginAttempts userLoginAttempts = new UserLoginAttempts();
		userLoginAttempts.setEmail(email);
		userLoginAttempts.setAttempts(1);
		userLoginAttempts.setLastModified(date);
		userLoginAttempts.setDateCreated(date);
		
		logger.debug("email is: " + email);
		logger.debug("date is: " + date.toString());
		
		try{
		//userLoginAttemptsRepository.save(userLoginAttempts);
		userLoginAttemptsRepository.saveAndFlush(userLoginAttempts);			
		} catch (UnexpectedRollbackException t){
			t.printStackTrace();
		}
		
		logger.debug("addUserLoginAttempts completed");
		return "addUserLoginAttempts completed";
	}

    @Override
	public String userUpdateFailedLoginAttempts(String email, Date date) {
		
		logger.debug("Checking if the User exists in failed logins table");
			if(theUserExists(email)){
				//if true, user object will be populated 
				//increments
				
				userLoginAttempts.setAttempts(userLoginAttempts.getAttempts() + 1);
				
				//userLoginAttemptsRepository.updateFailedLoginAttempts(email, date);
				//userLoginAttemptsRepository.updateFailAttempts(email, new Date());
			
				userLoginAttemptsRepository.saveAndFlush(userLoginAttempts);
				
				if(userLoginAttempts.getAttempts() >= MAX_ATTEMPTS){
					//lock the account
					
					UserAccount user = new UserAccount();
					
					user = userRepository.findByEmail(userLoginAttempts.getEmail());
					
					user.setAccountNonLocked(false);
					userRepository.saveAndFlush(user);
					
					//userRepository.lockUserAccount(email);
					//return "UserAccountLocked";
				}			
			}else{
				logger.debug("The user does not have failed logins--returned no record");
		
				//return "NoRecordFound";
					addUserLoginAttempts(email, date);
			}

			return "updateFailedLoginAttempts completed";
	}
    
    @Override
	public UserLoginAttempts findByEmail(String email) {

		try {

			UserLoginAttempts userAttempts = userLoginAttemptsRepository.findByEmail(email);
			userLoginAttempts = new UserLoginAttempts();
			userLoginAttempts.setId(userAttempts.getId());
			userLoginAttempts.setEmail(userAttempts.getEmail());
			userLoginAttempts.setAttempts(userAttempts.getAttempts());
			userLoginAttempts.setLastModified(userAttempts.getLastModified());
			userLoginAttempts.setDateCreated(userAttempts.getDateCreated());

			logger.debug("Id is: " + userLoginAttempts.getId());
			logger.debug("Email is: " + userLoginAttempts.getEmail());
			logger.debug("Attempts is: " + userLoginAttempts.getAttempts());
			logger.debug("LastModified is: " + userLoginAttempts.getLastModified().toString());
			
			return userLoginAttempts;
			

		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (NullPointerException npe){ //jpa doesnt throw EmptyResultDataAccessException
			logger.debug("Returning null from findByEmail method");
			return null;
		}
	}

    @Override
	public void clearFailedLoginAttempts(String email, Date date) {

    	userLoginAttempts = findByEmail(email);
		
		if(userLoginAttempts != null){
		// resets to zero
		//	see query in IUserLoginAttemptsRepository			
		//	userLoginAttemptsRepository.clearFailedLoginAttempts(email, new Date());
			userLoginAttemptsRepository.delete(userLoginAttempts);
		}
	}

	private boolean theUserExists( final String email) {
	   	userLoginAttempts = findByEmail(email);
	   	
        return userLoginAttempts != null;
    }	


	/* (non-Javadoc)
	 * @see com.aexample.persistence.repositories.IBaseRepository#delete(java.lang.Object)
	 */
    //@Transactional
	@Override
	public void delete(UserLoginAttempts deleted) {
		// TODO Auto-generated method stub
    	userLoginAttemptsRepository.delete(deleted);
	}

	public String serviceInstantiated() {
		
		// TODO Auto-generated method stub
		return "Created";
	}	


}
