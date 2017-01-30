/**
 * 
 */
package com.aexample.website.error;

/**
 * @author Main Login
 * $Rev$
 * $Date$
 *
 */
public final class AccountAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = 3936837393569580444L;

	public AccountAlreadyExistsException() {
        super();
    }

    public AccountAlreadyExistsException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public AccountAlreadyExistsException(final String message) {
        super(message);
    }

    public AccountAlreadyExistsException(final Throwable cause) {
        super(cause);
    }
	
}
