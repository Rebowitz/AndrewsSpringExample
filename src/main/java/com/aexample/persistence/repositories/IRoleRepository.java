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

import com.aexample.persistence.model.Roles;

public interface IRoleRepository extends JpaRepository<Roles, Long> {


	Roles findByName(String name);

  //  @Override
    void delete(Roles roles);

	/**
	 * @param id
	 * @return
	 */

    
    

}
