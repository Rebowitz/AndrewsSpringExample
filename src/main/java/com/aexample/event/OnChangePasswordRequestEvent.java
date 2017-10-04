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
public class OnChangePasswordRequestEvent extends ApplicationEvent {

	/**
	 * @param source
	 */
	public OnChangePasswordRequestEvent(Object source) {
		super(source);
		// TODO Auto-generated constructor stub
	}

}
