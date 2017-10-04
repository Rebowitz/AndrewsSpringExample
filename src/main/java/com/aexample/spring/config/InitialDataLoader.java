/**
 * 
 */
package com.aexample.spring.config;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.aexample.persistence.model.Privilege;
import com.aexample.persistence.model.Role;
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
        Privilege readPrivilege = createPrivilegeIfNotFound("READ_PRIVILEGE");
        Privilege writePrivilege = createPrivilegeIfNotFound("WRITE_PRIVILEGE");
  
        List<Privilege> adminPrivileges = Arrays.asList(readPrivilege, writePrivilege);        
        createRoleIfNotFound("ROLE_ADMIN", adminPrivileges);
        createRoleIfNotFound("ROLE_USER", Arrays.asList(readPrivilege));
 
        //clean out user_roles and userAccount tables
		Connection conn = null;
		String sql = "DELETE FROM user_roles WHERE 1";
		String sql2 = "DELETE FROM userVerificationToken WHERE 1";
		String sql3 = "DELETE FROM userAccount WHERE 1";
		String sql4 = "DELETE FROM userLoginAttempts WHERE 1";
		String sql5 = "DELETE FROM activityLog WHERE 1";
    	try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.execute();
			ps.close();
			
			ps = conn.prepareStatement(sql2);
			ps.execute();
			ps.close();

			ps = conn.prepareStatement(sql3);
			ps.execute();
			ps.close();

			ps = conn.prepareStatement(sql4);
			ps.execute();
			ps.close();

			ps = conn.prepareStatement(sql5);
			ps.execute();
			ps.close();
			
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
 //       Role adminRole = roleRepository.findByName("ROLE_ADMIN");
        
        
        UserAccount user = new UserAccount();
    	
        user.setFirstName("Test");
        user.setLastName("Object");
        user.setEmail("TestObject@aexample.com");        
    	String password = "UsuallyBcryptEncoded";
    	//CharSequence charSeqPassword = "UsuallyBcryptEncoded";    	
        user.setEncryptedPassword(encryptionService.encryptString(password));
        user.setSecret("secret");
        user.setDeviceId("androiddevice");
        user.setRoles(Arrays.asList(roleRepository.findByName("ROLE_USER")));
        user.setEnabled(Boolean.TRUE);
        user.setAccountNonExpired(Boolean.TRUE);
        user.setAccountNonLocked(Boolean.TRUE);
        user.setCredentialsNonExpired(Boolean.TRUE);
        user.setCredentialsNonExpired(Boolean.TRUE);
        
        
        
        userRepository.save(user);
 
        alreadySetup = true;
    }
 
    @Transactional
    private Privilege createPrivilegeIfNotFound(String name) {
  
        Privilege privilege = privilegeRepository.findByName(name);
        if (privilege == null) {
            privilege = new Privilege(name);
            privilegeRepository.save(privilege);
        }
        return privilege;
    }
 
    @Transactional
    private Role createRoleIfNotFound(
      String name, Collection<Privilege> privileges) {
  
    	Role role = roleRepository.findByName(name);
        if (role == null) {
        	role = new Role();
        	role.setName(name);
        	role.setPrivileges(privileges);
            roleRepository.save(role);
        }
        return role;
    }
}