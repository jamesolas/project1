package com.app.service.impl;

import com.app.dao.impl.LoginDAOImpl;
import com.app.exception.BusinessException;
import com.app.model.Login;
import com.app.service.LoginService;

public class LoginServiceImpl implements LoginService {
	
	LoginDAOImpl dao = new LoginDAOImpl();

	@Override
	public String login(String email, String password) throws BusinessException {
		
		String returnedEmail = dao.login(email, password);
		
		return returnedEmail;
	}

	@Override
	public int getEmployeeId(String email) throws BusinessException {
		return this.dao.getEmployeeId(email);
	}

	@Override
	public int getManagerId(int employeeId) throws BusinessException {
		return this.dao.getManagerId(employeeId);
	}

}
