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

import com.aexample.persistence.model.Privileges;
import com.aexample.persistence.repositories.IPrivilegeRepository;
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
public class PrivilegeRepositoryTest extends BaseTest{
	 
	
	    @Autowired
	    private IPrivilegeRepository privilegeRepository;
	 
	    // write test cases here
        Privileges privs = null;
        Privileges privs2 = null;
        
	    @Test
	    public void privilegeRepositorySaveRetrieve() {
	        // given
			
			DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy'T'HH:mm:ss");
			Date dateTime = null;
			try {
				dateTime = formatter.parse("01-31-2018T18:25:43");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
//these values are set by the dbsetup classes
			
	/*			privs = new Privileges();
	//			privs.setId(Integer.toUnsignedLong(2345));  this is an autoincrement field
				privs.setName("READ_PRIVILEGE");
				privilegeRepository.saveAndFlush(privs);
				
				
				privs2 = new Privileges();
	//			privs.setId(Integer.toUnsignedLong(2345));  this is an autoincrement field
				privs2.setName("WRITE_PRIVILEGE");
				privilegeRepository.saveAndFlush(privs2);
			//this also sets the id field of the above Privilege record and returns the value to this object	
	*/			
				
				Privileges found = privilegeRepository.findByName("READ_PRIVILEGE");
		        assertThat(found.getName()).isEqualTo("READ_PRIVILEGE");
		        
				found = privilegeRepository.findByName("WRITE_PRIVILEGE");
		        assertThat(found.getName()).isEqualTo("WRITE_PRIVILEGE");		        
		        
		    //checking findByAccountID
		    List<Privileges> privilegeList= privilegeRepository.findAll();
		    
	        //there should be four records in the activity log
	        Integer inty = new Integer(privilegeList.size());
	        assertThat(inty.compareTo(2));
	        
	    }
	    
}

