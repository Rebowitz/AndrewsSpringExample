/**
 * 
 */
package com.aexample.website.validator;

/**
 * @author Main Login
 * $Rev$
 * $Date$
 *
 */
@SuppressWarnings("serial")
public class EmailExistsException extends Throwable {

    public EmailExistsException(final String message) {
        super(message);
    }

}
