/**
 * 
 */
package com.aexample.website.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aexample.persistence.model.AccountPasswordResetToken;
import com.aexample.persistence.model.Accounts;
import com.aexample.persistence.model.RegistrationVerificationToken;
import com.aexample.persistence.repositories.IAccountPasswordResetTokenRepository;
import com.aexample.persistence.repositories.IRegistrationRepository;
import com.aexample.persistence.repositories.IRegistrationVerificationTokenRepository;
import com.aexample.website.error.AccountAlreadyExistsException;
import com.aexample.website.service.IRegistrationService;
import com.aexample.website.viewBean.RegistrationBean;
/**
 * @author Robert B. Andrews
 * $Rev$
 * $Date$
 *
 */
@Service
@Transactional
public class RegistrationServiceImpl implements IRegistrationService {

	@Autowired
    private IRegistrationRepository repository;

    @Autowired
    private IRegistrationVerificationTokenRepository tokenRepository;

    @Autowired
    private IAccountPasswordResetTokenRepository passwordTokenRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private SessionRegistry sessionRegistry;

    public static final String TOKEN_INVALID = "invalidToken";
    public static final String TOKEN_EXPIRED = "expired";
    public static final String TOKEN_VALID = "valid";

    public static String QR_PREFIX = "https://chart.googleapis.com/chart?chs=200x200&chld=M%%7C0&cht=qr&chl=";
    public static String APP_NAME = "SpringRegistration";

    // API	
	
    @Override
    public Accounts registerNewAccount(final RegistrationBean registrationBean) {
        if (emailExist(registrationBean.getEmail())) {
            throw new AccountAlreadyExistsException("There is an account with that email adress: " + registrationBean.getEmail());
        }
		Accounts transferInfo = new Accounts();
		transferInfo.setOrgName(registrationBean.getOrgName());
		transferInfo.setFirstName(registrationBean.getFirstName());
		transferInfo.setLastName(registrationBean.getLastName());
		transferInfo.setEmail(registrationBean.getEmail());
		transferInfo.setLoginId(registrationBean.getLoginId());
		transferInfo.setProfileFlag(Boolean.FALSE);
		transferInfo.setPassword(registrationBean.getPassword());
		transferInfo.setPlan(registrationBean.getPlan());
		transferInfo.setAccountNonExpired(Boolean.TRUE);
		transferInfo.setAccountNonLocked(Boolean.TRUE);
		transferInfo.setEnabled(Boolean.TRUE);
		transferInfo.setRole("ROLE_Account");
		transferInfo.setCreateDate(new Date());
		transferInfo.setCredentialsNonExpired(Boolean.TRUE);
        return repository.save(transferInfo);
    }

    @Override
    public Accounts getAccount(final String verificationToken) {
        final RegistrationVerificationToken token = tokenRepository.findByToken(verificationToken);
        if (token != null) {
            return token.getRegistration();
        }
        return null;
    }

    @Override
    public RegistrationVerificationToken getVerificationToken(final String VerificationToken) {
        return tokenRepository.findByToken(VerificationToken);
    }

    @Override
    public void saveRegisteredAccount(final Accounts account) {
        repository.save(account);
    }

 /*
 * We do not want to ever delete an account, just make it inactive.
 * Its possible that someone may come back and ask to reactivate it
 * plus we might need a history for legal reasons
 *  
    public void deleteAccount(final Accounts account) {
        final VerificationToken verificationToken = tokenRepository.findByAccount(account);

        if (verificationToken != null) {
            tokenRepository.delete(verificationToken);
        }

        final PasswordResetToken passwordToken = passwordTokenRepository.findByAccount(account);

        if (passwordToken != null) {
            passwordTokenRepository.delete(passwordToken);
        }

        repository.delete(account);
    }
*/

    @Override
    public void createVerificationTokenForAccount(final Accounts account, final String token) {
        final RegistrationVerificationToken myToken = new RegistrationVerificationToken(token, account);
        tokenRepository.save(myToken);
    }

    @Override
    public RegistrationVerificationToken generateNewVerificationToken(final String existingVerificationToken) {
    	RegistrationVerificationToken vToken = tokenRepository.findByToken(existingVerificationToken);
        vToken.updateToken(UUID.randomUUID().toString());
        vToken = tokenRepository.save(vToken);
        return vToken;
    }

    @Override
    public void createPasswordResetTokenForAccount(final Accounts account, final String token) {
        final AccountPasswordResetToken myToken = new AccountPasswordResetToken(token, account);
        passwordTokenRepository.save(myToken);
    }

    @Override
    public Accounts findAccountByEmail(final String email) {
        return repository.findByEmail(email);
    }

    @Override
    public AccountPasswordResetToken getPasswordResetToken(final String token) {
        return passwordTokenRepository.findByToken(token);
    }

    @Override
    public Accounts getAccountByPasswordResetToken(final String token) {
        return passwordTokenRepository.findByToken(token).getAccount();
    }

    @Override
    public Accounts getAccountByID(final long id) {
        return repository.findOne(id);
    }

    @Override
    public void changeAccountPassword(final Accounts account, final String password) {
        account.setPassword(passwordEncoder.encode(password));
        repository.save(account);    	
    }

