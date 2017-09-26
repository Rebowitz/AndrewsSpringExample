/**
 * 
 */
package com.aexample.aop.rootcontext;


import java.util.Arrays;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author Main Login
 * $Rev$
 * $Date$
 *
 */
@Aspect
public class PersistenceAdviceInterceptor implements MethodInterceptor{
	
	private static final Logger logger = LoggerFactory.getLogger(PersistenceAdviceInterceptor.class);
//	private static @ILogger Logger logger;	
	
	/* (non-Javadoc)
	 * @see org.aopalliance.intercept.MethodInterceptor#invoke(org.aopalliance.intercept.MethodInvocation)
	 */
	@Override
	public Object invoke(MethodInvocation methodInvocation) throws Throwable {
		logger.debug("PersistenceAdviceInterceptor : Before method");
		
		logger.debug("Method name : "
				+ methodInvocation.getMethod().getName());
		logger.debug("Method arguments : "
				+ Arrays.toString(methodInvocation.getArguments()));


		try {
			// proceed to original method call
			Object result = methodInvocation.proceed();

			// same with AfterReturningAdvice
			logger.debug("PersistenceAdviceInterceptor : After method");

			return result;

		} catch (IllegalArgumentException e) {
			// same with ThrowsAdvice
			logger.debug("PersistenceAdviceInterceptor : Throw exception!");
			throw e;
		}
	}

}
