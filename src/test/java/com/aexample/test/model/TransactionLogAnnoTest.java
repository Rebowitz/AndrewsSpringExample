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
import javax.validation.constraints.NotNull;

import org.junit.Assert;
import org.junit.Test;

import com.aexample.persistence.model.TransactionLog;

/**
 * @author Robert B. Andrews
 * $Rev$
 * $Date$
 *
 */
public class TransactionLogAnnoTest {
	  @Test
	  public void typeAnnotations() {
	    // assert
	    AssertAnnotations.assertType(
	        TransactionLog.class, Entity.class, Table.class);
	  }
	  
	  @Test
	  public void fieldAnnotations() {
	    // assert
	    AssertAnnotations.assertField(TransactionLog.class, "id", Id.class, GeneratedValue.class);
	    AssertAnnotations.assertField(TransactionLog.class, "accountId", NotNull.class);
	    AssertAnnotations.assertField(TransactionLog.class, "action", NotNull.class);
	    AssertAnnotations.assertField(TransactionLog.class, "dateTime", Column.class, NotNull.class, Temporal.class);	    
	    AssertAnnotations.assertField(TransactionLog.class, "outcome", NotNull.class);
	    AssertAnnotations.assertField(TransactionLog.class, "note", NotNull.class);
	    AssertAnnotations.assertField(TransactionLog.class, "userId", NotNull.class);	    
	  }
	  
	  @Test
	  public void methodAnnotations() {
	    // assert
	    AssertAnnotations.assertMethod(TransactionLog.class, "getId");
	    AssertAnnotations.assertMethod(TransactionLog.class, "getDateTime");
	    AssertAnnotations.assertMethod(TransactionLog.class, "getAccountId");
	    AssertAnnotations.assertMethod(TransactionLog.class, "getUserId");
	    AssertAnnotations.assertMethod(TransactionLog.class, "getAction");
	    AssertAnnotations.assertMethod(TransactionLog.class, "getOutcome");
	    AssertAnnotations.assertMethod(TransactionLog.class, "getNote");
	  }
	  
	  @Test
	  public void entity() {
	    // setup
	    Entity a
	    = ReflectTool.getClassAnnotation(TransactionLog.class, Entity.class);
	    // assert
	    Assert.assertEquals("", a.name());
	  }
	  
	  @Test
	  public void table() {
	    // setup
	    Table t
	    = ReflectTool.getClassAnnotation(TransactionLog.class, Table.class);
	    // assert
	    Assert.assertEquals("transactionLog", t.name());
	  }
	  
	  @Test
	  public void id() {
	    // setup
	    GeneratedValue a
	    = ReflectTool.getFieldAnnotation(
	        TransactionLog.class, "id", GeneratedValue.class);
	    // assert
	    Assert.assertEquals("", a.generator());
	    Assert.assertEquals(GenerationType.IDENTITY, a.strategy());
    
	    
	  }
	  
	  @Test
	  public void dateTime() {
	    // setup
	    Column c
	    = ReflectTool.getFieldAnnotation(
	        TransactionLog.class, "dateTime", Column.class);
	    // assert
	    Assert.assertEquals("dateTime", c.name());
	    Assert.assertEquals("DATETIME", c.columnDefinition());
	    Temporal t = ReflectTool.getFieldAnnotation(TransactionLog.class, "dateTime", Temporal.class);
	    Assert.assertEquals(TemporalType.TIMESTAMP,t.value());
	  }

}
