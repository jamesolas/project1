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
import com.app.model.Reimbursement;
import com.app.service.EmployeeService;
import com.app.service.LoginService;
import com.app.service.ManagerService;
import com.app.service.impl.EmployeeServiceImpl;
import com.app.service.impl.LoginServiceImpl;
import com.app.service.impl.ManagerServiceImpl;

public class RequestHelper {
	
	private static final Logger log = LogManager.getLogger(RequestHelper.class);
	
	
	
	public static Object processGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		EmployeeService employeeServiceImpl = new EmployeeServiceImpl();
		ManagerService managerServiceImpl = new ManagerServiceImpl();	
	
		int managerId = 0;
		
		final String URI = request.getRequestURI();
		final String RESOURCE = URI.replace("/Project1/api", "");
		
		log.info("request helper");
		
		switch(RESOURCE) {
		case "/employee":
			response.sendRedirect("Project1/Pages/EmployeeHome.html");
		
		case "/manager":
			return "Manager page";
			
		
			
		case "employeepending":
			
			final int employeeId = Integer.parseInt(request.getParameter("employeeId"));
			try {
				return employeeServiceImpl.viewPendingRequests(employeeId);
			} catch (BusinessException e) {
				e.printStackTrace();
			}
			
		case "employeeresolved":
			
			final int employeeId2 = Integer.parseInt(request.getParameter("employeeId"));
			try {
				return employeeServiceImpl.viewResolvedRequests(employeeId2);
			} catch (BusinessException e) {
				e.printStackTrace();
			}
			
		case "managerviewpending":
			try {
				managerServiceImpl.viewPendingRequests(managerId);
			} catch (BusinessException e) {
				e.printStackTrace();
			}
			
		case "viewimages":
			try {
				managerServiceImpl.viewAllReceipts();
			} catch (BusinessException e) {
				e.printStackTrace();
			}
			
		case "managerviewresolved":
			try {
				try {
					managerServiceImpl.viewResolvedRequests(managerId);
				} catch (BusinessException e) {
					e.printStackTrace();
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
		case "viewemployees":
			try {
				managerServiceImpl.viewEmployees();
			} catch (BusinessException e) {
				e.printStackTrace();
			}
			
		case "viewoneemployeerequests":
			try {
				managerServiceImpl.viewOneEmployeeRequests(employeeId);
			} catch (BusinessException e) {
				e.printStackTrace();
			}
		
			
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
		EmployeeService employeeServiceImpl = new EmployeeServiceImpl();
		ManagerService managerServiceImpl = new ManagerServiceImpl();	
		
		int requestId = 0;
		int managerId = 0;
		int employeeId = 0;
		
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
			
		case "employeesubmit":
			try {
				employeeServiceImpl.submitRequest(new Reimbursement());
			} catch (BusinessException e1) {
				e1.printStackTrace();
			}
		
		case "managerapprove":
			try {
				managerServiceImpl.approveRequest(requestId);
			} catch (BusinessException e) {
				e.printStackTrace();
			}
			
		case "managerdeny":
			try {
				managerServiceImpl.denyRequest(requestId);
			} catch (BusinessException e) {
				e.printStackTrace();
			}
			
		
			
			
		break;
		default:
				response.setStatus(404);		
		}
	}

}
