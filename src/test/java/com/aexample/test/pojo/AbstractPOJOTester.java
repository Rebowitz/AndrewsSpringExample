/**
 * 
 */
package com.aexample.test.pojo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.Map;

import org.springframework.test.util.ReflectionTestUtils;


/**
 * @author Main Login
 * $Rev$
 * $Date$
 *
 */

/*
 * To use this class, pass a map object with a string that is the name of the field in the pojo
 * and then the actual object such as String, Boolean, Long, Date that contains
 * the test value.  
 */
		
public class AbstractPOJOTester {

//	List<String> fieldList = new ArrayList<String>();
	
	public void testThePOJO(Class c, Map<String,Object> fieldList){
		Object plainPOJO = null;
		try {
			plainPOJO = c.newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		//testing the setter methods
		for(String key : fieldList.keySet()){
			Object value = fieldList.get(key);
			if(value instanceof String){
				ReflectionTestUtils.invokeSetterMethod(plainPOJO,key,fieldList.get(key),String.class);
			}else if(value instanceof Boolean){
				ReflectionTestUtils.invokeSetterMethod(plainPOJO,key,fieldList.get(key),Boolean.class);
			}else if(value instanceof Date){  //expecting java.sql.date
				ReflectionTestUtils.invokeSetterMethod(plainPOJO,key,fieldList.get(key),Date.class);				
			}else if(value instanceof Long){
				ReflectionTestUtils.invokeSetterMethod(plainPOJO,key,fieldList.get(key),Long.class);				
			}else{
				//oops!! fail assertion
				assertTrue(false);
			}
		}
		
		//testing the getter methods
		for(String key : fieldList.keySet()){
			Object fieldObj = fieldList.get(key);
			if(fieldObj instanceof String){
				assertEquals((String) fieldObj, ReflectionTestUtils.invokeGetterMethod(plainPOJO, key));
			}else if(fieldObj instanceof Boolean){
				assertEquals(((Boolean) fieldObj).booleanValue(),ReflectionTestUtils.invokeGetterMethod(plainPOJO,key));
			}else if(fieldObj instanceof Date){
				assertEquals((Date) fieldObj, ReflectionTestUtils.invokeGetterMethod(plainPOJO, key));
			}else if(fieldObj instanceof Long){
				assertEquals((Long) fieldObj, ReflectionTestUtils.invokeGetterMethod(plainPOJO, key));
			}else{
				assertTrue(false);
			}
			
		}
	}
	
	
}
