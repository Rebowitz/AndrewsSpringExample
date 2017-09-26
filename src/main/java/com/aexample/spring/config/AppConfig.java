package com.aexample.spring.config;

import java.util.Properties;

import org.slf4j.Logger;
import org.springframework.aop.interceptor.DebugInterceptor;
import org.springframework.aop.interceptor.SimpleTraceInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.EnableScheduling;
//@PropertySource(value = "classpath:email.properties", ignoreResourceNotFound = true)
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import com.aexample.annotations.ILogger;


@Configuration
@EnableAspectJAutoProxy
@EnableScheduling
@PropertySources({@PropertySource("classpath:email.properties")})
public class AppConfig {
//    private final Logger logger = LoggerFactory.getLogger(getClass());
	private static @ILogger Logger logger;	
	
    @Autowired
    private Environment env;

    // beans

    @Bean()
    public static PropertySourcesPlaceholderConfigurer propertyPlaceHolderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    //production level email service
    @Bean
    public JavaMailSenderImpl javaMailSenderImpl() {
        final JavaMailSenderImpl mailSenderImpl = new JavaMailSenderImpl();

        try {
            mailSenderImpl.setHost(env.getRequiredProperty("smtp.host"));
            mailSenderImpl.setPort(env.getRequiredProperty("smtp.port", Integer.class));
            //mailSenderImpl.setProtocol(env.getRequiredProperty("smtp.protocol"));
            mailSenderImpl.setUsername(env.getRequiredProperty("smtp.username"));
            mailSenderImpl.setPassword(env.getRequiredProperty("smtp.password"));
        } catch (IllegalStateException ise) {
            logger.error("Could not resolve email.properties.  See email.properties.sample");
            throw ise;
        }
        final Properties javaMailProps = new Properties();
        javaMailProps.put("mail.smtp.auth", true);
        //transport layer security
        javaMailProps.put("mail.smtp.starttls.enable", false);
        javaMailProps.put("mail.transport.protocol", "smtp");
        javaMailProps.put("mail.debug", env.getRequiredProperty("smtp.debug")); //prints out everything
        mailSenderImpl.setJavaMailProperties(javaMailProps);
        return mailSenderImpl;
    }

    
	@Bean(name="simpleTraceInterceptor")
	public SimpleTraceInterceptor getSimpleTraceInterceptor(){
		SimpleTraceInterceptor simpleTraceInterceptor = new SimpleTraceInterceptor();
		simpleTraceInterceptor.setUseDynamicLogger(true);
		
		return simpleTraceInterceptor;
	}
	
	@Bean(name="debugInterceptor")
	public DebugInterceptor getDebugInterceptor(){
		DebugInterceptor debugInterceptor = new DebugInterceptor();
		debugInterceptor.setUseDynamicLogger(true);
		return debugInterceptor;
	}
	
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/", "/resources/");
    }

    public void addInterceptors(final InterceptorRegistry registry) {
        final LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("lang");
        registry.addInterceptor(localeChangeInterceptor);
    }

    // beans
   
	@Bean(name="messageSource")
	public MessageSource resourceBundleMessageSource(){
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages");
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setUseCodeAsDefaultMessage(true);
        messageSource.setFallbackToSystemLocale(true);
        return messageSource;
	}
	
}