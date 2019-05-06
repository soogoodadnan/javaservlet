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

@WebServlet("/PlateFormRoot")
public class PlateFormRoot extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static Logger logger = Logger.getLogger(SubmitComments.class);

    public PlateFormRoot() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out = response.getWriter();
        String email = request.getParameter("email");
        String name = request.getParameter("name");
        System.out.println(name+" -- "+email);
       
		String string = email;
		String[] parts = string.split("@");
		String password = parts[0];  

		Connection con = (Connection) getServletContext().getAttribute("DBConnection");
		PreparedStatement ps = null;
		ResultSet rs = null;
		PreparedStatement psTwo = null;
		try {
			
			psTwo = con.prepareStatement("select email from Users where email=?  limit 1");
			psTwo.setString(1, email);
			rs = psTwo.executeQuery();
			if(rs != null && rs.next()){
//>>>>>Login			
				getLogin(email,password,con,request, response);
			
			}else {
			
//>>>>>Register			
		
			ps = con.prepareStatement("insert into Users(name,email,country, password) values (?,?,?,?)");
			ps.setString(1, name);
			ps.setString(2, email);
			ps.setString(3, "KSA");
			ps.setString(4, password);
			ps.execute();
			logger.info("User registered with email="+email);
			//forward to home Page
			getLogin(email,password,con,request, response);
			
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


	protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
    
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

    
    private void getLogin(String emai,String password,Connection conn, HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		
	      String sql = "select id, name, email,country,role from Users where email=? and password=? limit 1";
	      PreparedStatement pstm = conn.prepareStatement(sql);
	      pstm.setString(1, emai);
	      pstm.setString(2, password);
	      ResultSet rs = pstm.executeQuery();
	  	if(rs != null && rs.next()){
	  	
			User user = new User( rs.getInt("id"), rs.getString("name"), rs.getString("email"), rs.getString("country"), rs.getInt("role"));
			logger.info("User found with details="+user);
			HttpSession session = request.getSession();
		
			 List<Images> list =getImageInTable(conn);
			 System.out.println("Download file here ## "	+list.size());
			
			session.setAttribute("User", user);
			session.setAttribute("Images", list);

			response.sendRedirect("home.jsp");
	          
	      }
	      rs.close();
        pstm.close();
	      
	  }

    
    
}
