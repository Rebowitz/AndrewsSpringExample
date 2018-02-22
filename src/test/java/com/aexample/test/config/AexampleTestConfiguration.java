package com.aexample.test.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
@Configuration
@ComponentScan(basePackages={
//		"com.aexample.handler",
//		"com.aexample.listener",
//		"com.aexample.persistence.model",
		"com.aexample.persistence.repositories",
//		"com.aexample.registration",
//		"com.aexample.security",
//		"com.aexample.security.service",
//		"com.aexample.aop.rootcontext",
//		"com.aexample.spring.task",
//		"com.aexample.website.controller",
//		"com.aexample.website.delegate",
//		"com.aexample.website.exception",
//		"com.aexample.website.service",
//		"com.aexample.website.service.impl",
//		"com.aexample.website.util",
//		"com.aexample.website.validation",
//		"com.aexample.website.viewBean",
//		"com.aexample.test.config",
		"com.aexample.test.pojo"

})
//Do not add com.aexample.spring.config package to above list!!!
//if above is commented, then the beans are created below
public class AexampleTestConfiguration {

}
