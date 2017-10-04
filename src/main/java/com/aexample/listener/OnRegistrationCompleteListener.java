/**
 * 
 */
package com.aexample.listener;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.aexample.annotations.ILogger;
import com.aexample.email.CustomHTMLMailer;
import com.aexample.event.OnRegistrationCompleteEvent;
import com.aexample.persistence.model.UserAccount;

@Component
public class OnRegistrationCompleteListener implements ApplicationListener<OnRegistrationCompleteEvent> {
	
	@Autowired
	CustomHTMLMailer customHTMLMailer;

//	private final Logger logger = LoggerFactory.getLogger(getClass());
	private static @ILogger Logger logger;	
	
	// API
	@Override
	public void onApplicationEvent(final OnRegistrationCompleteEvent event) {
		logger.debug("OnRegistrationCompleteEvent routine fired off");
		
		
		//custommailer2
		String configname = "REGCONFIRM";
		
		final UserAccount user = event.getUser();
		String theUrl = event.getAppUrl();
		
		String response = customHTMLMailer.rockinTheEmailMessage(user, configname, theUrl);
				
		
		logger.debug(response);
	}	

}