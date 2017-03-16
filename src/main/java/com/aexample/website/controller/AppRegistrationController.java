/**
 * 
 */
package com.aexample.website.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * @author Main Login
 * $Rev$
 * $Date$
 *
 */

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.aexample.website.dto.RegistrationDto;

@RestController
public class AppRegistrationController {


    	@GetMapping("/rest/appregistration")
    	public ResponseEntity getRegistration(@RequestParam("deviceId") String deviceId,
    											@RequestParam("email") String email,
    											@RequestParam("password") String password) {
    		
    			
    		
    		
    		
    		
    		
    		RegistrationDto registrationDto = new RegistrationDto();
    			
    			

    		return new ResponseEntity(registrationDto, HttpStatus.OK);
    	}

    	
    	@PostMapping(value = "/rest/appregistration")
    	@ResponseStatus(HttpStatus.CREATED)
    	@ResponseBody
    	public ResponseEntity createRegistration(@RequestBody RegistrationDto regObject) {  
    		//RegistrationDto matches android app RegistrationObject
    		//establishes a guest registration in the database
    		
    		RegistrationDto registrationDto = new RegistrationDto();


    		return new ResponseEntity(registrationDto, HttpStatus.OK);
    	}

    	@DeleteMapping("/rest/appregistration")
    	public ResponseEntity deleteRegistration(@RequestBody RegistrationDto regObject) {

    		
    		RegistrationDto registrationDto = new RegistrationDto();
    		
    		return new ResponseEntity(registrationDto, HttpStatus.OK);

    	}

    	@PutMapping("/rest/appregistration")
    	public ResponseEntity updateRegistration(@RequestParam("deviceId") String deviceId,
													@RequestParam("email") String email,
													@RequestParam("password") String password) {

    		RegistrationDto registrationDto = new RegistrationDto();

    		return new ResponseEntity(registrationDto, HttpStatus.OK);
    	}

    }
    
   
