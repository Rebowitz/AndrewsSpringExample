/**
 * 
 */
package com.aexample.persistence.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aexample.persistence.model.ActivityLog;
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
    List<TransactionLog> findByAccountId(Long accountId);
}
