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

import com.aexample.test.dao.AexampleRegistrationDaoTest;
import com.aexample.test.pojo.AccountPOJOCreationTest;
import com.aexample.test.service.AexampleRegistrationServiceImplTest;
import com.aexample.test.service.AexampleUserServiceImplTest;


@RunWith(Suite.class)

@Suite.SuiteClasses({
   AccountPOJOCreationTest.class,
   AexampleRegistrationServiceImplTest.class, 
   AexampleUserServiceImplTest.class,
   AexampleRegistrationDaoTest.class
//   TestJunit2.class
})

@Import(JUnitTestConfiguration.class)
public class TestSuite {
	@BeforeClass
	public static void setup(){
		
	}
}
