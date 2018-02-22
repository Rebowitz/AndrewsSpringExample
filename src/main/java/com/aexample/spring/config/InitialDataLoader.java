/**
 * 
 */
package com.aexample.spring.config;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.aexample.persistence.model.Privileges;
import com.aexample.persistence.model.Roles;
import com.aexample.persistence.model.UserAccount;
import com.aexample.persistence.repositories.IActivityLogRepository;
import com.aexample.persistence.repositories.IPrivilegeRepository;
import com.aexample.persistence.repositories.IRoleRepository;
import com.aexample.persistence.repositories.IUserRepository;
import com.aexample.security.IEncryptionService;

/**
 * @author Main Login
 * $Rev$
 * $Date$
 *
 */

@Component
public class InitialDataLoader implements
  ApplicationListener<ContextRefreshedEvent> {
 
    boolean alreadySetup = false;
 
    @Autowired
    private IUserRepository userRepository;
  
    @Autowired
    private IRoleRepository roleRepository;
  
    @Autowired
    private IPrivilegeRepository privilegeRepository;
  
	@Autowired
	private IEncryptionService encryptionService;
	
	@Autowired
	private IActivityLogRepository activityLogRepository;
	
    @Autowired
    public void setEncryptionService(IEncryptionService encryptionService) {
        this.encryptionService = encryptionService;
    }
    
    @Autowired
    DataSource dataSource;
  
    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
  
        if (alreadySetup)
            return;
        Privileges readPrivilege = createPrivilegeIfNotFound("READ_PRIVILEGE");
        Privileges writePrivilege = createPrivilegeIfNotFound("WRITE_PRIVILEGE");
  
        List<Privileges> adminPrivileges = Arrays.asList(readPrivilege, writePrivilege);        
        createRoleIfNotFound("ROLE_ADMIN", adminPrivileges);
        createRoleIfNotFound("ROLE_USER", Arrays.asList(readPrivilege));
 
     
        
        Roles adminRole = roleRepository.findByName("ROLE_ADMIN");
        UserAccount user = new UserAccount();
        
        Date dateTime = new Date();
    	
        user.setFirstName("Initializer");
        user.setLastName("Account");
        user.setEmail("initializer@aexample.com");        
    	String password = "UsuallyBcryptEncoded";
    	//CharSequence charSeqPassword = "UsuallyBcryptEncoded";    	
        user.setEncryptedPassword(encryptionService.encryptString(password));
        user.setSecret("secret");
        user.setDeviceId("androiddevice");
        user.setRoles(Arrays.asList(adminRole));
        user.setEnabled(true);  
        user.setCreateDate(dateTime);
        
        userRepository.save(user);
 
        alreadySetup = true;
    }
 
    @Transactional
    private Privileges createPrivilegeIfNotFound(String name) {
  
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