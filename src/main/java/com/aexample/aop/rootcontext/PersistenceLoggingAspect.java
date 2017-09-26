/**
 * 
 */
package com.aexample.aop.rootcontext;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author Main Login
 * $Rev$
 * $Date$
 *
 */
@Aspect
@Component
public class PersistenceLoggingAspect {
  private final Logger logger = LoggerFactory.getLogger(getClass());
//	private static @ILogger Logger logger;	
	
	//asterik is any return type|pkg name.class *.method *(any type and number of arguments ..)
	//so advice is for all methods in all controllers in the controller package, with any
	//type and number of arguments.  These are known as ADVICES
	
	@Before("logBeforePersistenceModels()")
	public void logBeforePersistenceModelsAdvice(JoinPoint joinPoint){
		logger.debug("logBefore PersistenceModels");
		System.out.println(joinPoint.getSignature().getName()+" is started! from logBefore");
	}

	@After("logAfterPersistenceModels()")
	public void logAfterPersistenceModelsAdvice(JoinPoint joinPoint){
		logger.debug("logAfter PersistenceModel");
	    System.out.println(joinPoint.getSignature().getName()+" is completed! from logAfter");
	}

	@Before("logBeforePersistenceRepositories()")
	public void logBeforePersistenceRepositoriesAdvice(JoinPoint joinPoint){
		logger.debug("logBefore logBeforePersistenceRepositories");
		System.out.println(joinPoint.getSignature().getName()+" is started! from logBefore");
	}

	@After("logAfterPersistenceRepositories()")
	public void logAfterPersistenceRepositoriesAdvice(JoinPoint joinPoint){
		logger.debug("logAfter logAfterPersistenceRepositories");
	    System.out.println(joinPoint.getSignature().getName()+" is completed! from logAfter");
	}	
	
	/*
	 * POINTCUT DEFINITIONS HERE
	 */
	
	@Pointcut("within(com.aexample.website.service.*.*)")
	public void allServiceMethodsPointcut() {}
	
	//#########################################################

	@Pointcut("within(com.aexample.security.*.*)")
	public void allSecurityMethodsPointcut() {}
	
	//#########################################################
		
	@Pointcut("execution(* com.aexample.persistence.model.*.*(..))")
	public void logBeforePersistenceModels(){}

	@Pointcut("execution(* com.aexample.persistence.model.*.*(..))")
	public void logAfterPersistenceModels(){}

	@Pointcut("execution(* com.aexample.persistence.repositories.*.*(..))")
	public void logBeforePersistenceRepositories(){}

	@Pointcut("execution(* com.aexample.persistence.repositories.*.*(..))")
	public void logAfterPersistenceRepositories(){}	
	
}
