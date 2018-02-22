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

import com.aexample.test.config.TestDatabasePrep;
import com.aexample.test.controller.RegistrationControllerTest;
import com.aexample.test.dto.DtoTester;
import com.aexample.test.model.ActivityLogAnnoTest;
import com.aexample.test.model.ModelTester;
import com.aexample.test.model.PrivilegesAnnoTest;
import com.aexample.test.model.RolesAnnoTest;
import com.aexample.test.model.RolesPrivilegesAnnoTest;
import com.aexample.test.model.TransactionLogAnnoTest;
import com.aexample.test.model.UserAccountAnnoTest;
import com.aexample.test.model.UserLoginAttemptsAnnoTest;
import com.aexample.test.model.UserPasswordResetTokenAnnoTest;
import com.aexample.test.model.UserRolesAnnoTest;
import com.aexample.test.model.UserVerificationTokenAnnoTest;
import com.aexample.test.repository.ActivityLogRepositoryTest;
import com.aexample.test.repository.PrivilegeRepositoryTest;
import com.aexample.test.repository.RoleRepositoryTest;
import com.aexample.test.repository.TransactionLogRepositoryTest;
import com.aexample.test.repository.UserLoginAttemptsRepositoryTest;
import com.aexample.test.repository.UserPasswordResetTokenRepositoryTest;
import com.aexample.test.repository.UserRepositoryTest;
import com.aexample.test.service.AexampleUserLoginAttemptsServiceImplTest;


@RunWith(Suite.class)
@Suite.SuiteClasses({
	TestDatabasePrep.class,
	DtoTester.class,
	ModelTester.class,	
	
	//Model annotation tests
	ActivityLogAnnoTest.class,
	PrivilegesAnnoTest.class,
	RolesAnnoTest.class,
	RolesPrivilegesAnnoTest.class,
	TransactionLogAnnoTest.class,
	UserAccountAnnoTest.class,
	UserLoginAttemptsAnnoTest.class,
	UserPasswordResetTokenAnnoTest.class,
	UserRolesAnnoTest.class,
	UserVerificationTokenAnnoTest.class,
	
	//repository tests
	PrivilegeRepositoryTest.class,
	RoleRepositoryTest.class,
	TransactionLogRepositoryTest.class,
	UserLoginAttemptsRepositoryTest.class,
	UserPasswordResetTokenRepositoryTest.class,
	ActivityLogRepositoryTest.class,
	UserRepositoryTest.class
	
//	AexampleUserLoginAttemptsServiceImplTest.class	
//  LoginViewBeanTest.class,
//   RegistrationViewBeanTest.class,
//   AexampleRegistrationServiceImplTest.class, 
//   AexampleUserServiceImplTest.class,
//   AexampleRegistrationDaoTest.class,
//   LoginControllerTest.class,
//   RegistrationControllerTest.class
//   DashboardControllerTest.class
})
public class TestSuite {

}
