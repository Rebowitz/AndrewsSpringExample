/**
 *
 */
package com.aexample.website.service;

import java.sql.SQLException;

import com.aexample.persistence.model.Accounts;

/**
 * @author RBA
 *
 */
public interface IUserService
{
		public Accounts isValidUser(String username, String password) throws SQLException;

		public Accounts findOne(Long id);

		public Long create(Accounts resource);
		
		public Long update(Accounts resource);
		
		public Long getById(Long id);
		
		public Long deleteById(Long id);
		
		public Accounts getByLoginId(String loginId);
		
		public String serviceInstantiated();
}
