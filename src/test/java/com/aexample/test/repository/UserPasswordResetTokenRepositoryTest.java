/**
 * 
 */
package com.aexample.test.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.aexample.persistence.model.UserAccount;
import com.aexample.persistence.model.UserPasswordResetToken;
import com.aexample.persistence.repositories.IUserPasswordResetTokenRepository;
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
public class UserPasswordResetTokenRepositoryTest extends BaseTest{
	
	@Autowired
	IUserRepository userAccountRepository;
	
	@Autowired
	IUserPasswordResetTokenRepository userPasswordResetTokenRepository;
	
	
	UserPasswordResetToken userPasswordResetToken = null;
	//AexampleTestingObjects ato = new AexampleTestingObjects();
	
	@Test
	public void UserPasswordResetTokenTest(){
		
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy'T'HH:mm:ss");
		Date dateTime = null;
		try {
			dateTime = formatter.parse("01-31-2018T18:25:43");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*
		 * the database relationship requires a User Account to tie the Reset Token to so 
		 * we add one here
		 */
		
		
		UserAccount ua = new UserAccount();
		ua.setAccountNonExpired(true);
		ua.setAccountNonLocked(true);
		
		//ua.setCreateDate(createDate);
		
		ua.setCredentialsNonExpired(true);
		ua.setEmail("bogus@bogus.com");
		ua.setEnabled(true);
		ua.setEncryptedPassword("gobbedlygook");
		ua.setFirstName("Aexample");
		ua.setLastName("TestCase");
		ua.setCreateDate(dateTime);
		//ua.setRoles(roles);
		
		userAccountRepository.save(ua);
		
		userPasswordResetToken = new UserPasswordResetToken();
		userPasswordResetToken.setExpiryDate(dateTime);
		userPasswordResetToken.setUser(ua);
		userPasswordResetToken.setNewEncryptPassword("AGobbedlyGookStringGoesHere");
		userPasswordResetToken.setToken("GottaHaveAToken");
		userPasswordResetToken.setUserId("bogus@bogus.com");
		
		userPasswordResetTokenRepository.save(userPasswordResetToken);
		
		
		UserPasswordResetToken found = userPasswordResetTokenRepository.findByUser(ua);
		
        // then
//        assertThat(found.getId()).isEqualTo(al.getId());
        assertThat(found.getExpiryDate().compareTo(userPasswordResetToken.getExpiryDate()));
        assertThat(found.getId()).isEqualTo(userPasswordResetToken.getId());
        assertThat(found.getNewEncryptPassword()).isEqualTo(userPasswordResetToken.getNewEncryptPassword());
        assertThat(found.getUserId()).isEqualTo(userPasswordResetToken.getUserId());

		userPasswordResetTokenRepository.delete(found);
        userAccountRepository.delete(ua);

        
	}
	
}

