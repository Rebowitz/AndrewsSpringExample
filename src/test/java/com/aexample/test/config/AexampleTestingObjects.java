/**
 * 
 */
package com.aexample.test.config;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.aexample.persistence.model.Privileges;
import com.aexample.persistence.model.Roles;
import com.aexample.persistence.model.UserAccount;
import com.aexample.persistence.repositories.IPrivilegeRepository;
import com.aexample.persistence.repositories.IRoleRepository;


/**
 * @author Robert B. Andrews
 * $Rev$
 * $Date$
 *
 * This class is intended to provide special objects for junit testing of database model classes.  
 *
 */
@Component
@Transactional
public class AexampleTestingObjects {
	Logger logger = LoggerFactory.getLogger(AexampleTestingObjects.class);
	
    @Autowired
    private IRoleRepository roleRepository;
  
    @Autowired
    private IPrivilegeRepository privilegeRepository;
    
    @Autowired
    private EntityManager entityManager;
	
	private UserAccount singleAccount;
	private Set<UserAccount> userAccountSet;
	private Collection<Privileges> privilegeCollection;
	private Collection<Roles> roleCollection;
	Privileges readPrivilege;
	Privileges writePrivilege;
	UserAccount ua1;
	UserAccount ua2;
	Roles r1;
	Roles r2;
	Date tempDate;
		
	/**
	 * 
	 */
	public AexampleTestingObjects() {
		super();
		createPrivilegeCollection();
		createRolesCollection();
		createSingleAccount();
		createUserAccountSet();
	//	testPrep();
		// TODO Auto-generated constructor stub
	}
	
	
	public UserAccount getSingleAccount(){
//		createSingleAccount();
		return singleAccount;
	}
	
	public UserAccount getUserAccountOne(){
//		createRolesCollection();
		return ua1;
	}
	
	public UserAccount getUserAccountTwo(){
//		createRolesCollection();
		return ua2;
	}
	
	public Set<UserAccount> getUserAccountSet(){
//		createUserAccountSet();
		return userAccountSet;
	}
	
	public Collection<Privileges> getPrivilegesCollection(){
		createPrivilegeCollection();
		return privilegeCollection;
	}
	
	public Collection<Roles> getRolesCollection(){
		createRolesCollection();
		return roleCollection;
	}
	
	public Date getADate(){
		return new Date();
	}
	
	
	private void createSingleAccount(){
		singleAccount = new UserAccount();
		Date tempDate = new Date();
		
//		singleAccount.setId(Integer.toUnsignedLong(1));  //autoinc primary key
		singleAccount.setFirstName("Aexample");
		singleAccount.setLastName("UnitTest");
		singleAccount.setEmail("bogus@bogus.com");
		singleAccount.setEncryptedPassword("gobbedlygookpassword");
		singleAccount.setSecret("shhh!");
		singleAccount.setDeviceId("HelloMoto");
		singleAccount.setAccountNonExpired(true);
		singleAccount.setAccountNonLocked(true);
		singleAccount.setCredentialsNonExpired(true);
		singleAccount.setEnabled(true);
		singleAccount.setCreateDate(tempDate);
		singleAccount.setRoles(roleCollection);
		
	}
	
	private void createUserAccountSet(){
		ua1 = new UserAccount();
		ua2 = new UserAccount();
		tempDate = new Date();
		userAccountSet = new HashSet<UserAccount>();
		
//		ua1.setId(Integer.toUnsignedLong(1));
		ua1.setFirstName("Aexample");
		ua1.setLastName("UnitTest");
		ua1.setEmail("bogus@bogus.com");
		ua1.setEncryptedPassword("gobbedlygook");
		ua1.setSecret("shush!");
		ua1.setDeviceId("HelloMoto");
		ua1.setAccountNonExpired(true);
		ua1.setAccountNonLocked(true);
		ua1.setCredentialsNonExpired(true);
		ua1.setEnabled(true);
		ua1.setCreateDate(tempDate);
		ua1.setRoles(roleCollection);
		
//		ua2.setId(Integer.toUnsignedLong(2));
		ua2.setFirstName("Aexample");
		ua2.setLastName("UnitTest2");
		ua2.setEmail("bogus@bogus.com");
		ua2.setEncryptedPassword("gobbedlygook");
		ua2.setSecret("ciaplot");
		ua2.setDeviceId("AppleSauce");
		ua2.setAccountNonExpired(false);
		ua2.setAccountNonLocked(false);
		ua2.setCredentialsNonExpired(false);
		ua2.setEnabled(false);
		ua2.setCreateDate(tempDate);
		ua2.setRoles(roleCollection);
		userAccountSet.add(ua1);
		userAccountSet.add(ua2);
	}
	
	private void createPrivilegeCollection(){
		Privileges p1 = new Privileges();
		Privileges p2 = new Privileges();		
		p1.setId(Integer.toUnsignedLong(1));
		p1.setName("READ_PRIVILEGE");
		p2.setId(Integer.toUnsignedLong(2));
		p2.setName("WRITE_PRIVILEGE");
		
		privilegeCollection = Arrays.asList(p1, p2);
	}
	
	@Transactional
	private void createRolesCollection(){
		r1 = new Roles();
		r2 = new Roles();
		
		r1 = roleRepository.findByName("ROLE_ADMIN");
		r2 = roleRepository.findByName("ROLE_USER");
		/*
//		r1.setId(Integer.toUnsignedLong(1));
		r1.setName("ROLE_ADMIN");
		r1.setPrivileges(privilegeCollection);
//		r2.setId(Integer.toUnsignedLong(2));
		r2.setName("ROLE_USER");
		r2.setPrivileges(privilegeCollection);
		*/
		roleCollection = Arrays.asList(r1,r2);
	}
	
	   @Transactional
	    private Privileges createPrivilegeIfNotFound(String name) {
		   
		   logger.debug("Privileges name is: " + name);
		   
	        Privileges privileges = privilegeRepository.findByName(name);
	        if (privileges == null) {
	            privileges = new Privileges(name);
	            privilegeRepository.save(privileges);
	        }
	        return privileges;
	    }
	 
	    @Transactional
	    private Roles createRoleIfNotFound(
	      String name, Collection<Privileges> privileges) {
	  
	    	Roles roles = roleRepository.findByName(name);
	        if (roles == null) {
	        	roles = new Roles();
	        	roles.setName(name);
	        	roles.setPrivileges(privileges);
	            roleRepository.save(roles);
	        }
	        return roles;
	    }
}
