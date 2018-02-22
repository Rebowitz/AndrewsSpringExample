/**
 * 
 */
package com.aexample.test.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.junit.Assert;
import org.junit.Test;

import com.aexample.persistence.model.UserAccount;
import com.aexample.persistence.model.UserPasswordResetToken;

/**
 * @author Robert B. Andrews
 * $Rev$
 * $Date$
 *
 */
public class UserPasswordResetTokenAnnoTest {
	  @Test
	  public void typeAnnotations() {
	    // assert
	    AssertAnnotations.assertType(
	        UserPasswordResetToken.class, Entity.class, Table.class);
	  }
	  
	  @Test
	  public void fieldAnnotations() {
	    // assert
	    AssertAnnotations.assertField(UserPasswordResetToken.class, "id", Id.class, GeneratedValue.class);
	    AssertAnnotations.assertField(UserPasswordResetToken.class, "expiryDate", Column.class, NotNull.class, Temporal.class);
	    AssertAnnotations.assertField(UserPasswordResetToken.class, "token", NotNull.class);
	    AssertAnnotations.assertField(UserPasswordResetToken.class, "userId", NotNull.class);
	    AssertAnnotations.assertField(UserPasswordResetToken.class, "newEncryptPassword", NotNull.class);
	    AssertAnnotations.assertField(UserPasswordResetToken.class, "user", OneToOne.class, JoinColumn.class);	    
	  }
	  
	  @Test
	  public void methodAnnotations() {
	    // assert
	    AssertAnnotations.assertMethod(UserPasswordResetToken.class, "getId");
	    AssertAnnotations.assertMethod(UserPasswordResetToken.class, "getExpiryDate");
	    AssertAnnotations.assertMethod(UserPasswordResetToken.class, "getToken");
	    AssertAnnotations.assertMethod(UserPasswordResetToken.class, "getUserId");
	    AssertAnnotations.assertMethod(UserPasswordResetToken.class, "getNewEncryptPassword");
	  }
	  
	  @Test
	  public void entity() {
	    // setup
	    Entity a
	    = ReflectTool.getClassAnnotation(UserPasswordResetToken.class, Entity.class);
	    // assert
	    Assert.assertEquals("", a.name());
	  }
	  
	  @Test
	  public void table() {
	    // setup
	    Table t
	    = ReflectTool.getClassAnnotation(UserPasswordResetToken.class, Table.class);
	    // assert
	    Assert.assertEquals("userPasswordResetToken", t.name());
	  }
	  
	  @Test
	  public void id() {
	    // setup
	    GeneratedValue a
	    = ReflectTool.getFieldAnnotation(
	        UserPasswordResetToken.class, "id", GeneratedValue.class);
	    // assert
	    Assert.assertEquals("", a.generator());
	    Assert.assertEquals(GenerationType.IDENTITY, a.strategy());
  
	    
	  }
	  
	  @Test
	  public void expiryDate() {
	    // setup
	    Column c
	    = ReflectTool.getFieldAnnotation(
	        UserPasswordResetToken.class, "expiryDate", Column.class);
	    // assert
	    Assert.assertEquals("expiryDate", c.name());
	    Assert.assertEquals("DATETIME", c.columnDefinition());
	    Temporal t = ReflectTool.getFieldAnnotation(UserPasswordResetToken.class, "expiryDate", Temporal.class);
	    Assert.assertEquals(TemporalType.TIMESTAMP,t.value());
	  }

	  @Test
	  public void user(){
		  OneToOne oto =
				  ReflectTool.getFieldAnnotation(UserPasswordResetToken.class, "user", OneToOne.class);
		  Assert.assertEquals(FetchType.EAGER, oto.fetch());
		  JoinColumn c =
				  ReflectTool.getFieldAnnotation(UserPasswordResetToken.class, "user", JoinColumn.class);
		  	Assert.assertEquals(false, c.nullable());
		  	Assert.assertEquals("user_id", c.name());
	  }
	  
}
