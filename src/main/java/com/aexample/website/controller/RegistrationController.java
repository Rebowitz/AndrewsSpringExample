package com.aexample.website.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.aexample.persistence.model.Accounts;
import com.aexample.website.service.IRegistrationService;
import com.aexample.website.validator.RegistrationValidator;
import com.aexample.website.viewBean.RegistrationBean;


@Controller
public class RegistrationController {

	private static final Logger logger = LoggerFactory.getLogger(RegistrationController.class);	
	
    @Autowired
    @Qualifier("registrationValidator")
    private RegistrationValidator registrationValidator;
    
    @Autowired
    private IRegistrationService rsi;
    
    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(registrationValidator);
    }
    
    @ModelAttribute("registrationBean")
    public RegistrationBean createRegistrationBean(){
    	// Model Attribute must be the same name as used in the registration.jsp form header
    	return new RegistrationBean();
    }
    
	@RequestMapping(value="/registration",method=RequestMethod.GET)
	public ModelAndView displayRegistration(HttpServletRequest request, HttpServletResponse response)

	{
		logger.info("Entered Registration Controller GET");
		ModelAndView model = new ModelAndView("registration");			
		RegistrationBean regBean = new RegistrationBean();
		model.addObject("regBean", regBean);
		logger.info("Created modelandview GET\n");
		
		return model;
	}	
	
	
	@RequestMapping(value="/registration",method = RequestMethod.POST)
	public String submitForm(Model model, @ModelAttribute("registrationBean") @Validated RegistrationBean registrationBean, BindingResult result) {
		logger.info("Processing registration POST");
		
		model.addAttribute("registration", registrationBean);
		String returnVal = "registration";
//		RegistrationServiceImpl rsi = new RegistrationServiceImpl();

		
		if(result.hasErrors()) {
			logger.info("Result has errors");
			logger.info("Result errors are:");
			
			for(ObjectError error : result.getAllErrors()){
				logger.info(error.getDefaultMessage());
			}
			
//			initModelList(model);
			returnVal = "registration";
		} else {
			model.addAttribute("registration", registrationBean);
			//persist the record to the database
			logger.info("Validation with no errors");
			logger.info("populating account object to save in db");
		
			
		//	SessionFactory hibSessFact = HibernateSessionFactory.getSessionFactory();
		//	Session hibSession = hibSessFact.openSession();
			
			Accounts account = new Accounts();
			account.setOrgName(registrationBean.getOrgName());
			account.setFirstName(registrationBean.getFirstName());
			account.setLastName(registrationBean.getLastName());
			account.setEmail(registrationBean.getEmail());
			account.setLoginId(registrationBean.getLoginId());
			account.setProfileFlag(Boolean.FALSE);
			account.setPassword(registrationBean.getPassword());
			account.setPlan(registrationBean.getPlan());
			account.setAccountNonExpired(Boolean.TRUE);
			account.setAccountNonLocked(Boolean.TRUE);
			account.setEnabled(Boolean.TRUE);
			account.setRole("ROLE_USER");
			account.setCreateDate(new Date());
			account.setCredentialsNonExpired(Boolean.TRUE);
			//account.setPlan(planIndicator);
			
			logger.info("persisting account info");
			rsi.create(account);
			
			returnVal = "Registered";
		}		
		return returnVal;
	}


}	