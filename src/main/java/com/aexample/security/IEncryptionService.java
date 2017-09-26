/**
 * 
 */
package com.aexample.security;

/**
 * @author Main Login
 * $Rev$
 * $Date$
 *
 */
public interface IEncryptionService {
    String encryptString(String input);
    boolean checkPassword(String plainPassword, String encryptedPassword);
}
