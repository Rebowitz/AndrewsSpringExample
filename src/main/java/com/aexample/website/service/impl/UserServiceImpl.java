package com.aexample.website.service.impl;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private PasswordEncoder passwordEncoder;
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

    // API
    @Transactional
    @Override
    public UserAccount registerNewUserAccount(final UserDto accountDto){
        if (emailExist(accountDto.getEmail())) {
            throw new UserAlreadyExistsException("There is a current account with that email address: " + accountDto.getEmail());
        }
 
        final UserAccount user = new UserAccount();        
        
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

    @Transactional(readOnly = true)
    @Override
    public UserAccount getUser(final String userVerificationToken) {
        final UserVerificationToken token = tokenRepository.findByToken(userVerificationToken);
        if (token != null) {
            return token.getUser();
        }
        return null;
    }

    @Transactional(readOnly = true)
    @Override
    public UserVerificationToken getVerificationToken(final String VerificationToken) {
        return tokenRepository.findByToken(VerificationToken);
    }
    
    @Transactional(readOnly = true)
    @Override
    public UserVerificationToken getPreviousToken(final String VerificationToken) {
        return tokenRepository.findByPreviousToken(VerificationToken);
    }    
    
    @Transactional
    public UserAccount saveOrUpdate(UserAccount domainObject) {
        return userRepository.save(domainObject);
    }
    
    @Transactional
    public UserVerificationToken findTokenByUser(UserAccount user){
    	final UserVerificationToken verificationToken = tokenRepository.findByUser(user);
    	return verificationToken;
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
    
    public String generateTokenValue(){
    	final String token = UUID.randomUUID().toString();
    	return token;
    }

    @Transactional
    @Override
    public void createVerificationTokenForUser(final UserAccount user, final String token) {
        final UserVerificationToken myToken = new UserVerificationToken(token, user);
        tokenRepository.save(myToken);
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
    
    @Transactional
    @Override
    public void createPasswordResetTokenForUser(final UserAccount user, final String token) {
        final UserPasswordResetToken myToken = new UserPasswordResetToken(token, user);
        passwordTokenRepository.save(myToken);
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

    @Transactional(readOnly = true)
    @Override
    public UserPasswordResetToken getPasswordResetToken(final String token) {
        return passwordTokenRepository.findByToken(token);
    }

    @Transactional(readOnly = true)
    @Override
    public UserAccount getUserByPasswordResetToken(final String token) {
        return passwordTokenRepository.findByToken(token).getUser();
    }

    @Transactional(readOnly = true)
    @Override
    public UserAccount getUserByID(final long id) {
        return userRepository.findOne(id);
    }

    @Transactional
    @Override
    public void changeUserPassword(final UserAccount user, final String password) {
        user.setEncryptedPassword(encryptionService.encryptString(password));
        userRepository.save(user);
    }

    @Override
    public boolean checkIfValidOldPassword(final UserAccount user, final String oldPassword) {
        return passwordEncoder.matches(oldPassword, user.getEncryptedPassword());
    }

    
    @Override
    public String validateVerificationToken(String token) {
        final UserVerificationToken verificationToken = tokenRepository.findByToken(token);
        if (verificationToken == null) {
            return TOKEN_INVALID;
        }

        final UserAccount user = verificationToken.getUser();
        final Calendar cal = Calendar.getInstance();
        if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
            tokenRepository.delete(verificationToken);
            return TOKEN_EXPIRED;
        }

       // user.setEnabled(true);
        // tokenRepository.delete(verificationToken);
        userRepository.save(user);
        return TOKEN_VALID;
    }

    /* (non-Javadoc)
	 * @see com.aexample.website.service.IUserService#serviceInstantiated()
	 */
	@Override
	public String serviceInstantiated() {
		
		// TODO Auto-generated method stub
		return "Created";
	}

	private boolean emailExist(final String email) {
        return userRepository.findByEmail(email) != null;
    }

	/* (non-Javadoc)
	 * @see com.aexample.website.service.IUserService#getUsersFromSessionRegistry()
	 */
	@Override
	public List<String> getUsersFromSessionRegistry() {
		// TODO Auto-generated method stub
		return null;
	}


	/* (non-Javadoc)
	 * @see com.aexample.website.service.IJPAService#listAll()
	 */
	@Override
	public List<?> listAll() {
        List<UserAccount> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add); //fun with Java 8
        return users;
	}

	/* (non-Javadoc)
	 * @see com.aexample.website.service.IJPAService#getById(java.lang.Integer)
	 */
	@Override
	public UserAccount getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public void delete(Long id) {
        userRepository.delete(id);
		
	}

	/* (non-Javadoc)
	 * @see com.aexample.website.service.IJPAService#delete(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.aexample.website.service.IUserService#saveRegisteredUser(com.aexample.persistence.model.UserAccount)
	 */
	@Override
	public void saveRegisteredUser(UserAccount user) {
		// TODO Auto-generated method stub
		
	}
}
