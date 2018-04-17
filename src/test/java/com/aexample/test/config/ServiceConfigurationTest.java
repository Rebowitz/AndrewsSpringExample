/**
 * 
 */
package com.aexample.test.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;

import com.aexample.security.service.EncryptionServiceImpl;
import com.aexample.spring.config.SpringSecurityBeans;


/**
 * @author Robert B. Andrews $Rev$ $Date$
 *
 */

@Configuration
@ContextConfiguration(classes={RepositoriesConfigurationTest.class,
		PersistenceJPAConfigTest.class,
		AexampleServiceTestConfiguration.class,
		EncryptionServiceImpl.class,
		SpringSecurityBeans.class})
public class ServiceConfigurationTest {


}

