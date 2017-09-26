package com.aexample.security.service;

import java.util.Arrays;
import java.util.Calendar;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import com.aexample.persistence.model.UserAccount;
import com.aexample.persistence.model.UserPasswordResetToken;
import com.aexample.persistence.repositories.IUserPasswordResetTokenRepository;
import com.aexample.security.ISecurityUserService;

public class UserSecurityService implements ISecurityUserService {

    private IUserPasswordResetTokenRepository passwordTokenRepository;

    // API

    @Override
    public String validatePasswordResetToken(long id, String token) {
        final UserPasswordResetToken passToken = passwordTokenRepository.findByToken(token);
        if ((passToken == null) || (passToken.getUser()
            .getId() != id)) {
            return "invalidToken";
        }

        final Calendar cal = Calendar.getInstance();
        if ((passToken.getExpiryDate()
            .getTime() - cal.getTime()
            .getTime()) <= 0) {
            return "expired";
        }

        final UserAccount user = passToken.getUser();
        final Authentication auth = new UsernamePasswordAuthenticationToken(user, null, Arrays.asList(new SimpleGrantedAuthority("CHANGE_PASSWORD_PRIVILEGE")));
        SecurityContextHolder.getContext()
            .setAuthentication(auth);
        return null;
    }

}