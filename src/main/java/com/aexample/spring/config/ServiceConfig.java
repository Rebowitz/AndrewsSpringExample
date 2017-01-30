package com.aexample.spring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({ "com.aexample.website.service" })
public class ServiceConfig {
}
