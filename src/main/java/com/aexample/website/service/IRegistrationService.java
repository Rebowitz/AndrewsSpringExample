/**
 * 
 */
package com.aexample.website.service;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.stereotype.Component;

import com.aexample.persistence.model.AccountPasswordResetToken;
import com.aexample.persistence.model.Accounts;
import com.aexample.persistence.model.UserPasswordResetToken;
import com.aexample.persistence.model.RegistrationVerificationToken;
import com.aexample.website.error.AccountAlreadyExistsException;
import com.aexample.website.error.UserAlreadyExistsException;
import com.aexample.website.viewBean.RegistrationBean;



/**
 * @author Robert B. Andrews
 * $Rev$
 * $Date$
 *
 */
public interface IRegistrationService {
/*
    Accounts findOne(long id);

    List<Accounts> findAll();

    String create(RegistrationBean entity);

    Accounts update(Accounts entity);

    void delete(Accounts entity);

    void deleteById(long entityId);
    
	public void manualIntervention();

	
*/	

	
	String serviceInstantiated();
	
    Accounts registerNewAccount(RegistrationBean regBean) throws AccountAlreadyExistsException;

	Accounts getAccount(String verificationToken);

 //using create above   
 //   void saveRegisteredUser(User user);

   //using delete above
 //   void deleteUser(User user);

    void createVerificationTokenForAccount(Accounts account, String token);

    RegistrationVerificationToken getVerificationToken(String VerificationToken);

    RegistrationVerificationToken generateNewVerificationToken(String token);

    void createPasswordResetTokenForAccount(Accounts account, String token);

    Accounts findAccountByEmail(String email);

    AccountPasswordResetToken getPasswordResetToken(String token);

    Accounts getAccountByPasswordResetToken(String token);

    Accounts getAccountByID(long id);

    void changeAccountPassword(Accounts account, String password);

    boolean checkIfValidOldPassword(Accounts account, String password);

    String validateVerificationToken(String token);

    String generateQRUrl(Accounts account) throws UnsupportedEncodingException;

    Accounts updateAccount2FA(boolean use2FA);

    List<String> getAccountFromSessionRegistry();

	void saveRegisteredAccount(Accounts account);

//	void deleteAccount(Accounts account);



	
	
	
	
}
