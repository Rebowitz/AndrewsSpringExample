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
import com.aexample.event.OnResetPasswordRequestEvent;
import com.aexample.persistence.model.UserAccount;

/**
 * @author Main Login
 * $Rev$
 * $Date$
 *
 */
@Component
public class OnResetPasswordRequestListener implements ApplicationListener<OnResetPasswordRequestEvent>{

	@Autowired
	CustomHTMLMailer customHTMLMailer;

//	private final Logger logger = LoggerFactory.getLogger(getClass());
	private static @ILogger Logger logger;	
	
	@Override
	public void onApplicationEvent(final OnResetPasswordRequestEvent event) {
		logger.debug("OnResendRegistrationTokenEvent routine fired off");
		
		
		//custommailer2
		String configname = "RESETPASSWORD";
		String theUrl = null;
		
		final UserAccount user = event.getUser();
		String appUrl = event.getAppUrl();
		String resetPasswordToken = event.getResetPasswordToken();
		for(String splitUrl:appUrl.split("website",2)){
			
			//build url to the same point as it would be
			//during the original registration request
			//this is necessary to build a correct url for the email
			theUrl = splitUrl + "website/user/resetPasswordToken";
			
			//split 1 did not work and returned the whole string
			//so we split twice and just break after the first loop
			break;
		}
		
		String response = customHTMLMailer.rockinTheEmailMessage2(user, configname, theUrl, resetPasswordToken);
		logger.debug(response);			
	}

}
