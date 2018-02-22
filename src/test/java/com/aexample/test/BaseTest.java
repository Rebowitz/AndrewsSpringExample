/**
 * 
 */
package com.aexample.test;

import org.junit.runner.RunWith;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * @author Main Login
 * $Rev$
 * $Date$
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
//ApplicationContext will be loaded from "/base-context.xml" in the root of the classpath
@ContextConfiguration
@ComponentScan(basePackages = { "AexampleTestConfiguration.class"})
public class BaseTest {

}
