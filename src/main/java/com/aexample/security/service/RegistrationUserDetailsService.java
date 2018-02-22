/**
 * 
 */
package com.aexample.security.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.aexample.persistence.model.Privileges;
import com.aexample.persistence.model.Roles;
import com.aexample.persistence.model.UserAccount;
import com.aexample.persistence.repositories.IRoleRepository;
import com.aexample.persistence.repositories.IUserRepository;

/**
 * @author Main Login
 * $Rev$
 * $Date$
 *
 */
//created as a bean with the name registrationUserDetailsService in spring config
//for configuration readability in that file
//@service annotation not necessary here.
public class RegistrationUserDetailsService implements UserDetailsService {
 
    private IUserRepository userRepository;
  
    private IRoleRepository roleRepository;
 
    @Override
    public UserDetails loadUserByUsername(String email)
      throws UsernameNotFoundException {
  
        UserAccount user = userRepository.findByEmail(email);
        if (user == null) {
            return new org.springframework.security.core.userdetails.User(
              " ", " ", true, true, true, true, 
              getAuthorities(Arrays.asList(
                roleRepository.findByName("ROLE_USER"))));
        }
 
        return new org.springframework.security.core.userdetails.User(
          user.getEmail(), user.getEncryptedPassword(), user.getEnabled(), true, true, 
          true, getAuthorities(user.getRoles()));
    }
 
    private Collection<? extends GrantedAuthority> getAuthorities(
      Collection<Roles> roles) {
  
        return getGrantedAuthorities(getPrivileges(roles));
    }
 
    private List<String> getPrivileges(Collection<Roles> roles) {
  
        List<String> privileges = new ArrayList<>();
        List<Privileges> collection = new ArrayList<>();
        for (Roles rolexs : roles) {
            collection.addAll(rolexs.getPrivileges());
        }
        for (Privileges item : collection) {
            privileges.add(item.getName());
        }
        return privileges;
    }
 
    private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String privilege : privileges) {
            authorities.add(new SimpleGrantedAuthority(privilege));
        }
        return authorities;
    }
}