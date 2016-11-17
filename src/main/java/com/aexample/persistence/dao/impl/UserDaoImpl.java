package com.aexample.persistence.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
import javax.persistence.PersistenceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.aexample.persistence.dao.IUserDao;
import com.aexample.persistence.model.Accounts;

/**
 * @author RBA
 */

@NamedQuery(
		name="isValidUser",
		query="Select * FROM Accounts a WHERE a.loginId = :loginId AND a.password = :passwd")

@Repository
public class UserDaoImpl implements IUserDao
{
	private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);
	
		@PersistenceContext
		private EntityManager jem;
			
		Accounts returnedAccount = null;

		public Accounts isValidUser(String loginid, String password)
		{
			logger.info("Validating user: {}.",loginid);

			try{
				returnedAccount = (Accounts) jem.createNamedQuery("isValidUser")
						.setParameter("loginid", loginid)
						.setParameter("passwd", password)
						.getSingleResult();

				if (returnedAccount == null){
					logger.info("Login failure for userid: {}", loginid);	
				}				
				
			}catch(RuntimeException e){
				logger.error(e.toString());			
			}finally{
				if(jem != null){
					jem.close();
				}	
			}

			
			return returnedAccount;

		}
/*
 * 	      
18
	                EntityManagerFactory emf = Persistence.createEntityManagerFactory("jcg-JPA");
19
	                EntityManager em = emf.createEntityManager();
20
	                 
21
	                em.getTransaction().begin();
22
	                Employee employee = new Employee();
23
	                employee.setName("Chandan");
24
	                System.out.println("COMIITING");
25
	                em.persist(employee);
26
	                em.getTransaction().commit();
27
	                 
28
	    }
 */
}