/**
 * 
 */
package com.aexample.test.model;

/**
 * @author Robert B. Andrews
 * $Rev$
 * $Date$
 *
 */

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import javax.transaction.Transactional;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import org.apache.commons.io.IOUtils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.aexample.annotations.ILogger;
import com.aexample.persistence.model.Privileges;
import com.aexample.persistence.model.Roles;
import com.aexample.persistence.model.UserAccount;
import com.aexample.test.BaseTest;
import com.aexample.test.pojo.AbstractPOJOTester;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * @author Robert B. Andrews $Rev$ $Date$
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
public class ModelTester extends BaseTest {
	
	private static @ILogger Logger logger;

	// due to the models supporting foreign key relationships, we make a few
	// objects on the side to support the test
	// roles and privilege collections.

	private UserAccount singleAccount;
	private Set<UserAccount> userAccountSet;
	private Collection<Privileges> privilegeCollection;
	private Collection<Roles> roleCollection;
	HashMap<String, Object> pojoForTest = new HashMap<String, Object>();


	@Test
	public void TestModels() throws JsonParseException, JsonMappingException, IOException, ClassNotFoundException {

		Resource resource = new ClassPathResource("model_test_config.json");

		// ORDER IS EmailDto, EmailOnlyDto, PasswordDto, RegistrationDto,
		// UserDto in json file

		AbstractPOJOTester apt = new AbstractPOJOTester();
		InputStream inputStream = resource.getInputStream();
		StringWriter writer = new StringWriter();
		IOUtils.copy(inputStream, writer, "UTF-8");
		String theString = writer.toString();

		Gson gson = new Gson();
		Type pojoType = new TypeToken<List<HashMap<String, Object>>>() {
		}.getType();

		// System.out.println(theString);
		List<HashMap<String, Object>> listOfDtos = gson.fromJson(theString, pojoType);

		// if this works, we then just iterate over the list of dtos in an
		// abstract fashion

		Iterator iter = listOfDtos.iterator();

		while (iter.hasNext()) {
			HashMap<String, Object> pojoMap = (HashMap<String, Object>) iter.next();

			Class classname = Class.forName((String) pojoMap.get("className"));
			pojoForTest = jsonInputFixer(pojoMap);

			apt.testThePOJO(classname, pojoForTest);

		}
	}

	private HashMap<String, Object> jsonInputFixer(HashMap<String, Object> inputMap) {

		String[] idfieldnames = { "id", "roleId", "accountId", "user_Id", "role_Id", "role_id", "privilege_id" };

		for (String keyName : idfieldnames) {
			if (inputMap.containsKey(keyName)) {
				Long idLong = new Long(1);
				inputMap.put(keyName, idLong);
				System.out.println("Key = " + keyName + ", Value = " + idLong.toString());
			}
		}
		
		if(inputMap.containsKey("attempts")){
			inputMap.put("attempts", new Integer(1));
		}

		
		String[] datefieldnames = { "dateTime", "createDate", "lastModified", "dateCreated", "lastUpdated",
				"expiryDate" };

		// the value for this key in the map is the data needs to be
		// converted to the date type for use in the test

		for (String keyName : datefieldnames) {

			if (inputMap.containsKey(keyName)) {
				// get the value of the key
				// convert it to date
				// replace value with the date object

				String jsonDateTime = (String) inputMap.get(keyName);
				DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy'T'HH:mm:ss");
				Date parsedDateTime = null;
				try {
					parsedDateTime = formatter.parse(jsonDateTime);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					logger.debug("Found date field but data does not parse correctly!");
				}

				inputMap.put(keyName, parsedDateTime);

			}

		} // endif dateTime

		return inputMap;
	}
}
