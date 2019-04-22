package com.journaldev.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

@WebServlet(name = "Register", urlPatterns = { "/Register" })
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	static Logger logger = Logger.getLogger(RegisterServlet.class);
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String country = request.getParameter("country");
		
		String errorMsg = null;
		 if(name == null || name.equals("")){
				errorMsg = "Name can't be null or empty.";
			}else
		
		if(email == null || email.equals("")){
			errorMsg = "Email ID can't be null or empty.";
		}
		else if(password == null || password.equals("")){
			errorMsg = "Password can't be null or empty.";
		}
		
		else if(country == null || country.equals("")){
			errorMsg = "Country can't be null or empty.";
		}
		
		if(errorMsg != null){
			request.getSession().invalidate();
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/register.html");
			PrintWriter out= response.getWriter();
			out.println("<font color=red>"+errorMsg+"</font>");
			rd.include(request, response);
		}else{
		
		Connection con = (Connection) getServletContext().getAttribute("DBConnection");
		PreparedStatement ps = null;
		ResultSet rs = null;
		PreparedStatement psTwo = null;
		try {
			
			
			psTwo = con.prepareStatement("select email from Users where email=?  limit 1");
			psTwo.setString(1, email);
			rs = psTwo.executeQuery();
			if(rs != null && rs.next()){
	
				
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/register.html");
				PrintWriter out= response.getWriter();
				logger.error("User already found with email="+email);
				out.println("<font color=red>User already found with email.</font>");
				rd.include(request, response);
				
			
			}else {
			
		
			ps = con.prepareStatement("insert into Users(name,email,country, password) values (?,?,?,?)");
			ps.setString(1, name);
			ps.setString(2, email);
			ps.setString(3, country);
			ps.setString(4, password);
			ps.execute();
			logger.info("User registered with email="+email);
			//forward to login page to login
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
			PrintWriter out= response.getWriter();
			out.println("<font color=green>Registration successful, please login below.</font>");
			rd.include(request, response);
			
			
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("Database connection problem");
			throw new ServletException("DB Connection problem.");
		}finally{
			try {
				if(psTwo != null)psTwo.close();
				if(rs != null)rs.close();
				if(ps != null)ps.close();
			} catch (SQLException e) {
				logger.error("SQLException in closing PreparedStatement");
			}
		}
		}
		
	}

}
