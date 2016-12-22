/**
 * 
 */
package com.aexample.spring.config;

/**
 * @author Main Login
 * $Rev$
 * $Date$
 *
 */
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.aexample.security.SpringSecurityConfig;



public class SpringMvcInitializer 
extends AbstractAnnotationConfigDispatcherServletInitializer {

@Override
protected Class<?>[] getRootConfigClasses() {
	return new Class[] { SpringSecurityConfig.class, WebMvcConfig.class };
}

@Override
protected Class<?>[] getServletConfigClasses() {
	return null;
}

@Override
protected String[] getServletMappings() {
	return new String[] { "/" };
}

}