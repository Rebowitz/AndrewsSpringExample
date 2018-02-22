/**
 * 
 */
package com.aexample.persistence.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author Main Login
 * $Rev$
 * $Date$
 *
 */
import com.aexample.persistence.model.Privileges;

public interface IPrivilegeRepository extends JpaRepository<Privileges, Long> {

	/* (non-Javadoc)
	 * @see com.aexample.persistence.repositories.IBaseRepository#delete(com.aexample.persistence.model.Privileges)
	 */
	//@Override
	default void delete(Privileges privileges) {
		// TODO Auto-generated method stub
		
	}

    @Query("SELECT p from Privileges p WHERE p.name = :name")
	Privileges findByName(@Param("name") String name);


}
