/**
 * 
 */
package com.aexample.persistence.dao;

import java.io.Serializable;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.config.BeanDefinition;

/**
 * @author Main Login
 * $Rev$
 * $Date$
 *
 */
@Repository
@Scope( BeanDefinition.SCOPE_PROTOTYPE )
public class GenericJPADao< T extends Serializable >
 extends AbstractJPADao< T > implements IGenericDao< T >{
   //
}
