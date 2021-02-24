package com.app.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.jboss.jandex.Main;

import com.fasterxml.jackson.databind.ObjectMapper;

public class DispatcherServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(DispatcherServlet.class);

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
	
		ObjectMapper objectMapper = new ObjectMapper();
		PrintWriter writer = response.getWriter();
		final String JSON = objectMapper.writeValueAsString(RequestHelper.processGet(request, response));
		writer.write(JSON);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		RequestHelper.processPost(request, response);
		
	}
	
	
}
