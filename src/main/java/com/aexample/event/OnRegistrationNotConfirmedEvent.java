/**
 * 
 */
package com.aexample.event;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;

import com.aexample.persistence.model.UserAccount;
import com.aexample.security.IEncryptionService;
import com.aexample.website.service.IUserService;

/**
 * @author Main Login
 * $Rev$
 * $Date$
 *
 */
@SuppressWarnings("serial")
public class OnRegistrationNotConfirmedEvent extends ApplicationEvent {
	/*use of the annotation injection of the logger throws 
	 * a null pointer exception in the ApplicationEvent Context  
	 */
	
	IUserService userService;
	
	@Autowired
    public void setUserService(IUserService userService) {
        this.userService = userService;
    }	
	
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
//	private static @ILogger Logger logger;	
	
    private final String appUrl;
    private final Locale locale;
    private final UserAccount user;
    
	public OnRegistrationNotConfirmedEvent(final UserAccount aUuser, final Locale locale, final String appUrl) {
		super(aUuser);
		
        this.user = aUuser;
        this.locale = locale;
        this.appUrl = appUrl;
		// TODO Auto-generated constructor stub
	}	

    public String getAppUrl() {
        return appUrl;
    }

    public Locale getLocale() {
        return locale;
    }

    public UserAccount getUser() {
        return user;
    }

}
