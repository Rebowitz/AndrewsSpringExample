package com.aexample.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.aexample.spring.config.WebMvcConfig;

import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith( SpringJUnit4ClassRunner.class )

@ContextConfiguration( classes = { WebMvcConfig.class },
//@ContextConfiguration( classes = { Configuration.class, PersistenceConfig.class },
loader = AnnotationConfigContextLoader.class ) 
public class SpringTest {

	   @Test
	   public void whenSpringContextIsInstantated_thenNoExceptons(){
		   // When    } 	
	   }
	
}
