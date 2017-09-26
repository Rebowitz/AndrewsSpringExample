/**
 * 
 */
package com.aexample.aop.webcontext;

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
public class ControllerAdviceInterceptor implements MethodInterceptor{
	
	public Object invoke(MethodInvocation invocation) throws Throwable {
		Logger log = LoggerFactory.getLogger(ControllerAdviceInterceptor.class);
		StringBuilder sb = new StringBuilder();
		sb.append("Target Class:").append(invocation.getThis()).append("n").append(invocation.getMethod()).append("n");

		Object retVal = invocation.proceed();
		
		sb.append(" return value:").append(retVal).append("n");
		log.info(sb.toString());
		return retVal;
	}
	
}
