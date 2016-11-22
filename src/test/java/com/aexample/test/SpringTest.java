package com.aexample.test;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.aexample.log4j.Slf4jTestWatcher;
import com.aexample.spring.config.WebMvcConfig;

import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith( SpringJUnit4ClassRunner.class )

@ContextConfiguration( classes = { WebMvcConfig.class },
//@ContextConfiguration( classes = { Configuration.class, PersistenceConfig.class },
loader = AnnotationConfigContextLoader.class ) 
public class SpringTest {

	@Rule
	public Slf4jTestWatcher watchman = new Slf4jTestWatcher();
	
	   @Test
	   public void whenSpringContextIsInstantated_thenNoExceptons(){
		   // When    } 	
	   }
	
}
