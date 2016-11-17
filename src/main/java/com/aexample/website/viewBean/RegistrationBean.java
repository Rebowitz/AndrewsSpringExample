package com.aexample.website.viewBean;

import org.springframework.stereotype.Component;

@Component
public class RegistrationBean implements java.io.Serializable {


		/**
	 * 
	 */
	private static final long serialVersionUID = 1486187074594805268L;
	
		private Integer id;
		private String orgName;
		private String firstName;
		private String lastName;
		private String email;
		private String loginId;
		private String password;
		private String confirmPassword;
		private String plan;

		public RegistrationBean() {
		}

		public RegistrationBean(String orgName, String firstName, String lastName,
				String email, String loginId, String password, String confirmPassword,
				String plan) {
			this.orgName = orgName;
			this.firstName = firstName;
			this.lastName = lastName;
			this.email = email;
			this.loginId = loginId;
			this.password = password;
			this.confirmPassword = confirmPassword;
			this.plan = plan;
		}

		public Integer getId() {
			return this.id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getOrgName() {
			return this.orgName;
		}

		public void setOrgName(String orgName) {
			this.orgName = orgName;
		}

		public String getFirstName() {
			return this.firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getLastName() {
			return this.lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		public String getEmail() {
			return this.email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getLoginId() {
			return this.loginId;
		}

		public void setLoginId(String loginId) {
			this.loginId = loginId;
		}

		public String getPassword() {
			return this.password;
		}

		public void setPassword(String password) {
			this.password = password;
		}
		
		public String getConfirmPassword() {
			return this.confirmPassword;
		}

		public void setConfirmPassword(String confirmPassword) {
			this.confirmPassword = confirmPassword;
		}
		
		public String getPlan() {
			return this.plan;
		}

		public void setPlan(String plan) {
			this.plan = plan;
		}

}
