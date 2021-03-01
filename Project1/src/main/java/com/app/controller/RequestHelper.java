package com.app.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

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
				log.info(employeeServiceImpl.viewPendingRequests(employeeId));
				
				String email = (String) request.getSession().getAttribute("email");
				System.out.println(email);
				
				return employeeServiceImpl.viewPendingRequests(employeeId);
				
				
				
			} catch (BusinessException e) {
				e.printStackTrace();
			}
		break;
		case "/employeeresolved":
			log.info("employeeresolved endpoint");
			final String employeeId2String = request.getParameter("employeeId");
			int employeeId2 = Integer.parseInt(employeeId2String);
			try {
				return employeeServiceImpl.viewResolvedRequests(employeeId2);
			} catch (BusinessException e) {
				e.printStackTrace();
			}
		break;
		case "/managerviewpending":
			final int managerId2 = Integer.parseInt(request.getParameter("managerId"));
			
			try {
				return managerServiceImpl.viewManagerPendingRequests(managerId2);
			} catch (BusinessException e) {
				e.printStackTrace();
			}
		break;	
		case "/viewimages":
			//no parameters needed
			try {
				return managerServiceImpl.viewAllReceipts();
			} catch (BusinessException e) {
				e.printStackTrace();
			}
		break;	
		case "/managerviewresolved":
			//no parameters needed
			try {
					return managerServiceImpl.viewResolvedRequests();
				} catch (BusinessException e) {
					e.printStackTrace();
			}
		break;	
		case "/viewemployees":
			//no parameters needed
			try {
				System.out.println("***viewemployees");
				return managerServiceImpl.viewEmployees();
			} catch (BusinessException e) {
				e.printStackTrace();
			}
		break;	
		case "/viewoneemployeerequests":
			final int employeeId3 = Integer.parseInt(request.getParameter("employeeId"));
			
			try {
				return managerServiceImpl.viewOneEmployeeRequests(employeeId3);
				//log.info(managerServiceImpl.viewOneEmployeeRequests(employeeId3));
			} catch (BusinessException e) {
				e.printStackTrace();
			}
		break;
		case "/viewpending":
			log.info("viewpending endpoint");
			final String employeeIdString2 = request.getParameter("employeeId");
			int employeeId4 = Integer.parseInt(employeeIdString2);
			try {
				
				return employeeServiceImpl.viewPendingRequests(employeeId4);
			} catch (BusinessException e) {
				e.printStackTrace();
			}
		break;
		case "/update":
			
			break;
		case "/viewinfo":
			log.info("viewpending endpoint");
			final int employeeId5 = Integer.parseInt(request.getParameter("employeeId1"));
			//int employeeId5 = Integer.parseInt(employeeIdString3);
			try {
				
				return employeeServiceImpl.viewInfo(employeeId5);
			} catch (BusinessException e) {
				e.printStackTrace();
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
		
		System.out.println("REAL PATH: "+request.getServletContext().getRealPath(""));
		
		switch (RESOURCE) {
		
		
		case "/login":
	
			final String email = request.getParameter("email");
			final String password = request.getParameter("password");
			
			try {
				if (loginServiceImpl.login(email, password).equals("employee")) {
					log.info("login = employee");
					HttpSession session = request.getSession();
					session.setAttribute("email", email);			
					response.sendRedirect("/Project1/Pages/EmployeeHome.html");
				}
						
				if(loginServiceImpl.login(email, password).equals("manager")) {
					log.info("login = manager");
					HttpSession session = request.getSession();
					session.setAttribute("email", email);
					response.sendRedirect("/Project1/Pages/ManagerHome.html");
				}
			} catch (BusinessException | IOException e) {
				e.printStackTrace();
			}
		break;	
		case "/employeesubmit":
			String fileName = upload(request, response);
			final String employeeId2 = request.getParameter("employeeId");
			final String managerId2 = request.getParameter("managerId");
			final String stringAmount = request.getParameter("amount");
			final String stringDate = request.getParameter("date");
			
			int employeeIdInt = Integer.parseInt(employeeId2);
			int managerIdInt = Integer.parseInt(managerId2);
			double amount = Double.parseDouble(stringAmount);
			Date date = null;
			
		
			try {
				date = new SimpleDateFormat("MM-dd-yyyy").parse(stringDate);
			} catch (ParseException e2) {
				e2.printStackTrace();
			}
			log.info("employeesubmit endpoint");
			try {
				employeeServiceImpl.submitRequest(employeeIdInt, managerIdInt, amount, date, fileName );//fileBytes
				
			} catch (BusinessException e1) {
				e1.printStackTrace();
			}
		break;
		case "/managerapprove":
			final int requestId2 = Integer.parseInt(request.getParameter("requestId"));
			log.info(requestId2);
			try {
				managerServiceImpl.approveRequest(requestId2);
			} catch (BusinessException e) {
				e.printStackTrace();
			}
		break;
		case "/managerdeny":
			final int requestId3 = Integer.parseInt(request.getParameter("requestId"));
			try {
				managerServiceImpl.denyRequest(requestId3);
			} catch (BusinessException e) {
				e.printStackTrace();
			}
			
		break;
		case "/changephone":
			final int employeeId3 = Integer.parseInt(request.getParameter("employeeId"));
			final String phone = request.getParameter("phone");
		
			try {
				employeeServiceImpl.changePhone(employeeId3, phone);
			} catch (BusinessException e) {
				e.printStackTrace();
			}
			break;
		case "/upload":
			
			upload(request, response);
		break;
		case "/logout":
			HttpSession session = request.getSession(false);
			response.sendRedirect("/Project1");
			if(session != null) {
				session.invalidate();
			}
	
		break;
		default:
				response.setStatus(404);		
		}
	}
	private static final String SAVE_DIR = "uploadFiles";
	private static String upload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		try {
			String appPath = request.getServletContext().getRealPath("");
	        // constructs path of the directory to save uploaded file
	        String savePath = appPath + SAVE_DIR;
	     //+ File.separator 
	        // creates the save directory if it does not exists
	        File fileSaveDir = new File(savePath);
	        if (!fileSaveDir.exists()) {
	            fileSaveDir.mkdir();
	        }
	        System.out.println ("uploading to "+savePath);
	        String fn ="";
	        Part part = request.getPart("image");
	      //  for (Part part : request.getParts()) {
	        	String fnExtra = new Date().toString().replace(" ", "_").replace(":", "_");
	            String fileName =fnExtra+"_"+ extractFileName(part);
	      
	            fn=fileName;
	            // refines the fileName in case it is an absolute path
	            fileName = new File(fileName).getName();
	            part.write(savePath + File.separator + fileName);
	       // }
	        Path path = Paths.get(savePath+fn);
	        byte[] data = Files.readAllBytes(path);
	        
	        
	        return fileName;
		}catch (Exception e) {
			return null;
		}
	}
    private static String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length()-1);
            }
        }
        return "";
    }
}
