/**
 * 
 */
package com.aexample.test.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.aexample.persistence.model.Roles;
import com.aexample.persistence.model.UserAccount;
import com.aexample.persistence.repositories.IRoleRepository;
import com.aexample.persistence.repositories.IUserRepository;
import com.aexample.test.BaseTest;
import com.aexample.test.config.AexampleTestConfiguration;
import com.aexample.test.config.PersistenceJPAConfigTest;


/**
 * @author Robert B. Andrews
 * $Rev$
 * $Date$
 *
 */
@RunWith(SpringRunner.class)
//@DataJpaTest
@ContextConfiguration(classes={AexampleTestConfiguration.class,PersistenceJPAConfigTest.class})
public class UserRepositoryTest extends BaseTest{

	@Autowired
	IUserRepository userRepository;
	
	@Autowired
	IRoleRepository roleRepository;
	
	UserAccount ua = new UserAccount();
	UserAccount ua2 = new UserAccount();
	UserAccount returnedUA = new UserAccount();
	Roles adminRole = new Roles();
//	AexampleTestingObjects ato = new AexampleTestingObjects();
	
	String email = null;
	
	String encryptedPassword = "gobbedlygookstring";
	
	//this test only checks the custom queries written on top of the repository class
	
	/**
	 * 
	 */
	public UserRepositoryTest() {
		super();
//		prepDbaseForTest();
		// TODO Auto-generated constructor stub
	}

	@Test
	@Transactional
	public void findByEmail(){
		setUserAccount();
		userRepository.save(ua);
		
		returnedUA = userRepository.findByEmail(ua.getEmail());
		System.out.println(returnedUA.getEmail());
		assertThat(returnedUA.getEmail()).isEqualTo(ua.getEmail());
	}
	
	/*
	 * When creating an integration test on an update statement, it may be necessary 
	 * to clear the entity manager so as to reload the first level cache. Indeed, 
	 * an update statement completely bypasses the first level cache and writes 
	 * directly to the database. The first level cache is then out of sync and 
	 * reflects the old value of the updated object. To avoid this stale state 
	 * of the object, use an explicit clear in the test body. 
	 */
	
	@Test
	@Transactional
	public void lockUserAccount(){
		setUserAccount();		
		userRepository.save(ua);
		userRepository.lockUserAccount(ua.getEmail());		
		returnedUA = userRepository.findByEmail(ua.getEmail());
		System.out.println(returnedUA.getAccountNonLocked());		
		assertThat(returnedUA.getAccountNonLocked(),is(false));
	}

	@Test
	@Transactional	
	public void expireUserAccount(){
		setUserAccount();		
		userRepository.save(ua);
		userRepository.expireUserAccount(ua.getEmail());
		returnedUA = userRepository.findByEmail(ua.getEmail());
		assertThat(returnedUA.getAccountNonExpired(),is(false));
	}

	
	@Test
	@Transactional	
	public void expireUserCredentials(){
		setUserAccount();		
		userRepository.save(ua);	
		userRepository.expireUserCredentials(ua.getEmail());
		returnedUA = userRepository.findByEmail(ua.getEmail());
		assertThat(returnedUA.getCredentialsNonExpired(),is(false));
	}
	
	@Test
	@Transactional	
	public void disableUserAccount(){
		setUserAccount();		
		userRepository.save(ua);	
		userRepository.disableUserAccount(ua.getEmail());
		returnedUA = userRepository.findByEmail(ua.getEmail());
		assertThat(returnedUA.getEnabled(),is(false));
	}	
	

/* REACTIVATE THINGS */	
	@Test
	@Transactional
	public void unlockUserAccount(){
		setUserAccountTwo();		
		userRepository.save(ua2);	
		userRepository.unlockUserAccount(ua2.getEmail());		
		returnedUA = userRepository.findByEmail(ua2.getEmail());
		System.out.println(returnedUA.getAccountNonLocked());		
		assertThat(returnedUA.getAccountNonLocked(),is(true));
	}


	@Test
	@Transactional	
	public void reactivateUserAccount(){
		setUserAccountTwo();		
		userRepository.save(ua2);		
		userRepository.reactivateUserAccount(ua2.getEmail());
		returnedUA = userRepository.findByEmail(ua2.getEmail());
		assertThat(returnedUA.getAccountNonExpired(),is(true));
		
	}
	
	@Test
	@Transactional	
	public void reactivateUserCredentials(){
		setUserAccountTwo();		
		userRepository.save(ua2);			
		userRepository.reactivateUserCredentials(ua2.getEmail());
		returnedUA = userRepository.findByEmail(ua2.getEmail());
		assertThat(returnedUA.getCredentialsNonExpired(),is(true));
	}
	
	@Test
	@Transactional	
	public void enableUserAccount(){
		setUserAccountTwo();		
		userRepository.save(ua2);			
		userRepository.enableUserAccount(ua2.getEmail());
		returnedUA = userRepository.findByEmail(ua2.getEmail());
		assertThat(returnedUA.getEnabled(),is(true));
	}	

	
	@Test
	@Transactional	
	public void updateEncryptedPassword(){
		setUserAccountTwo();		
		userRepository.save(ua2);	
		userRepository.updateEncryptedPassword(ua2.getEmail(), encryptedPassword);
		returnedUA = userRepository.findByEmail(ua2.getEmail());
		assertThat(returnedUA.getEncryptedPassword().equals(encryptedPassword));
		
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
    }
    
    private void setUserAccountTwo(){
    	
	    adminRole = roleRepository.findByName("ROLE_ADMIN");
	    	    
	    Date dateTime = new Date();
		
	    ua2.setFirstName("Initializer");
	    ua2.setLastName("Account");
	    ua2.setEmail("initializer@aexample.com");        
		String password = "UsuallyBcryptEncoded";
		ua2.setEncryptedPassword(password);
		ua2.setSecret("secret");
		ua2.setDeviceId("androiddevice");
		ua2.setRoles(Arrays.asList(adminRole));
		ua2.setEnabled(false);  
		ua2.setCreateDate(dateTime);
		ua2.setAccountNonExpired(false);
		ua2.setAccountNonLocked(false);
		ua2.setCredentialsNonExpired(false);
		ua2.setEnabled(false);
    }
	
}
