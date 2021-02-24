package com.app.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jboss.jandex.Main;

import com.app.exception.BusinessException;
import com.app.service.LoginService;
import com.app.service.impl.LoginServiceImpl;

public class RequestHelper {
	
	private static final Logger log = LogManager.getLogger(RequestHelper.class);
 
	
	public static Object processGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		final String URI = request.getRequestURI();
		final String RESOURCE = URI.replace("/Project1/api", "");
		
		log.info("request helper");
		
		switch(RESOURCE) {
		case "/test":
				return "test";
		case "/employee":
			response.sendRedirect("Project1/Pages/EmployeeHome.html");
		case "/manager":
			return "Manager page";
		case "/logout":
			HttpSession session = request.getSession(false);
			if(session != null) {
				session.invalidate();
			}
		default:
			response.setStatus(404);
			return "Wrong page.";
		}
	}
	
	public static void processPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LoginService loginServiceImpl = new LoginServiceImpl();
		
		final String URI = request.getRequestURI();
		final String RESOURCE = URI.replace("/Project1/api", "");
		
	
		
		switch (RESOURCE) {
		case "/login":
	
			final String email = request.getParameter("email");
			final String password = request.getParameter("password");
			
			try {
				if (loginServiceImpl.login(email, password)) {
					HttpSession session = request.getSession();
					session.setAttribute("email", email);			
					response.sendRedirect("/Project1/Pages/EmployeeHome.html");
				}
			} catch (BusinessException e) {
				e.printStackTrace();
			}
			
//			try {
//				if(loginServiceImpl.login(email, password) == true) {
//					HttpSession session = request.getSession();
//					session.setAttribute("username", email);
//					response.sendRedirect("/Project1/Pages/ManagerHome.html");
//				}
//			} catch (BusinessException | IOException e) {
//				e.printStackTrace();
//			}
			break;
			default:
				response.setStatus(404);		
		}
	}

}
