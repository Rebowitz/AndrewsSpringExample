package com.aexample.security;

import java.util.Arrays;
import java.util.Calendar;

import org.springframework.transaction.annotation.Transactional;

import com.aexample.persistence.model.UserPasswordResetToken;
import com.aexample.persistence.model.User;
import com.aexample.persistence.repositories.IUserPasswordResetTokenRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserSecurityService implements ISecurityRegService {

    @Autowired
    private IUserPasswordResetTokenRepository passwordTokenRepository;

    @Autowired
    private UserDetailsService userDetailsService;

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

        final User user = passToken.getUser();
        final Authentication auth = new UsernamePasswordAuthenticationToken(user, null, Arrays.asList(new SimpleGrantedAuthority("CHANGE_PASSWORD_PRIVILEGE")));
        SecurityContextHolder.getContext()
            .setAuthentication(auth);
        return null;
    }

}