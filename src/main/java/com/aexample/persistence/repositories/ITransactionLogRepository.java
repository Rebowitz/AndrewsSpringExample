/**
 * 
 */
package com.aexample.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aexample.persistence.model.TransactionLog;

/**
 * @author Main Login
 * $Rev$
 * $Date$
 *
 */
public interface ITransactionLogRepository extends JpaRepository<TransactionLog, Long> {
	
	void flush();

    @SuppressWarnings("unchecked")
	TransactionLog save(TransactionLog persisted);

}
