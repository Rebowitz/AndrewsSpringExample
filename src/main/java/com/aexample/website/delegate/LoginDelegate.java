package com.aexample.website.delegate;

import java.sql.SQLException;
//import com.aexample.persistence.model.Accounts;
//import com.aexample.persistence.model.AccountsHome;



import org.springframework.beans.factory.annotation.Autowired;

import com.aexample.persistence.model.Accounts;
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

		public Accounts isValidUser(String username, String password) throws SQLException 
		{
					
		    return iUserService.isValidUser(username, password);
		}
}
