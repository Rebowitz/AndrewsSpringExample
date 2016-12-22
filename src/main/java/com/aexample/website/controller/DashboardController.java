package com.aexample.website.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;



	@Controller
	public class DashboardController
	{
		private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
		

		@RequestMapping(value = { "/dashboard**" }, method = RequestMethod.GET)
		public ModelAndView dashPage() {

			ModelAndView model = new ModelAndView();
			model.addObject("title", "Spring Security Custom Login Form");
			model.addObject("message", "This is welcome page!");
			model.addObject("message2", "Dashboard controller /dashboard mapping request method GET");
			model.setViewName("dashboard");
			return model;

		}

		@RequestMapping(value = "/dashboard", method = RequestMethod.POST)
		@ResponseStatus(value=HttpStatus.OK)
		public ModelAndView sendToDashboard() {
			ModelAndView model = new ModelAndView();
			model.addObject("title", "Spring Security Custom Login Form");
			model.addObject("message", "This is protected page!");
			model.addObject("message2", "Dashboard controller /dashboard mapping request method POST");
			
			model.setViewName("dashboard");

			return model;

		}	
		

}
