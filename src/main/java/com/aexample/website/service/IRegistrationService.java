/**
 * 
 */
package com.aexample.website.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.aexample.persistence.model.Accounts;



/**
 * @author Robert B. Andrews
 * $Rev$
 * $Date$
 *
 */
@Component
public interface IRegistrationService {

    Accounts findOne(long id);

    List<Accounts> findAll();

    String create(Accounts entity);

    Accounts update(Accounts entity);

    void delete(Accounts entity);

    void deleteById(long entityId);
    
	public void manualIntervention();
	
	String serviceInstantiated();
	
	
	
	
	
}
