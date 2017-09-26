package com.aexample.website.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.aexample.annotations.ILogger;



	@Controller
	public class LoginController
	{
//	    private static Logger logger = LoggerFactory.getLogger(LoginController.class);
		private static @ILogger Logger logger;	
		
		@RequestMapping(value = { "/", "/welcome**" }, method = RequestMethod.GET)
		public ModelAndView defaultPage() {

		  ModelAndView model = new ModelAndView();
		  model.addObject("title", "Spring Security Login Form - Database Authentication");
		  model.addObject("message", "This is default page!");
		  model.setViewName("hello");
		  return model;

		}	    
	    
		@RequestMapping(value = "/admin**", method = RequestMethod.GET)
		public ModelAndView adminPage() {

			logger.info("admin controller");
			
			ModelAndView model = new ModelAndView();
			model.addObject("title", "Spring Security Custom Login Form");
			model.addObject("message", "This is protected page!");
			model.setViewName("admin");

			return model;

		}

		/*
		 * Spring security configuration will redirect a sucessful login to the dashboard page. 
		 * Therefore there is no /login POST RequestMapping
		 */		
		
		@RequestMapping(value = "/login", method = RequestMethod.GET)
		public ModelAndView login(@RequestParam(value = "error", required = false) String error,
				@RequestParam(value = "logout", required = false) String logout,
                HttpServletRequest request) { 

			ModelAndView model = new ModelAndView();
			if (error != null) {
			//	model.addObject("error", "Invalid username and password!");
				
				model.addObject("error", getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION"));
			}

			if (logout != null) {
				model.addObject("msg", "You've been logged out successfully.");
			}
			model.setViewName("login");
			
			logger.info("login controller");

			return model;

		}
		/**
		 * @param request
		 * @param string
		 * @return
		 */
		private Object getErrorMessage(HttpServletRequest request, String key) {
			Exception exception =
	                   (Exception) request.getSession().getAttribute(key);

			String error = "";
			if (exception instanceof BadCredentialsException) {
				error = "Invalid username or password!";
			}else if(exception instanceof LockedException) {
				error = "User account is locked!";
			}else{
				error = "Invalid username or password!";
			}

			return error;
		}
		//for 403 access denied page
		@RequestMapping(value = "/403", method = RequestMethod.GET)
		public ModelAndView accesssDenied() {

		  ModelAndView model = new ModelAndView();

		  //check if user is login
		  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		  if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			model.addObject("username", userDetail.getUsername());
		  }

		  model.setViewName("403");
		  return model;

		}
}
