/**
 * 
 */
package com.aexample.test.dao;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.aexample.log4j.Slf4jTestWatcher;
import com.aexample.persistence.dao.IRegistrationDao;
import com.aexample.persistence.model.Accounts;
import com.aexample.test.JUnitTestConfiguration;


/**
 * @author Main Login $Rev$ $Date$
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = { JUnitTestConfiguration.class })
public class AexampleRegistrationDaoTest {

	private static final Logger logger = LoggerFactory.getLogger(AexampleRegistrationDaoTest.class);
	
	@Autowired
	IRegistrationDao regDao;

	@PersistenceContext
	private EntityManager entityManager;

	@Rule
	public Slf4jTestWatcher watchman = new Slf4jTestWatcher();

	/*
	 * we need to test these methods
	 * 
	 * Boolean isNewAccount(); void manualIntervention();
	 * 
	 * Accounts findOne(long id); List<Accounts> findAll(); void create(Accounts
	 * entity); Accounts update(Accounts entity); void delete(Accounts entity);
	 * void deleteById(long entityId);
	 * 
	 * 
	 * 
	 */

	@Test
	//@Transactional
	public void testRegistrationDaoInterfaceCreate() {

		Accounts entity = createAccountPojo();
		regDao.create(entity);
	
		logger.info("Account POJO persisted");
		
		entity = regDao.findOne(1);
		logger.info("Retrieving persisted account information");
		
		assertEquals("AExample",entity.getOrgName());
		assertEquals("bogus@aexample.com", entity.getEmail());
		assertEquals(Boolean.TRUE,entity.getAccountNonExpired());
	}

	@Before
	public void before() throws Exception {
		logger.info("Starting testRegistrationDaoInterfaceCreate");
	}
	@After // tearDown()
	public void after() throws Exception {
		logger.info("Running: tearDown");

	}

	private Accounts createAccountPojo() {

		Accounts accPojo = new Accounts();
		// accPojo.setId(Long.valueOf(1)); //this is usually supplied as
		// autoincremented index on db insert
		// detached entity error if set when persisted
		accPojo.setOrgName("AExample");
		accPojo.setFirstName("JUnit");
		accPojo.setLastName("Test Case");
		accPojo.setEmail("bogus@aexample.com");
		accPojo.setLoginId("aexample");
		accPojo.setProfileFlag(Boolean.FALSE);
		accPojo.setPassword("cleartext");
		accPojo.setPlan("Basic");
		accPojo.setAccountNonExpired(Boolean.TRUE);
		accPojo.setAccountNonLocked(Boolean.TRUE);
		accPojo.setEnabled(Boolean.TRUE);
		accPojo.setRole("ROLE_USER");
		accPojo.setCreateDate(new Date(0));
		accPojo.setCredentialsNonExpired(Boolean.TRUE);

		return accPojo;
	}
}
