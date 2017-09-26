/**
 * 
 */
package com.aexample.security.service;

/**
 * @author Main Login
 * $Rev$
 * $Date$
 *
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.aexample.persistence.model.UserAccount;
import com.aexample.website.service.IUserService;
 
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
 
    private IUserService userService;
    private Converter<UserAccount, UserDetails> userUserDetailsConverter;
 
    @Autowired
    public void setUserService(IUserService userService) {
        this.userService = userService;
    }
 
    @Autowired
    @Qualifier(value = "userToUserDetails")
    public void setUserUserDetailsConverter(Converter<UserAccount, UserDetails> userUserDetailsConverter) {
        this.userUserDetailsConverter = userUserDetailsConverter;
    }
 
    //Username is the email address in our system
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userUserDetailsConverter.convert(userService.findUserByEmail(username));
    }
}
