package com.app.service.impl;

import java.util.List;

import com.app.dao.impl.ManagerDAOImpl;
import com.app.exception.BusinessException;
import com.app.model.Employee;
import com.app.model.Reimbursement;
import com.app.service.ManagerService;

public class ManagerServiceImpl implements ManagerService {
	ManagerDAOImpl managerDAOImpl = new ManagerDAOImpl();
	
	@Override
	public List<Reimbursement> viewManagerPendingRequests(int managerId) throws BusinessException {
		
		return this.managerDAOImpl.viewManagerPendingRequests(managerId);
	}

	@Override
	public List<Reimbursement> viewOneEmployeeRequests(int employeeId) throws BusinessException {
		return this.managerDAOImpl.viewOneEmployeeRequests(employeeId);
	}

	@Override
	public int approveRequest(int requestId) throws BusinessException {
		return this.managerDAOImpl.approveRequest(requestId);
	}

	@Override
	public int denyRequest(int requestId) throws BusinessException {
		return this.managerDAOImpl.denyRequest(requestId);
	}

	@Override
	public List<Reimbursement> viewReceipts(int requestId) throws BusinessException {
		return this.managerDAOImpl.viewReceipts(requestId);
	}

	@Override
	public List<Reimbursement> viewResolvedRequests() throws BusinessException {
		return this.managerDAOImpl.viewResolvedRequests();
	}

	@Override
	public List<Employee> viewEmployees() throws BusinessException {
		return this.managerDAOImpl.viewEmployees();
	}

	@Override
	public List<Reimbursement> viewAllReceipts() throws BusinessException {
		return this.managerDAOImpl.viewAllReceipts();
	}

}
