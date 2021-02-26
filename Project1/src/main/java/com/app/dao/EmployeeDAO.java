package com.app.dao;

import java.util.Date;
import java.util.List;

import com.app.exception.BusinessException;
import com.app.model.Reimbursement;

public interface EmployeeDAO {
	
	public int submitRequest(Reimbursement reimbursement) throws BusinessException;
	public List<Reimbursement> viewPendingRequests (int employeeId) throws BusinessException;
	public List<Reimbursement> viewResolvedRequests (int employeeId) throws BusinessException;

}
