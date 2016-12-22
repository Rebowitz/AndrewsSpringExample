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
import com.aexample.log4j.Slf4jTestWatcher;

//the bean to be tested
import com.aexample.website.viewBean.RegistrationBean;
//the bean to be tested

import com.aexample.test.JUnitTestConfiguration;
import com.aexample.test.pojo.AbstractPOJOTester;


/**
 * @author Main Login
 * $Rev$
 * $Date$
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={JUnitTestConfiguration.class})
public class RegistrationViewBeanTest {

	//change this to read the class name of the test
	private static final Logger logger = LoggerFactory.getLogger(RegistrationViewBeanTest.class);	
	AbstractPOJOTester apt = new AbstractPOJOTester();
	
		@Rule
		public Slf4jTestWatcher watchman = new Slf4jTestWatcher();
		 
		@Test
		public <T> void testThePOJO(){
			//change these to the class of the bean
			Class<? extends RegistrationBean> beanClass = RegistrationBean.class;
			Map<String,Object> pojoMap = populateMap();
			
			apt.testThePOJO(beanClass, pojoMap);
			
		}
		
		//object equals and hash code tests
		//the AbstractEqualsTester code carries out both tests
		@Test
		public void testEqualsAndHashCode(){
			RegistrationBean rb1 = new RegistrationBean();
			RegistrationBean rb2 = new RegistrationBean();
			RegistrationBean rb3 = new RegistrationBean();			
			
			//not necessary to set all fields for an object equals and hashcode test
			rb1.setOrgName("RB1");
			rb1.setPassword("RB1Pass");
			rb1.setPlan("RBean1");
			
			rb2.setOrgName("RB2");
			rb2.setPassword("RB2Pass");
			rb2.setPlan("RBean2");
			
			rb3.setOrgName("RB1");
			rb3.setPassword("RB1Pass");
			rb3.setPlan("RBean1");
			
			AbstractEqualsTester<RegistrationBean> equalsTester = AbstractEqualsTester.newInstance(rb1);
			equalsTester.assertEqual(rb1, rb3);
			equalsTester.assertNotEqual(rb1, rb2);				
			
		}

		private Map<String,Object> populateMap(){
			
			
			Map<String, Object> theMap = new HashMap<String,Object>();

			//change map entries to match methods of the bean being tested
//			theMap.put("id", Long.valueOf(1));  //this is usually supplied as autoincremented index on db insert
			theMap.put("Id", Integer.valueOf(1));  //this is usually supplied as autoincremented index on db insert
			theMap.put("OrgName","AExample");
			theMap.put("FirstName","JUnit");
			theMap.put("LastName","Test Case");
			theMap.put("Email","bogus@aexample.com");
			theMap.put("LoginId","aexample");
			theMap.put("Password","cleartext");
			theMap.put("ConfirmPassword","cleartext");			
			theMap.put("Plan","Basic");
			return theMap;
		}

		

	
	
}
