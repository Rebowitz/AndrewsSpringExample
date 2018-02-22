/**
 * 
 */
package com.aexample.test.repository;

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

import com.aexample.persistence.model.ActivityLog;
import com.aexample.persistence.repositories.IActivityLogRepository;
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
public class ActivityLogRepositoryTest extends BaseTest{
	 
	    @Autowired
	    private IActivityLogRepository activityLogRepository;
	 
	    // write test cases here
        ActivityLog al = null;
        
	    @Test
	    public void actvityLogRepostiorySaveRetrieve() {
	        // given
			
			DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy'T'HH:mm:ss");
			Date dateTime = null;
			try {
				dateTime = formatter.parse("01-31-2018T18:25:43");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			for(int i = 0; i < 4; i++){
		        al = new ActivityLog();
	//			al.setId(Integer.toUnsignedLong(2345));  this is an autoincrement field
				al.setDateTime(dateTime); 
				al.setUserId("bogus@bogus.com"); 
				al.setAccountId(Integer.toUnsignedLong(2345));
				al.setActivity("activity");
				al.setAction("action");
				al.setOutcome("outcome"); 
				al.setNote("note");

	        	         
				activityLogRepository.saveAndFlush(al);

			}			
			//this also sets the id field of the above activitylog record and returns the value to this object			

			
		    //checking findByAccountID
		    List<ActivityLog> activityLogList= activityLogRepository.findByAccountId(Integer.toUnsignedLong(2345));
		    
		    ActivityLog found = activityLogList.get(0);
	     
	        //the datetime is stored as a datetime, but converted to a timestamp when persisted to mySQL.
	        //upon retrieval, it is now a timestamp even though it is in a date field.
	        
	        Date ts = found.getDateTime();

	        
	        // then
//	        assertThat(found.getId()).isEqualTo(al.getId());
	        assertThat(found.getDateTime().compareTo(al.getDateTime()));
	        assertThat(found.getUserId()).isEqualTo(al.getUserId());
	        assertThat(found.getAccountId()).isEqualTo(al.getAccountId());
	        assertThat(found.getActivity()).isEqualTo(al.getActivity());
	        assertThat(found.getAction()).isEqualTo(al.getAction());
	        assertThat(found.getOutcome()).isEqualTo(al.getOutcome());
	        assertThat(found.getNote()).isEqualTo(al.getNote());


	        //there should be four records in the activity log
	        Integer inty = new Integer(activityLogList.size());
	        assertThat(inty.compareTo(4));
	        
	    }
	    
}
