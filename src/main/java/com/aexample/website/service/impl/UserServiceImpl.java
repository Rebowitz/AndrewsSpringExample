package com.aexample.website.service.impl;



import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.transaction.annotation.Transactional;
//import javax.transaction.Transactional;

import com.aexample.persistence.model.UserPasswordResetToken;
import com.aexample.persistence.model.User;
import com.aexample.persistence.model.UserVerificationToken;
import com.aexample.persistence.repositories.IUserPasswordResetTokenRepository;
import com.aexample.persistence.repositories.IRoleRepository;
import com.aexample.persistence.repositories.IUserRepository;
import com.aexample.persistence.repositories.IUserVerificationTokenRepository;
import com.aexample.website.dto.UserDto;
import com.aexample.website.error.UserAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import com.aexample.persistence.dao.IUserDao;
import com.aexample.persistence.model.Accounts;
import com.aexample.website.service.IUserService;



/* old imports holding onto for now

import java.sql.SQLException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aexample.persistence.dao.IUserDao;
import com.aexample.persistence.model.Accounts;
import com.aexample.website.service.IUserService;

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
*/


@Service
@Transactional
public class UserServiceImpl implements IUserService
{


    @Autowired
    private IUserRepository repository;

    @Autowired
    private IUserVerificationTokenRepository tokenRepository;

    @Autowired
    private IUserPasswordResetTokenRepository passwordTokenRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private IRoleRepository roleRepository;

    @Autowired
    private SessionRegistry sessionRegistry;

    public static final String TOKEN_INVALID = "invalidToken";
    public static final String TOKEN_EXPIRED = "expired";
    public static final String TOKEN_VALID = "valid";

    public static String QR_PREFIX = "https://chart.googleapis.com/chart?chs=200x200&chld=M%%7C0&cht=qr&chl=";
    public static String APP_NAME = "SpringRegistration";

    // API

    @Override
    public User registerNewUserAccount(final UserDto accountDto) {
        if (emailExist(accountDto.getEmail())) {
            throw new UserAlreadyExistsException("There is an account with that email adress: " + accountDto.getEmail());
        }
        final User user = new User();

        user.setFirstName(accountDto.getFirstName());
        user.setLastName(accountDto.getLastName());
        user.setPassword(passwordEncoder.encode(accountDto.getPassword()));
        user.setEmail(accountDto.getEmail());
        user.setUsing2FA(accountDto.isUsing2FA());
        user.setRoles(Arrays.asList(roleRepository.findByName("ROLE_USER")));
        return repository.save(user);
    }

    @Override
    public User getUser(final String verificationToken) {
        final UserVerificationToken token = tokenRepository.findByToken(verificationToken);
        if (token != null) {
            return token.getUser();
        }
        return null;
    }

    @Override
    public UserVerificationToken getVerificationToken(final String VerificationToken) {
        return tokenRepository.findByToken(VerificationToken);
    }

    @Override
    public void saveRegisteredUser(final User user) {
        repository.save(user);
    }

    @Override
    public void deleteUser(final User user) {
        final UserVerificationToken userVerificationToken = tokenRepository.findByUser(user);

        if (userVerificationToken != null) {
            tokenRepository.delete(userVerificationToken);
        }

        final UserPasswordResetToken passwordToken = passwordTokenRepository.findByUser(user);

        if (passwordToken != null) {
            passwordTokenRepository.delete(passwordToken);
        }

        repository.delete(user);
    }

    @Override
    public void createVerificationTokenForUser(final User user, final String token) {
        final UserVerificationToken myToken = new UserVerificationToken(token, user);
        tokenRepository.save(myToken);
    }

    @Override
    public UserVerificationToken generateNewVerificationToken(final String existingVerificationToken) {
        UserVerificationToken vToken = tokenRepository.findByToken(existingVerificationToken);
        vToken.updateToken(UUID.randomUUID().toString());
        vToken = tokenRepository.save(vToken);
        return vToken;
    }

    @Override
    public void createPasswordResetTokenForUser(final User user, final String token) {
        final UserPasswordResetToken myToken = new UserPasswordResetToken(token, user);
        passwordTokenRepository.save(myToken);
    }

    @Override
    public User findUserByEmail(final String email) {
        return repository.findByEmail(email);
    }

    @Override
    public UserPasswordResetToken getPasswordResetToken(final String token) {
        return passwordTokenRepository.findByToken(token);
    }

    @Override
    public User getUserByPasswordResetToken(final String token) {
        return passwordTokenRepository.findByToken(token).getUser();
    }

    @Override
    public User getUserByID(final long id) {
        return repository.findOne(id);
    }

    @Override
    public void changeUserPassword(final User user, final String password) {
        user.setPassword(passwordEncoder.encode(password));
        repository.save(user);
    }

    @Override
    public boolean checkIfValidOldPassword(final User user, final String oldPassword) {
        return passwordEncoder.matches(oldPassword, user.getPassword());
    }

    @Override
    public String validateVerificationToken(String token) {
        final UserVerificationToken userVerificationToken = tokenRepository.findByToken(token);
        if (userVerificationToken == null) {
            return TOKEN_INVALID;
        }

        final User user = userVerificationToken.getUser();
        final Calendar cal = Calendar.getInstance();
        if ((userVerificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
            tokenRepository.delete(userVerificationToken);
            return TOKEN_EXPIRED;
        }

        user.setEnabled(true);
        // tokenRepository.delete(verificationToken);
        repository.save(user);
        return TOKEN_VALID;
    }

    @Override
    public String generateQRUrl(User user) throws UnsupportedEncodingException {
        return QR_PREFIX + URLEncoder.encode(String.format("otpauth://totp/%s:%s?secret=%s&issuer=%s", APP_NAME, user.getEmail(), user.getSecret(), APP_NAME), "UTF-8");
    }

    @Override
    public User updateUser2FA(boolean use2FA) {
        final Authentication curAuth = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) curAuth.getPrincipal();
        currentUser.setUsing2FA(use2FA);
        currentUser = repository.save(currentUser);
        final Authentication auth = new UsernamePasswordAuthenticationToken(currentUser, currentUser.getPassword(), curAuth.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);
        return currentUser;
    }

    private boolean emailExist(final String email) {
        return repository.findByEmail(email) != null;
    }

    @Override
    public List<String> getUsersFromSessionRegistry() {
        return sessionRegistry.getAllPrincipals().stream().filter((u) -> !sessionRegistry.getAllSessions(u, false).isEmpty()).map(Object::toString).collect(Collectors.toList());
    }
	
	
	public String serviceInstantiated(){
		return "Created";
		
	}	
	
	
	
	
	
	
/* old user code hanging onto for now	
	
	
	
	private IUserDao iUserDao;

		public IUserDao getUserDao()
		{
				return this.iUserDao;
		}

		public void setUserDao(IUserDao iUserDao)
		{
				this.iUserDao = iUserDao;
		}

		public Accounts isValidUser(String username, String password) throws SQLException
		{
				return iUserDao.isValidUser(username, password);
		}

		@Override
		public Accounts findOne(Long id) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Long create(Accounts resource) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Long update(Accounts resource) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Long getById(Long id) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Long deleteById(Long id) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Accounts getByLoginId(String loginId) {
			// TODO Auto-generated method stub
			return null;
		}

		
		public String serviceInstantiated(){
			return "Created";
			
		}
*/
}
