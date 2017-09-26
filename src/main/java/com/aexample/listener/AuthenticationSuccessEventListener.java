package com.aexample.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

import com.aexample.security.service.UserLoginAttemptsServiceImpl;

@Component
public class AuthenticationSuccessEventListener implements ApplicationListener<AuthenticationSuccessEvent> {

    @Autowired
    private UserLoginAttemptsServiceImpl userLoginAttemptsServiceImpl;

    @Override
    public void onApplicationEvent(final AuthenticationSuccessEvent e) {
        final WebAuthenticationDetails auth = (WebAuthenticationDetails) e.getAuthentication().getDetails();
        if (auth != null) {
//            userLoginAttemptsServiceImpl.loginSucceeded(auth.getRemoteAddress());
        }
    }

}