    @Override
    public boolean checkIfValidOldPassword(final Accounts account, final String oldPassword) {
        return passwordEncoder.matches(oldPassword, account.getPassword());
    }

    @Override
    public String validateVerificationToken(String token) {
        final RegistrationVerificationToken registrationVerificationToken = tokenRepository.findByToken(token);
        if (registrationVerificationToken == null) {
            return TOKEN_INVALID;
        }

        final Accounts account = registrationVerificationToken.getRegistration();
        final Calendar cal = Calendar.getInstance();
        if ((registrationVerificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
            tokenRepository.delete(registrationVerificationToken);
            return TOKEN_EXPIRED;
        }

        account.setEnabled(true);
        // tokenRepository.delete(verificationToken);
        repository.save(account);
        return TOKEN_VALID;
    }

    @Override
    public String generateQRUrl(Accounts account) throws UnsupportedEncodingException {
        return QR_PREFIX + URLEncoder.encode(String.format("otpauth://totp/%s:%s?secret=%s&issuer=%s", APP_NAME, account.getEmail(), account.getSecret(), APP_NAME), "UTF-8");
    }

    @Override
    public Accounts updateAccount2FA(boolean use2FA) {
        final Authentication curAuth = SecurityContextHolder.getContext().getAuthentication();
        Accounts currentAccount= (Accounts) curAuth.getPrincipal();
        currentAccount.setUsing2FA(use2FA);
        currentAccount = repository.save(currentAccount);
        final Authentication auth = new UsernamePasswordAuthenticationToken(currentAccount, currentAccount.getPassword(), curAuth.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);
        return currentAccount;
    }

    private boolean emailExist(final String email) {
        return repository.findByEmail(email) != null;
    }

    @Override
    public List<String> getAccountFromSessionRegistry() {
        return sessionRegistry.getAllPrincipals().stream().filter((u) -> !sessionRegistry.getAllSessions(u, false).isEmpty()).map(Object::toString).collect(Collectors.toList());
    }
    
	public String serviceInstantiated(){
		return "Created";
		
	}
    
    
    
    
    
    /////////////////////////////////////////old stuff here
	
	
	
	
	
	
/*	
	
	
	
	
	
	
	
	
	
	

	
	@Autowired
	IRegistrationDao regDao;
	
	public Accounts isNewAccount() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void manualIntervention() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public Accounts findOne(long id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Accounts> findAll() {
		// TODO Auto-generated method stub
		return null;
	}



//	@Override
	public String create(RegistrationBean regEntity) {
		// TODO Auto-generated method stub
		Accounts entity = buildAccountEntity(regEntity);
//		regDao.create(entity);
		return null;
	}


	@Override
	public Accounts update(Accounts entity) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void delete(Accounts entity) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void deleteById(long entityId) {
		// TODO Auto-generated method stub
		
	}
	
	public String serviceInstantiated(){
		return "Created";
		
	}

	private Accounts buildAccountEntity(RegistrationBean registrationBean){
		Accounts transferInfo = new Accounts();
		transferInfo.setOrgName(registrationBean.getOrgName());
		transferInfo.setFirstName(registrationBean.getFirstName());
		transferInfo.setLastName(registrationBean.getLastName());
		transferInfo.setEmail(registrationBean.getEmail());
		transferInfo.setLoginId(registrationBean.getLoginId());
		transferInfo.setProfileFlag(Boolean.FALSE);
		transferInfo.setPassword(registrationBean.getPassword());
		transferInfo.setPlan(registrationBean.getPlan());
		transferInfo.setAccountNonExpired(Boolean.TRUE);
		transferInfo.setAccountNonLocked(Boolean.TRUE);
		transferInfo.setEnabled(Boolean.TRUE);
		transferInfo.setRole("ROLE_Account");
		transferInfo.setCreateDate(new Date());
		transferInfo.setCredentialsNonExpired(Boolean.TRUE);
		
		return transferInfo;
	}


	@Override
	public Accounts registerNewAccount(RegistrationBean regEntity) throws AccountAlreadyExistException {
	     if (emailExist(regEntity.getEmail())) {
	            throw new AccountAlreadyExistException("There is an account with that email adress: " + regEntity.getEmail());
	        }
		Accounts entity = buildAccountEntity(regEntity);
//		regDao.create(entity);
		repository.save(entity);
		return null;
	}


	@Override
	public Accounts getAccount(String verificationToken) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void createVerificationTokenForAccount(Accounts account, String token) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public VerificationToken getVerificationToken(String VerificationToken) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public VerificationToken generateNewVerificationToken(String token) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void createPasswordResetTokenForAccount(Accounts account, String token) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public Accounts findAccountByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public PasswordResetToken getPasswordResetToken(String token) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Accounts getAccountByPasswordResetToken(String token) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Accounts getAccountByID(long id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void changeAccountPassword(Accounts account, String password) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public boolean checkIfValidOldPassword(Accounts account, String password) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public String validateVerificationToken(String token) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public String generateQRUrl(Accounts account) throws UnsupportedEncodingException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Accounts updateAccount2FA(boolean use2fa) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<String> getAccountFromSessionRegistry() {
		// TODO Auto-generated method stub
		return null;
	}
    private boolean emailExist(final String email) {
        return repository.findByEmail(email) != null;
    }
*/
}
