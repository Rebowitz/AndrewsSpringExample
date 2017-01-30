package com.aexample.website.validator;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import com.aexample.website.viewBean.RegistrationBean;

@Component
public class RegistrationValidator implements Validator {
		
		private static final Logger logger = LoggerFactory.getLogger(RegistrationValidator.class);			
	
		//which objects can be validated by this validator
		@Override
		public boolean supports(Class<?> paramClass) {
			return RegistrationBean.class.equals(paramClass);
		}

		@Override
		public void validate(Object obj, Errors errors) {
			logger.info("Entered registration validation");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "orgName", "NotEmpty.user.organization");			
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "NotEmpty.user.firstName");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "NotEmpty.user.lastName");			
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "loginId", "NotEmpty.user.loginId");

			logger.info("Exiting registration validation");
			
	

			
		}

}
