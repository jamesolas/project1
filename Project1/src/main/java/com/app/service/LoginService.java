package com.app.service;

import com.app.exception.BusinessException;
import com.app.model.Login;

public interface LoginService {
	
	public String login (String email, String password) throws BusinessException;
	public int getEmployeeId(String email) throws BusinessException;
	public int getManagerId(int employeeId) throws BusinessException;
}
