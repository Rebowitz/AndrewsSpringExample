/**
 * 
 */
package com.aexample.website.exception;

/**
 * @author Main Login
 * $Rev$
 * $Date$
 *
 */
public class RegistrationFailedException extends RuntimeException {

    private static final long serialVersionUID = 5861310537366287163L;

    public RegistrationFailedException() {
        super();
    }

    public RegistrationFailedException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public RegistrationFailedException(final String message) {
        super(message);
    }

    public RegistrationFailedException(final Throwable cause) {
        super(cause);
    }

}
