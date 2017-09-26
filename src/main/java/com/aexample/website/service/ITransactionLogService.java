/**
 * 
 */
package com.aexample.website.service;

import com.aexample.persistence.model.TransactionLog;

/**
 * @author Main Login
 * $Rev$
 * $Date$
 *
 */
public interface ITransactionLogService {
	
	TransactionLog saveTransactionRecord(TransactionLog persisted);

}
