package com.aexample.spring.config;

import java.io.File;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({ "com.aexample.website.service" })
public class ServiceConfig {
	
	public static File CRM_STORAGE_UPLOADS_DIRECTORY = new File("/storage");
}
