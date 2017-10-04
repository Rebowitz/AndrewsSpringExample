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
public class OnSavePasswordEvent extends ApplicationEvent {

	/**
	 * @param source
	 */
	public OnSavePasswordEvent(Object source) {
		super(source);
		// TODO Auto-generated constructor stub
	}

}
