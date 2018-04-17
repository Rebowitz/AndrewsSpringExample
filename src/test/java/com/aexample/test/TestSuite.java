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
import com.aexample.test.repository.UserVerificationTokenRepositoryTest;
import com.aexample.test.service.UserServiceImplTest;


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
	UserRepositoryTest.class,
	UserVerificationTokenRepositoryTest.class,
	
	//service layer tests
	UserServiceImplTest.class
	//ActivityLogServiceTest.class,
	//ApplicationTaskExecutorServiceTest.class,
	//RoleServiceTest.class,
	//TransactionLogService.class,
	//UserService.class,

	//security service layer tests
	//ActiveUserStoreTest.class,
	//AuthAttemptsLoggerTest.class,
	//CustomWebAuthenicationDetailsTest.class,
	//CustomeWebAuthenticationDetailsSource.class,
	//EncryptionServiceClassTest.class,
	//SecurityUserServiceTest.class,
	//UserLoginAttemptsServiceTest.class,
	//LimitLoginAttemptsAuthenticationProviderTest.class,
	//LoggedUserTest.class,
	//SpringSecurityBeansTest.class,
	//CustomSpringSecurityUserDetailsService.class,
	//EncryptionServiceImplTest.class,
	//RegistrationUserDetailsServiceTest.class,
	//UserDetailsImplTest.class,
	//UserDetailsServiceImplTest.class,
	//UserLoginAttemptsServiceImplTest.class,
	//UserSecurityServiceTest.class,
	//UserToUserDetailsTest.class,
	
	
	//controller tests
	
 // RegistrationControllerTest.class,
  
//	AexampleUserLoginAttemptsServiceImplTest.class	
//  LoginViewBeanTest.class,
//   RegistrationViewBeanTest.class,
//   AexampleRegistrationServiceImplTest.class, 
//   UserServiceImplTest.class,
//   AexampleRegistrationDaoTest.class,
//   LoginControllerTest.class,

//   DashboardControllerTest.class
})
public class TestSuite {

}
