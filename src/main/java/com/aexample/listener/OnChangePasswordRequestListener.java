/**
 * 
 */
package com.aexample.listener;

import org.springframework.context.ApplicationListener;

import com.aexample.event.OnChangePasswordRequestEvent;

/**
 * @author Main Login
 * $Rev$
 * $Date$
 *
 */
public class OnChangePasswordRequestListener implements ApplicationListener<OnChangePasswordRequestEvent>{

	/* (non-Javadoc)
	 * @see org.springframework.context.ApplicationListener#onApplicationEvent(org.springframework.context.ApplicationEvent)
	 */
	@Override
	public void onApplicationEvent(OnChangePasswordRequestEvent event) {
		// TODO Auto-generated method stub
		
	}

}
