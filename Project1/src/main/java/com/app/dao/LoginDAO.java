package com.app.dao;

import com.app.exception.BusinessException;
import com.app.model.Login;

public interface LoginDAO {
	
	public Boolean login(String email, String password) throws BusinessException;
		
}
