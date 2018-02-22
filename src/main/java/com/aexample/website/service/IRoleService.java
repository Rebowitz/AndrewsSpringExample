/**
 * 
 */
package com.aexample.website.service;

import java.util.List;

import com.aexample.persistence.model.Roles;
import com.aexample.persistence.repositories.IRoleRepository;

/**
 * @author Main Login
 * $Rev$
 * $Date$
 *
 */

public interface IRoleService {
	
    public void setRoleRepository(IRoleRepository roleRepository);
 
    public List<Roles> listAll();
 
    public Roles getById(Integer id);
 
    public Roles saveOrUpdate(Roles domainObject);
 
    public void delete(Integer id);

}
