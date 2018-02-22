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

import com.aexample.persistence.model.TransactionLog;
import com.aexample.persistence.repositories.ITransactionLogRepository;
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
public class TransactionLogRepositoryTest extends BaseTest{
	 
	    @Autowired
	    private ITransactionLogRepository transactionLogRepository;
	 
	    // write test cases here
        TransactionLog transLog = null;
        
	    @Test
	    public void transactionLogRepostiorySaveRetrieve() {
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
				transLog = new TransactionLog();
	//			al.setId(Integer.toUnsignedLong(2345));  this is an autoincrement field
				transLog.setDateTime(dateTime); 
				transLog.setUserId("bogus@bogus.com"); 
				transLog.setAccountId(Integer.toUnsignedLong(2345));
				transLog.setActivity("activity");
				transLog.setAction("action");
				transLog.setOutcome("outcome"); 
				transLog.setNote("note");

	        	         
				transactionLogRepository.saveAndFlush(transLog);

			}			
			//this also sets the id field of the above activitylog record and returns the value to this object			

			
		    //checking findByAccountID
		    List<TransactionLog> transactionLogList= transactionLogRepository.findByAccountId(Integer.toUnsignedLong(2345));
		    
		    TransactionLog found = transactionLogList.get(0);
	     
	        //the datetime is stored as a datetime, but converted to a timestamp when persisted to mySQL.
	        //upon retrieval, it is now a timestamp even though it is in a date field.
	        
	        Date ts = found.getDateTime();

	        
	        // then
//	        assertThat(found.getId()).isEqualTo(al.getId());
	        assertThat(found.getDateTime().compareTo(transLog.getDateTime()));
	        assertThat(found.getUserId()).isEqualTo(transLog.getUserId());
	        assertThat(found.getAccountId()).isEqualTo(transLog.getAccountId());
	        assertThat(found.getActivity()).isEqualTo(transLog.getActivity());
	        assertThat(found.getAction()).isEqualTo(transLog.getAction());
	        assertThat(found.getOutcome()).isEqualTo(transLog.getOutcome());
	        assertThat(found.getNote()).isEqualTo(transLog.getNote());


	        //there should be four records in the activity log
	        Integer inty = new Integer(transactionLogList.size());
	        assertThat(inty.compareTo(4));
	        
	    }
	    
}
