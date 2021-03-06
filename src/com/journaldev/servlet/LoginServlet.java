package com.journaldev.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.journaldev.util.Images;
import com.journaldev.util.User;

@WebServlet(name = "Login", urlPatterns = { "/Login" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	static Logger logger = Logger.getLogger(LoginServlet.class);
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String errorMsg = null;
		if(email == null || email.equals("")){
			errorMsg ="User Email can't be null or empty";
		}
		if(password == null || password.equals("")){
			errorMsg = "Password can't be null or empty";
		}
		 
		if(errorMsg != null){
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
			PrintWriter out= response.getWriter();
			out.println("<font color=red>"+errorMsg+"</font>");
			rd.include(request, response);
		}else{
		
		Connection con = (Connection) getServletContext().getAttribute("DBConnection");
		PreparedStatement ps = null;

		ResultSet rs = null;

		try {
			ps = con.prepareStatement("select id, name, email,country,role from Users where email=? and password=? limit 1");
			ps.setString(1, email);
			ps.setString(2, password);
			rs = ps.executeQuery();
			if(rs != null && rs.next()){
			
				User user = new User( rs.getInt("id"), rs.getString("name"), rs.getString("email"), rs.getString("country"), rs.getInt("role"));
				logger.info("User found with details="+user);
				HttpSession session = request.getSession();
				
//images data
				 List<Images> list =getImageInTable(con);
				 System.out.println("Download file here ## "	+list.size());
				 
						 
							
				session.setAttribute("User", user);
				session.setAttribute("Images", list);

				response.sendRedirect("home.jsp");
				
			}else{
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
				PrintWriter out= response.getWriter();
				logger.error("User not found with email ="+email);
				out.println("<font color=red>No user found with given email id, please register first.</font>");
				rd.include(request, response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("Database connection problem");
			throw new ServletException("DB Connection problem.");
		}finally{
			try {
				rs.close();
				ps.close();
			} catch (SQLException e) {
				logger.error("SQLException in closing PreparedStatement or ResultSet");;
			}
			
		}
		}
	}
	  private  List<Images> getImageInTable(Connection conn) throws SQLException {
		  System.out.println("Download file here start"	);
	      String sql = "select id, name, photo from subjects";
	      PreparedStatement pstm = conn.prepareStatement(sql);
	      List<Images> list = new ArrayList();		
	      ResultSet rs = pstm.executeQuery();
	      while (rs.next()) {
	    	  Images img = new Images();
	          byte[] imageData = rs.getBytes("photo");
	          
	          String encode = Base64.getEncoder().encodeToString(imageData);
	          img.setPhoto(encode);
	     	 System.out.println("Download file Link >>>>> "	+encode);
	          
	          img.setName(rs.getString("name"));
	          img.setId(rs.getInt("id"));

	          
	          list.add(img);
	          
	      }
	      rs.close();
          pstm.close();
	      return list;
	      
	  }
}
