/**
 * 
 */
package com.aexample.test;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * @author Main Login
 * $Rev$
 * $Date$
 *
 */
public class SessionFactoryUtil {
    private static final SessionFactory sessionFactory = buildSessionFactory();
    
    private static SessionFactory buildSessionFactory() {
        try {
        	
        	Configuration configuration = new Configuration();

        	 // Create properties file
        	 Properties properties = new Properties();
        	 properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("hibernate.properties"));
        	 // Pass hibernate properties file
        	 configuration.setProperties(properties);
        	 // Since version 4.x, service registry is being used
        	 ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().
        	 applySettings(configuration.getProperties()).build(); 

        	 // Create session factory instance
        	 SessionFactory factory = configuration.buildSessionFactory(serviceRegistry);

        	
            // Create the SessionFactory from hibernate.cfg.xml
            return factory;
        }
        catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed. " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
 
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
 
}
