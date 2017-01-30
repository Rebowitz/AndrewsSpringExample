package com.aexample.registration;

import java.util.Locale;

import com.aexample.persistence.model.Accounts;
import org.springframework.context.ApplicationEvent;


@SuppressWarnings("serial")
public class OnRegistrationCompleteEvent extends ApplicationEvent {

    private final String appUrl;
    private final Locale locale;
    private final Accounts account;

    public OnRegistrationCompleteEvent(final Accounts account, final Locale locale, final String appUrl) {
        super(account);
        this.account = account;
        this.locale = locale;
        this.appUrl = appUrl;
    }

    //

    public String getAppUrl() {
        return appUrl;
    }

    public Locale getLocale() {
        return locale;
    }

    public Accounts getUser() {
        return account;
    }

}
