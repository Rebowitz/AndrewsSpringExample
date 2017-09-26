/**
 * 
 */
package com.aexample.website.service;

import java.util.List;

import com.aexample.persistence.model.Role;
import com.aexample.persistence.repositories.IRoleRepository;

/**
 * @author Main Login
 * $Rev$
 * $Date$
 *
 */

public interface IRoleService {
	
    public void setRoleRepository(IRoleRepository roleRepository);
 
    public List<Role> listAll();
 
    public Role getById(Integer id);
 
    public Role saveOrUpdate(Role domainObject);
 
    public void delete(Integer id);

}
