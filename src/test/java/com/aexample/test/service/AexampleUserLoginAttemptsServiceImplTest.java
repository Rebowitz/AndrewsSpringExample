/**
 * 
 */
package com.aexample.test.service;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.aexample.log4j.Slf4jTestWatcher;
import com.aexample.persistence.model.UserLoginAttempts;
import com.aexample.persistence.repositories.IUserLoginAttemptsRepository;
import com.aexample.security.service.UserLoginAttemptsServiceImpl;
import com.aexample.test.config.AexampleTestConfiguration;
import com.aexample.website.service.IUserService;

/**
 * @author Main Login
 * $Rev$
 * $Date$
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={AexampleTestConfiguration.class})
public class AexampleUserLoginAttemptsServiceImplTest {

	@Autowired
	IUserLoginAttemptsRepository userLoginAttemptsRepository;
	
	@Autowired
	UserLoginAttemptsServiceImpl userLoginAttemptsServiceImpl;
	
	@Rule
	public Slf4jTestWatcher watchman = new Slf4jTestWatcher();
	
	
	@Test
	public void testUserServiceCreate(){
		String result = userLoginAttemptsServiceImpl.serviceInstantiated();
		
		assertEquals("Created",result);
	}
	
	@Test
	public void testSaveUserLoginAttempt(){
		UserLoginAttempts userLoginAttempts = new UserLoginAttempts();
		userLoginAttempts.setUserId("bogustestemail@aexample.com");
		userLoginAttempts.setAttempts(1);
		userLoginAttempts.setLastModified(new Date());
		
		String response = userLoginAttemptsServiceImpl.addUserLoginAttempts("bogustestemail@aexample.com",new Date());
		
		assertEquals("addUserLoginAttempts completed",response);
	}
}
