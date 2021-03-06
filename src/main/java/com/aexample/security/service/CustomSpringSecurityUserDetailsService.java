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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aexample.persistence.model.Privileges;
import com.aexample.persistence.model.Roles;
import com.aexample.persistence.model.UserAccount;
import com.aexample.persistence.repositories.IUserRepository;


//Transactional annotation to prevent lazy initialization of the role repository
//that results in an error for dbase access outside of session
@Service("customSpringSecurityUserDetailsService")
@Transactional
public class CustomSpringSecurityUserDetailsService implements UserDetailsService {
	
	Logger logger = LoggerFactory.getLogger(CustomSpringSecurityUserDetailsService.class);
//	private static @ILogger Logger logger;	
	
	@Autowired
	private IUserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		try {
			final UserAccount user = userRepository.findByEmail(email);
			if (user == null) {
				throw new UsernameNotFoundException("No user found with username: " + email);
			}

			logger.debug(user.getEmail());
			logger.debug(user.getFirstName());
			logger.debug(user.getLastName());
					
			
			return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getEncryptedPassword(),
					user.getEnabled(), user.getAccountNonExpired(),user.getCredentialsNonExpired(),
					user.getAccountNonLocked(),getAuthorities(user.getRoles()));
		
	
	}catch(final NullPointerException npe){
			logger.debug("Email used as a USER ID is: " + email);
			logger.debug(npe.getMessage());
			throw new RuntimeException(npe);
		}catch (final Exception e) {
			throw new RuntimeException(e);
		}
		
	}

	// UTIL

	private final Collection<? extends GrantedAuthority> getAuthorities(final Collection<Roles> roles) {
		return getGrantedAuthorities(getPrivileges(roles));
	}

	private final List<String> getPrivileges(final Collection<Roles> roles) {
		final List<String> privileges = new ArrayList<String>();
		final List<Privileges> collection = new ArrayList<Privileges>();
		for (final Roles rolexs : roles) {
			collection.addAll(rolexs.getPrivileges());
		}
		for (final Privileges item : collection) {
			privileges.add(item.getName());
		}

		return privileges;
	}

	private final List<GrantedAuthority> getGrantedAuthorities(final List<String> privileges) {
		final List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for (final String privilege : privileges) {
			authorities.add(new SimpleGrantedAuthority(privilege));
		}
		return authorities;
	}

/*	private final String getClientIP() {
	
  		final String xfHeader = request.getHeader("X-Forwarded-For");  //returned null, threw NPE
  		if (xfHeader == null) {
			return request.getRemoteAddr();
		}
		return xfHeader.split(",")[0];
		
	}
*/
}
