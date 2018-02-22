/**
 * 
 */
package com.aexample.spring.config;

import java.io.File;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
/**
 * @author Main Login
 * $Rev$
 * $Date$
 *
 */
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

//this is the WebApplicationInitializer
public class SpringMvcInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	private int maxUploadSizeInMb = 5 * 1024 * 1024; // 5 MB

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { 
				SpringSecurityConfig.class, 
				PersistenceJPAConfig.class,
				AuthenticationJPAConfig.class, 				
				ServiceConfig.class,
				AppConfig.class, 
				AspectBeansRootContextConfig.class, 
				AopConfigRootContext.class };
		// AuthenticationJPAConfig allows a separate connection for either
		// internal or external auth providers such as LDAP, Redis, etc.

		// put repository, services, security config.
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		// return null;

		return new Class[] { 
				SpringControllerConfig.class, 
				SpringHandlerConfig.class, 
				WebMvcConfig.class,
				AspectBeansWebContextConfig.class, 
				EventListenerConfig.class,
				ExceptionResolverConfig.class, 
				SpringTaskConfig.class, 
				SpringEmailConfig.class,
				SpringLoggerConfig.class, 
				CustomAnnotationConfig.class, 
				AopConfigWebContext.class };

		// put view resolvers, controllers in WebMvcConfig and add handler
		// configs here.
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

	@Override
	protected void registerDispatcherServlet(ServletContext servletContext) {
		super.registerDispatcherServlet(servletContext);

		servletContext.addListener(new HttpSessionEventPublisher());

	}

	@Override
	protected WebApplicationContext createRootApplicationContext() {
		AnnotationConfigWebApplicationContext appContext = (AnnotationConfigWebApplicationContext) super.createRootApplicationContext();

		String profile;
		profile = "aexample";

		appContext.getEnvironment().setActiveProfiles(profile);
		return appContext;
	}
/*
	@Override
	protected WebApplicationContext createServletApplicationContext() {
		AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
		return applicationContext;
	}
*/
	@Override
	protected void customizeRegistration(ServletRegistration.Dynamic registration) {

		File uploadDirectory = ServiceConfig.CRM_STORAGE_UPLOADS_DIRECTORY;

		MultipartConfigElement multipartConfigElement = new MultipartConfigElement(uploadDirectory.getAbsolutePath(),
				maxUploadSizeInMb, maxUploadSizeInMb * 2, maxUploadSizeInMb / 2);

		registration.setMultipartConfig(multipartConfigElement);

	}

}