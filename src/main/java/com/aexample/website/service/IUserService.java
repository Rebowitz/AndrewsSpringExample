/**
 *
 */
package com.aexample.website.service;

import java.sql.SQLException;

import com.aexample.persistence.model.Accounts;

import java.io.UnsupportedEncodingException;
import java.util.List;

import com.aexample.persistence.model.UserPasswordResetToken;
import com.aexample.persistence.model.User;
import com.aexample.persistence.model.UserVerificationToken;
import com.aexample.website.dto.UserDto;
import com.aexample.website.error.UserAlreadyExistsException;




/**
 * @author RBA
 *
 */
public interface IUserService
{

    User registerNewUserAccount(UserDto accountDto) throws UserAlreadyExistsException;

    User getUser(String verificationToken);

    void saveRegisteredUser(User user);

    void deleteUser(User user);

    void createVerificationTokenForUser(User user, String token);

    UserVerificationToken getVerificationToken(String VerificationToken);

    UserVerificationToken generateNewVerificationToken(String token);

    void createPasswordResetTokenForUser(User user, String token);

    User findUserByEmail(String email);

    UserPasswordResetToken getPasswordResetToken(String token);

    User getUserByPasswordResetToken(String token);

    User getUserByID(long id);

    void changeUserPassword(User user, String password);

    boolean checkIfValidOldPassword(User user, String password);

    String validateVerificationToken(String token);

    String generateQRUrl(User user) throws UnsupportedEncodingException;

    User updateUser2FA(boolean use2FA);

    List<String> getUsersFromSessionRegistry();
    
	public String serviceInstantiated();    
	
/* old user methods hanging onto them for now	
	
	public Accounts isValidUser(String username, String password) throws SQLException;

		public Accounts findOne(Long id);

		public Long create(Accounts resource);
		
		public Long update(Accounts resource);
		
		public Long getById(Long id);
		
		public Long deleteById(Long id);
		
		public Accounts getByLoginId(String loginId);
		
		public String serviceInstantiated();
		
*/
	
}
