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

	import java.io.File;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


	@Configuration
	@ComponentScan({ "com.aexample.handler" })
	public class SpringHandlerConfig {
		
		public static File CRM_STORAGE_UPLOADS_DIRECTORY = new File("/storage");
		
	
	}
