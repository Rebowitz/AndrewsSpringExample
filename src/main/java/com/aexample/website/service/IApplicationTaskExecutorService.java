/**
 * 
 */
package com.aexample.website.service;

import com.aexample.task.TokensPurgeTask;

/**
 * @author Main Login
 * $Rev$
 * $Date$
 *
 */
public interface IApplicationTaskExecutorService {
	
	public void executeTokenPurge(TokensPurgeTask tokensPurgeTask);
}
