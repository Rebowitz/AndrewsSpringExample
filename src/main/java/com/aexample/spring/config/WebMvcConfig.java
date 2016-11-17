package com.aexample.spring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@ImportResource({"classpath*:springBeanConfiguration.xml"})
@ComponentScan("com.aexample")
@EnableWebMvc
@Import(persistenceJPAConfig.class)
/*This is a shortcut, and though it may be useful in many situations, it’s not perfect. 
 * When more complex confguration is needed, remove the EnableWebMvc annotation and extend 
 * WebMvcConfgurationSupport directly. 
*/
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    public WebMvcConfig() {
        super();
    }

    // API

}