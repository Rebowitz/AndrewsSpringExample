/**
 * 
 */
package com.aexample.test.config;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.io.IOUtils;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.aexample.persistence.model.Privileges;
import com.aexample.persistence.model.Roles;
import com.aexample.persistence.model.UserAccount;
import com.aexample.persistence.repositories.IActivityLogRepository;
import com.aexample.persistence.repositories.IPrivilegeRepository;
import com.aexample.persistence.repositories.IRoleRepository;
import com.aexample.persistence.repositories.IUserRepository;
import com.aexample.security.IEncryptionService;
import com.aexample.test.BaseTest;
import com.aexample.test.pojo.AbstractPOJOTester;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javassist.bytecode.Descriptor.Iterator;

/**
 * @author Robert B. Andrews
 * $Rev$
 * $Date$
 *
 */



@RunWith(SpringRunner.class)
//@DataJpaTest  -- use H2 in memory database
@ContextConfiguration(classes={AexampleTestConfiguration.class,PersistenceJPAConfigTest.class})
public class TestDatabasePrep extends BaseTest{

    @Autowired
    DataSource dataSource;
    
    @Autowired
    private IUserRepository userRepository;
    
    @Autowired
    IRoleRepository roleRepository;
  
    @Autowired
    private IPrivilegeRepository privilegeRepository;
  
	@Autowired
	private IActivityLogRepository activityLogRepository;
	/**
	 * @throws SQLException 
	 * 
	 */

	public TestDatabasePrep() throws SQLException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Test
	@Transactional
	public void dropCreateTables() throws SQLException, IOException{

		
		Connection conn = dataSource.getConnection();
		
		Resource resource = new ClassPathResource("resetDbTables.sql");
	
		InputStream inputStream = resource.getInputStream();

	try {
		// Initialize object for ScripRunner
		ScriptRunner sr = new ScriptRunner(conn);

		// Give the input file to Reader
		Reader reader = new InputStreamReader(inputStream);

		// Exctute script
		sr.runScript(reader);

	} catch (Exception e) {
		System.err.println("Failed to Execute resetDbTables.sql"
				+ " The error is " + e.getMessage());
	}
}
	
	@Test
	@Transactional
	public void cleanTheDbs() throws IOException{
	
		Connection conn = null;
		
		Resource resource = new ClassPathResource("resetDbsConfig.json");
	
		InputStream inputStream = resource.getInputStream();
		StringWriter writer = new StringWriter();
		IOUtils.copy(inputStream, writer, "UTF-8");
		String theString = writer.toString();
	
		Gson gson = new Gson();
		Type pojoType = new TypeToken<List<HashMap<String, String>>>() {
		}.getType();

		// System.out.println(theString);
		List<HashMap<String, String>> listOfSqls = gson.fromJson(theString, pojoType);
		
		HashMap<String, String> thesqlMap = listOfSqls.get(0);
		
		for(String key: thesqlMap.keySet()){
				
		   	try {
				conn = dataSource.getConnection();
				String sqlStatement = thesqlMap.get(key);
				PreparedStatement ps = conn.prepareStatement(sqlStatement);
				ps.execute();
				ps.close();
			
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		
		}
	}		
	
}
