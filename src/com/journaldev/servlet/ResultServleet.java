package com.journaldev.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ResultServleet
 */
@WebServlet(name = "Result", urlPatterns = { "/Result" })
public class ResultServleet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
response.getWriter().append("Served at: ").append(request.getContextPath());

System.out.println("doGet here ## "	);

	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);

System.out.println("doPost here ## "	);
	}

}
