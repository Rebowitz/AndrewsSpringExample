package com.aexample.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@ImportResource({"classpath:springBeanConfiguration.xml"})
@ComponentScan("com.aexample")
@EnableWebMvc
@Import(persistenceJPAConfig.class)
/*This is a shortcut, and though it may be useful in many situations, it’s not perfect. 
 * When more complex confguration is needed, remove the EnableWebMvc annotation and extend 
 * WebMvcConfgurationSupport directly. 
*/
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    public WebMvcConfig() {
        super();
    }

    // API
    @Override
    public void addViewControllers(final ViewControllerRegistry registry) {
        super.addViewControllers(registry);

//        registry.addViewController("/anonymous.html");

        registry.addViewController("/login.html");
        registry.addViewController("/registration.html");
        registry.addViewController("/dashboard.html");        
//        registry.addViewController("/console.html");
    }

    @Bean
    public ViewResolver viewResolver() {
        final InternalResourceViewResolver bean = new InternalResourceViewResolver();

        bean.setViewClass(JstlView.class);
        bean.setPrefix("/WEB-INF/pages/");
        bean.setSuffix(".jsp");

        return bean;
    }
}