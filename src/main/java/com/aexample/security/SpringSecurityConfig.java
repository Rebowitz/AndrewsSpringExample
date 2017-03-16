package com.aexample.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.aexample.spring.config.AuthenticationJPAConfig;

@Configuration
//@ImportResource({ "classpath:webSecurityConfig.xml" })
@EnableWebSecurity
public class SpringSecurityConfig  extends WebSecurityConfigurerAdapter {
	

	@Autowired
	UserDetailsService userDetailsService;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		
	 auth.userDetailsService(userDetailsService).passwordEncoder(passwordencoder());		
	
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

	  http.authorizeRequests()
		.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
		.antMatchers("/dba/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_DBA')")
        .antMatchers("/login*").permitAll()
        .antMatchers("/registration*").permitAll()
        .antMatchers("/rest/**").permitAll()
        .anyRequest().authenticated()		
		.and().formLogin()
	  			.loginPage("/login.html")
	    .usernameParameter("username").passwordParameter("password") 
	      .loginProcessingUrl("/aexample_login")
	      .defaultSuccessUrl("/dashboard.html",true)
	      .failureUrl("/login.html?error=true")

	    .and().logout().logoutSuccessUrl("/login?logout") 
	     .and().exceptionHandling().accessDeniedPage("/403")
	    .and().csrf();
	
	}
	
	@Bean(name="passwordEncoder")
    public PasswordEncoder passwordencoder(){
    	return new BCryptPasswordEncoder();
    }

    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }

}