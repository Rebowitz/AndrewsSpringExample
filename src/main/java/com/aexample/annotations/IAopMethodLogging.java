/**
 * 
 */
package com.aexample.annotations;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * @author Main Login
 * $Rev$
 * $Date$
 *
 */
@Target({TYPE, METHOD, ANNOTATION_TYPE}) 
@Retention(RUNTIME)
@Documented
public @interface IAopMethodLogging {
	
}