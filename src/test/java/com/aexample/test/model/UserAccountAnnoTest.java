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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.junit.Assert;
import org.junit.Test;

import com.aexample.persistence.model.Roles;
import com.aexample.persistence.model.UserAccount;

/**
 * @author Robert B. Andrews
 * $Rev$
 * $Date$
 *
 */
public class UserAccountAnnoTest {

	@Test
	  public void typeAnnotations() {
	    // assert
	    AssertAnnotations.assertType(
	        UserAccount.class, Entity.class, Table.class);
	  }
	  
	  @Test
	  public void fieldAnnotations() {
	    // assert
	    AssertAnnotations.assertField(UserAccount.class, "id", Id.class, GeneratedValue.class);
	    AssertAnnotations.assertField(UserAccount.class, "accountNonExpired", NotNull.class);
	    AssertAnnotations.assertField(UserAccount.class, "accountNonLocked", NotNull.class);
	    AssertAnnotations.assertField(UserAccount.class, "createDate", Column.class, NotNull.class, Temporal.class);	    
	    AssertAnnotations.assertField(UserAccount.class, "credentialsNonExpired", NotNull.class);
	    AssertAnnotations.assertField(UserAccount.class, "deviceId");
	    AssertAnnotations.assertField(UserAccount.class, "email", NotNull.class);
	    AssertAnnotations.assertField(UserAccount.class, "enabled", NotNull.class);
	    AssertAnnotations.assertField(UserAccount.class, "encryptedPassword", NotNull.class);
	    AssertAnnotations.assertField(UserAccount.class, "firstName", NotNull.class);
	    AssertAnnotations.assertField(UserAccount.class, "lastName", NotNull.class);
	    AssertAnnotations.assertField(UserAccount.class, "secret");
	  }
	  
	  @Test
	  public void methodAnnotations() {
	    // assert
	    AssertAnnotations.assertMethod(UserAccount.class, "getId");
	    AssertAnnotations.assertMethod(UserAccount.class, "getCreateDate");
	    AssertAnnotations.assertMethod(UserAccount.class, "getAccountNonLocked");
	    AssertAnnotations.assertMethod(UserAccount.class, "getAccountNonExpired");
	    AssertAnnotations.assertMethod(UserAccount.class, "getCredentialsNonExpired");
	    AssertAnnotations.assertMethod(UserAccount.class, "getDeviceId");
	    AssertAnnotations.assertMethod(UserAccount.class, "getEmail");
	    AssertAnnotations.assertMethod(UserAccount.class, "getEnabled");
	    AssertAnnotations.assertMethod(UserAccount.class, "getEncryptedPassword");
	    AssertAnnotations.assertMethod(UserAccount.class, "getFirstName");
	    AssertAnnotations.assertMethod(UserAccount.class, "getLastName");
	    AssertAnnotations.assertMethod(UserAccount.class, "getSecret");
	  }
	  
	  @Test
	  public void entity() {
	    // setup
	    Entity a
	    = ReflectTool.getClassAnnotation(UserAccount.class, Entity.class);
	    // assert
	    Assert.assertEquals("", a.name());
	  }
	  
	  @Test
	  public void table() {
	    // setup
	    Table t
	    = ReflectTool.getClassAnnotation(UserAccount.class, Table.class);
	    // assert
	    Assert.assertEquals("userAccount", t.name());
	  }
	  
	  @Test
	  public void id() {
	    // setup
	    GeneratedValue a
	    = ReflectTool.getFieldAnnotation(
	        UserAccount.class, "id", GeneratedValue.class);
	    // assert
	    Assert.assertEquals("", a.generator());
	    Assert.assertEquals(GenerationType.IDENTITY, a.strategy());
  
	    
	  }
	  
	  @Test
	  public void dateTime() {
	    // setup
	    Column c
	    = ReflectTool.getFieldAnnotation(
	        UserAccount.class, "createDate", Column.class);
	    // assert
	    Assert.assertEquals("createDate", c.name());
	    Assert.assertEquals("DATETIME", c.columnDefinition());
	    Temporal t = ReflectTool.getFieldAnnotation(UserAccount.class, "createDate", Temporal.class);
	    Assert.assertEquals(TemporalType.TIMESTAMP,t.value());
	  }
	  
	  @Test
	  public void user_roles() {
	    // setup
	    ManyToMany a
	    = ReflectTool.getFieldAnnotation(
	        UserAccount.class, "roles", ManyToMany.class);
	    // assert
	    Assert.assertEquals(FetchType.EAGER, a.fetch());

	 /* Hibernate annotations do not provide the info necessary for this test
	    Cascade cas
	    = ReflectTool.getFieldAnnotation(
	        UserAccount.class, "roles", Cascade.class);
	    // assert
	  //  Assert.assertEquals(CascadeType.SAVE_UPDATE,cas.);    
	*/    
	    JoinTable jt
	    = ReflectTool.getFieldAnnotation(UserAccount.class, "roles", JoinTable.class);
	    Assert.assertEquals("user_roles", jt.name());
	     JoinColumn[] jc = jt.joinColumns();
	     JoinColumn[] injc = jt.inverseJoinColumns();
	    
	     Assert.assertEquals("user_id", jc[0].name());
	     Assert.assertEquals("id", jc[0].referencedColumnName());	     
	    
	     Assert.assertEquals("role_id", injc[0].name());
	     Assert.assertEquals("id", injc[0].referencedColumnName());		     
	    
	  }
}
