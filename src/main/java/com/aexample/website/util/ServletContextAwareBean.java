/**
 * 
 */
package com.aexample.website.util;

/**
 * @author Main Login
 * $Rev$
 * $Date$
 *
 */
import javax.servlet.ServletContext;

import org.springframework.web.context.ServletContextAware;

	/**
	 * @author Juergen Hoeller
	 */
	public class ServletContextAwareBean implements ServletContextAware {

		private ServletContext servletContext;

		public void setServletContext(ServletContext servletContext) {
			this.servletContext = servletContext;
		}

		public ServletContext getServletContext() {
			return servletContext;
		}
	}
