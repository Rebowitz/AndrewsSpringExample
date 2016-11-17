/**
 * 
 */
package com.aexample.persistence.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.aexample.persistence.model.Accounts;

/**
 * @author Robert B. Andrews
 * $Rev$
 * $Date$
 * @param <T>
 *
 */
@Component
public interface IRegistrationDao
{
		Boolean isNewAccount();
		void manualIntervention();
		
		Accounts findOne(long id);
	    List<Accounts> findAll();
	    void create(Accounts entity);
	    Accounts update(Accounts entity);
	    void delete(Accounts entity);
	    void deleteById(long entityId);

}


