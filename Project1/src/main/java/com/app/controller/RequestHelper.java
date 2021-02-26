package com.app.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.app.exception.BusinessException;
import com.app.model.Employee;
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
		System.out.println ("get: "+RESOURCE);
		switch(RESOURCE) {
				
		case "/employeepending":
			log.info("employeepending endpoint");
			final String employeeIdString = request.getParameter("employeeId");
			int employeeId = Integer.parseInt(employeeIdString);
			try {
				
				return employeeServiceImpl.viewPendingRequests(employeeId);
			} catch (BusinessException e) {
				e.printStackTrace();
			}
		break;
		case "/employeeresolved":
			
			final String employeeId2String = request.getParameter("employeeId");
			int employeeId2 = Integer.parseInt(employeeId2String);
			try {
				return employeeServiceImpl.viewResolvedRequests(employeeId2);
			} catch (BusinessException e) {
				e.printStackTrace();
			}
		break;
		case "/managerviewpending":
			final String managerId2String = request.getParameter("managerId");
			int managerId2 = Integer.parseInt(managerId2String);
			try {
				managerServiceImpl.viewPendingRequests(managerId2);
			} catch (BusinessException e) {
				e.printStackTrace();
			}
		break;	
		case "/viewimages":
			try {
				managerServiceImpl.viewAllReceipts();
			} catch (BusinessException e) {
				e.printStackTrace();
			}
		break;	
		case "/managerviewresolved":
			try {
				try {
					managerServiceImpl.viewResolvedRequests();
				} catch (BusinessException e) {
					e.printStackTrace();
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		break;	
		case "viewemployees":
			try {
				managerServiceImpl.viewEmployees();
			} catch (BusinessException e) {
				e.printStackTrace();
			}
		break;	
		case "/viewoneemployeerequests":
			int employeeId3 = 0;
			try {
				managerServiceImpl.viewOneEmployeeRequests(employeeId3);
			} catch (BusinessException e) {
				e.printStackTrace();
			}
		
		break;	
		case "/logout":
			HttpSession session = request.getSession(false);
			response.sendRedirect("/Project1/Pages/EmployeeHome.html");
			if(session != null) {
				session.invalidate();
			}
		break;
		default:
			response.setStatus(404);
			return "Wrong page.";
		}
		return null;
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
		break;	
		case "/employeesubmit":
			final String employeeId2 = request.getParameter("employeeId");
			final String managerId2 = request.getParameter("managerId");
			final Double amount = Double.parseDouble(request.getParameter("amount"));
			final String stringDate = request.getParameter("date");
			
			int employeeIdInt = Integer.parseInt(employeeId2);
			int managerIdInt = Integer.parseInt(managerId2);
			//float amountFloat = Float.parseFloat(amount);
			Date date = null;
			
		
			try {
				date = new SimpleDateFormat("MM-dd-yyyy").parse(stringDate);
			} catch (ParseException e2) {
				e2.printStackTrace();
			}
			log.info("employeesubmit endpoint");
			try {
				employeeServiceImpl.submitRequest(employeeIdInt, managerIdInt, amount, date);
			} catch (BusinessException e1) {
				e1.printStackTrace();
			}
		break;
		case "/managerapprove":
			try {
				managerServiceImpl.approveRequest(requestId);
			} catch (BusinessException e) {
				e.printStackTrace();
			}
		break;
		case "/managerdeny":
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
