/**
 * 
 */
package com.aexample.spring.config;


import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.aop.support.RegexpMethodPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.aexample.aop.webcontext.ControllerAdviceInterceptor;
import com.aexample.aop.webcontext.ControllerLoggingAspect;

/**
 * @author Main Login
 * $Rev$
 * $Date$
 *
 */
@Configuration
public class AspectBeansWebContextConfig {

	@Bean(name="controllerMethodNamePointcut")
	public NameMatchMethodPointcut nameMatchMethodPointcut(){
		NameMatchMethodPointcut nameMatchMethodPointcut = new NameMatchMethodPointcut();
		nameMatchMethodPointcut.setMappedNames("showRegistrationForm","registerUserAccount","confirmRegistration","resendRegistrationToken","resetPassword","changePassword","savePassword");
		return nameMatchMethodPointcut;
		
	}
	

	
	@Bean("controllerAdvice")
	ControllerAdviceInterceptor controllerAdvice(){
		ControllerAdviceInterceptor cai = new ControllerAdviceInterceptor();
		return cai;
		
	}
	
/*	@Bean("controllerAdvisor")
	DefaultPointcutAdvisor defaultPointcutAdvisor(){
		DefaultPointcutAdvisor dpa = new DefaultPointcutAdvisor();
		
		dpa.setPointcut(nameMatchMethodPointcut());
		dpa.setAdvice(controllerAdvice());
		
		return dpa;
		
	}
*/
	
	@Bean(name="controllerMethodRegExPointcut")
	public RegexpMethodPointcutAdvisor regexpMethodPointcutAdvisor(){
		RegexpMethodPointcutAdvisor regexpMethodPointcutAdvisor = new RegexpMethodPointcutAdvisor();
		regexpMethodPointcutAdvisor.setPattern(".controller.*");
		return regexpMethodPointcutAdvisor;
	}
	
	@Bean("controllerLoggingAspect")
	ControllerLoggingAspect controllerLoggingAspect(){
		ControllerLoggingAspect cla = new ControllerLoggingAspect();
		return cla;
	}
	
}
