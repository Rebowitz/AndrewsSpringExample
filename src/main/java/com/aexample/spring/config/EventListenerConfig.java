/**
 * 
 */
package com.aexample.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.aexample.listener.OnRegistrationCompleteListener;

/**
 * @author Main Login
 * $Rev$
 * $Date$
 *
 */
@Configuration
@ComponentScan({ "com.aexample.listener" })
public class EventListenerConfig {
	
	@Bean
	public OnRegistrationCompleteListener onRegistrationCompleteListener(){
		return new OnRegistrationCompleteListener();
	}
	

}
