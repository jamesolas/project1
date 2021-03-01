package com.app.dao;

import com.app.exception.BusinessException;
import com.app.model.Login;

public interface LoginDAO {
	
	public String login(String email, String password) throws BusinessException;
		
}
