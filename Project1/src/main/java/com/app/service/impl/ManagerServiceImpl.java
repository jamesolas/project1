package com.app.service.impl;

import java.util.List;

import com.app.exception.BusinessException;
import com.app.model.Reimbursement;
import com.app.service.ManagerService;

public class ManagerServiceImpl implements ManagerService {

	@Override
	public List<Reimbursement> viewPendingRequests(int managerId) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reimbursement> viewOnePendingRequest(int employeeId) throws BusinessException {
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
	public List<Reimbursement> viewResolvedRequests(int managerId) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reimbursement> viewEmployees() throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

}
