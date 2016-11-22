/**
 * 
 */
package com.aexample.test;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.aexample.log4j.Slf4jTestWatcher;
import com.aexample.website.service.IRegistrationService;
import com.aexample.website.service.IUserService;

/**
 * @author Main Login
 * $Rev$
 * $Date$
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={JUnitTestConfiguration.class})
public class AexampleServiceImplTest {

	@Autowired
	private IRegistrationService iRegSvc;
	
	@Autowired
	private IUserService iUsrSvc;
	
	@Rule
	public Slf4jTestWatcher watchman = new Slf4jTestWatcher();
	
	 
	
	@Test
	public void testRegistrationServiceCreate(){
/* need to move this to the appropriate unit test

		Accounts entity = new Accounts();

		entity.setOrgName("AExample");
		entity.setFirstName("JUnit");
		entity.setLastName("Test Case");
		entity.setEmail("bogus@aexample.com");
		entity.setLoginId("aexample");
		entity.setProfileFlag(false);
		entity.setPassword("cleartext");
		entity.setPlan("Basic");
		entity.setAccountNonExpired(true);
		entity.setAccountNonLocked(true);
		entity.setEnabled(true);
		entity.setRole("ROLE_USER");
		entity.setCreateDate(new Date());
		entity.setCredentialsNonExpired(true);
		
		String retr = iRegSvc.create(entity);
		String expected = "Registered";
		
		//check for successful persistence from service layer
		assertEquals(retr,expected);
	*/	
		
		String result = iRegSvc.serviceInstantiated();
		
		assertEquals("Created",result);

	}
	
	@Test
	public void testUserServiceCreate(){
		String result = iUsrSvc.serviceInstantiated();
		
		assertEquals("Created",result);
	}
	
}
