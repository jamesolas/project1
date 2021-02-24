package com.app.service.impl;

import java.util.List;

import com.app.exception.BusinessException;
import com.app.model.Reimbursement;
import com.app.service.EmployeeService;

public class EmployeeServiceImpl implements EmployeeService {

	@Override
	public int submitRequest(String something) throws BusinessException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Reimbursement> viewPendingRequests(int employeeId) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reimbursement> viewResolvedRequests(int employeeId) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

}
