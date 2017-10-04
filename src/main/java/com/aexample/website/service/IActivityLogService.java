/**
 * 
 */
package com.aexample.website.service;

import com.aexample.persistence.model.ActivityLog;

/**
 * @author Main Login
 * $Rev$
 * $Date$
 *
 */
public interface IActivityLogService {
	
	ActivityLog saveActivityRecord(ActivityLog persisted);

}
