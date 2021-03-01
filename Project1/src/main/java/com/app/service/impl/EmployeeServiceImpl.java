package com.app.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.app.dao.EmployeeDAO;
import com.app.dao.impl.EmployeeDAOImpl;
import com.app.dao.impl.LoginDAOImpl;
import com.app.exception.BusinessException;
import com.app.model.Employee;
import com.app.model.Manager;
import com.app.model.Reimbursement;
import com.app.service.EmployeeService;


public class EmployeeServiceImpl implements EmployeeService {
	private static Logger log = Logger.getLogger(EmployeeServiceImpl.class);
	EmployeeDAO employeeDAOImpl = new EmployeeDAOImpl();

	@Override
	public int submitRequest(int employeeId, int managerId, double amount, Date date) throws BusinessException {
		String status = "pending";
		//log.info("submitRequest service");
		Reimbursement reimbursement = new Reimbursement(1, employeeId, managerId, status, amount, date);
		employeeDAOImpl.submitRequest(reimbursement);
		return 0;
	}
	@Override
	public int submitRequest(int employeeId, int managerId, double amount, Date date, byte[]receipt) throws BusinessException {
		String status = "pending";
		//log.info("submitRequest service");
		Reimbursement reimbursement = new Reimbursement(1, employeeId, managerId, status, amount, date, receipt);
		employeeDAOImpl.submitRequest(reimbursement);
		return 0;
	}
	@Override
	public int submitRequest(int employeeId, int managerId, double amount, Date date,  String fn) throws BusinessException {
		String status = "pending";
		//log.info("submitRequest service");
		Reimbursement reimbursement = new Reimbursement(1, employeeId, managerId, status, amount, date,  fn);
		employeeDAOImpl.submitRequest(reimbursement);
		return 0;
	}
	@Override
	public List<Reimbursement> viewPendingRequests(int employeeId) throws BusinessException {
		log.info("viewPendingRequests service");
		return this.employeeDAOImpl.viewPendingRequests(employeeId);
	}

	@Override
	public List<Reimbursement> viewResolvedRequests(int employeeId) throws BusinessException {
		log.info("viewResolvedRequests service");
		return this.employeeDAOImpl.viewResolvedRequests(employeeId);
	}

	@Override
	public Employee viewInfo(int employeeId) throws BusinessException {
		log.info("viewInfo service");
		return this.employeeDAOImpl.viewInfo(employeeId);
	}

	@Override
	public int changePhone(int employeeId, String phone) throws BusinessException {
		log.info("changePhone service");
		return this.employeeDAOImpl.changePhone(employeeId, phone);
	}

}
