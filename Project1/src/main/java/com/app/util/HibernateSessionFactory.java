package com.app.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactory {

	private static SessionFactory sessionFactory;
	
	public static Session getSession() {
		if(sessionFactory == null) {
			sessionFactory = new Configuration().configure()
					.setProperty("hibernate.connection.url", "jdbc:postgresql://database-1.cqrofvu1umtq.us-west-1.rds.amazonaws.com:5432/")
					.setProperty("hibernate.connection.username", "postgres")
					.setProperty("hibernate.connection.password","password")
					
//					.setProperty("hibernate.connection.username", System.getenv("dbusername"))
//					.setProperty("hibernate.connection.password", System.getenv("dbpassword"))
					.buildSessionFactory();
		}
		return sessionFactory.getCurrentSession();
	}
	
}
