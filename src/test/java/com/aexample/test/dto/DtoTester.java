/**
 * 
 */
package com.aexample.test.dto;


import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.IOUtils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.aexample.annotations.ILogger;
import com.aexample.test.BaseTest;
import com.aexample.test.pojo.AbstractPOJOTester;
import com.aexample.website.dto.EmailDto;
import com.aexample.website.dto.EmailOnlyDto;
import com.aexample.website.dto.PasswordDto;
import com.aexample.website.dto.RegistrationDto;
import com.aexample.website.dto.UserDto;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;



/**
 * @author Robert B. Andrews
 * $Rev$
 * $Date$
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
public class DtoTester extends BaseTest{
	private static @ILogger Logger logger;

	
	@SuppressWarnings("unchecked")
	@Test
	public void TestDtos() throws JsonParseException, JsonMappingException, IOException, ClassNotFoundException{
		Resource resource = new ClassPathResource("dto_test_configs.json");
	
		//ORDER IS EmailDto, EmailOnlyDto, PasswordDto, RegistrationDto, UserDto in json file
		
			AbstractPOJOTester apt = new AbstractPOJOTester();
			InputStream inputStream = resource.getInputStream();			
			StringWriter writer = new StringWriter();
			IOUtils.copy(inputStream, writer, "UTF-8");
			String theString = writer.toString();
			
			Gson gson = new Gson();
			Type pojoType = new TypeToken<List<HashMap<String,Object>>>(){}.getType();
			
//			System.out.println(theString);
			List<HashMap<String, Object>> listOfDtos =  gson.fromJson(theString, pojoType);
			
			//if this works, we then just iterate over the list of dtos in an abstract fashion
			
			
			Iterator iter = listOfDtos.iterator();
			
			
			while(iter.hasNext()){
				HashMap<String, Object> pojoMap = (HashMap<String, Object>) iter.next();
			
			Class classname = Class.forName((String) pojoMap.get("className")); 
			
			apt.testThePOJO(classname, pojoMap);		

			}
		
	}
}