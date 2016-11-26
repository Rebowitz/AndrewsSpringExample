/**
 * 
 */
package com.aexample.test;

import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.aexample.log4j.Slf4jTestWatcher;
import com.aexample.persistence.model.Accounts;

/**
 * @author Main Login
 * $Rev$
 * $Date$
 *
 */


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={JUnitTestConfiguration.class})
public class AccountPOJOCreationTest {

	private static final Logger logger = LoggerFactory.getLogger(AccountPOJOCreationTest.class);	
	AbstractPOJOTester apt = new AbstractPOJOTester();
	
		@Rule
		public Slf4jTestWatcher watchman = new Slf4jTestWatcher();
		 
		@Test
		public <T> void testThePOJO(){
			Class<? extends Accounts> account = Accounts.class;
			Map<String,Object> pojoMap = populateAccountMap();
			
			apt.testThePOJO(account, pojoMap);
			
		}

		private Map<String,Object> populateAccountMap(){
			
			
			Map<String, Object> theMap = new HashMap<String,Object>();
			
			theMap.put("id", Long.valueOf(1));  //this is usually supplied as autoincremented index on db insert
			theMap.put("orgName","AExample");
			theMap.put("firstName","JUnit");
			theMap.put("lastName","Test Case");
			theMap.put("email","bogus@aexample.com");
			theMap.put("loginId","aexample");
			theMap.put("profileFlag",Boolean.FALSE);
			theMap.put("password","cleartext");
			theMap.put("plan","Basic");
			theMap.put("accountNonExpired",Boolean.TRUE);
			theMap.put("accountNonLocked",Boolean.TRUE);
			theMap.put("enabled",Boolean.TRUE);
			theMap.put("role","ROLE_USER");
			theMap.put("createDate",new Date(0));
			theMap.put("credentialsNonExpired",Boolean.TRUE);
			
			return theMap;
		}

		

}
