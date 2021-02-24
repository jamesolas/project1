package com.app.service.impl;

import com.app.dao.impl.LoginDAOImpl;
import com.app.exception.BusinessException;
import com.app.model.Login;
import com.app.service.LoginService;

public class LoginServiceImpl implements LoginService {
	
	LoginDAOImpl dao = new LoginDAOImpl();

	@Override
	public Boolean login(String email, String password) throws BusinessException {
		String type = "";
		type = "employee";
		Boolean returnedEmail = dao.login(email, password);
		
		return returnedEmail;
	}

}
