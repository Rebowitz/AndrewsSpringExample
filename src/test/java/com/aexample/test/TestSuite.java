/**
 * 
 */
package com.aexample.test;

import org.junit.BeforeClass;

/**
 * @author Main Login
 * $Rev$
 * $Date$
 *
 */

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.context.annotation.Import;

import com.aexample.test.bean.LoginViewBeanTest;
import com.aexample.test.bean.RegistrationViewBeanTest;
import com.aexample.test.config.PersistenceJPAConfigTest;
import com.aexample.test.controller.DashboardControllerTest;
import com.aexample.test.controller.LoginControllerTest;
import com.aexample.test.controller.RegistrationControllerTest;
import com.aexample.test.dao.AexampleRegistrationDaoTest;
import com.aexample.test.pojo.AccountPOJOCreationTest;
import com.aexample.test.service.AexampleRegistrationServiceImplTest;
import com.aexample.test.service.AexampleUserServiceImplTest;


@RunWith(Suite.class)

@Suite.SuiteClasses({
   PersistenceJPAConfigTest.class,
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

@Import(JUnitTestConfiguration.class)
public class TestSuite {
	@BeforeClass
	public static void setup(){
		
	}
}
