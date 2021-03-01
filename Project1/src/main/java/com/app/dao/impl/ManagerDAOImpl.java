package com.app.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.app.dao.ManagerDAO;
import com.app.exception.BusinessException;
import com.app.model.Employee;
import com.app.model.Reimbursement;
import com.app.util.HibernateSessionFactory;

public class ManagerDAOImpl implements ManagerDAO {
	private static Logger log = Logger.getLogger(EmployeeDAOImpl.class);
	
	@Override
	public List<Reimbursement> viewManagerPendingRequests(int managerId) throws BusinessException {
		Session s = null;
		Transaction t = null;
	
		List<Reimbursement>rList = new ArrayList<>();
		
		log.info("manager viewpending DAO");
		
		try {
		s = HibernateSessionFactory.getSession();
		t = s.beginTransaction();
		rList = s.createQuery("FROM Reimbursement WHERE managerId = :managerId AND status = 'pending'", Reimbursement.class).setParameter("managerId", managerId).getResultList();
				t.commit();
	
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
	public List<Reimbursement> viewOneEmployeeRequests(int employeeId) throws BusinessException {
		Session s = null;
		Transaction t = null;
	
		List<Reimbursement>rList = new ArrayList<>();
		
		log.info("manager view an employee's requests DAO");
		
		try {
		s = HibernateSessionFactory.getSession();
		t = s.beginTransaction();
		rList = s.createQuery("FROM Reimbursement WHERE employeeId = :employeeId", Reimbursement.class).setParameter("employeeId", employeeId).getResultList();
				t.commit();
	
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
	public int approveRequest(int requestId) throws BusinessException {
		Session s = null;
		Transaction t = null;
		int success = 0;
		
		log.info("manager approve DAO");
		log.info(requestId);
		try {
		s = HibernateSessionFactory.getSession();
		t = s.beginTransaction();
		
		Reimbursement reimbursement = s.get(Reimbursement.class, requestId);
		reimbursement.setRequestId(requestId);
		reimbursement.setStatus("approved");
		success = 1;
		s.update(reimbursement);
		
		//int hqlUpdate = s.createQuery("UPDATE Reimbursement R SET R.status = 'approved' WHERE requestId = :requestId").setParameter("requestId", requestId).executeUpdate();
				
		
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

	@Override
	public int denyRequest(int requestId) throws BusinessException {
		Session s = null;
		Transaction t = null;
		int success = 0;
		
		log.info("manager deny DAO");
		
		try {
		s = HibernateSessionFactory.getSession();
		t = s.beginTransaction();
		Reimbursement reimbursement = s.load(Reimbursement.class, requestId);
		log.info(reimbursement);
		//reimbursement.setRequestId(requestId);
		reimbursement.setStatus("denied");
		s.update(reimbursement);
		
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

	@Override
	public List<Reimbursement> viewReceipts(int requestId) throws BusinessException {
		Session s = null;
		Transaction t = null;
	
		List<Reimbursement>rList = new ArrayList<>();
		
		log.info("manager viewpending DAO");
		
		try {
		s = HibernateSessionFactory.getSession();
		t = s.beginTransaction();
		
		//Reimbursement r = s.find(Reimbursement.class, requestId);
		
		rList = s.createQuery("FROM Reimbursement", Reimbursement.class).getResultList();
				t.commit();
	
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
	
	public Reimbursement viewReceipt(int requestId) throws BusinessException {
		Session s = null;
		Transaction t = null;
	
		List<Reimbursement>rList = new ArrayList<>();
		
		log.info("manager viewpending DAO");
		
		try {
		s = HibernateSessionFactory.getSession();
		t = s.beginTransaction();
		
		Reimbursement r = s.find(Reimbursement.class, requestId);
		return r;
		/*
		rList = s.createQuery("FROM Reimbursement", Reimbursement.class).getResultList();
				t.commit();
	*/
		}catch(HibernateException e) {
			e.printStackTrace();
			t.rollback();
		}finally {
			if(s != null) {
				s.close();
			}
		}
		return null;
	}
	@Override
	public List<Reimbursement> viewResolvedRequests() throws BusinessException {
		Session s = null;
		Transaction t = null;
	
		List<Reimbursement>rList = new ArrayList<>();
		
		log.info("manager viewpending DAO");
		
		try {
		s = HibernateSessionFactory.getSession();
		t = s.beginTransaction();
		rList = s.createQuery("FROM Reimbursement WHERE status = 'approved' OR status = 'denied'", Reimbursement.class).getResultList();
				t.commit();
	
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
	public List<Employee> viewEmployees() throws BusinessException {
		Session s = null;
		Transaction t = null;
	
		List<Employee>rList = new ArrayList<>();
		
		log.info("manager viewpending DAO");
		
		try {
		s = HibernateSessionFactory.getSession();
		t = s.beginTransaction();
		rList = s.createQuery("FROM Employee", Employee.class).getResultList();
				t.commit();
	
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
	public List<Reimbursement> viewAllReceipts() throws BusinessException {
		Session s = null;
		Transaction t = null;
	
		List<Reimbursement>rList = new ArrayList<>();
		
		log.info("manager viewpending DAO");
		
		try {
		s = HibernateSessionFactory.getSession();
		t = s.beginTransaction();
		rList = s.createQuery("FROM Reimbursement", Reimbursement.class).getResultList();
				t.commit();
	
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

}
