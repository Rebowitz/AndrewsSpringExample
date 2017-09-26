package com.aexample.spring.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.aexample.email.CustomHTMLMailer;
import com.aexample.website.delegate.LoginDelegate;
import com.aexample.website.validation.EmailValidator;
import com.aexample.website.validation.PasswordMatchesValidator;
import com.aexample.website.validation.RegistrationValidator;

//@Import({PersistenceJPAConfig.class,AuthenticationJPAConfig.class})


/*This is a shortcut, and though it may be useful in many situations, it’s not perfect. 
 * When more complex confguration is needed, remove the EnableWebMvc annotation and extend 
 * WebMvcConfgurationSupport directly. 
*/
@Configuration
@EnableWebMvc
@EnableAspectJAutoProxy
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    // API
	//overrids WebMvcConfigurationSupport#viewControllerHandlerMapping
    @Override
    public void addViewControllers(final ViewControllerRegistry registry) {
        super.addViewControllers(registry);

        registry.addViewController("/").setViewName("index");
        registry.addViewController("/login").setViewName("login");
//        registry.addViewController("/user/registration");
        registry.addRedirectViewController("/registration", "/user/registration");        
        registry.addViewController("/dashboard").setViewName("dashboard");
        registry.addViewController("/logout");
        registry.addViewController("/expiredAccount").setViewName("expiredAccount");        
        registry.addViewController("/badUser");
        registry.addViewController("/invalidSession");
        registry.addViewController("/console");
        registry.addViewController("/admin");
        registry.addViewController("/forgotPassword");
        registry.addViewController("/updatePassword");
        registry.addViewController("/changePassword");
        registry.addViewController("/user");
 //       registry.addViewController("/qrcode");

    }

   @Bean
    public ViewResolver viewResolver() {
        final InternalResourceViewResolver bean = new InternalResourceViewResolver();

        bean.setViewClass(JstlView.class);
        bean.setPrefix("/WEB-INF/pages/");
    //    bean.setPrefix("/");        
        bean.setSuffix(".jsp");
        bean.setOrder(3);

        return bean;
    }
 
    @Override
    public void configureDefaultServletHandling(final DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }    
      
    @Bean
    public EmailValidator usernameValidator() {
        return new EmailValidator();
    }

    @Bean
    public PasswordMatchesValidator passwordMatchesValidator() {
        return new PasswordMatchesValidator();
    }

    @Bean
    @ConditionalOnMissingBean(RequestContextListener.class)
    public RequestContextListener requestContextListener() {
        return new RequestContextListener();
    }    
    
    @Bean
    public LoginDelegate loginDelegate(){
    	return new LoginDelegate();
    }

    @Bean
    public RegistrationValidator registrationValidator(){
    	return new RegistrationValidator();
    }

    @Bean
    public InitialDataLoader initialDataLoader(){
    	return new InitialDataLoader();
    }

    @Bean
    public CustomHTMLMailer customHTMLMailer(){
    	return new CustomHTMLMailer();
    }

}