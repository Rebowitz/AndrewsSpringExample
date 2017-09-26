/**
 * 
 */
package com.aexample.spring.config;

import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.aexample.aop.rootcontext.PersistenceAdviceInterceptor;
import com.aexample.aop.rootcontext.PersistenceLoggingAspect;

/**
 * @author Main Login
 * $Rev$
 * $Date$
 *
 */
@Configuration
public class AspectBeansRootContextConfig {
	
	@Bean(name="persistenceMethodNamePointcut")
	public NameMatchMethodPointcut nameMatchMethodPointcut(){
		NameMatchMethodPointcut nameMatchMethodPointcut = new NameMatchMethodPointcut();
		nameMatchMethodPointcut.setMappedNames("save","findByEmail");
		return nameMatchMethodPointcut;
		
	}
	
	@Bean("persistenceAdvice")
	PersistenceAdviceInterceptor persistenceAdvice(){
		PersistenceAdviceInterceptor pai = new PersistenceAdviceInterceptor();
		return pai;
		
	}
	
	@Bean("persistenceAdvisor")
	DefaultPointcutAdvisor defaultPointcutAdvisor(){
		DefaultPointcutAdvisor dpa = new DefaultPointcutAdvisor();
		
		dpa.setPointcut(nameMatchMethodPointcut());
		dpa.setAdvice(persistenceAdvice());
		
		return dpa;
		
	}
	
	@Bean("persistenceLoggingAspect")
	public PersistenceLoggingAspect persistenceLoggingAspect(){
		PersistenceLoggingAspect persistenceLoggingAspect = new PersistenceLoggingAspect();
		return persistenceLoggingAspect;
	}
/*	
	@Bean("rootPointCutDefinitions")
	public RootPointCutDefinitions rootPointCutDefinitions(){
		RootPointCutDefinitions rpcd = new RootPointCutDefinitions();
		return rpcd;
	}
*/	
}
