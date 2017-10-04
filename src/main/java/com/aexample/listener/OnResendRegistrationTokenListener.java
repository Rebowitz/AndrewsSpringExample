/**
 * 
 */
package com.aexample.listener;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;

import com.aexample.annotations.ILogger;
import com.aexample.email.CustomHTMLMailer;
import com.aexample.event.OnResendRegistrationTokenEvent;
import com.aexample.persistence.model.UserAccount;

/**
 * @author Main Login
 * $Rev$
 * $Date$
 *
 */
public class OnResendRegistrationTokenListener implements ApplicationListener<OnResendRegistrationTokenEvent>{
	
	@Autowired
	CustomHTMLMailer customHTMLMailer;

//	private final Logger logger = LoggerFactory.getLogger(getClass());
	private static @ILogger Logger logger;	
	
	@Override
	public void onApplicationEvent(OnResendRegistrationTokenEvent event) {
		logger.debug("OnResendRegistrationTokenEvent routine fired off");
		
		
		//custommailer2
		String configname = "RESENDREGTOKEN";
		
		final UserAccount user = event.getUser();
		String theUrl = event.getAppUrl();
		
		String response = customHTMLMailer.rockinTheEmailMessage(user, configname, theUrl);
				
		
		logger.debug(response);
	}

}
