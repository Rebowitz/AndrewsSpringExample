/**
 * 
 */
package com.aexample.spring.config;

import javax.servlet.ServletContext;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.web.servlet.support.AbstractDispatcherServletInitializer;

/**
 * @author Main Login
 * $Rev$
 * $Date$
 *
 */

//This loads the spring security filter chain

@Configuration
public class SpringSecurityInitializer extends AbstractSecurityWebApplicationInitializer {
    
	@Override
    protected String getDispatcherWebApplicationContextSuffix() {
        return AbstractDispatcherServletInitializer.DEFAULT_SERVLET_NAME;
    }

    /**
     * Insert the following filters before Spring Security. Be careful when inserting
     * filters before Spring Security!
     */
    @Override
    protected void afterSpringSecurityFilterChain(ServletContext servletContext) {
    //      insertFilters(servletContext, new HiddenHttpMethodFilter(), new MultipartFilter() , new OpenEntityManagerInViewFilter());
    }

    /**
     * Register the {@link HttpSessionEventPublisher}
     */
    @Override
    protected boolean enableHttpSessionEventPublisher() {
        return true;
    }
  
}
    
