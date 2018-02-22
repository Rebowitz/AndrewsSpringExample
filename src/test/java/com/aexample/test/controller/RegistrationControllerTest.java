/**
 * 
 */
package com.aexample.test.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
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
import com.aexample.website.controller.RegistrationController;

/**
 * @author Robert B. Andrews
 * $Rev$
 * $Date$
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={AexampleTestConfiguration.class})
@WebAppConfiguration
public class RegistrationControllerTest {
//	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	private static @ILogger Logger logger;	
	
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
        mockMvc.perform(get("/user/registration")).andDo(print());
        
        mockMvc.perform(get("/user/registration"))
            .andExpect(status().isOk())
            .andExpect(forwardedUrl("/WEB-INF/pages/registration.jsp"));
            
        logger.info("Passed Registration Get test");
    }

    @Test
    public void testRegistrationConfirm() throws Exception {

        mockMvc.perform(get("/user/registrationConfirm"))
            .andExpect(status().isOk())
            .andExpect(forwardedUrl("/WEB-INF/pages/registrationConfirm.jsp"));
        logger.info("Passed RegistrationConfirm Get test");
    }    

/*    @Test
    public void testResendRegistrationToken() throws Exception {

        mockMvc.perform(get("/user/resendRegistrationToken"))
            .andExpect(status().isOk())
            .andExpect(forwardedUrl("/WEB-INF/pages/resendRegistrationToken.jsp"));
        logger.info("Passed testResendRegistrationToken test");
    }
*/
    
    @Test
    public void testResetPassword() throws Exception {

        mockMvc.perform(get("/user/resetPassword"))
            .andExpect(status().isOk())
            .andExpect(forwardedUrl("/WEB-INF/pages/resetPassword.jsp"));
        logger.info("Passed testResetPassword test");
    }    

    @Test
    public void testResetPasswordTokenConfirm() throws Exception {

        mockMvc.perform(get("/user/resetPasswordTokenConfirm"))
            .andExpect(status().isOk())
            .andExpect(forwardedUrl("/WEB-INF/pages/passwordUpdated.jsp"));
        logger.info("Passed testSavePassword test");
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
