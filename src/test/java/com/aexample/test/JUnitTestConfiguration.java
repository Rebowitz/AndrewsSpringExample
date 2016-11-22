/**
 * 
 */
package com.aexample.test;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
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
@Import(AexampleTestConfiguration.class)
public class JUnitTestConfiguration {

    @Autowired
    private Environment env;    
	
	@Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan(new String[] { "com.aexample.persistence.model" });

        final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(additionalProperties());

        return em;
    }

    @Bean
    public DataSource dataSource() {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(Preconditions.checkNotNull(env.getProperty("jdbc.driverClassName")));
        dataSource.setUrl(Preconditions.checkNotNull(env.getProperty("jdbc.url")));
        dataSource.setUsername(Preconditions.checkNotNull(env.getProperty("jdbc.user")));
        dataSource.setPassword(Preconditions.checkNotNull(env.getProperty("jdbc.pass")));

        return dataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager(final EntityManagerFactory emf) {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);
        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    final Properties additionalProperties() {
        final Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
        hibernateProperties.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
        // hibernateProperties.setProperty("hibernate.globally_quoted_identifiers", "true");
        return hibernateProperties;
    }
/*    
	
	
	
	
	
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(){
//    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(){ 
        LocalContainerEntityManagerFactoryBean lcemfb
            = new LocalContainerEntityManagerFactoryBean();
 
        lcemfb.setDataSource(this.dataSource());
        lcemfb.setPackagesToScan(new String[] {"com.aexample"});
        lcemfb.setPersistenceUnitName("AexampleTestPU");
 
        HibernateJpaVendorAdapter va = new HibernateJpaVendorAdapter();
        lcemfb.setJpaVendorAdapter(va);
 
        Properties ps = new Properties();
        ps.put("hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
        ps.put("hibernate.hbm2ddl.auto", "create");
        lcemfb.setJpaProperties(ps);
 
        lcemfb.afterPropertiesSet();
 
        return lcemfb;
    }
    
    @Bean
    public DataSource dataSource(){
 
        DriverManagerDataSource ds = new DriverManagerDataSource();
 
        ds.setDriverClassName("org.hsqldb.jdbcDriver");
        ds.setUrl("jdbc:hsqldb:mem:testdb");
        ds.setUsername("sa");
        ds.setPassword("");
 
        return ds;
 
    }
    
    @Bean
    public PlatformTransactionManager transactionManager(final EntityManagerFactory emf) {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);
        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }
 
    /*
    @Bean
    public PlatformTransactionManager transactionManager(){
 
        JpaTransactionManager tm = new JpaTransactionManager();
 
        tm.setEntityManagerFactory(
            this.entityManagerFactory().getObject() );
 
        return tm;
 
    }
 
    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
        return new PersistenceExceptionTranslationPostProcessor();
    }    
 */
   
}
