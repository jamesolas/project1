package com.app.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.jboss.jandex.Main;

import com.fasterxml.jackson.databind.ObjectMapper;
@MultipartConfig(fileSizeThreshold=1024*1024*2, // 2MB
maxFileSize=1024*1024*10,      // 10MB
maxRequestSize=1024*1024*50)
public class DispatcherServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(DispatcherServlet.class);

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
	System.out.println ("doGet");
		ObjectMapper objectMapper = new ObjectMapper();
		PrintWriter writer = response.getWriter();
		final String JSON = objectMapper.writeValueAsString(RequestHelper.processGet(request, response));
		writer.write(JSON);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		RequestHelper.processPost(request, response);
		
	}
	
	
}
