package com.aexample.website.service.impl;

import java.sql.SQLException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aexample.persistence.dao.IUserDao;
import com.aexample.persistence.model.Accounts;
import com.aexample.website.service.IUserService;

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;


@Service
@Transactional
public class UserServiceImpl implements IUserService
{

		private IUserDao iUserDao;

		public IUserDao getUserDao()
		{
				return this.iUserDao;
		}

		public void setUserDao(IUserDao iUserDao)
		{
				this.iUserDao = iUserDao;
		}

		public Accounts isValidUser(String username, String password) throws SQLException
		{
				return iUserDao.isValidUser(username, password);
		}

		@Override
		public Accounts findOne(Long id) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Long create(Accounts resource) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Long update(Accounts resource) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Long getById(Long id) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Long deleteById(Long id) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Accounts getByLoginId(String loginId) {
			// TODO Auto-generated method stub
			return null;
		}

		
		public String serviceInstantiated(){
			return "Created";
			
		}

}
