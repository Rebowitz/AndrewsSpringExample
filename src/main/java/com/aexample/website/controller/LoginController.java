	package com.aexample.website.controller;

	import org.slf4j.Logger;
	import org.slf4j.LoggerFactory;

	import org.springframework.stereotype.Controller;

	import org.springframework.web.bind.annotation.RequestMapping;
	import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.aexample.website.delegate.LoginDelegate;
import com.aexample.website.viewBean.LoginBean;


	@Controller
	public class LoginController
	{
		private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
		

		@RequestMapping(value = { "/", "/dashboard**" }, method = RequestMethod.GET)
		public ModelAndView dashPage() {

			ModelAndView model = new ModelAndView();
			model.addObject("title", "Spring Security Custom Login Form");
			model.addObject("message", "This is welcome page!");
			model.setViewName("dashboard");
			return model;

		}

		@RequestMapping(value = "/admin**", method = RequestMethod.GET)
		public ModelAndView adminPage() {

			ModelAndView model = new ModelAndView();
			model.addObject("title", "Spring Security Custom Login Form");
			model.addObject("message", "This is protected page!");
			model.setViewName("admin");

			return model;

		}

		@RequestMapping(value = "/login", method = RequestMethod.GET)
		public ModelAndView login(@RequestParam(value = "error", required = false) String error,
				@RequestParam(value = "logout", required = false) String logout) {

			ModelAndView model = new ModelAndView();
			if (error != null) {
				model.addObject("error", "Invalid username and password!");
			}

			if (logout != null) {
				model.addObject("msg", "You've been logged out successfully.");
			}
			model.setViewName("login");

			return model;

		}
}
