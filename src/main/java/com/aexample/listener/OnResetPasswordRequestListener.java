/**
 * 
 */
package com.aexample.listener;

import org.springframework.context.ApplicationListener;

import com.aexample.event.OnResetPasswordRequestEvent;

/**
 * @author Main Login
 * $Rev$
 * $Date$
 *
 */
public class OnResetPasswordRequestListener implements ApplicationListener<OnResetPasswordRequestEvent>{

	/* (non-Javadoc)
	 * @see org.springframework.context.ApplicationListener#onApplicationEvent(org.springframework.context.ApplicationEvent)
	 */
	@Override
	public void onApplicationEvent(OnResetPasswordRequestEvent event) {
		// TODO Auto-generated method stub
		
	}

}
