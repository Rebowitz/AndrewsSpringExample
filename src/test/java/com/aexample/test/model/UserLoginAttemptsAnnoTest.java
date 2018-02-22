/**
 * 
 */
package com.aexample.test.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

import org.junit.Assert;
import org.junit.Test;

import com.aexample.persistence.model.ActivityLog;
import com.aexample.persistence.model.UserLoginAttempts;

/**
 * @author Robert B. Andrews
 * $Rev$
 * $Date$
 *
 */
public class UserLoginAttemptsAnnoTest {

	  @Test
	  public void typeAnnotations() {
	    // assert
	    AssertAnnotations.assertType(
	        UserLoginAttempts.class, Entity.class, Table.class);
	  }
	  
	  @Test
	  public void fieldAnnotations() {
	    // assert
	    AssertAnnotations.assertField(UserLoginAttempts.class, "id", Id.class, GeneratedValue.class);
	    AssertAnnotations.assertField(UserLoginAttempts.class, "userId", NotNull.class);
	    AssertAnnotations.assertField(UserLoginAttempts.class, "attempts", NotNull.class,Max.class);
	    AssertAnnotations.assertField(UserLoginAttempts.class, "lastModified", Column.class, NotNull.class, Temporal.class);		    
	    AssertAnnotations.assertField(UserLoginAttempts.class, "dateCreated", Column.class, NotNull.class, Temporal.class);	
	  }
	  
	  @Test
	  public void methodAnnotations() {
	    // assert
	    AssertAnnotations.assertMethod(UserLoginAttempts.class, "getId");
	    AssertAnnotations.assertMethod(UserLoginAttempts.class, "getUserId");
	    AssertAnnotations.assertMethod(UserLoginAttempts.class, "getAttempts");
	    AssertAnnotations.assertMethod(UserLoginAttempts.class, "getLastModified");
	    AssertAnnotations.assertMethod(UserLoginAttempts.class, "getDateCreated");
	  }
	  
	  @Test
	  public void entity() {
	    // setup
	    Entity a
	    = ReflectTool.getClassAnnotation(UserLoginAttempts.class, Entity.class);
	    // assert
	    Assert.assertEquals("", a.name());
	  }
	  
	  @Test
	  public void table() {
	    // setup
	    Table t
	    = ReflectTool.getClassAnnotation(UserLoginAttempts.class, Table.class);
	    // assert
	    Assert.assertEquals("userLoginAttempts", t.name());
	  }
	  
	  @Test
	  public void id() {
	    // setup
	    GeneratedValue a
	    = ReflectTool.getFieldAnnotation(
	        UserLoginAttempts.class, "id", GeneratedValue.class);
	    // assert
	    Assert.assertEquals("", a.generator());
	    Assert.assertEquals(GenerationType.IDENTITY, a.strategy());

	    
	  }
	  
	  
	  @Test
	  public void lastModified() {
	    // setup
	    Column c
	    = ReflectTool.getFieldAnnotation(
	    		UserLoginAttempts.class, "lastModified", Column.class);
	    // assert
	    Assert.assertEquals("lastModified", c.name());
	    Assert.assertEquals("DATETIME", c.columnDefinition());
	    Temporal t = ReflectTool.getFieldAnnotation(UserLoginAttempts.class, "lastModified", Temporal.class);
	    Assert.assertEquals(TemporalType.TIMESTAMP,t.value());
	  }
	  
	  
	  @Test
	  public void dateCreated() {
	    // setup
	    Column c
	    = ReflectTool.getFieldAnnotation(
	    		UserLoginAttempts.class, "dateCreated", Column.class);
	    // assert
	    Assert.assertEquals("dateCreated", c.name());
	    Assert.assertEquals("DATETIME", c.columnDefinition());
	    Temporal t = ReflectTool.getFieldAnnotation(UserLoginAttempts.class, "dateCreated", Temporal.class);
	    Assert.assertEquals(TemporalType.TIMESTAMP,t.value());
	  }	
	  
	  @Test 
	  public void attempts(){
		  Max max =
				  ReflectTool.getFieldAnnotation(UserLoginAttempts.class, "attempts", Max.class);
		  Assert.assertEquals(5, max.value());
		  
	  }
	  
}
