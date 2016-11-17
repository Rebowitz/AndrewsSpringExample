package com.aexample.persistence.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.aexample.persistence.dao.impl.RegistrationDaoImpl;


/**
 * @author Main Login
 * $Rev$
 * $Date$
 *
 */


@Component
	public abstract class AbstractHibernateDao< T extends Serializable> {
	//public abstract class AbstractJpaDAO<T> {
	    private Class< T > jpaClass;

	    private static final Logger logger = LoggerFactory.getLogger(RegistrationDaoImpl.class);
	    
	        @PersistenceContext
	        private EntityManager entityManager;

	        public final void setJpaClass(final Class<T> jpaClass) {
	        	System.out.println("AbstractHibernateDao -- setting jpaClass");
	            this.jpaClass = jpaClass;
	        }

	        public T findOne(final long id) {
	            return entityManager.find(jpaClass, id);
	        }

	        @SuppressWarnings("unchecked")
	        public List<T> findAll() {
	            return entityManager.createQuery("from " + jpaClass.getName()).getResultList();
	        }

	        public void create(final T entity) {
	        	logger.info("Persisting new account information");
	            entityManager.persist(entity);
	        }

	        public T update(final T entity) {
	            return entityManager.merge(entity);
	        }

	        public void delete(final T entity) {
	            entityManager.remove(entity);
	        }

	        public void deleteById(final long entityId) {
	            final T entity = findOne(entityId);
	            delete(entity);
	        }

	    }	    
	  
	