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
public final class AccountsNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 5002155561441380021L;

	public AccountsNotFoundException() {
        super();
    }

    public AccountsNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public AccountsNotFoundException(final String message) {
        super(message);
    }

    public AccountsNotFoundException(final Throwable cause) {
        super(cause);
    }

}
