/**
 * 
 */
package com.aexample.email;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.aexample.persistence.model.UserAccount;
import com.aexample.persistence.model.UserVerificationToken;
import com.aexample.website.dto.EmailDto;
import com.aexample.website.service.IUserService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Main Login $Rev$ $Date$
 *
 */
@Component
public class CustomHTMLMailer {

	/**
	 * @throws IOException
	 * 
	 */

	@Autowired
	IUserService userService;

	@Autowired
	JavaMailSenderImpl javaMailSenderImpl;

	/*
	 * Note: when using the annotation injection of the logger, injected loggers
	 * are not available until after the initalization of the bean therefore,
	 * logging statements in the constructor will cause a NPE. The
	 * buildConfigStructure method below is called by the constructor and
	 * therefore throws the NPE. Switch to the Logger Factory statement below to
	 * log messages in the buildConfigStructure method as needed.
	 */

	Logger logger = LoggerFactory.getLogger(CustomHTMLMailer.class);
	// private static @ILogger Logger logger;

	Map<String, Object> emailConfigMap;

	public CustomHTMLMailer() {
		super();

		buildConfigStructure();
		// TODO Auto-generated constructor stub
	}

	private void buildConfigStructure() {

		Resource resource = new ClassPathResource("email_configs.json");

		// logger.info("Building CustomHTMLMailer configuration structure");
		ObjectMapper objectMapper = new ObjectMapper();

		// need stream
		List<EmailDto> listEmailConfigs = null;
		try {
			listEmailConfigs = objectMapper.readValue(resource.getInputStream(), new TypeReference<List<EmailDto>>() {
			});

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		emailConfigMap = new HashMap<String, Object>();

		Iterator<EmailDto> iter = listEmailConfigs.iterator();

		while (iter.hasNext()) {
			EmailDto transferDto = iter.next();
			emailConfigMap.put(transferDto.getConfigname(), transferDto);
		}
		// logger.info("CustomHTMLMailer configuration completed");
	}

	public final String rockinTheEmailMessage(UserAccount user, String configname, String appUrl) {

		// get the email config from the config structure
		EmailDto emailDto = (EmailDto) emailConfigMap.get(configname);

		//here for documentation purposes
		final String TOKEN_INVALID = "invalidToken";
		final String TOKEN_EXPIRED = "expired";
		final String TOKEN_VALID = "valid";

		String msg = null;
		String token = null;
		String confirmationUrl = null;
		String theToken = null;
		String result = null;

		if (emailDto.getTokenneeded().booleanValue() == true) {

			UserVerificationToken uvtoken = userService.findTokenByUser(user);
			if(uvtoken != null){
				 theToken = uvtoken.getToken();
				//checks expiry date
				 result = userService.validateVerificationToken(theToken);
			}else{
				result = TOKEN_INVALID;
			}
			
			if (result.equalsIgnoreCase(TOKEN_VALID)) {

				logger.debug("Using valid token from repository");
				confirmationUrl = appUrl + emailDto.getUrlsuffix() + theToken;

			} else if (result.equalsIgnoreCase(TOKEN_EXPIRED)) {
				//stores old token, generates new token
				UserVerificationToken newToken = userService.generateNewVerificationToken(theToken);
				confirmationUrl = appUrl + emailDto.getUrlsuffix() + newToken.getToken();

			} else { // TOKEN INVALID RETURNED --this means there is no token
						// for the user
				logger.debug("creating verification token");
				token = userService.generateTokenValue();
				userService.createVerificationTokenForUser(user, token);
				confirmationUrl = appUrl + emailDto.getUrlsuffix() + token;
			}

		} else {
			confirmationUrl = appUrl + emailDto.getUrlsuffix();
		}

		emailDto.setToaddress(user.getEmail());
		emailConfigMap.put(configname, emailDto);

		// Set key values--identify location in email for button confirmation
		// link
		Map<String, String> input = new HashMap<String, String>();
		input.put(emailDto.getPlaceholder(), confirmationUrl);
		try {
			msg = readEmailFromHtml(configname, input);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String returnTxt = sendTheEmail(msg, emailDto);

		return ("email process completed - " + returnTxt);
	}

	// Method to replace the values for keys in HTML Emails
	private String readEmailFromHtml(String configName, Map<String, String> input) throws IOException {
		// take the configname and get the proper emailDto for the message type

		EmailDto emailDto = (EmailDto) emailConfigMap.get(configName);

		String emailTemplate = emailDto.getFilename();

		logger.debug("EmailDTO configuration contents");
		logger.debug("Config Name: " + emailDto.getConfigname());
		logger.debug("File Name: " + emailDto.getFilename());
		logger.debug("To Address: " + emailDto.getToaddress());
		logger.debug("From Address: " + emailDto.getFromaddress());
		logger.debug("Subject: " + emailDto.getSubject());
		logger.debug("Placeholder: " + emailDto.getPlaceholder());
		logger.debug("Mimetype: " + emailDto.getMimetype());
		logger.debug("Multipart: " + emailDto.getMultipart());
		logger.debug("EMAIL TEMPLATE: " + emailTemplate + "==checking for trailing characters");

		String msg = readContentFromFile(emailTemplate);
		try {
			Set<Entry<String, String>> entries = input.entrySet();
			for (Map.Entry<String, String> entry : entries) {
				msg = msg.replace(entry.getKey().trim(), entry.getValue().trim());
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return msg;
	}

	// Method to read HTML file as a String
	// call this if you have nothing to substitute in the text of the email
	private String readContentFromFile(String fileName) throws IOException {
		StringBuffer contents = new StringBuffer();

		logger.debug("Entered readContentFromFile: " + fileName);

		Resource resource = null;

		try {
			resource = new ClassPathResource(fileName);
		} catch (NullPointerException npe) {
			logger.debug("More resource classpath madness");
			npe.printStackTrace();
		}

		try {
			logger.debug("Getting inputstream for html file");
			InputStream is = resource.getInputStream();
			// use buffering, reading one line at a time
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));

			String line = null;
			while ((line = reader.readLine()) != null) {
				contents.append(line);
				contents.append(System.getProperty("line.separator"));
			}
			reader.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return contents.toString();

	}

	private String sendTheEmail(String msg, EmailDto emailDto) {

		logger.debug("Entered SendTheEmail");
		logger.debug(msg);

		javaMailSenderImpl = new JavaMailSenderImpl();

		MimeMessage message = null;

		try {
			message = javaMailSenderImpl.createMimeMessage();
			MimeMessageHelper mimeMessage = new MimeMessageHelper(message, emailDto.getMultipart().booleanValue());// true
																													// means
																													// multipart
																													// messagae
			mimeMessage.setTo(new InternetAddress(emailDto.getToaddress()));
			mimeMessage.setFrom(new InternetAddress(emailDto.getFromaddress()));
			mimeMessage.setSubject(emailDto.getSubject());
			if (emailDto.getMimetype().equals("html")) {
				mimeMessage.setText(msg, true);// mimetype true means text is
												// html
			} else {
				mimeMessage.setText(msg, false);// mimetype true means text is
												// html
			}
		} catch (NullPointerException npe) {
			logger.debug("MimeMessage null pointer exception");
			npe.printStackTrace();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		javaMailSenderImpl.send(message);
		return ("Mail sent!");

	}

}
