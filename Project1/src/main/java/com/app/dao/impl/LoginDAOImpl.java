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

//		Login login2 = s.createQuery("SELECT Employee.type FROM Employee JOIN Login ON Employee.employeeId = Login.employee.Id WHERE "
//				+ "Login.email = :email", Login.class).setParameter("email", email).getSingleResult();
//		System.out.println(login2);
		
//		Employee employee = s.createQuery("SELECT FROM Employee E WHERE "
//				+ "E.Login.email = :email", Employee.class).setParameter("email", email).getSingleResult();
//		System.out.println(employee);
		
//		Object sql = s.createNativeQuery("SELECT Employee.type FROM Employee JOIN Login ON Employee.employeeId = Login.employeeId "
//				+ "WHERE Login.email = ?").setParameter(1, email).getSingleResult();
//		log.info(sql);
		
//		Object employee = s.get(Employee.class, "SELECT type FROM Employee E WHERE E.employeeEmail = email");
//		log.info(employee);
		
		
		Login login = (Login) s.createQuery("FROM Login L WHERE L.email = :email").setParameter("email", email).uniqueResult();
		System.out.println(login);

		
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
		if(s != null) {
			s.close();	
		}
		
	}
		return false;
	}
}
