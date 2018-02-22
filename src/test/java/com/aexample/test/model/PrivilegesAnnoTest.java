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
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.junit.Assert;
import org.junit.Test;

import com.aexample.persistence.model.ActivityLog;
import com.aexample.persistence.model.Privileges;

/**
 * @author Robert B. Andrews
 * $Rev$
 * $Date$
 *
 */
public class PrivilegesAnnoTest {

	  @Test
	  public void typeAnnotations() {
	    // assert
	    AssertAnnotations.assertType(
	        Privileges.class, Entity.class, Table.class);
	  }
	  
	  @Test
	  public void fieldAnnotations() {
	    // assert
	    AssertAnnotations.assertField(Privileges.class, "id", Id.class, GeneratedValue.class);
	    AssertAnnotations.assertField(Privileges.class, "name", NotNull.class);
	    AssertAnnotations.assertField(Privileges.class, "roles", ManyToMany.class);
	  }
	  
	  @Test
	  public void methodAnnotations() {
	    // assert
	    AssertAnnotations.assertMethod(Privileges.class, "getId");
	    AssertAnnotations.assertMethod(Privileges.class, "getName");
	    AssertAnnotations.assertMethod(Privileges.class, "getRoles");	  	    
	  }
	  
	  @Test
	  public void entity() {
	    // setup
	    Entity a
	    = ReflectTool.getClassAnnotation(Privileges.class, Entity.class);
	    // assert
	    Assert.assertEquals("", a.name());
	  }
	  
	  @Test
	  public void table() {
	    // setup
	    Table t
	    = ReflectTool.getClassAnnotation(Privileges.class, Table.class);
	    // assert
	    Assert.assertEquals("privileges", t.name());
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
	  public void roles() {
	    // setup
	    ManyToMany a
	    = ReflectTool.getFieldAnnotation(
	        Privileges.class, "roles", ManyToMany.class);
	    // assert
	    Assert.assertEquals("privileges", a.mappedBy());
	  }

}
