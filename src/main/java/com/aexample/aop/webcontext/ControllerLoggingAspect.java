/**
 * 
 */
package com.aexample.aop.webcontext;

/**
 * @author Main Login
 * $Rev$
 * $Date$
 *
 */
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ControllerLoggingAspect {
    private final Logger logger = LoggerFactory.getLogger(getClass());
//	private static @ILogger Logger logger;	
	
	//asterik is any return type|pkg name.class *.method *(any type and number of arguments ..)
	//so advice is for all methods in all controllers in the controller package, with any
	//type and number of arguments
	
	@Before("logBeforeRegistrationController()")
	public void logBeforeRegistrationControllerAdvice(JoinPoint joinPoint){
		logger.debug("logBefore RegistrationController");
		System.out.println(joinPoint.getSignature().getName()+" is started! from logBefore");
	}

	@After("logAfterRegistrationController()")
	public void logAfterRegistrationControllerAdvice(JoinPoint joinPoint){
		logger.debug("logAfter RegistrationController");
	    System.out.println(joinPoint.getSignature().getName()+" is completed! from logAfter");
	}

/*	@Before("allControllerMethodsPointcut()")
	public void allControllerMethodsAdvice() {
		logger.debug("Before executing controller method");
	}
*/	
	/*
	 * POINTCUT DEFINITIONS HERE
	 */

/*	
	@Pointcut("within(com.aexample.website.controller.*.*)")
	public void allControllerMethodsPointcut() {

	}
*/
	
	@Pointcut("execution(* com.aexample.website.controller.*.*(..))")
	public void logBeforeRegistrationController(){}
	
	@Pointcut("execution(* com.aexample.website.controller.*.*(..))")
	public void logAfterRegistrationController(){}
	
	// #########################################################

	@Before("allValidatorMethodsPointcut()")
	public void allValidatorMethodsAdvice() {
		logger.debug("Before executing validation method");
	}

	@Pointcut("within(com.aexample.website.validation.*.*)")
	public void allValidatorMethodsPointcut() {
	}
 
	
}
