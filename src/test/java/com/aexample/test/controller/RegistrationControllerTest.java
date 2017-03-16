/**
 * 
 */
package com.aexample.test.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.aexample.test.JUnitTestConfiguration;
import com.aexample.website.controller.RegistrationController;
import com.aexample.website.viewBean.RegistrationBean;
import com.aexample.test.config.TestConstants;
/**
 * @author Main Login
 * $Rev$
 * $Date$
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={JUnitTestConfiguration.class})
@WebAppConfiguration
public class RegistrationControllerTest {

	private static final Logger logger = LoggerFactory.getLogger(RegistrationController.class);
	
    @Autowired
    private WebApplicationContext ctx;
    
    private MockMvc mockMvc;
 
    @Configuration
    public static class TestConfiguration {
 
        @Bean public RegistrationController registrationController() {
            return new RegistrationController();
        }
 
    }     
    
    @Before public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/pages/");
        viewResolver.setSuffix(".jsp");
 
        mockMvc = MockMvcBuilders.standaloneSetup(new RegistrationController())
                                 .setViewResolvers(viewResolver)
                                 .build();
    }
	
    @Test
    public void testRegistration() throws Exception {

        mockMvc.perform(get("/registration"))
            .andExpect(status().isOk())
            .andExpect(forwardedUrl("/WEB-INF/pages/registration.jsp"));
        logger.info("Passed testRegistration test");
    }
    
  /*  @Test
    @Transactional
    public void testRegistrationPostForm() throws Exception {

  //need to check db for proper storage of record

        	mockMvc.perform(postRegistration())
        	
        					.andExpect(status().isOk());
 //       					.andExpect(view().name(VIEW_VALIDATION_FAIL))
 //       					.andExpect(model().attributeHasFieldErrors(MODEL_SPANNER, FIELD_SIZE));
        	
            logger.info("Passed testRegistrationPostForm test");
        
    }    
       
    private MockHttpServletRequestBuilder postRegistration(){
    	RegistrationBean registrationBean = new RegistrationBean();
    	
    	return post("/registration")
    			.param("orgName", TestConstants.RegistrationOrgName)
    			.param("firstName", TestConstants.RegistrationFirstName)
    			.param("lastName", TestConstants.RegistrationLastName)
    			.param("email",TestConstants.RegistrationEmail )
    			.param("loginId",TestConstants.RegistrationLoginId )
    			.param("password", TestConstants.RegistrationPassword)
    			.param("confirmPassword", TestConstants.RegistrationPassword)
    			.param("plan", TestConstants.RegistrationPlan);
    }
    */
    
}
