/**
 * 
 */
package com.aexample.website.util;

import java.util.Arrays;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * $Rev$
 * $Date$
 *
 */
public class SpringBeanLister implements ApplicationContextAware {

@Autowired
ApplicationContext applicationContext;

public void listThemBeans(){
	setApplicationContext(applicationContext);
}

/* (non-Javadoc)
 * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
 */
@Override
public void setApplicationContext(ApplicationContext arg0) throws BeansException {
	// TODO Auto-generated method stub
    System.out.println(Arrays.asList(applicationContext.getBeanDefinitionNames()));
}}
