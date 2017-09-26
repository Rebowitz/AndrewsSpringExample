/**
 * 
 */
package com.aexample.website.util;

import org.springframework.context.ApplicationContext;

/**
 * @author Main Login
 * $Rev$
 * $Date$
 *
 */
public class AppContextProvider {

    private static ApplicationContext ctx;

    /**
     * Injected from the class "ApplicationContextSetter" which is automatically
     * loaded during Spring-Initialization.
     */
    public static void setApplicationContext(ApplicationContext applicationContext) {
        ctx = applicationContext;
    }


    /**
     * Get access to the Spring ApplicationContext from everywhere in your Application.
     *
     * @return
     */
    public static ApplicationContext getApplicationContext() {
        return ctx;
    }
} // .EOF