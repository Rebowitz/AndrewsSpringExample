/**
 * 
 */
package com.aexample.test.repository;

/**
 * @author Robert B. Andrews
 * $Rev$
 * $Date$
 *
 */

import static org.assertj.core.api.Assertions.assertThat;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.aexample.persistence.model.Roles;
import com.aexample.persistence.repositories.IPrivilegeRepository;
import com.aexample.persistence.repositories.IRoleRepository;
import com.aexample.test.BaseTest;
import com.aexample.test.config.AexampleTestConfiguration;
import com.aexample.test.config.PersistenceJPAConfigTest;

/**
 * @author Robert B. Andrews
 * $Rev$
 * $Date$
 *
 */
@RunWith(SpringRunner.class)
//@DataJpaTest
@ContextConfiguration(classes={AexampleTestConfiguration.class,PersistenceJPAConfigTest.class})
public class RoleRepositoryTest extends BaseTest{
	 
	
	    @Autowired
	    private IRoleRepository roleRepository;
	    
	    @Autowired
	    private IPrivilegeRepository privilegesRepository;
	 
	    // write test cases here
        Roles role = null;
        Roles role2 = null;
        
	    @Test
	    public void roleRepositorySaveRetrieve() {
	        // given
			
			DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy'T'HH:mm:ss");
			Date dateTime = null;
			try {
				dateTime = formatter.parse("01-31-2018T18:25:43");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			//we should never be creating a role in our application software except maybe from an admin console
			//right now the answer is NEVER

			/*
				role = new Roles();
	//			privs.setId(Integer.toUnsignedLong(2345));  this is an autoincrement field
				role.setName("ROLE_ADMIN");
				role.setPrivileges(privilegesRepository.findAll());
								
				roleRepository.save(role);
				
				
				role2 = new Roles();
	//			privs.setId(Integer.toUnsignedLong(2345));  this is an autoincrement field
				role2.setName("ROLE_USER");
				role2.setPrivileges(privilegesRepository.findAll());
				
				roleRepository.save(role2);
			//this also sets the id field of the above Privilege record and returns the value to this object	
				
*/				
				Roles found = roleRepository.findByName("ROLE_ADMIN");
		        assertThat(found.getName()).isEqualTo("ROLE_ADMIN");
		        
				found = roleRepository.findByName("ROLE_USER");
		        assertThat(found.getName()).isEqualTo("ROLE_USER");		        
		        
		    //checking findByAccountID
		    List<Roles> roleList = roleRepository.findAll();
		    
	        //there should be four records in the activity log
	        Integer inty = new Integer(roleList.size());
	        assertThat(inty.compareTo(2));
	        
	    }
	    
}

