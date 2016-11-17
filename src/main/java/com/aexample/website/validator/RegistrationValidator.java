package com.aexample.website.validator;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.aexample.website.controller.RegistrationController;
import com.aexample.website.viewBean.RegistrationBean;

@Component
public class RegistrationValidator implements Validator {
		
		private static final Logger logger = LoggerFactory.getLogger(RegistrationValidator.class);			
	
		private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		
		@Override
		public boolean supports(Class<?> paramClass) {
			return RegistrationBean.class.equals(paramClass);
		}

		@Override
		public void validate(Object obj, Errors errors) {
			logger.info("Entered registration validation");
			RegistrationBean form = (RegistrationBean) obj;
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "orgName", "valid.orgname");			
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "valid.name");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "valid.name");			
			if(!form.getEmail().matches(EMAIL_PATTERN)) {
				errors.rejectValue("email","valid.email");
			}
			
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "loginId", "valid.id");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "valid.password");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", "valid.passwordConf");
//			if (!form.getPassword().equals(form.getConfirmPassword())) {
//				errors.rejectValue("confirmPassword", "valid.passwordConfDiff");
//			}		
			logger.info("Exiting registration validation");
			
		}

}
