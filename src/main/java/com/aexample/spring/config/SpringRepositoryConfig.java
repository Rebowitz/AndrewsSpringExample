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
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({ "com.aexample.persistence.repositories" })
public class SpringRepositoryConfig {

}
