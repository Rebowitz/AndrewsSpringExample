/**
 * 
 */
package com.aexample.test.bean;

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

import com.aexample.annotations.ILogger;
import com.aexample.log4j.Slf4jTestWatcher;

//the bean to be tested
import com.aexample.website.viewBean.LoginBean;
//the bean to be tested

import com.aexample.test.config.AexampleTestConfiguration;
import com.aexample.test.pojo.AbstractPOJOTester;


/**
 * @author Main Login
 * $Rev$
 * $Date$
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={AexampleTestConfiguration.class})
public class LoginViewBeanTest {

	//change this to read the class name of the test
//	private static final Logger logger = LoggerFactory.getLogger(LoginViewBeanTest.class);
	private static @ILogger Logger logger;	
	
	AbstractPOJOTester apt = new AbstractPOJOTester();
	
		@Rule
		public Slf4jTestWatcher watchman = new Slf4jTestWatcher();
		 
		@Test
		public <T> void testThePOJO(){
			//change these to the class of the bean
			Class<? extends LoginBean> beanClass = LoginBean.class;
			Map<String,Object> pojoMap = populateMap();
			
			apt.testThePOJO(beanClass, pojoMap);
			
		}
		
		//object equals and hash code tests
		//the AbstractEqualsTester code carries out both tests
		@Test
		public void testEqualsAndHashCode(){
			LoginBean lb1 = new LoginBean();
			LoginBean lb2 = new LoginBean();
			LoginBean lb3 = new LoginBean();			
			LoginBean lb4 = new LoginBean();
			
			lb1.setUsername("LB1");
			lb1.setPassword("LB1Pass");
			lb1.setMessage("LoginBean1");
			
			lb2.setUsername("LB2");
			lb2.setPassword("LB2Pass");
			lb2.setMessage("LoginBean2");
			
			lb3.setUsername("LB1");
			lb3.setPassword("LB1Pass");
			lb3.setMessage("LoginBean1");

			lb4.setUsername("LB1");
			lb4.setPassword("LB1Pass");
			lb4.setMessage("LoginBean1");
			
			AbstractEqualsTester<LoginBean> equalsTester = AbstractEqualsTester.newInstance(lb1);
			equalsTester.assertImplementsEqualsAndHashCode();
			equalsTester.assertEqual(lb1, lb3);
			equalsTester.assertEqual(lb1, lb3, lb4);			
			equalsTester.assertNotEqual(lb1, lb2);				
		}

		private Map<String,Object> populateMap(){
			
			
			Map<String, Object> theMap = new HashMap<String,Object>();

			//change map entries to match methods of the bean being tested
//			theMap.put("id", Long.valueOf(1));  //this is usually supplied as autoincremented index on db insert
			theMap.put("Username","JUnit");
			theMap.put("Password","cleartext");
			theMap.put("Message","Account not found!");
			
			return theMap;
		}

		

	
	
}
