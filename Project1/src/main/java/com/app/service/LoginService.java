package com.app.service;

import com.app.exception.BusinessException;
import com.app.model.Login;

public interface LoginService {
	
	public Boolean login (String email, String password) throws BusinessException;

}
