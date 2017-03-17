package com.aexample.spring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

@Configuration
@EnableScheduling
@ComponentScan({ "com.aexample.task" })
@PropertySource({ "classpath:cron.properties" })
public class SpringTaskConfig {

}
