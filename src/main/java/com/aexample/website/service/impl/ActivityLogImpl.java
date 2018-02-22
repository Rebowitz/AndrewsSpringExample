/**
 * 
 */
package com.aexample.website.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.aexample.persistence.model.ActivityLog;
import com.aexample.persistence.repositories.IActivityLogRepository;
import com.aexample.website.service.IActivityLogService;

/**
 * @author Main Login
 * $Rev$
 * $Date$
 *
 */
public class ActivityLogImpl implements IActivityLogService{

	

	@Autowired
	IActivityLogRepository activityLogRepository;
		
	@Override
	public ActivityLog saveActivityRecord(ActivityLog persisted) {
		activityLogRepository.save(persisted);
		return null;
	}
	
	public List<ActivityLog> findByAccountId(Long accountId){
		List<ActivityLog> activityLogList = activityLogRepository.findByAccountId(accountId);
		return activityLogList;
		
	}

}
