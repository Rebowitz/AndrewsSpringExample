/**
 * 
 */
package com.aexample.test.config;


import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.jasypt.springsecurity3.authentication.encoding.PasswordEncoder;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.junit.runner.RunWith;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.aexample.persistence.repositories.IRoleRepository;
import com.google.common.base.Preconditions;

/**
 * @author Main Login $Rev$ $Date$
 *
 */
@Configuration
@EnableTransactionManagement
@PropertySource({ "classpath:persistence-mysql-test.properties" })
@EnableJpaRepositories(basePackages = "com.aexample.persistence.repositories")
public class PersistenceJPAConfigTest {

	@Autowired
	private Environment env;

	public PersistenceJPAConfigTest() {
		super();
	}

	// beans
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

	@Bean(name = "dataSource")
	public DataSource dataSource() {
		final DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(Preconditions.checkNotNull(env.getProperty("jdbc.driverClassName")));
		dataSource.setUrl(Preconditions.checkNotNull(env.getProperty("jdbc.url")));
		dataSource.setUsername(Preconditions.checkNotNull(env.getProperty("jdbc.user")));
		dataSource.setPassword(Preconditions.checkNotNull(env.getProperty("jdbc.pass")));

		return dataSource;
	}

	@Bean
	public JpaTransactionManager transactionManager(final EntityManagerFactory emf) {
		final JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(emf);
		return transactionManager;
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}

	@Bean(name="passwordEncoder")
	public PasswordEncoder passwordEncoder(StrongPasswordEncryptor passwordEncryptor) {
		PasswordEncoder passwordEncoder = new PasswordEncoder();
		passwordEncoder.setPasswordEncryptor(passwordEncryptor);
		return passwordEncoder;
	}
	
	final Properties additionalProperties() {
		final Properties hibernateProperties = new Properties();
		hibernateProperties.setProperty("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
		hibernateProperties.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
		// hibernateProperties.setProperty("hibernate.globally_quoted_identifiers",
		// "true");
		return hibernateProperties;
	}

}
