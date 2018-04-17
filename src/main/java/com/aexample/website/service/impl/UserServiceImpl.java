package com.aexample.website.service.impl;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.EntityNotFoundException;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
//import javax.transaction.Transactional;

import com.aexample.annotations.ILogger;
import com.aexample.persistence.model.UserAccount;
import com.aexample.persistence.model.UserPasswordResetToken;
import com.aexample.persistence.model.UserVerificationToken;
import com.aexample.persistence.repositories.IRoleRepository;
import com.aexample.persistence.repositories.IUserPasswordResetTokenRepository;
import com.aexample.persistence.repositories.IUserRepository;
import com.aexample.persistence.repositories.IUserVerificationTokenRepository;
import com.aexample.security.IEncryptionService;
import com.aexample.website.dto.UserDto;
import com.aexample.website.exception.RegistrationFailedException;
import com.aexample.website.exception.UserAlreadyExistsException;
import com.aexample.website.exception.UsernameNotFoundException;
import com.aexample.website.service.IUserService;

@Service
@Transactional
public class UserServiceImpl implements IUserService
{

//	Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	private static @ILogger Logger logger;	
	
	@Autowired
	private IUserRepository userRepository;
    private IRoleRepository roleRepository;    
    private IUserVerificationTokenRepository tokenRepository;
    private IUserPasswordResetTokenRepository passwordTokenRepository;

    private IEncryptionService encryptionService;

    @Autowired
    public void setIUserRepository(IUserRepository userRepository){
    	this.userRepository = userRepository;
    }
    
    @Autowired
    public void setIRoleRepository(IRoleRepository roleRepository){
    	this.roleRepository = roleRepository;
    }

    @Autowired
    public void setIUserVerificationTokenRepository(IUserVerificationTokenRepository tokenRepository){
    	this.tokenRepository = tokenRepository;
    }

    @Autowired
    public void setIUserPasswordResetTokenRepository(IUserPasswordResetTokenRepository passwordTokenRepository){
    	this.passwordTokenRepository = passwordTokenRepository;
    }

    @Autowired
    public void setEncryptionService(IEncryptionService encryptionService) {
        this.encryptionService = encryptionService;
    }
	public static final String TOKEN_INVALID = "invalidToken";
    public static final String TOKEN_EXPIRED = "expired";
    public static final String TOKEN_VALID = "valid";

    @Transactional
    @Override
    public UserAccount registerNewUserAccount(final UserDto accountDto){
        if (emailExist(accountDto.getEmail())) {
            throw new UserAlreadyExistsException("There is a current account with that email address: " + accountDto.getEmail());
        }
 
        final UserAccount user = new UserAccount();        
        //we use javascript on the front end to insure strong passwords are used
        //and we have matching passwords entered. However, we still need to do a programatic
        //check to ensure this on the server side.
        
        if(!accountDto.getPassword().equals(accountDto.getMatchingPassword())){
        	
        	//TODO notify system admins that we have a security attack here.
        	
        	throw new RegistrationFailedException("Entered passwords do not match");
        }
        
        if(accountDto.getPassword() != null){
            user.setEncryptedPassword(encryptionService.encryptString(accountDto.getPassword()));
        }        
                
        user.setFirstName(accountDto.getFirstName());
        user.setLastName(accountDto.getLastName());
        user.setEmail(accountDto.getEmail());
        user.setRoles(Arrays.asList(roleRepository.findByName("ROLE_USER")));
        user.setSecret("");
        user.setDeviceId("");
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setEnabled(false);  //must be activated by email response
        user.setCreateDate(new Date());
        
        return userRepository.save(user);
    }

    @Transactional
    @Override
    public void deleteUser(final UserAccount user) {
        final UserVerificationToken verificationToken = tokenRepository.findByUser(user);

        if (verificationToken != null) {
            tokenRepository.delete(verificationToken);
        }

        final UserPasswordResetToken passwordToken = passwordTokenRepository.findByUser(user);

        if (passwordToken != null) {
            passwordTokenRepository.delete(passwordToken);
        }

        userRepository.delete(user.getId());
    }

    @Transactional(readOnly = true)
    @Override
    public UserAccount findUserByEmail(final String email) throws UsernameNotFoundException{
    	//the email is the username in this system, so that is the exception that is thrown
    	//if we do not find the email in the database.
    	UserAccount userAcct = userRepository.findByEmail(email);
    	
    	if(userAcct == null){
    		throw new UsernameNotFoundException("Email not found");
    	} 
    	
        return userAcct;
    }
    
	   
    //mysql supports JDBC specification(primitives) and also Java Data Types see getBy Id(Long) below
    @Override
    public UserAccount getUserByIDLongPrimitive(final long id) throws EntityNotFoundException {
    	return userRepository.findOne(id);
    }    
    
	//Mysql provides Java Data Type support
	@Override
	public UserAccount getUserByIdLongObject(Long id) throws EntityNotFoundException {
		// TODO Auto-generated method stub
        return userRepository.getOne(id);
	}
	
    @Transactional
    @Override
    public void changeUserPassword(final UserAccount user, final String password) {
        user.setEncryptedPassword(encryptionService.encryptString(password));
        userRepository.updateEncryptedPassword(user.getEmail(), user.getEncryptedPassword());
    }
    
