/**
 * 
 */
package com.aexample.spring.config;

import java.io.InputStream;
import java.util.Properties;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.google.common.base.Preconditions;

/**
 * @author Main Login
 * $Rev$
 * $Date$
 *
 */
@Configuration
@EnableTransactionManagement
@PropertySource({ "classpath:persistence-mysql.properties" })
@ComponentScan({ "com.aexample.persistence" })
public class AuthenticationJPAConfig{

/*
 * Setting up a second datasource that accesses the same underlying dbase.  This class exists
 * so that an alternative authentication solution such as LDAP, etc. can be configured here and used
 * without interfering with the application database connection and vice versa. * 
 */

    @Autowired
    private Environment env;
 
    public AuthenticationJPAConfig() {
    	super();
    }

    
    @Bean(name="dataSourceAuth")
    public DataSource dataSourceAuth() {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(Preconditions.checkNotNull(env.getProperty("jdbc.driverClassName")));
        dataSource.setUrl(Preconditions.checkNotNull(env.getProperty("jdbc.url")));
        dataSource.setUsername(Preconditions.checkNotNull(env.getProperty("jdbc.user")));
        dataSource.setPassword(Preconditions.checkNotNull(env.getProperty("jdbc.pass")));

        return dataSource;
    }
    
    @Bean(name="userDetailsService")
    public UserDetailsService userDetailsService(){
    	JdbcDaoImpl jdbcImpl = new JdbcDaoImpl();
    	jdbcImpl.setDataSource(dataSourceAuth());
    	jdbcImpl.setUsersByUsernameQuery("select LoginId,Password, Enabled from Accounts where LoginId=?");
    	jdbcImpl.setAuthoritiesByUsernameQuery("select b.LoginId, a.Role from UserRole a, Accounts b where b.LoginId=? and a.LoginId=b.LoginId");
    	return jdbcImpl;
    }	
}
