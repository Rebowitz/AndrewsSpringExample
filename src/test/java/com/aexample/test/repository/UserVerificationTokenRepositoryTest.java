/**
 * 
 */
package com.aexample.test.repository;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.aexample.persistence.model.Roles;
import com.aexample.persistence.model.UserAccount;
import com.aexample.persistence.model.UserVerificationToken;
import com.aexample.persistence.repositories.IRoleRepository;
import com.aexample.persistence.repositories.IUserRepository;
import com.aexample.persistence.repositories.IUserVerificationTokenRepository;
import com.aexample.test.BaseTest;
import com.aexample.test.config.PersistenceJPAConfigTest;
import com.aexample.test.config.RepositoriesConfigurationTest;

/**
 * @author Robert B. Andrews
 * $Rev$
 * $Date$
 *
 */
@RunWith(SpringRunner.class)
//@DataJpaTest
@ContextConfiguration(classes={RepositoriesConfigurationTest.class,PersistenceJPAConfigTest.class})
public class UserVerificationTokenRepositoryTest extends BaseTest{

	
	@Autowired
	IUserVerificationTokenRepository userVerificationTokenRepository;
	
	@Autowired
	IRoleRepository roleRepository;
	
	@Autowired
	IUserRepository userRepository;
	
	
	UserVerificationToken uvt = new UserVerificationToken();
	UserVerificationToken ruvt = new UserVerificationToken();	
	UserAccount ua = new UserAccount();
	Roles adminRole = new Roles();
	
	

	/**
	 * 
	 */
	public UserVerificationTokenRepositoryTest() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Test
	@Transactional
	public void findByToken(){
		
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy'T'HH:mm:ss");
		Date dateTime = null;
		try {
			dateTime = formatter.parse("31-01-2018T18:25:43");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		setUserAccount();
		uvt.setId(ua.getId());
		uvt.setExpiryDate(dateTime);
		uvt.setPreviousToken("previousToken");
		uvt.setToken("token");
		uvt.setUser(ua);
		
		userVerificationTokenRepository.save(uvt);
		
		ruvt = userVerificationTokenRepository.findByToken(uvt.getToken());
		assertThat(ruvt.getToken().equals(uvt.getToken()));
		assertThat(ruvt.getPreviousToken().equals(uvt.getPreviousToken()));
		assertThat(ruvt.getUser().equals(uvt.getUser()));
		assertThat(ruvt.getExpiryDate().compareTo(uvt.getExpiryDate()));
			
	}
	
	@Test
	@Transactional
	public void findByUser(){
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy'T'HH:mm:ss");
		Date dateTime = null;
		try {
			dateTime = formatter.parse("31-01-2018T18:25:43");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		setUserAccount();
		
		uvt.setId(ua.getId());
		uvt.setExpiryDate(dateTime);
		uvt.setPreviousToken("previousToken");
		uvt.setToken("token");
		uvt.setUser(ua);
		
		userVerificationTokenRepository.save(uvt);
		
		ruvt = userVerificationTokenRepository.findByUser(ua);
		assertThat(ruvt.getToken().equals(uvt.getToken()));
		assertThat(ruvt.getPreviousToken().equals(uvt.getPreviousToken()));
		assertThat(ruvt.getUser().equals(uvt.getUser()));
		assertThat(ruvt.getExpiryDate().compareTo(uvt.getExpiryDate()));		
	}
	
	@Test
	@Transactional
	public void findByPreviousToken(){
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy'T'HH:mm:ss");
		Date dateTime = null;
		try {
			dateTime = formatter.parse("31-01-2018T18:25:43");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		setUserAccount();
		uvt.setId(ua.getId());
		uvt.setExpiryDate(dateTime);
		uvt.setPreviousToken("previousToken");
		uvt.setToken("token");
		uvt.setUser(ua);
		
		userVerificationTokenRepository.save(uvt);
		
		ruvt = userVerificationTokenRepository.findByPreviousToken(uvt.getPreviousToken());
		assertThat(ruvt.getToken().equals(uvt.getToken()));
		assertThat(ruvt.getPreviousToken().equals(uvt.getPreviousToken()));
		assertThat(ruvt.getUser().equals(uvt.getUser()));
		assertThat(ruvt.getExpiryDate().compareTo(uvt.getExpiryDate()));
	}
	
	@Test
	@Transactional
	public void deleteAllExpiredSinceDate(){
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy'T'HH:mm:ss");
		Date dateTime = null;
		try {
			dateTime = formatter.parse("31-01-2018T18:25:43");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		setUserAccount();
		
		uvt.setId(ua.getId());
		uvt.setExpiryDate(dateTime);
		uvt.setPreviousToken("previousToken");
		uvt.setToken("token");
		uvt.setUser(ua);
		
		userVerificationTokenRepository.save(uvt);
		
		try {
			dateTime = formatter.parse("01-02-2018T18:25:43");  //one day later
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		userVerificationTokenRepository.deleteAllExpiredSince(dateTime);

		ruvt = userVerificationTokenRepository.findByToken(uvt.getToken());
		assertNull(ruvt);
	
		
	}
	
	
    private void setUserAccount(){
    	
	    adminRole = roleRepository.findByName("ROLE_ADMIN");
	    	    
	    Date dateTime = new Date();
		
	    ua.setFirstName("Initializer");
	    ua.setLastName("Account");
	    ua.setEmail("initializer@aexample.com");        
		String password = "UsuallyBcryptEncoded";
		ua.setEncryptedPassword(password);
		ua.setSecret("secret");
		ua.setDeviceId("androiddevice");
		ua.setRoles(Arrays.asList(adminRole));
		ua.setEnabled(true);  
		ua.setCreateDate(dateTime);
		ua.setAccountNonExpired(true);
		ua.setAccountNonLocked(true);
		ua.setCredentialsNonExpired(true);
		ua.setEnabled(true);
		
		userRepository.save(ua);
		
		//we need to retrieve the saved object to get the id assigned via autoincrement
		String emailAddy = ua.getEmail();
		ua = userRepository.findByEmail(emailAddy);
    }
}
