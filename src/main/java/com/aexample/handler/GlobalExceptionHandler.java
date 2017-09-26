/**
 * 
 */
package com.aexample.handler;

import java.io.IOException;

import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.aexample.annotations.ILogger;
import com.aexample.website.exception.UserNotFoundException;

/**
 * @author Main Login $Rev$ $Date$
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler {

//	Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	private static @ILogger Logger logger;	
	
	// @ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="Not all mandatory
	// fields are filled")
	// @ExceptionHandler(EmptyFieldException.class)
	// public void handleEmptyFieldException(EmptyFieldException e){
	// logger.error("Not all mandatory fields are filled", e);
	// }

	@ExceptionHandler(IOException.class)
	public ModelAndView myError(Exception exception) {
		System.out.println("----Caught IOException----");
		ModelAndView mav = new ModelAndView();
		mav.addObject("exception", exception);
		mav.setViewName("globalerror");
		return mav;
	}

	//working version at end of Registration Controller class
/*	@ExceptionHandler(UserAlreadyExistsException.class)
	public ModelAndView handleUserAlreadyExistsException(UserAlreadyExistsException e, Model model) {
		logger.error("User already exists", e);
		UserDto userDto = new UserDto();
		model.addAttribute(userDto);
		ModelAndView mav = new ModelAndView("registration","user",model);
		return mav;		
		
	}
*/
	
	@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "User does not exist")
	@ExceptionHandler(UserNotFoundException.class)
	public void handleUserNotFoundException(UserNotFoundException e) {
		logger.error("User does not exist", e);
	}

}
