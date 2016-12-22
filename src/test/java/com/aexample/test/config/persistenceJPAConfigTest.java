/**
 * 
 */
package com.aexample.test.config;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.PlatformTransactionManager;

import com.aexample.spring.config.PersistenceJPAConfig;


/**
 * @author Main Login
 * $Rev$
 * $Date$
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = PersistenceJPAConfig.class)
public class persistenceJPAConfigTest {
	
	private static final Logger logger = LoggerFactory.getLogger(persistenceJPAConfigTest.class);
	
    @Autowired
    private ApplicationContext context;
    
    @Autowired   
	DataSource ds;	
	
    @Autowired	
	PlatformTransactionManager ptm;
	
    @Autowired	
	PersistenceExceptionTranslationPostProcessor petpp;
        
    @Autowired 
    LocalContainerEntityManagerFactoryBean lcemfb;
    
    @Test
    public void listBeansToTest() throws Exception{
    	logger.info("beans: " + context.getBeanDefinitionCount());
    	for(String name : context.getBeanDefinitionNames()){
    		logger.info(name);
    	}    	
    }

	/**
	 * Test method for {@link com.aexample.spring.config.PersistenceJPAConfig#dataSource()}.
	 */
		
	@Test
	public void testDataSource() {
		assertNotNull(ds);
		Connection conn=null;
		
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("Test DataSource and Connection creation");
		
		assertNotNull(conn);
	}

	/**
	 * Test method for {@link com.aexample.spring.config.PersistenceJPAConfig#transactionManager(javax.persistence.EntityManagerFactory)}.
	 */
		
	@Test
	public void testTransactionManager() {
		assertNotNull(ptm);
		logger.info("Test TransactionManager creation");
	}

	/**
	 * Test method for {@link com.aexample.spring.config.PersistenceJPAConfig#exceptionTranslation()}.
	 */
		
	@Test
	public void testExceptionTranslation() {
		assertNotNull(petpp);
		logger.info("Test ExceptionTranslation creation");
	}
	
	/**
	 * Test method for {@link com.aexample.spring.config.PersistenceJPAConfig#entityManagerFactory()}.
	 */    
	@Test
	public void testEntityManagerFactory() {
		assertNotNull(lcemfb);
		logger.info("Test EntityManagerFactory creation");
	}


}
