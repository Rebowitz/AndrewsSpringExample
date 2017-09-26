/**
 * 
 */
package com.aexample.test.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.aexample.annotations.ILogger;
import com.aexample.test.config.AexampleTestConfiguration;
import com.aexample.website.controller.DashboardController;
import com.aexample.website.controller.LoginController;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
/**
 * @author Main Login
 * $Rev$
 * $Date$
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={AexampleTestConfiguration.class})
@WebAppConfiguration
public class LoginControllerTest {

//	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	private static @ILogger Logger logger;	
	
    @Autowired
    private WebApplicationContext ctx;
    
    private MockMvc mockMvc;
     
    @Configuration
    public static class TestConfiguration {
 
        @Bean public LoginController loginController() {
            return new LoginController();
        }
 
    }    
    
    @Before public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/pages/");
        viewResolver.setSuffix(".jsp");
 
        mockMvc = MockMvcBuilders.standaloneSetup(new LoginController())
                                 .setViewResolvers(viewResolver)
                                 .build();
    }

    @Test
    public void testHome() throws Exception {
        mockMvc.perform(get("/login")).andDo(print());
        
        mockMvc.perform(get("/login"))
            .andExpect(status().isOk())
            .andExpect(forwardedUrl("/WEB-INF/pages/login.jsp"));
            
        logger.info("Passed testHome test");
    }

    @Test
    public void testAdmin() throws Exception {

        mockMvc.perform(get("/admin"))
            .andExpect(status().isOk())
            .andExpect(forwardedUrl("/WEB-INF/pages/admin.jsp"));
        logger.info("Passed testAdmin test");
    }    

    /*
    @Test
    public void testDashboard() throws Exception {

        mockMvc.perform(get("/"))
            .andExpect(status().isOk())
            .andExpect(forwardedUrl("/WEB-INF/pages/dashboard.jsp"));
        logger.info("Passed testDashboard test");
    }
    */


}

