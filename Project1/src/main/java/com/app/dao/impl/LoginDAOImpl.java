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

import jdk.internal.org.jline.utils.Log;

public class LoginDAOImpl implements LoginDAO {

	private static Logger log = Logger.getLogger(LoginDAOImpl.class);
	
	@Override
	public Boolean login(String email, String password) throws BusinessException {
		Session s = null;
		Transaction t = null;
		log.info("login");
		
		try {
		s = HibernateSessionFactory.getSession();
		t = s.beginTransaction();

		String type = (String) s.createQuery("SELECT Employee.type FROM Employee JOIN Login WHERE Login.email = :email").setParameter("email", email).getSingleResult();
		System.out.println(type);
		
		
//		Object employee = s.get(Employee.class, "SELECT type FROM Employee E WHERE E.employeeEmail = email");
//		log.info(employee);
		
		
		Login login = (Login) s.createQuery("FROM Login L WHERE L.email = :email").setParameter("email", email).uniqueResult();
		//System.out.println(login);

		
		if(login.getEmail().equals(email) && login.getPassword().equals(password)) {
			log.info("login true");
			return true;	
		}else {
			log.info("login false");
			return false;
		}
		
	}catch(HibernateException e){
		e.printStackTrace();
		t.rollback();
	}finally {
		s.close();
	}
		return false;
	}
}
