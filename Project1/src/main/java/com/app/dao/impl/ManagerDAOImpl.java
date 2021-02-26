package com.app.dao.impl;

import java.util.List;

import com.app.dao.ManagerDAO;
import com.app.exception.BusinessException;
import com.app.model.Reimbursement;

public class ManagerDAOImpl implements ManagerDAO {

	@Override
	public List<Reimbursement> viewPendingRequests(int managerId) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reimbursement> viewOneEmployeeRequests(int employeeId) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int approveRequest(int requestId) throws BusinessException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int denyRequest(int requestId) throws BusinessException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Reimbursement> viewReceipts(int requestId) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reimbursement> viewResolvedRequests() throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reimbursement> viewEmployees() throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reimbursement> viewAllReceipts() throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

}
