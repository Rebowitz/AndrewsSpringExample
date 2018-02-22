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

import com.aexample.persistence.model.UserRoles;

/**
 * @author Robert B. Andrews
 * $Rev$
 * $Date$
 *
 */
public class UserRolesAnnoTest {
	  @Test
	  public void typeAnnotations() {
	    // assert
	    AssertAnnotations.assertType(
	        UserRoles.class, Entity.class, Table.class);
	  }
	  
	  @Test
	  public void fieldAnnotations() {
	    // assert
	    AssertAnnotations.assertField(UserRoles.class, "user_Id", Id.class, NotNull.class);
	    AssertAnnotations.assertField(UserRoles.class, "role_Id", NotNull.class);
	  }
	  
	  @Test
	  public void methodAnnotations() {
	    // assert
	    AssertAnnotations.assertMethod(UserRoles.class, "getUser_Id");
	    AssertAnnotations.assertMethod(UserRoles.class, "getRole_Id");
	  }
	  
	  @Test
	  public void entity() {
	    // setup
	    Entity a
	    = ReflectTool.getClassAnnotation(UserRoles.class, Entity.class);
	    // assert
	    Assert.assertEquals("", a.name());
	  }
	  
	  @Test
	  public void table() {
	    // setup
	    Table t
	    = ReflectTool.getClassAnnotation(UserRoles.class, Table.class);
	    // assert
	    Assert.assertEquals("user_roles", t.name());
	  }


}
