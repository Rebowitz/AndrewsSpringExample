/**
 * 
 */
package com.aexample.test;



/**
 * @author Main Login
 * $Rev$
 * $Date$
 *
 */

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.aexample.test.service.AexampleUserLoginAttemptsServiceImplTest;


@RunWith(Suite.class)
@Suite.SuiteClasses({
	AexampleUserLoginAttemptsServiceImplTest.class	
//	UserObjectTest.class
//   AccountPOJOCreationTest.class,
//  LoginViewBeanTest.class,
//   RegistrationViewBeanTest.class,
//   AexampleRegistrationServiceImplTest.class, 
//   AexampleUserServiceImplTest.class,
//   AexampleRegistrationDaoTest.class,
//   LoginControllerTest.class,
//   RegistrationControllerTest.class,
//   DashboardControllerTest.class
})
public class TestSuite {

	
 //   static SessionFactoryUtil util;
    
    // This is a static field.  Per the ClassRule documentation,
    // to use a ClassRule we need a field that is "public, static,
    // and a subtype of of TestRule."
    // See http://junit.czweb.org/apidocs/org/junit/ClassRule.html
/*    @ClassRule
    public static ExternalResource testRule = new ExternalResource(){
        @Override
        protected void before() throws Throwable{
        	Logger logger = LoggerFactory.getLogger(com.aexample.test.TestSuite.class);
            logger.debug("Inside RuleSuite::ExternalResource::before.");
  //          util = new SessionFactoryUtil();
        };
 
        @Override
        protected void after(){
            // Nothing to do here in this case.
        	Logger logger = LoggerFactory.getLogger(com.aexample.test.TestSuite.class);
            logger.debug("Inside RuleSuite::ExternalResource::after.");       };
    };
   */ 	
}
