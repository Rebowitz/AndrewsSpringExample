/**
 * 
 */
package com.aexample.log4j;

/**
 * @author Main Login
 * $Rev$
 * $Date$
 *
 */


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
 
public class Slf4jTestWatcher extends TestWatcher{
 
	private static final Logger logger = LoggerFactory.getLogger(Slf4jTestWatcher.class);	
 
    	@Override
        protected void starting(Description d) {
            logger.trace("  Test [{}] starting ", d.getMethodName());
        }

        @Override
        protected void succeeded(Description d) {
        	logger.info("  Test [{}]   succeeded ", d.getMethodName());	
        }

        @Override
        protected void failed(Throwable e, Description d) {
        	logger.warn("  Test [{}]   failed with exception [{}]", d.getMethodName(), e.getMessage());
        }

        @Override
        protected void finished(Description d) {
        	logger.trace("  Test [{}] finished ", d.getMethodName());
        }
	
}   