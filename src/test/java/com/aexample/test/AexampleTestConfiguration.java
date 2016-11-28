package com.aexample.test;

import com.aexample.persistence.dao.IRegistrationDao;
import com.aexample.persistence.dao.IUserDao;
import com.aexample.persistence.dao.impl.RegistrationDaoImpl;
import com.aexample.persistence.dao.impl.UserDaoImpl;
import com.aexample.spring.config.persistenceJPAConfig;
import com.aexample.website.controller.RegistrationController;
import com.aexample.website.service.IRegistrationService;
import com.aexample.website.service.IUserService;
import com.aexample.website.service.impl.RegistrationServiceImpl;
import com.aexample.website.service.impl.UserServiceImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;


@Configuration
@ComponentScan(basePackages={
		"com.aexample.hibernate",
		"com.aexample.logging",
		"com.aexample.persistence.dao",
		"com.aexample.persistence.dao.impl",
		"com.aexample.persistence.model",
		"com.aexample.website.controller",
		"com.aexample.website.delegate",
		"com.aexample.website.error",
		"com.aexample.website.exception",
		"com.aexample.website.service",
		"com.aexample.website.service.impl",
		"com.aexample.website.util",
		"com.aexample.website.validator",
		"com.aexample.website.viewBean"
})
//Do not add config package to above list!!!
//if above is commented, then the beans are created below

public class AexampleTestConfiguration {
	

//	private static final Logger logger = LoggerFactory.getLogger(AexampleTestConfiguration.class);


}
