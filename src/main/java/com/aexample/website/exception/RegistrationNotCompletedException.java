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
public class RegistrationNotCompletedException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String userId;
	private String errMsg;

	/**
	 * @param username
	 * @param string
	 */
	public RegistrationNotCompletedException(String userId, String errMsg) {
		this.userId = userId;
		this.errMsg = errMsg;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

}
