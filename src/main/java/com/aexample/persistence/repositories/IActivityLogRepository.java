/**
 * 
 */
package com.aexample.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aexample.persistence.model.ActivityLog;

/**
 * @author Main Login
 * $Rev$
 * $Date$
 *
 */
public interface IActivityLogRepository extends JpaRepository<ActivityLog, Long> {
	
	void flush();

    @SuppressWarnings("unchecked")
	ActivityLog save(ActivityLog persisted);

}
