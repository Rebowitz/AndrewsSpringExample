/**
 * 
 */
package com.aexample.website.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Main Login
 * $Rev$
 * $Date$
 *
 */
import com.aexample.persistence.model.Role;
import com.aexample.persistence.repositories.IRoleRepository;
import com.aexample.website.service.IRoleService;

//@Profile("springdatajpa")
@Service
@Transactional
public class RoleServiceImpl implements IRoleService {
 
    private IRoleRepository roleRepository;
 
  
    public void setRoleRepository(IRoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
 
    @Override
    public List<Role> listAll() {
        List<Role> roles = new ArrayList<>();
        roleRepository.findAll().forEach(roles::add);
        return roles;
    }
 
 
    @Override
    public Role saveOrUpdate(Role domainObject) {
        return roleRepository.save(domainObject);
    }

	/* (non-Javadoc)
	 * @see com.aexample.website.service.IRoleService#getById(java.lang.Integer)
	 */
	@Override
	public Role getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.aexample.website.service.IRoleService#delete(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}
 

}