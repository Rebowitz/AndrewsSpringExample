/**
 * 
 */
package com.aexample.spring.config;

import javax.servlet.annotation.WebListener;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextListener;

/**
 * @author Main Login
 * $Rev$
 * $Date$
 *
 */
@Configuration
@WebListener
public class WebRequestContextListener extends RequestContextListener {
	
}
