/**
 * 
 */
package com.aexample.test.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.aexample.annotations.ILogger;
import com.aexample.persistence.model.Role;
import com.aexample.persistence.model.UserAccount;
import com.aexample.persistence.repositories.IPrivilegeRepository;
import com.aexample.persistence.repositories.IRoleRepository;
import com.aexample.persistence.repositories.IUserRepository;
import com.aexample.spring.config.InitialDataLoader;
import com.aexample.spring.config.SpringSecurityConfig;
import com.aexample.test.BaseTest;
import com.aexample.test.config.AexampleTestConfiguration;
import com.aexample.test.config.PersistenceJPAConfigTest;
import com.aexample.test.pojo.AbstractPOJOTester;

/**
 * @author Main Login
 * $Rev$
 * $Date$
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
//ApplicationContext will be loaded from "/base-context.xml" in the root of the classpath
@ContextConfiguration(classes = {PersistenceJPAConfigTest.class, AexampleTestConfiguration.class,
		SpringSecurityConfig.class})
public class UserObjectTest extends BaseTest{
	
	@Autowired
    private IUserRepository repository;
	
	@Autowired
	private IRoleRepository roleRepository;
/*	
	@Autowired
    private final IUserVerificationTokenRepository tokenRepository;
	
	@Autowired
    private final IUserPasswordResetTokenRepository passwordTokenRepository;
*/
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private IPrivilegeRepository privilegeRepository;

//	private static final Logger logger = LoggerFactory.getLogger(UserObjectTest.class);
	private static @ILogger Logger logger;	
	
	@Before
	public void setup(){
//		setApplicationContext(applicationContext);
		
		
//		InitialDataLoader itdl = new InitialDataLoader();
	//	itdl.onApplicationEvent();
	}
	@After
	public void tearDown(){
		//stuff here to clean out the db
	}
    
    //using AbstractPOJOTester
    @Test
    public void UserGetterSetterTest(){
    	UserAccount user = new UserAccount();
    	
    	logger.debug("Model object USER -- Running basic getter/setter tests");
    	Map<String,Object> userMap = new HashMap<String,Object>();
    	//loading the dto
    	
    	String firstName = "Test";
    	String lastName = "Object";
    	String email = "TestObject@aexample.com";
    	String password = "UsuallyBcryptEncoded";
    	CharSequence charSeqPassword = "UsuallyBcryptEncoded";
    	String secret = "secret";
        String deviceId = "androiddevice";   
        Boolean accountNonExpired = Boolean.TRUE;
        Boolean accountNonLocked = Boolean.TRUE;  
        Boolean credentialsNonExpired = Boolean.TRUE;
        Boolean enabled = Boolean.TRUE;
        Date createDate = new Date();

        
        
    	userMap.put("firstName", firstName);
    	userMap.put("lastName", lastName);
    	userMap.put("email", email);
    	userMap.put("password", password);
    	userMap.put("secret", secret);    	
    	userMap.put("deviceId", deviceId); 
    	userMap.put("accountNonExpired", accountNonExpired); 
    	userMap.put("accountNonLocked", accountNonLocked); 
    	userMap.put("credentialsNonExpired", credentialsNonExpired); 
    	userMap.put("enabled", enabled); 
    	userMap.put("createDate", createDate);
    	    	
    	
    	AbstractPOJOTester apt = new AbstractPOJOTester();
    	apt.testThePOJO(UserAccount.class,userMap);
    	
    	logger.debug("Passed basic getter/setter tests");
    	
 
    	
    	//testing with the repository
    	user.setFirstName(firstName);
    	user.setLastName(lastName);
    	user.setEmail(email);
    	user.setEncryptedPassword(passwordEncoder.encode(charSeqPassword));
//    	user.setPassword(password);
    	user.setSecret(secret);    	
    	user.setDeviceId(deviceId);
    	user.setAccountNonExpired(accountNonExpired);
    	user.setAccountNonLocked(accountNonLocked);
    	user.setCredentialsNonExpired(credentialsNonExpired);
    	user.setEnabled(enabled);
    	user.setCreateDate(createDate);	
        user.setRoles(Arrays.asList(roleRepository.findByName("ROLE_USER")));
    	    	
        UserAccount createdAccount = repository.save(user);
        UserAccount returnedAccount = repository.findOne(createdAccount.getId());
        
    	logger.debug("-----");
    	logger.debug("-----"); 
    	logger.debug("-----");     	
    	logger.debug("Saved UserAccount object");
    	logger.debug("Trying user comparator routine--zero is desired value");
    	int rtnval = user.compareTo(returnedAccount);    	
    	logger.debug("comparator rtnval is " + rtnval);
    	
    	Collection<Role> roleList = user.getRoles();
    	Iterator<Role> iter = roleList.iterator();
    	while(iter.hasNext()){
    		Role iterRole = (Role)iter.next();
    		logger.debug("User ID is " + iterRole.getId().toString());    		
    		logger.debug(iterRole.getName());
    	}
    	
    	logger.debug("-----"); 
    	logger.debug("-----"); 
    	logger.debug("-----");     	
    	
    	assertNotNull(returnedAccount);
    	
    	assertEquals(user.getFirstName(),returnedAccount.getFirstName());
    	assertEquals(user.getLastName(),returnedAccount.getLastName());
    	assertEquals(user.getEmail(),returnedAccount.getEmail());
    	assertEquals(user.getDeviceId(),returnedAccount.getDeviceId());
    	assertEquals(user.getEncryptedPassword(),returnedAccount.getEncryptedPassword());
    	assertEquals(user.getSecret(),returnedAccount.getSecret());
    	assertEquals(user.getAccountNonExpired().toString(),returnedAccount.getAccountNonExpired().toString());
    	assertEquals(user.getAccountNonLocked().toString(),returnedAccount.getAccountNonLocked().toString());
    	assertEquals(user.getCredentialsNonExpired().toString(),returnedAccount.getCredentialsNonExpired().toString());
    	assertEquals(user.getEnabled().toString(),returnedAccount.getEnabled().toString());
    	assertEquals(user.getId().toString(),returnedAccount.getId().toString());
  //  	logger.debug(user);
  //  	logger.debug(returnedAccount);
 //   	assertEquals(true,returnedAccount.equals(user));
    	    	    	
    }

	
}
