/**
 * 
 */
package com.aexample.security;

import java.util.Date;

import com.aexample.persistence.model.UserLoginAttempts;

/**
 * @author Main Login
 * $Rev$
 * $Date$
 *
 */
public interface IUserLoginAttemptsService {
	
//	void addUserLoginAttempts(String email, Date date);
	String userUpdateFailedLoginAttempts(String email, Date date);
	UserLoginAttempts findByEmail(String email);
	void clearFailedLoginAttempts(String email, Date date);
//	boolean theUserExists(String email);
	void delete(UserLoginAttempts deleted);

}
