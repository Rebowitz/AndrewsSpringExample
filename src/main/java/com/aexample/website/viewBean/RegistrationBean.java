package com.aexample.website.viewBean;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;

import com.aexample.annotations.IValidEmail;
import com.aexample.annotations.IValidPassword;

@IValidPassword
@Component
public class RegistrationBean implements java.io.Serializable {


		/**
	 * 
	 */
	private static final long serialVersionUID = 1486187074594805268L;
	
		private Integer id;

		@NotNull
	    @NotEmpty
		private String orgName;
		
		@NotNull
	    @NotEmpty
		private String firstName;
		
		@NotNull
	    @NotEmpty
		private String lastName;
		
		@IValidEmail
		@NotNull
	    @NotEmpty @Email
		private String email;
		
		@NotNull
	    @NotEmpty
		private String loginId;
		
		@NotNull
	    @NotEmpty
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

		/* (non-Javadoc)
		 * @see java.lang.Object#hashCode()
		 */
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((confirmPassword == null) ? 0 : confirmPassword.hashCode());
			result = prime * result + ((email == null) ? 0 : email.hashCode());
			result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
			result = prime * result + ((id == null) ? 0 : id.hashCode());
			result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
			result = prime * result + ((loginId == null) ? 0 : loginId.hashCode());
			result = prime * result + ((password == null) ? 0 : password.hashCode());
			result = prime * result + ((plan == null) ? 0 : plan.hashCode());
			return result;
		}

		/* (non-Javadoc)
		 * @see java.lang.Object#equals(java.lang.Object)
		 */
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			RegistrationBean other = (RegistrationBean) obj;
			if (confirmPassword == null) {
				if (other.confirmPassword != null)
					return false;
			} else if (!confirmPassword.equals(other.confirmPassword))
				return false;
			if (email == null) {
				if (other.email != null)
					return false;
			} else if (!email.equals(other.email))
				return false;
			if (firstName == null) {
				if (other.firstName != null)
					return false;
			} else if (!firstName.equals(other.firstName))
				return false;
			if (id == null) {
				if (other.id != null)
					return false;
			} else if (!id.equals(other.id))
				return false;
			if (lastName == null) {
				if (other.lastName != null)
					return false;
			} else if (!lastName.equals(other.lastName))
				return false;
			if (loginId == null) {
				if (other.loginId != null)
					return false;
			} else if (!loginId.equals(other.loginId))
				return false;
			if (password == null) {
				if (other.password != null)
					return false;
			} else if (!password.equals(other.password))
				return false;
			if (plan == null) {
				if (other.plan != null)
					return false;
			} else if (!plan.equals(other.plan))
				return false;
			return true;
		}

}
