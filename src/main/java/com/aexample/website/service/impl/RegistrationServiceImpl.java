/**
 * 
 */
package com.aexample.website.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aexample.persistence.dao.IRegistrationDao;
import com.aexample.persistence.model.Accounts;
import com.aexample.website.service.IRegistrationService;
/**
 * @author Robert B. Andrews
 * $Rev$
 * $Date$
 *
 */
@Service
@Transactional
public class RegistrationServiceImpl implements IRegistrationService {

	/* (non-Javadoc)
	 * @see com.aexample.website.service.IRegistrationService#isNewAccount()
	 */
	
	@Autowired
	IRegistrationDao regDao;
	
	public Accounts isNewAccount() {
		// TODO Auto-generated method stub
		return null;
	}


	/* (non-Javadoc)
	 * @see com.aexample.website.service.IRegistrationService#manualIntervention()
	 */
	@Override
	public void manualIntervention() {
		// TODO Auto-generated method stub
		
	}


	/* (non-Javadoc)
	 * @see com.aexample.website.service.IRegistrationService#findOne(long)
	 */
	@Override
	public Accounts findOne(long id) {
		// TODO Auto-generated method stub
		return null;
	}


	/* (non-Javadoc)
	 * @see com.aexample.website.service.IRegistrationService#findAll()
	 */
	@Override
	public List<Accounts> findAll() {
		// TODO Auto-generated method stub
		return null;
	}


	/* (non-Javadoc)
	 * @see com.aexample.website.service.IRegistrationService#create(com.orgify.persistence.model.Accounts)
	 */
	@Override
	public String create(Accounts entity) {
		// TODO Auto-generated method stub
		regDao.create(entity);
		return null;
	}


	/* (non-Javadoc)
	 * @see com.aexample.website.service.IRegistrationService#update(com.orgify.persistence.model.Accounts)
	 */
	@Override
	public Accounts update(Accounts entity) {
		// TODO Auto-generated method stub
		return null;
	}


	/* (non-Javadoc)
	 * @see com.aexample.website.service.IRegistrationService#delete(com.orgify.persistence.model.Accounts)
	 */
	@Override
	public void delete(Accounts entity) {
		// TODO Auto-generated method stub
		
	}


	/* (non-Javadoc)
	 * @see com.aexample.website.service.IRegistrationService#deleteById(long)
	 */
	@Override
	public void deleteById(long entityId) {
		// TODO Auto-generated method stub
		
	}
	
	public String serviceInstantiated(){
		return "Created";
		
	}

}
