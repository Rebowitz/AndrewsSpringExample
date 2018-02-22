/**
 * 
 */
package com.aexample.test.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.junit.Assert;
import org.junit.Test;

import com.aexample.persistence.model.ActivityLog;
import com.aexample.persistence.model.Roles;

/**
 * @author Robert B. Andrews
 * $Rev$
 * $Date$
 *
 */
public class RolesAnnoTest {

	  @Test
	  public void typeAnnotations() {
	    // assert
	    AssertAnnotations.assertType(
	        Roles.class, Entity.class, Table.class);
	  }
	  
	  @Test
	  public void fieldAnnotations() {
	    // assert
	    AssertAnnotations.assertField(Roles.class, "id", Id.class, GeneratedValue.class);
	    AssertAnnotations.assertField(Roles.class, "name", NotNull.class);
	    AssertAnnotations.assertField(Roles.class, "users", ManyToMany.class);
	    AssertAnnotations.assertField(Roles.class, "privileges", ManyToMany.class, JoinTable.class);
	  }
	  
	  @Test
	  public void methodAnnotations() {
	    // assert
	    AssertAnnotations.assertMethod(Roles.class, "getId");
	    AssertAnnotations.assertMethod(Roles.class, "getName");
	    AssertAnnotations.assertMethod(Roles.class, "getUsers");
	    AssertAnnotations.assertMethod(Roles.class, "getPrivileges");	    
	  }
	  
	  @Test
	  public void entity() {
	    // setup
	    Entity a
	    = ReflectTool.getClassAnnotation(Roles.class, Entity.class);
	    // assert
	    Assert.assertEquals("", a.name());
	  }
	  
	  @Test
	  public void table() {
	    // setup
	    Table t
	    = ReflectTool.getClassAnnotation(Roles.class, Table.class);
	    // assert
	    Assert.assertEquals("roles", t.name());
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
	  public void users() {
	    // setup
	    ManyToMany a
	    = ReflectTool.getFieldAnnotation(
	        Roles.class, "users", ManyToMany.class);
	    // assert
	    Assert.assertEquals("roles", a.mappedBy());
	  }	  
	  
	  
	  @Test
	  public void privileges() {
	    // setup
	    ManyToMany a
	    = ReflectTool.getFieldAnnotation(
	        Roles.class, "privileges", ManyToMany.class);
	    // assert
	    Assert.assertEquals("", a.mappedBy());
	    
	    JoinTable jt
	    = ReflectTool.getFieldAnnotation(Roles.class, "privileges", JoinTable.class);
	    Assert.assertEquals("roles_privileges", jt.name());
	     JoinColumn[] jc = jt.joinColumns();
	     JoinColumn[] injc = jt.inverseJoinColumns();
	    
	     Assert.assertEquals("role_id", jc[0].name());
	     Assert.assertEquals("id", jc[0].referencedColumnName());	     
	    
	     Assert.assertEquals("privilege_id", injc[0].name());
	     Assert.assertEquals("id", injc[0].referencedColumnName());		     
	    
	  }

}
