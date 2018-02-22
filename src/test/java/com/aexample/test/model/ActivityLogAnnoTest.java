/**
 * 
 */
package com.aexample.test.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.junit.Assert;
import org.junit.Test;
import com.aexample.test.model.AssertAnnotations;

import com.aexample.persistence.model.ActivityLog;

/**
 * @author Robert B. Andrews
 * $Rev$
 * $Date$
 *
 */
public class ActivityLogAnnoTest {

	  @Test
	  public void typeAnnotations() {
	    // assert
	    AssertAnnotations.assertType(
	        ActivityLog.class, Entity.class, Table.class);
	  }
	  
	  @Test
	  public void fieldAnnotations() {
	    // assert
	    AssertAnnotations.assertField(ActivityLog.class, "id", Id.class, GeneratedValue.class);
	    AssertAnnotations.assertField(ActivityLog.class, "dateTime", Column.class, NotNull.class, Temporal.class);
	    AssertAnnotations.assertField(ActivityLog.class, "accountId", NotNull.class);
	    AssertAnnotations.assertField(ActivityLog.class, "userId", NotNull.class);
	    AssertAnnotations.assertField(ActivityLog.class, "activity", NotNull.class);
	    AssertAnnotations.assertField(ActivityLog.class, "action", NotNull.class);
	    AssertAnnotations.assertField(ActivityLog.class, "outcome", NotNull.class);
	    AssertAnnotations.assertField(ActivityLog.class, "note", NotNull.class);	    
	  }
	  
	  @Test
	  public void methodAnnotations() {
	    // assert
	    AssertAnnotations.assertMethod(ActivityLog.class, "getId");
	    AssertAnnotations.assertMethod(ActivityLog.class, "getDateTime");
	    AssertAnnotations.assertMethod(ActivityLog.class, "getAccountId");
	    AssertAnnotations.assertMethod(ActivityLog.class, "getUserId");
	    AssertAnnotations.assertMethod(ActivityLog.class, "getActivity");
	    AssertAnnotations.assertMethod(ActivityLog.class, "getAction");
	    AssertAnnotations.assertMethod(ActivityLog.class, "getOutcome");
	    AssertAnnotations.assertMethod(ActivityLog.class, "getNote");
	  }
	  
	  @Test
	  public void entity() {
	    // setup
	    Entity a
	    = ReflectTool.getClassAnnotation(ActivityLog.class, Entity.class);
	    // assert
	    Assert.assertEquals("", a.name());
	  }
	  
	  @Test
	  public void table() {
	    // setup
	    Table t
	    = ReflectTool.getClassAnnotation(ActivityLog.class, Table.class);
	    // assert
	    Assert.assertEquals("activityLog", t.name());
	  }
	  
	  @Test
	  public void id() {
	    // setup
	    GeneratedValue a
	    = ReflectTool.getFieldAnnotation(
	        ActivityLog.class, "id", GeneratedValue.class);
	    // assert
	    Assert.assertEquals("", a.generator());
	    Assert.assertEquals(GenerationType.IDENTITY, a.strategy());
    
	    
	  }
	  
	  @Test
	  public void dateTime() {
	    // setup
	    Column c
	    = ReflectTool.getFieldAnnotation(
	        ActivityLog.class, "dateTime", Column.class);
	    // assert
	    Assert.assertEquals("dateTime", c.name());
	    Assert.assertEquals("DATETIME", c.columnDefinition());
	    Temporal t = ReflectTool.getFieldAnnotation(ActivityLog.class, "dateTime", Temporal.class);
	    Assert.assertEquals(TemporalType.TIMESTAMP,t.value());
	  }

	}
