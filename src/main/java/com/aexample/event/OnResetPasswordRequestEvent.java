/**
 * 
 */
package com.aexample.event;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEvent;

import com.aexample.persistence.model.UserAccount;

/**
 * @author Main Login
 * $Rev$
 * $Date$
 *
 */
@SuppressWarnings("serial")
public class OnResetPasswordRequestEvent extends ApplicationEvent {

	/*use of the annotation injection of the logger throws 
	 * a null pointer exception in the ApplicationEvent Context  
	 */
	private final Logger logger = LoggerFactory.getLogger(getClass());
//	private static @ILogger Logger logger;	
	
    private final String appUrl;
    private final Locale locale;
    private final UserAccount user;
    private final String resetPasswordToken;
    
	public OnResetPasswordRequestEvent(final UserAccount user, final Locale locale, final String appUrl, final String resetPasswordToken) {
		super(user);
        this.user = user;
        this.locale = locale;
        this.appUrl = appUrl;
        this.resetPasswordToken = resetPasswordToken;
		// TODO Auto-generated constructor stub
	}
    //

    public String getAppUrl() {
        return appUrl;
    }

    public Locale getLocale() {
        return locale;
    }

    public UserAccount getUser() {
        return user;
    }
	
	public String getResetPasswordToken(){
		return resetPasswordToken;
	}
	
}
