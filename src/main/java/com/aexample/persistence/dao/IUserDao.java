package com.aexample.persistence.dao;

import java.sql.SQLException;

import com.aexample.persistence.model.Accounts;

/**
 * @author RBA
 * This interface will be used to communicate with the
 * Database
 */
public interface IUserDao
{
		public Accounts isValidUser(String username, String password) throws SQLException;
}
