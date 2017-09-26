/**
 * 
 */
package com.aexample.annotations;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.aexample.website.validation.EmailValidator;
/**
 * @author Main Login
 * $Rev$
 * $Date$
 *
 */
@Target({TYPE, FIELD, ANNOTATION_TYPE}) 
@Retention(RUNTIME)
@Constraint(validatedBy = EmailValidator.class)
@Documented
public @interface IValidPhoneNumber {
    String message() default "Invalid phone number";
    Class<?>[] groups() default {}; 
    Class<? extends Payload>[] payload() default {};
}
