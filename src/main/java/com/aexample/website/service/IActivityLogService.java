/**
 * 
 */
package com.aexample.website.service;

import java.util.List;

import com.aexample.persistence.model.ActivityLog;

/**
 * @author Main Login
 * $Rev$
 * $Date$
 *
 */
public interface IActivityLogService {
	
	ActivityLog saveActivityRecord(ActivityLog persisted);
	List<ActivityLog> findByAccountId(Long accountId);

}
