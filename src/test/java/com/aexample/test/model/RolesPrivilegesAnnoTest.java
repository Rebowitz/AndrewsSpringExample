/**
 * 
 */
package com.aexample.test.model;


import javax.persistence.Entity;
import javax.persistence.Table;

import org.junit.Assert;
import org.junit.Test;

import com.aexample.persistence.model.ActivityLog;
import com.aexample.persistence.model.Roles;
import com.aexample.persistence.model.RolesPrivileges;

/**
 * @author Robert B. Andrews
 * $Rev$
 * $Date$
 *
 */
public class RolesPrivilegesAnnoTest {
	
	  @Test
	  public void typeAnnotations() {
	    // assert
	    AssertAnnotations.assertType(
	        ActivityLog.class, Entity.class, Table.class);
	  }
	  
	  
	
	  @Test
	  public void fieldAnnotations() {
	    // assert
	    AssertAnnotations.assertField(RolesPrivileges.class, "role_id");
	    AssertAnnotations.assertField(RolesPrivileges.class, "privilege_id");
	  }
	  
	  @Test
	  public void methodAnnotations() {
	    // assert
	    AssertAnnotations.assertMethod(RolesPrivileges.class, "getRole_id");
	    AssertAnnotations.assertMethod(RolesPrivileges.class, "getPrivilege_id");
	  }
/*	  
	  @Test
	  public void entity() {
	    // setup
	    Entity a
	    = ReflectTool.getClassAnnotation(RolesPrivileges.class, Entity.class);
	    // assert
	    Assert.assertEquals("", a.name());
	  }
	  
	  @Test
	  public void table() {
	    // setup
	    Table t
	    = ReflectTool.getClassAnnotation(RolesPrivileges.class, Table.class);
	    // assert
	    Assert.assertEquals("roles_privileges", t.name());
	  }
*/	  
}
