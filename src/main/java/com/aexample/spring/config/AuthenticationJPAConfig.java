/**
 * 
 */
package com.aexample.spring.config;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.google.common.base.Preconditions;

/**
 * @author Main Login
 * $Rev$
 * $Date$
 *
 */
@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
@PropertySource({ "classpath:persistence-mysql.properties" })
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
    
 //   @Bean(name="userDetailsService")   commented out in way of caution
 /*   public UserDetailsService userDetailsService(){
    	JdbcDaoImpl jdbcImpl = new JdbcDaoImpl();
    	jdbcImpl.setDataSource(dataSourceAuth());
    	jdbcImpl.setUsersByUsernameQuery("select id,password, enabled from userAccount where id=?");
    	jdbcImpl.setAuthoritiesByUsernameQuery("select b.id, a.authority from authorities a, userAccount b where b.id=? and a.id=b.id");
    	return jdbcImpl;
    }
*/    	
}
