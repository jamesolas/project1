package com.app.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.app.dao.EmployeeDAO;
import com.app.exception.BusinessException;
import com.app.model.Employee;
import com.app.model.Reimbursement;
import com.app.util.HibernateSessionFactory;


public class EmployeeDAOImpl implements EmployeeDAO {
	private static Logger log = Logger.getLogger(EmployeeDAOImpl.class);
	
	LoginDAOImpl dao = new LoginDAOImpl();
	
	@Override
	public int submitRequest(Reimbursement reimbursement) throws BusinessException {
		Session s = null;
		Transaction t = null;
		int success = 0;
		
		log.info("employee submitted request");
		
		try {
			s = HibernateSessionFactory.getSession();
			t = s.beginTransaction();
			s.save(reimbursement);
			t.commit();
			success = 1;
		}catch(HibernateException e) {
			e.printStackTrace();
			t.rollback();
		}finally {
			if(s != null) {
				s.close();
			}
		}
			
		return 1;
	}

	@Override
	public List<Reimbursement> viewPendingRequests(int employeeId) throws BusinessException {
		Session s = null;
		Transaction t = null;
		int success = 0;
		List<Reimbursement>rList = new ArrayList<>();
		
		log.info("employee viewpending DAO");
		
		try {
		s = HibernateSessionFactory.getSession();
		t = s.beginTransaction();
		rList = s.createQuery("FROM Reimbursement WHERE employeeId = :employeeId AND status = 'pending'", Reimbursement.class).setParameter("employeeId", employeeId).getResultList();
				t.commit();
		success = 1;
		}catch(HibernateException e) {
			e.printStackTrace();
			t.rollback();
		}finally {
			if(s != null) {
				s.close();
			}
		}
		return rList;
	}

	@Override
	public List<Reimbursement> viewResolvedRequests(int employeeId) throws BusinessException {
		Session s = null;
		Transaction t = null;
		int success = 0;
		List<Reimbursement>rList = new ArrayList<>();
		
		log.info("employee viewpending DAO");
		
		try {
		s = HibernateSessionFactory.getSession();
		t = s.beginTransaction();
		rList = s.createQuery("FROM Reimbursement WHERE employeeId = :employeeId AND status != 'pending'", Reimbursement.class).setParameter("employeeId", employeeId).getResultList();
				t.commit();
		success = 1;
		}catch(HibernateException e) {
			e.printStackTrace();
			t.rollback();
		}finally {
			if(s != null) {
				s.close();
			}
		}
		return rList;
	}

	@Override
	public Employee viewInfo(int employeeId) throws BusinessException {
		Session s = null;
		Transaction t = null;
		int success = 0;
		Employee employee = null;
		
		log.info("employee viewinfo DAO");
		
		try {
		s = HibernateSessionFactory.getSession();
		t = s.beginTransaction();
		employee = s.createQuery("FROM Employee WHERE employeeId = :employeeId", Employee.class).setParameter("employeeId", employeeId).getSingleResult();
				t.commit();
		success = 1;
		}catch(HibernateException e) {
			e.printStackTrace();
			t.rollback();
		}finally {
			if(s != null) {
				s.close();
			}
		}
		return employee;
	}

	@Override
	public int changePhone(int employeeId, String phone) throws BusinessException {
		Session s = null;
		Transaction t = null;
		int success = 0;
		
		log.info("changePhone DAO");
		
		try {
		s = HibernateSessionFactory.getSession();
		t = s.beginTransaction();
		Employee employee = s.load(Employee.class, employeeId);
		log.info(employee);
		employee.setPhone(phone);
		s.update(employee);
		
		success = 1;
		t.commit();
	
		}catch(HibernateException e) {
			success = 0;
			e.printStackTrace();
			t.rollback();
		}finally {
			if(s != null) {
				s.close();
			}
		}
		return success;
	}

}
