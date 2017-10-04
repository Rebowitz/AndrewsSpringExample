/**
 * 
 */
package com.aexample.listener;

import org.springframework.context.ApplicationListener;

import com.aexample.event.OnSavePasswordEvent;

/**
 * @author Main Login
 * $Rev$
 * $Date$
 *
 */
public class OnSavePasswordListener implements ApplicationListener<OnSavePasswordEvent>{

	/* (non-Javadoc)
	 * @see org.springframework.context.ApplicationListener#onApplicationEvent(org.springframework.context.ApplicationEvent)
	 */
	@Override
	public void onApplicationEvent(OnSavePasswordEvent event) {
		// TODO Auto-generated method stub
		
	}

}
