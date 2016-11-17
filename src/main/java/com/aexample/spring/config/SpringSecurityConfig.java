package com.aexample.spring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource({ "classpath:webSecurityConfig.xml" })
public class SpringSecurityConfig {

    public SpringSecurityConfig() {
        super();
    }

}
