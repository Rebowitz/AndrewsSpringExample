/**
 *
 */
package com.aexample.website.service;

import java.util.List;

import com.aexample.persistence.model.UserAccount;
import com.aexample.persistence.model.UserPasswordResetToken;
import com.aexample.persistence.model.UserVerificationToken;
import com.aexample.website.dto.UserDto;
import com.aexample.website.exception.UserAlreadyExistsException;


/**
 * @author RBA
 *
 */
public interface IUserService{	

    UserAccount registerNewUserAccount(UserDto accountDto) throws UserAlreadyExistsException;

    UserAccount getUser(String verificationToken);

    void saveRegisteredUser(UserAccount user);

    void deleteUser(UserAccount user);
    
    String generateTokenValue();

    void createVerificationTokenForUser(UserAccount user, String token);

    UserVerificationToken getVerificationToken(String VerificationToken);

    UserVerificationToken generateNewVerificationToken(String token);

    void createPasswordResetTokenForUser(UserAccount user, String token);

    UserAccount findUserByEmail(String email);

    UserPasswordResetToken getPasswordResetToken(String token);

    UserAccount getUserByPasswordResetToken(String token);

    UserAccount getUserByID(long id);

    void changeUserPassword(UserAccount user, String password);

    boolean checkIfValidOldPassword(UserAccount user, String password);

    String validateVerificationToken(String token);


    List<String> getUsersFromSessionRegistry();
    
	public String serviceInstantiated();

	void delete(Long id);

	UserAccount saveOrUpdate(UserAccount user);

	UserAccount getById(Integer id);

	void delete(Integer id);

	List<?> listAll();    
	
}
