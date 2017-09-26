package com.aexample.spring.config;

import org.jasypt.springsecurity3.authentication.encoding.PasswordEncoder;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import com.aexample.handler.CustomAccessDeniedHandler;
import com.aexample.handler.CustomAuthenticationFailureHandler;
import com.aexample.handler.MyLogoutSuccessHandler;
import com.aexample.handler.SiteAuthenticationSuccessHandler;
import com.aexample.security.CustomWebAuthenticationDetailsSource;
import com.aexample.security.LimitLoginAttemptsAuthenticationProvider;
import com.aexample.security.service.CustomSpringSecurityUserDetailsService;

@Configuration
@ComponentScan("com.aexample.security")
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	Logger logger = LoggerFactory.getLogger(SpringSecurityConfig.class);
	
	@Override
	public void configure(final WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**");
	}

	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		// @formatter:off
		// csrf enabled by default
		http.authorizeRequests()
				.antMatchers("/login*", "/logout*", "/signin/**", "/signup/**", "/user/registration*",
						"/expiredAccount*", "/badUser*", "/user/resendRegistrationToken*", "/forgetPassword*",
						"/user/resetPassword*", "/user/changePassword*", "/emailError*", "/successRegister*",
						"/registrationCompleted*")
				.permitAll().antMatchers("/admin/**").access("hasRole('ADMIN')").antMatchers("/invalidSession*")
				.anonymous().antMatchers("/user/updatePassword*", "/user/savePassword*", "/updatePassword*")
				.hasAuthority("CHANGE_PASSWORD_PRIVILEGE")
				// .anyRequest().hasAuthority("READ_PRIVILEGE")
				.anyRequest().authenticated()
				.and()
					.formLogin()
					.loginPage("/login")
					.loginProcessingUrl("/j_spring_security_check")
					.defaultSuccessUrl("/dashboard.html")
					//using .failureUrl will override using the failurehandler configured below
					//we also set the failureUrl in the handler
					.failureUrl("/login?error=true")  
					.usernameParameter("email")
					.passwordParameter("password")
		//			.successHandler(siteAuthenticationSuccessHandler())
		//			.failureHandler(authenticationFailureHandler())
					//.authenticationDetailsSource(authenticationDetailsSource())
				
				.permitAll()

				.and()
					.logout()
					.logoutUrl("/logout.html")
		//			.logoutSuccessHandler(myLogoutSuccessHandler())
					.invalidateHttpSession(true)
					.logoutSuccessUrl("/logout.html?logSucc=true")
					.deleteCookies("JSESSIONID")
					.permitAll()
				.and()
					.exceptionHandling()
		//			.accessDeniedHandler(accessDeniedHandler())
				.and()
					.csrf()
				.and()
					.sessionManagement()
					.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
					.invalidSessionUrl("/invalidSession.html")
					.maximumSessions(1);
		// @formatter:on

	}

	/*
	 * Importing UserDetailsServiceImpl and
	 * setting it as the UserDetailsService
	 */

    @Autowired
    private AuthenticationSuccessHandler myAuthenticationSuccessHandler;
    
    @Autowired
    private LogoutSuccessHandler myLogoutSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    private CustomWebAuthenticationDetailsSource authenticationDetailsSource;
    
    @Autowired
    CustomSpringSecurityUserDetailsService customSpringSecurityUserDetailsService;
    
    @Autowired
    StrongPasswordEncryptor strongPasswordEncryptor;

	//////////////////////////
	
	private AuthenticationProvider authenticationProvider;
	
	@Bean
	public PasswordEncoder passwordEncoder(StrongPasswordEncryptor passwordEncryptor) {
		PasswordEncoder passwordEncoder = new PasswordEncoder();
		passwordEncoder.setPasswordEncryptor(passwordEncryptor);
		logger.debug("Setting passwordEncoder");
		if(passwordEncryptor == null)
			logger.debug("StrongPasswordEncryptor is NULL!!!!");
		return passwordEncoder;
	}	
/*	
	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		logger.debug("Setting daoAuthenticationProvider configuration");
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder(strongPasswordEncryptor));
		daoAuthenticationProvider.setUserDetailsService(customSpringSecurityUserDetailsService);
		return daoAuthenticationProvider;
	}
*/
	

	@Bean
	public LimitLoginAttemptsAuthenticationProvider limitLoginAttemptsAuthenticationProvider() {

		LimitLoginAttemptsAuthenticationProvider limitLoginAttemptsAuthenticationProvider = new LimitLoginAttemptsAuthenticationProvider();
		limitLoginAttemptsAuthenticationProvider.setPasswordEncoder(passwordEncoder(strongPasswordEncryptor));
		limitLoginAttemptsAuthenticationProvider.setUserDetailsService(customSpringSecurityUserDetailsService);
		return limitLoginAttemptsAuthenticationProvider;
	}

	
	/*
	 * this method populates the AuthMgrBuilder which makes j_spring_security_check url work
	 * the authprovider is wired above and the daoAuthprovider configured below along
	 * with injected classes that provide functionality
	 */

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		logger.debug("Setting AuthenticationManagerBuilder");	
		
		auth.authenticationProvider(limitLoginAttemptsAuthenticationProvider());
		if(authenticationProvider==null)
			logger.debug("AuthenticationProvider is NULL!!!!");	
	}

	//used by login controller, login attempts handling
	@Bean(name = "siteAuthenticationSuccessHandler")
	public SiteAuthenticationSuccessHandler siteAuthenticationSuccessHandler() {
		return new SiteAuthenticationSuccessHandler();
	}
	
	//handles login failure, login attempts handling
	@Bean(name = "authenticationFailureHandler")
	public CustomAuthenticationFailureHandler authenticationFailureHandler() {
		return new CustomAuthenticationFailureHandler();
	}

	//handles logout redirect
	@Bean(name = "myLogoutSuccessHandler")
	public MyLogoutSuccessHandler myLogoutSuccessHandler() {
		return new MyLogoutSuccessHandler();
	}

	//redirect to /403 page
	@Bean(name = "accessDeniedHandler")
	public CustomAccessDeniedHandler accessDeniedHandler() {
		return new CustomAccessDeniedHandler();
	}	
}