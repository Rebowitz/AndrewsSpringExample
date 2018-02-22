/**
 * 
 */
package com.aexample.persistence.repositories;

import java.util.List;

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
    
    ActivityLog findOne(Long id);
    List<ActivityLog> findByAccountId(Long accountId);

}
