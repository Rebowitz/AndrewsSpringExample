/**
 * 
 */
package com.aexample.test.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.aexample.persistence.model.UserLoginAttempts;
import com.aexample.persistence.repositories.IUserLoginAttemptsRepository;
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
public class UserLoginAttemptsRepositoryTest extends BaseTest{
	 
	    @Autowired
	    private IUserLoginAttemptsRepository userLoginAttemptsRepository;
	 
	    // write test cases here
        UserLoginAttempts userLoginAttempts = null;
        
	    @Test
	    public void UserLoginAttemptsRepostiorySaveRetrieve() {
	        // given
			
			DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy'T'HH:mm:ss");
			Date dateTime = null;
			try {
				dateTime = formatter.parse("01-31-2018T18:25:43");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
				userLoginAttempts = new UserLoginAttempts();
//				userLoginAttempts.setId(Integer.toUnsignedLong(2345)); 
				userLoginAttempts.setDateCreated(dateTime);
				userLoginAttempts.setLastModified(dateTime);
				userLoginAttempts.setAttempts(1);
				userLoginAttempts.setUserId("bogus@bogus.com");

				userLoginAttemptsRepository.save(userLoginAttempts);

//				@Query("Select u from UserLoginAttempts u where u.userID= :email")
				UserLoginAttempts testUserLogAttempts = userLoginAttemptsRepository.findByEmail(userLoginAttempts.getUserId());
				assertThat(testUserLogAttempts.getDateCreated().compareTo(userLoginAttempts.getDateCreated()));
				
//			    @Modifying
//			    @Query("update UserLoginAttempts u set u.attempts = u.attempts + 1, lastModified = :date where u.userID = :email")
				Date testDate = new Date();
				userLoginAttemptsRepository.updateFailedLoginAttempts(userLoginAttempts.getUserId(), testDate);
				testUserLogAttempts = userLoginAttemptsRepository.findByEmail(userLoginAttempts.getUserId());
				assertThat(testUserLogAttempts.getAttempts()).isEqualTo(2);
				assertThat(testUserLogAttempts.getLastModified().compareTo(testDate));
				
				
//			    @Modifying
//			    @Query("update UserLoginAttempts u set u.attempts = 0, lastModified = :date where u.userID = :email")
				userLoginAttemptsRepository.clearFailedLoginAttempts(userLoginAttempts.getUserId(), testDate);
				testUserLogAttempts = userLoginAttemptsRepository.findByEmail(userLoginAttempts.getUserId());
				assertThat(testUserLogAttempts.getAttempts()).isEqualTo(0);
				assertThat(testUserLogAttempts.getLastModified().compareTo(testDate));				
				
			    
			    //other syntax is "delete from userLoginAttempts t where t.email = ?1"
			    //no @param is used in the method call  delete(String email)
//			    @Modifying
//			    @Query("delete from UserLoginAttempts u where u.userID = :email")
				userLoginAttemptsRepository.deleteByEmail(userLoginAttempts.getUserId());
				testUserLogAttempts = userLoginAttemptsRepository.findByEmail(userLoginAttempts.getUserId());				
				assertThat(testUserLogAttempts).isNull();
				
	        
	    }
	    
}
