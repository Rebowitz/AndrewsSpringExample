/**
 * 
 */
package com.aexample.security.service;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Main Login
 * $Rev$
 * $Date$
 *
 */

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.aexample.persistence.model.UserAccount;

@Component
public class UserToUserDetails implements Converter<UserAccount, UserDetails> {
    @Override
    public UserDetails convert(UserAccount user) {
        UserDetailsImpl userDetails = new UserDetailsImpl();
 
        if (user != null) {
            userDetails.setUsername(user.getEmail());
            userDetails.setPassword(user.getEncryptedPassword());
            userDetails.setEnabled(user.getEnabled());
            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            user.getRoles().forEach(role -> {
                authorities.add(new SimpleGrantedAuthority(role.getName()));
            });
            userDetails.setAuthorities(authorities);
        }
 
        return userDetails;
    }
}
