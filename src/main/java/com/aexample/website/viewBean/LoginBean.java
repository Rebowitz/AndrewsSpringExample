package com.aexample.website.viewBean;

public class LoginBean
{
		private String username;

		private String password;
		
		private String message;

		public String getUsername()
		{
				return this.username;
		}

		public void setUsername(String username)
		{
				this.username = username;
		}

		public void setPassword(String password)
		{
				this.password = password;
		}

		public String getPassword()
		{
				return this.password;
		}

		public void setMessage(String message){
			this.message = message;
		}
		
		public String getMessage(){
			return this.message;
		}
		
}
