/**
 * 
 */
package com.aexample.website.validator;

/**
 * @author Main Login
 * $Rev$
 * $Date$
 *
 */
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.aexample.website.viewBean.RegistrationBean;

public class PasswordMatchesValidator implements ConstraintValidator<IPasswordMatches, Object> {

    @Override
    public void initialize(final IPasswordMatches constraintAnnotation) {
        //
    }

    @Override
    public boolean isValid(final Object obj, final ConstraintValidatorContext context) {
        final RegistrationBean regBean = (RegistrationBean) obj;
        return regBean.getPassword().equals(regBean.getConfirmPassword());
    }

}
