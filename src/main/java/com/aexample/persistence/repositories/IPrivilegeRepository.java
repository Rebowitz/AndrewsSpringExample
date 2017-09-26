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
import com.aexample.persistence.model.Privilege;

public interface IPrivilegeRepository extends JpaRepository<Privilege, Long> {

	/* (non-Javadoc)
	 * @see com.aexample.persistence.repositories.IBaseRepository#delete(com.aexample.persistence.model.Privilege)
	 */
	//@Override
	default void delete(Privilege privilege) {
		// TODO Auto-generated method stub
		
	}

	Privilege findByName(String name);


}
