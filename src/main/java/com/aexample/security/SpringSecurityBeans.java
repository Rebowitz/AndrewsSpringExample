/**
 * 
 */
package com.aexample.security;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.aexample.security.service.RegistrationUserDetailsService;
import com.aexample.security.service.UserSecurityService;

/**
 * @author Main Login
 * $Rev$
 * $Date$
 *
 */
@Configuration
public class SpringSecurityBeans {

	
	//used to validate user password reset token
	@Bean(name = "userSecurityService")
	public UserSecurityService userSecurityService() {
		return new UserSecurityService();
	}

	//used in registration controller
	@Bean(name = "registrationUserDetailsService")
	public RegistrationUserDetailsService registrationUserDetailsService() {
		return new RegistrationUserDetailsService();
	}

	// @Bean(name="springSecurityUserDetailsService")
//	public CustomSpringSecurityUserDetailsService customSpringSecurityUserDetailsService() {
//		return new CustomSpringSecurityUserDetailsService();
//	}
/* class is annotated w/ @Services so this bean is not needed
	@Bean(name="userDetailsServiceImpl")
	public UserDetailsServiceImpl userDetailsServiceImpl() {
		return new UserDetailsServiceImpl();
	}	
*/	


	//used in password encoder
	@Bean(name="strongPasswordEncryptor")
	public StrongPasswordEncryptor strongPasswordEncryptor() {
		StrongPasswordEncryptor encryptor = new StrongPasswordEncryptor();
		return encryptor;
	}

	// used in multiple locations
	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver resolver = new SessionLocaleResolver();
		resolver.setDefaultLocale(Locale.US);
		return resolver;
	}

	//used in user controller
	@Bean
	public ActiveUserStore activeUserStore() {
		return new ActiveUserStore();
	}

	
	@Bean
	RememberMeServices rememberMeServices() {
		RememberMeServices rememberMeServices = new RememberMeServices() {

			@Override
			public void loginSuccess(HttpServletRequest arg0, HttpServletResponse arg1, Authentication arg2) {

			}

			@Override
			public void loginFail(HttpServletRequest arg0, HttpServletResponse arg1) {

			}

			@Override
			public Authentication autoLogin(HttpServletRequest arg0, HttpServletResponse arg1) {
				return null;
			}
		};
		return rememberMeServices;
	}
	
	
	
}
