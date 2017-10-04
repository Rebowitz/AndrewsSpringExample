/**
 * 
 */
package com.aexample.spring.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.handler.HandlerExceptionResolverComposite;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.mvc.annotation.ResponseStatusExceptionResolver;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

import com.aexample.website.exception.RegistrationNotCompletedException;

/**
 * @author Main Login
 * $Rev$
 * $Date$
 *
 */
public class ExceptionResolverConfig {
	
	SimpleMappingExceptionResolver getSimpleMappingExceptionResolver() {
		
		SimpleMappingExceptionResolver result
		    = new SimpleMappingExceptionResolver();
		
		// Setting customized exception mappings
		Properties p = new Properties();
		p.put(RegistrationNotCompletedException.class.getName(), "Errors/Exception1");
		result.setExceptionMappings(p);
		
		// Un-mapped exceptions will be directed to here
		result.setDefaultErrorView("Errors/Default");
		
		// Setting a default HTTP status code
		result.setDefaultStatusCode(HttpStatus.BAD_REQUEST.value());
		
		return result;
		
	}
	
	@Bean
	HandlerExceptionResolverComposite getHandlerExceptionResolverComposite() {
		
		HandlerExceptionResolverComposite result
			= new HandlerExceptionResolverComposite();
		
		List<HandlerExceptionResolver> l
			= new ArrayList<HandlerExceptionResolver>();
		
		l.add(new ExceptionHandlerExceptionResolver ());
		l.add(new ResponseStatusExceptionResolver());
		l.add(getSimpleMappingExceptionResolver());
		l.add(new DefaultHandlerExceptionResolver());
		
		result.setExceptionResolvers(l);
		
		return result;
		
	}
	
	

}
