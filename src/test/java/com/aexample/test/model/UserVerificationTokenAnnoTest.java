/**
 * 
 */
package com.aexample.test.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
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
import com.aexample.persistence.model.UserVerificationToken;

/**
 * @author Robert B. Andrews
 * $Rev$
 * $Date$
 *
 */
public class UserVerificationTokenAnnoTest {
	
	  @Test
	  public void typeAnnotations() {
	    // assert
	    AssertAnnotations.assertType(
	        UserVerificationToken.class, Entity.class, Table.class);
	  }
	  
	  @Test
	  public void fieldAnnotations() {
	    // assert
	    AssertAnnotations.assertField(UserVerificationToken.class, "id", Id.class, GeneratedValue.class);
	    AssertAnnotations.assertField(UserVerificationToken.class, "expiryDate", Column.class, NotNull.class, Temporal.class);
	    AssertAnnotations.assertField(UserVerificationToken.class, "previousToken", NotNull.class);
	    AssertAnnotations.assertField(UserVerificationToken.class, "token", NotNull.class);
	    AssertAnnotations.assertField(UserVerificationToken.class, "user", OneToOne.class, JoinColumn.class);
	  }
	  
	  @Test
	  public void methodAnnotations() {
	    // assert
	    AssertAnnotations.assertMethod(UserVerificationToken.class, "getId");
	    AssertAnnotations.assertMethod(UserVerificationToken.class, "getExpiryDate");
	    AssertAnnotations.assertMethod(UserVerificationToken.class, "getPreviousToken");
	    AssertAnnotations.assertMethod(UserVerificationToken.class, "getToken");
	    AssertAnnotations.assertMethod(UserVerificationToken.class, "getUser");
	  }
	  
	  @Test
	  public void entity() {
	    // setup
	    Entity a
	    = ReflectTool.getClassAnnotation(UserVerificationToken.class, Entity.class);
	    // assert
	    Assert.assertEquals("", a.name());
	  }
	  
	  @Test
	  public void table() {
	    // setup
	    Table t
	    = ReflectTool.getClassAnnotation(UserVerificationToken.class, Table.class);
	    // assert
	    Assert.assertEquals("userVerificationToken", t.name());
	  }
	  
	  @Test
	  public void id() {
	    // setup
	    GeneratedValue a
	    = ReflectTool.getFieldAnnotation(
	        UserVerificationToken.class, "id", GeneratedValue.class);
	    // assert
	    Assert.assertEquals("", a.generator());
	    Assert.assertEquals(GenerationType.IDENTITY, a.strategy());
  
	    
	  }
	  
	  @Test
	  public void expiryDate() {
	    // setup
	    Column c
	    = ReflectTool.getFieldAnnotation(
	        UserVerificationToken.class, "expiryDate", Column.class);
	    // assert
	    Assert.assertEquals("expiryDate", c.name());
	    Assert.assertEquals("DATETIME", c.columnDefinition());
	    Temporal t = ReflectTool.getFieldAnnotation(UserVerificationToken.class, "expiryDate", Temporal.class);
	    Assert.assertEquals(TemporalType.TIMESTAMP,t.value());
	  }
	  
	  @Test
	  public void user(){
		    //@OneToOne(targetEntity = UserAccount.class, fetch = FetchType.EAGER)
		    //@JoinColumn(nullable = false, name = "user_id", foreignKey = @ForeignKey(name = "FK_VERIFY_USER"))
		  
		  OneToOne oto =
				  ReflectTool.getFieldAnnotation(UserVerificationToken.class, "user", OneToOne.class);
		  Assert.assertEquals(UserAccount.class, oto.targetEntity());
		  Assert.assertEquals(FetchType.EAGER, oto.fetch());
		  
		  JoinColumn jc =
				  ReflectTool.getFieldAnnotation(UserVerificationToken.class, "user", JoinColumn.class);
		  Assert.assertEquals(false, jc.nullable());
		  Assert.assertEquals("user_id", jc.name());
		  ForeignKey fk = jc.foreignKey();
		  Assert.assertEquals("FK_VERIFY_USER", fk.name());
	  }

}
