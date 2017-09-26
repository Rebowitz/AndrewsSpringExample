/**
 * 
 */
package com.aexample.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Main Login
 * $Rev$
 * $Date$
 *
 */

import com.aexample.persistence.model.Role;

public interface IRoleRepository extends JpaRepository<Role, Long> {


	Role findByName(String name);

  //  @Override
    void delete(Role role);

	/**
	 * @param id
	 * @return
	 */

    
    

}
