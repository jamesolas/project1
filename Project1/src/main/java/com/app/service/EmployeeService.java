package com.app.service;

import java.util.Date;
import java.util.List;

import com.app.exception.BusinessException;
import com.app.model.Employee;
import com.app.model.Manager;
import com.app.model.Reimbursement;

public interface EmployeeService {
	
	public int submitRequest(int employeeId, int managerId, double amount, Date date) throws BusinessException;
	public int submitRequest(int employeeId, int managerId, double amount, Date date, byte[]data) throws BusinessException;
	public int submitRequest(int employeeId, int managerId, double amount, Date date, String filename) throws BusinessException;
	public List<Reimbursement> viewPendingRequests (int employeeId) throws BusinessException;
	public List<Reimbursement> viewResolvedRequests (int employeeId) throws BusinessException;
	public Employee viewInfo(int employeeId) throws BusinessException;
	public int changePhone(int employeeId, String phone) throws BusinessException;

}
