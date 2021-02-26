package com.app.dao;

import java.util.List;

import com.app.exception.BusinessException;
import com.app.model.Reimbursement;

public interface ManagerDAO {
	
	public List<Reimbursement> viewPendingRequests (int managerId) throws BusinessException;
	public List<Reimbursement> viewOneEmployeeRequests (int employeeId) throws BusinessException;
	public int approveRequest (int requestId) throws BusinessException;
	public int denyRequest (int requestId) throws BusinessException;
	public List<Reimbursement> viewReceipts (int requestId) throws BusinessException; //may not need this one
	public List<Reimbursement> viewResolvedRequests () throws BusinessException;
	public List<Reimbursement> viewEmployees () throws BusinessException;
	public List<Reimbursement> viewAllReceipts () throws BusinessException;

}
