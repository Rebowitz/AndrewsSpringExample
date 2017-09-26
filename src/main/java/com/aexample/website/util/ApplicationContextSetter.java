/**
 * 
 */
package com.aexample.website.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author Main Login
 * $Rev$
 * $Date$
 *
 */
/*This class implements the ApplicationContextAware. A bean which implements 
 * the ApplicationContextAware-interface and is deployed into the context, 
 * will be called back on creation of the bean, using the interface’s 
 * setApplicationContext(…) method, and provided with a reference to the context, 
 * which may be stored for later interaction with the context.
*/

public class ApplicationContextSetter implements ApplicationContextAware{

    public void setApplicationContext(ApplicationContext ctx)
            throws BeansException {
    	AppContextProvider.setApplicationContext(ctx);
    }
}
