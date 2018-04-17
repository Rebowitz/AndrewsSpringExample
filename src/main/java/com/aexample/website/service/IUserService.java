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
	
	//User Account

    UserAccount registerNewUserAccount(UserDto accountDto) throws UserAlreadyExistsException;

    void deleteUser(UserAccount user);
    
    UserAccount findUserByEmail(String email);    
  
    UserAccount getUserByIDLongPrimitive(long id);

    void changeUserPassword(UserAccount user, String password);    

    boolean checkIfValidOldPassword(UserAccount user, String password);    

    List<String> getUsersFromSessionRegistry();
    
	UserAccount saveOrUpdate(UserAccount user);

	UserAccount getUserByIdLongObject(Long long1);

	void deleteUserByLongPrimitive(long id);
	
	void deleteUserByLongObject(Long id);  //autoboxing

	List<?> listAllUsers();
	    
    
    
    //UserVerificationToken

    void createVerificationTokenForUser(UserAccount user, String token);

    UserVerificationToken getVerificationToken(String VerificationToken);

    UserVerificationToken generateNewVerificationToken(String token);

    String validateVerificationToken(String token);    
    
	UserVerificationToken getPreviousToken(String token);
	
	UserVerificationToken findTokenByUser(UserAccount user);
	
    UserAccount getUserFromToken(String verificationToken);
	
	void deleteUserVerificationToken(UserVerificationToken userVerficationToken);   
    
    
    //PasswordResetToken

    void createPasswordResetTokenForUser(UserAccount user, String token, String newEncryptPassword);

    UserPasswordResetToken getPasswordResetToken(String token);
    
    UserPasswordResetToken generateNewPasswordResetToken(UserAccount user, String token, String newEncryptedPassword);
    
    String validateResetPasswordToken(String token);
 
    UserPasswordResetToken findPasswordResetTokenByUser(UserAccount user);
    
    UserAccount getUserByPasswordResetToken(String token);

    void deletePasswordResetToken(UserPasswordResetToken passwordResetToken);


    //util    
    
    String generateTokenValue();
    
	public String serviceInstantiated();




	
}
