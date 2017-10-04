/**
 * 
 */
package com.aexample.aop.webcontext;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.aexample.persistence.model.ActivityLog;
import com.aexample.persistence.model.UserAccount;
import com.aexample.persistence.model.UserVerificationToken;
import com.aexample.persistence.repositories.IActivityLogRepository;
import com.aexample.persistence.repositories.IUserRepository;
import com.aexample.website.controller.RegistrationController;
import com.aexample.website.dto.UserDto;
import com.aexample.website.service.IUserService;
import com.aexample.website.service.impl.UserServiceImpl;

/**
 * @author Main Login
 * $Rev$
 * $Date$
 *
 */
@Aspect
@Component
public class ActivityLogAspect {
    private final Logger logger = LoggerFactory.getLogger(getClass());
//	private static @ILogger Logger logger;	
	
    @Autowired
    IUserRepository userRepository;
    
    @Autowired
    IActivityLogRepository activityLogRepository;
    
    @Autowired
    private IUserService userService;
    
	private ActivityLog buildActivityLog(UserAccount user, JoinPoint jp, String outcome) throws NoSuchFieldException, SecurityException, IllegalAccessException{
    	
    	Map<String,String> methodNote = new HashMap<String,String>();
    	methodNote.put("registerUserAccount","Create user, send confirmation email");
    	methodNote.put("confirmRegistration","Set Account enabled to true, redirect to reg confirmed page");
    	methodNote.put("resendRegistrationToken","Regenerate Token, send confirmation email");
    	methodNote.put("resetPassword","Create reset token, send confirmation email");
    	methodNote.put("changePassword","Authenticate user, redirect to update password page");
    	methodNote.put("savePassword","Updated password, send confirmation email");
    	
		Signature signature = jp.getSignature();
		logger.debug("Signature declaring type: " + signature.getDeclaringTypeName());
		logger.debug("Signature name: " + signature.getName());
			
		ActivityLog activityLog = new ActivityLog();
		activityLog.setAccountId(user.getId());
		activityLog.setUserId(user.getEmail());
		activityLog.setDateTime(new Date());
		activityLog.setActivity(signature.getName());
		activityLog.setAction(signature.getDeclaringTypeName());
		activityLog.setOutcome(outcome);
		activityLog.setNote(methodNote.get(signature.getName()));
    	
		return activityLog;

    }
    

	@After("logRegisterUserAccountActivity()")
	public void logRegisterUserAccountActivityAdvice(JoinPoint joinPoint)
			throws NoSuchFieldException, SecurityException, IllegalAccessException {

		UserDto userDto = null;
		UserAccount user = null;

		Object[] args = joinPoint.getArgs();

		for (int i = 0; i < args.length; i++) {
			if (args[i] instanceof UserDto) {
				userDto = (UserDto) args[i];
				user = userRepository.findByEmail(userDto.getEmail());
				break;
			}
		}

		ActivityLog activityLog = buildActivityLog(user, joinPoint, "Completed");

		logger.debug(activityLog.toString());
		activityLogRepository.save(activityLog);

		logger.debug("logAfter logRegisterUserAccountActivityAdvice");
		logger.debug(joinPoint.getSignature().getName() + " is completed! from logAfter");
	}
	
/*	@AfterThrowing("com.aexample.website.controller.RegistrationController()")
	public void logExceptionRegistrationController(){
		logger.debug("Caught exception from RegistrationController");
	}
*/	
	
	@After("logConfirmRegistrationActivity()")
	public void logConfirmRegistrationActivityAdvice(JoinPoint joinPoint)
			throws NoSuchFieldException, SecurityException, IllegalAccessException {
		Object[] args = joinPoint.getArgs();

		for (int i = 0; i < args.length; i++) {
			Object obj = args[i];
			logger.debug("Object: " + args[i]);

			if (obj instanceof String) {

				String token = obj.toString();
				int occurence = StringUtils.countOccurrencesOf(token, "-");

				if (token.length() == 36 && occurence == 4) {
					logger.debug("Token found");

					final UserVerificationToken verificationToken = userService.getVerificationToken(token);
					final UserAccount user = verificationToken.getUser();

					ActivityLog activityLog = buildActivityLog(user, joinPoint, "Completed");

					logger.debug(activityLog.toString());
					activityLogRepository.save(activityLog);

				}
			}
		}

	}

	@After("logResendRegistrationTokenActivity()")
	public void logResendRegistrationTokenActivityAdvice(JoinPoint joinPoint) throws NoSuchFieldException, SecurityException, IllegalAccessException{
		
		//we have generated a new registration token already and the previous token
		//passed in by the joinpoint object is stored in the verificationToken db as previousToken
		Object[] args = joinPoint.getArgs();

		for (int i = 0; i < args.length; i++) {
			Object obj = args[i];
			logger.debug("Object: " + args[i]);

			if (obj instanceof String) {

				String token = obj.toString();
				int occurence = StringUtils.countOccurrencesOf(token, "-");

				if (token.length() == 36 && occurence == 4) {
					logger.debug("Token found");

					final UserVerificationToken verificationToken = userService.getPreviousToken(token);
					final UserAccount user = verificationToken.getUser();

					ActivityLog activityLog = buildActivityLog(user, joinPoint, "Completed");

					logger.debug(activityLog.toString());
					activityLogRepository.save(activityLog);

				}
			}
		}		

	}

	@After("logResetPasswordActivity()")
	public void logResetPasswordActivityAdvice(JoinPoint joinPoint){
		
	}
	
	@After("logChangePasswordActivity()")
	public void logChangePasswordActivityAdvice(JoinPoint joinPoint){
		
	}
	
	@After("logSavePasswordActivity()")
	public void logSavePasswordActivityAdvice(JoinPoint joinPoint){
		
	}	
	
	/*
	 * POINTCUT DEFINITIONS HERE
	 */


	
	@Pointcut("execution(* com.aexample.website.controller.RegistrationController.registerUserAccount(..))")
	public void logRegisterUserAccountActivity(){}

	@Pointcut("execution(* com.aexample.website.controller.RegistrationController.confirmRegistration(..))")
	public void logConfirmRegistrationActivity(){}
	
	@Pointcut("execution(* com.aexample.website.controller.RegistrationController.resendRegistrationToken(..))")
	public void logResendRegistrationTokenActivity(){}
	
	@Pointcut("execution(* com.aexample.website.controller.RegistrationController.resetPassword(..))")
	public void logResetPasswordActivity(){}
	
	@Pointcut("execution(* com.aexample.website.controller.RegistrationController.changePassword(..))")
	public void logChangePasswordActivity(){}
	
	@Pointcut("execution(* com.aexample.website.controller.RegistrationController.savePassword(..))")
	public void logSavePasswordActivity(){}
	
	
	
	
	// #########################################################
/*
	@Before("allValidatorMethodsPointcut()")
	public void allValidatorMethodsAdvice() {
		logger.debug("Before executing validation method");
	}

	@Pointcut("within(com.aexample.website.validation.*.*)")
	public void allValidatorMethodsPointcut() {
	}
*/	
}
