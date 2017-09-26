package com.aexample.website.delegate;

import com.aexample.website.service.IUserService;


public class LoginDelegate
{
	
		private IUserService iUserService;

		public IUserService getUserService()
		{
				return this.iUserService;
		}

		public void setUserService(IUserService iUserService)
		{
				this.iUserService = iUserService;
		}
/*
		public Accounts isValidUser(String username, String password) throws SQLException 
		{
					
		    return iUserService..isValidUser(username, password);
		}
*/		
}
