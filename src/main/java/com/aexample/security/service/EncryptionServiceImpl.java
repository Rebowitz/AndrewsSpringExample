/**
 * 
 */
package com.aexample.security.service;

import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aexample.security.IEncryptionService;

/**
 * @author Main Login
 * $Rev$
 * $Date$
 *
 */
@Service
public class EncryptionServiceImpl implements IEncryptionService {
 
    private StrongPasswordEncryptor strongEncryptor;
    
    @Autowired
    public void setStrongEncryptor(StrongPasswordEncryptor strongEncryptor) {
        this.strongEncryptor = strongEncryptor;
    }
 
    public String encryptString(String input){
        return strongEncryptor.encryptPassword(input);
    }
 
    public boolean checkPassword(String plainPassword, String encryptedPassword){
        return strongEncryptor.checkPassword(plainPassword, encryptedPassword);
    }
}
