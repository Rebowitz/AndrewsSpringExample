/**
 * 
 */
package com.aexample.persistence.repositories.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;


import com.aexample.persistence.repositories.IRegistrationRepository;

/**
 * @author Main Login
 * $Rev$
 * $Date$
 *
 */
@Repository
public abstract class RegistryRepositoryImpl implements IRegistrationRepository{

	private static final Logger logger = LoggerFactory.getLogger(RegistryRepositoryImpl.class);

	 public RegistryRepositoryImpl(){
		 super();
		 logger.info("Initializing RegistryRepositoryImpl.class");

	}

}
