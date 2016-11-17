package com.aexample.persistence.dao.impl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.aexample.persistence.dao.AbstractHibernateDao;
import com.aexample.persistence.dao.IRegistrationDao;
import com.aexample.persistence.model.Accounts;

/**
 * @author Robert B. Andrews
 * $Rev$
 * $Date$
 *
 */

@Repository
public class RegistrationDaoImpl extends AbstractHibernateDao< Accounts > implements IRegistrationDao

{
	private static final Logger logger = LoggerFactory.getLogger(RegistrationDaoImpl.class);
  
	 public RegistrationDaoImpl(){
		 super();
		 System.out.println("Setting JPA Class to Accounts.class");
		setJpaClass( Accounts.class );
	}

	/* (non-Javadoc)
	 * @see com.aexample.persistence.dao.IRegistrationDao#isNewAccount()
	 */
	@Override
	public Boolean isNewAccount( ) {
		// TODO Auto-generated method stub
		Boolean nuAccount = true;
		return nuAccount;

	}

	/* (non-Javadoc)
	 * @see com.aexample.persistence.dao.IRegistrationDao#manualIntervention()
	 */
	@Override
	public void manualIntervention() {
		// TODO Auto-generated method stub
		
	}
	

}