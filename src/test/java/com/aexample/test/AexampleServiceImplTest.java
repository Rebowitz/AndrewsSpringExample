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
		
		String result = iRegSvc.serviceInstantiated();
		
		assertEquals("Created",result);

	}
	
	@Test
	public void testUserServiceCreate(){
		String result = iUsrSvc.serviceInstantiated();
		
		assertEquals("Created",result);
	}
	
}
