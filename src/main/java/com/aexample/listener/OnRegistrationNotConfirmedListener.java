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
import com.aexample.event.OnRegistrationEnteredEvent;
import com.aexample.event.OnRegistrationNotConfirmedEvent;
import com.aexample.persistence.model.UserAccount;

/**
 * @author Main Login
 * $Rev$
 * $Date$
 *
 */
@Component
public class OnRegistrationNotConfirmedListener implements ApplicationListener<OnRegistrationNotConfirmedEvent> {
	@Autowired
	CustomHTMLMailer customHTMLMailer;

//	private final Logger logger = LoggerFactory.getLogger(getClass());
	private static @ILogger Logger logger;	
	
	// API
	@Override
	public void onApplicationEvent(final OnRegistrationNotConfirmedEvent event) {
		logger.debug("OnRegistrationNotConfirmedEvent routine fired off");
		//custommailer2
		String configname = "REGCONFIRM";
		String theUrl = null;
		
		final UserAccount user = event.getUser();
		String appUrl = event.getAppUrl();
		for(String splitUrl:appUrl.split("website",1)){
			
			theUrl = splitUrl + "website/user/registrationConfirm.html";
			
		}
		
		String response = customHTMLMailer.rockinTheEmailMessage(user, configname, theUrl);
		logger.debug(response);			

	}
}
