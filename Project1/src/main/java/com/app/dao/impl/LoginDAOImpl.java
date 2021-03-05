package com.app.dao.impl;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.app.dao.LoginDAO;
import com.app.exception.BusinessException;
import com.app.model.Employee;
import com.app.model.Login;
import com.app.util.HibernateSessionFactory;



public class LoginDAOImpl implements LoginDAO {

	private static Logger log = Logger.getLogger(LoginDAOImpl.class);
	
	@Override
	public String login(String email, String password) throws BusinessException {
		Session s = null;
		Transaction t = null;
		log.info("login");
		String loginReturn = "";
		
		try {
		s = HibernateSessionFactory.getSession();
		t = s.beginTransaction();
		
		Login login = (Login) s.createQuery("FROM Login L WHERE L.email = :email").setParameter("email", email).uniqueResult();
		log.info(login.getEmployeeId());
		
		int employeeId = login.getEmployeeId();
		log.info(login);
		
			
		Employee employee = (Employee) s.createQuery("FROM Employee E WHERE employeeId = :employeeId").setParameter("employeeId", employeeId).uniqueResult();
		log.info(employee.getType());
		
		if(login.getEmail().equals(email) && login.getPassword().equals(password)) {
		log.info("login true");	
		loginReturn = employee.getType();
		}					
	}catch(HibernateException e){
		e.printStackTrace();
		t.rollback();
	}finally {
		if(s != null) {
			s.close();	
		}
		
	}
		return loginReturn;
	}

	@Override
	public int getEmployeeId(String email) throws BusinessException {
		
		Session s = null;
		Transaction t = null;
	
		int employeeId = 0;
		
		try {
		s = HibernateSessionFactory.getSession();
		t = s.beginTransaction();
		
		Login login = (Login) s.createQuery("FROM Login L WHERE L.email = :email").setParameter("email", email).uniqueResult();
		
		employeeId = login.getEmployeeId();
		System.out.println("get employeeID DAO: "+employeeId);
		
						
	}catch(HibernateException e){
		e.printStackTrace();
		t.rollback();
	}finally {
		if(s != null) {
			s.close();	
		}
		
	}
		return employeeId;
	}

	@Override
	public int getManagerId(int employeeId) throws BusinessException {
		System.out.println("getManagerID employeeId "+employeeId);
		Session s = null;
		Transaction t = null;
	
		int managerId = 0;
		
		try {
		s = HibernateSessionFactory.getSession();
		t = s.beginTransaction();
		
		Employee employee = (Employee) s.createQuery("FROM Employee E WHERE E.employeeId = :employeeId").setParameter("employeeId", employeeId).uniqueResult();
		System.out.println("getManagerId employee "+employee);
		
		managerId = employee.getManager().getManagerId();
		System.out.println("get managerID DAO: "+managerId);
		
						
	}catch(HibernateException e){
		e.printStackTrace();
		t.rollback();
	}finally {
		if(s != null) {
			s.close();	
		}
		
	}
		return managerId;
	}
}
