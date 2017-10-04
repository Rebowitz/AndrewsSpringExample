/**
 * 
 */
package com.aexample.event;

import org.springframework.context.ApplicationEvent;

/**
 * @author Main Login
 * $Rev$
 * $Date$
 *
 */
@SuppressWarnings("serial")
public class OnResetPasswordRequestEvent extends ApplicationEvent {

	/**
	 * @param source
	 */
	public OnResetPasswordRequestEvent(Object source) {
		super(source);
		// TODO Auto-generated constructor stub
	}

}
