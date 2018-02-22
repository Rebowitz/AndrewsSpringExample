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
import com.aexample.annotations.IValidPasswordReset;
import com.aexample.website.dto.PasswordDto;

//implemented via the @IPasswordMatches annotation in the UserDto class for the password

public class ResetPasswordMatchesValidator implements ConstraintValidator<IValidPasswordReset, Object> {
//    private final Logger logger = LoggerFactory.getLogger(getClass());
	private static @ILogger Logger logger;	
	
    @Override
    public void initialize(final IValidPasswordReset constraintAnnotation) {
        //
    }
    
    @Override
    public boolean isValid(final Object obj, final ConstraintValidatorContext context) {
        final PasswordDto passwordDto = (PasswordDto) obj;
        logger.debug("Validating password");
        
        if(!passwordDto.getPassword().equals(passwordDto.getMatchingPassword())){
        	logger.debug("Password validation failed - should never happen" );
        	return false;
        }
        
        return true;
    }
}
