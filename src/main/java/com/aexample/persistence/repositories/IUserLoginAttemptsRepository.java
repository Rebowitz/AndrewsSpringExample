/**
 * 
 */
package com.aexample.persistence.repositories;

import java.util.Date;



import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.aexample.persistence.model.UserLoginAttempts;

/**
 * @author Main Login
 * $Rev$
 * $Date$
 *
 */
@Transactional
public interface IUserLoginAttemptsRepository extends IBaseRepository<UserLoginAttempts, Long> {
	
	@Query("Select u from UserLoginAttempts u where u.userId= :email")
	UserLoginAttempts findByEmail(@Param("email") String email);
	
//	UserLoginAttempts findByEmail(String email);
	
    @Modifying
    @Transactional
    @Query("update UserLoginAttempts u set u.attempts = u.attempts + 1, lastModified = :date where u.userId = :email")
	void updateFailedLoginAttempts(@Param("email") String email, @Param("date") Date date);
	
    @Modifying
    @Transactional    
    @Query("update UserLoginAttempts u set u.attempts = 0, lastModified = :date where u.userId = :email")
	void clearFailedLoginAttempts(@Param("email") String email, @Param("date") Date date);
    
    //other syntax is "delete from userLoginAttempts t where t.email = ?1"
    //no @param is used in the method call  delete(String email)
    @Modifying
    @Transactional    
    @Query("delete from UserLoginAttempts u where u.userId = :email")
	void deleteByEmail(@Param("email") String email);
    
    @SuppressWarnings("unchecked")
    UserLoginAttempts save(UserLoginAttempts persisted);

}