    @Override
    public boolean checkIfValidOldPassword(final UserAccount user, final String oldPassword) {
    	return encryptionService.checkPassword(oldPassword, user.getEncryptedPassword());
    }
    
	@Override
	public List<String> getUsersFromSessionRegistry() {
		// TODO getUsersFromSessionRegistry
		return null;
	}
	
  //  @Transactional
    @Override
    public UserAccount saveOrUpdate(UserAccount user) {
        return userRepository.save(user);
    } 

	
	@Override
	@Transactional
	public void deleteUserByLongObject(Long id) {
        userRepository.delete(id);
		
	}

	@Override
	@Transactional
	public void deleteUserByLongPrimitive(long id) {
        userRepository.delete(id);
		
	}	
	
	@Override
	public List<?> listAllUsers() {
        List<UserAccount> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add); //fun with Java 8
        return users;
	}

//UserVerificationToken
    
    @Transactional
    @Override
    public void createVerificationTokenForUser(final UserAccount user, final String token) {
        final UserVerificationToken myToken = new UserVerificationToken(token, user);
        myToken.setId(user.getId());
        tokenRepository.save(myToken);
    }
    
    @Transactional(readOnly = true)
    @Override
    public UserVerificationToken getVerificationToken(final String VerificationToken) {
        return tokenRepository.findByToken(VerificationToken);
    }
    
    @Transactional
    @Override
    public UserVerificationToken generateNewVerificationToken(final String existingVerificationToken) {
    	UserVerificationToken vToken = tokenRepository.findByToken(existingVerificationToken);
        vToken.updateToken(generateTokenValue());
        vToken.setPreviousToken(existingVerificationToken);
        vToken = tokenRepository.save(vToken);
        return vToken;
    }
    
    @Override
    public String validateVerificationToken(String token) {
        final UserVerificationToken verificationToken = tokenRepository.findByToken(token);
        if (verificationToken == null) {
            return TOKEN_INVALID;
        }

        UserAccount user = verificationToken.getUser();
        final Calendar cal = Calendar.getInstance();
        if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
            tokenRepository.delete(verificationToken);
            return TOKEN_EXPIRED;
        }

        user.setEnabled(true);
        // tokenRepository.delete(verificationToken);
 //       userRepository.save(user);
        return TOKEN_VALID;
    }
    
    @Transactional(readOnly = true)
    @Override
    public UserVerificationToken getPreviousToken(final String VerificationToken) {
        return tokenRepository.findByPreviousToken(VerificationToken);
    }
    
    @Transactional
    public UserVerificationToken findTokenByUser(UserAccount user){
    	final UserVerificationToken verificationToken = tokenRepository.findByUser(user);
    	return verificationToken;
    }
    
    @Transactional(readOnly = true)
    @Override
    public UserAccount getUserFromToken(final String userVerificationToken) {
        final UserVerificationToken token = tokenRepository.findByToken(userVerificationToken);
        if (token != null) {
            return token.getUser();
        }
        return null;
    }
    
    @Override
    public void deleteUserVerificationToken(UserVerificationToken userVerificationToken){
    	tokenRepository.delete(userVerificationToken);
    }
    

//PasswordResetTokens    
    
    @Transactional
    @Override
    public void createPasswordResetTokenForUser(final UserAccount user, final String token, final String newEncryptPassword) {
        final UserPasswordResetToken myToken = new UserPasswordResetToken(user, token, newEncryptPassword);
        passwordTokenRepository.save(myToken);
    }

	
    @Override
    public String validateResetPasswordToken(String token) {
        final UserPasswordResetToken verificationToken = passwordTokenRepository.findByToken(token);
        if (verificationToken == null) {
            return TOKEN_INVALID;
        }

        final UserAccount user = verificationToken.getUser();
        final Calendar cal = Calendar.getInstance();
        if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
        	passwordTokenRepository.delete(verificationToken);
            return TOKEN_EXPIRED;
        }

       // user.setEnabled(true);
        // tokenRepository.delete(verificationToken);
//        userRepository.save(user);
        return TOKEN_VALID;
    }

    @Transactional(readOnly = true)
    @Override
    public UserAccount getUserByPasswordResetToken(final String token) {
        return passwordTokenRepository.findByToken(token).getUser();
    }
    
    
    //Token related methods
    
    public String generateTokenValue(){
    	final String token = UUID.randomUUID().toString();
    	return token;
    }    
    
    //Utility

	@Override
	public String serviceInstantiated() {
		return "Created";
	}
    
    //Internal utility method

	private boolean emailExist(final String email) {
        return userRepository.findByEmail(email) != null;
    }

	@Override
	public UserPasswordResetToken getPasswordResetToken(String token) {
		return passwordTokenRepository.findByToken(token);
	}

	@Override
	public UserPasswordResetToken generateNewPasswordResetToken(final UserAccount user, final String token, final String newEncryptPassword) {
        final UserPasswordResetToken myToken = new UserPasswordResetToken(user, token, newEncryptPassword);
        return passwordTokenRepository.save(myToken);
	}

	@Override
	public UserPasswordResetToken findPasswordResetTokenByUser(UserAccount user) {
		return passwordTokenRepository.findByUser(user);
	}

	@Override
	public void deletePasswordResetToken(UserPasswordResetToken passwordResetToken) {
		passwordTokenRepository.delete(passwordResetToken);
		
	}    

}
