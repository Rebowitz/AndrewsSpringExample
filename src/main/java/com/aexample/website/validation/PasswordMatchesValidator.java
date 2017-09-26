/**
 * 
 */
package com.aexample.website.validation;

/**
 * @author Main Login
 * $Rev$
 * $Date$
 *
 */
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.slf4j.Logger;

import com.aexample.annotations.ILogger;
import com.aexample.annotations.IValidPassword;
import com.aexample.website.dto.UserDto;

//implemented via the @IPasswordMatches annotation in the UserDto class for the password

public class PasswordMatchesValidator implements ConstraintValidator<IValidPassword, Object> {
//    private final Logger logger = LoggerFactory.getLogger(getClass());
	private static @ILogger Logger logger;	
	
    @Override
    public void initialize(final IValidPassword constraintAnnotation) {
        //
    }
    
    @Override
    public boolean isValid(final Object obj, final ConstraintValidatorContext context) {
        final UserDto userDto = (UserDto) obj;
        logger.debug("Validating password");
        return userDto.getPassword().equals(userDto.getMatchingPassword());
    }
}
