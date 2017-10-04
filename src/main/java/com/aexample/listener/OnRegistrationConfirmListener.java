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
import com.aexample.event.OnRegistrationConfirmEvent;
import com.aexample.persistence.model.UserAccount;

/**
 * @author Main Login
 * $Rev$
 * $Date$
 *
 */
@Component
public class OnRegistrationConfirmListener implements ApplicationListener<OnRegistrationConfirmEvent>{
	
	@Autowired
	CustomHTMLMailer customHTMLMailer;

//	private final Logger logger = LoggerFactory.getLogger(getClass());
	private static @ILogger Logger logger;	
	
	@Override
	public void onApplicationEvent(OnRegistrationConfirmEvent event) {
		logger.debug("OnRegistrationConfirmEvent routine fired off");
		
		
		//custommailer2
		String configname = "WELCOME";
		
		final UserAccount user = event.getUser();
		String theUrl = event.getAppUrl();
		//theUrl will be similar to http://domainorip/website/user/registrationConfirm.html?token=[tokenvalue]
		String site = theUrl.substring(0, theUrl.indexOf("user/")) + "login";
		
		String response = customHTMLMailer.rockinTheEmailMessage(user, configname, site);
				
		
		logger.debug(response);
		
	}

}
