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

import com.journaldev.util.Comments;
import com.journaldev.util.Images;
import com.journaldev.util.User;


@WebServlet(name = "Result", urlPatterns = { "/Result" })
public class ResultServleet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	static Logger logger = Logger.getLogger(ResultServleet.class);
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 PrintWriter out = response.getWriter();
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/home.jsp");
		    String id = request.getParameter("imageButton");
		    logger.info("ResultServlet= "+id);
		    Connection con = (Connection) getServletContext().getAttribute("DBConnection");
		    HttpSession session = request.getSession();

			try {
				{
					

				Images img  =getImageData(con,id);
				List<Comments> coment  =getImageComments(con,id);
			    System.out.println("ResultServleet Link >>>>> "+img.getId());
				session.setAttribute("ResultImages",img);
				session.setAttribute("Comments",coment);
				response.sendRedirect("ShowResult.jsp");
				
				}
			} catch (SQLException e) {
				e.printStackTrace();
				logger.error("Database connection problem");
				throw new ServletException("DB Connection problem.");
			}
				

	}

	 private  Images getImageData(Connection conn, String id) throws SQLException {
		  System.out.println("Download file here start"	);
	      String sql = "select id, name, photo from subjects  where id=?  limit 1";
	 
	      PreparedStatement pstm = conn.prepareStatement(sql);
	      pstm.setString(1, id);
	      Images img = new Images();
	      ResultSet rs = pstm.executeQuery();
	      if(rs != null && rs.next()){
	          byte[] imageData = rs.getBytes("photo");
	          
	          String encode = Base64.getEncoder().encodeToString(imageData);
	          img.setPhoto(encode);
	     	 System.out.println("ResultServleet Download file Link >>>>> "	+encode);
	          
	          img.setName(rs.getString("name"));
	          img.setId(rs.getInt("id"));
	      }
	      rs.close();
          pstm.close();
	      return img;
	      
	      
	  }
	 
	 
	 private   List<Comments> getImageComments(Connection conn, String id) throws SQLException {
		  System.out.println("Download Comments here "	);
	      String sql = "select id, imageId, userId,comments,userName from Comments  where imageId=?";
	 
	      PreparedStatement pstm = conn.prepareStatement(sql);
	      pstm.setString(1, id);
	      List<Comments> list = new ArrayList();	
	      ResultSet rs = pstm.executeQuery();
	      while (rs != null && rs.next()){
	    	  
	          Comments cment = new Comments();
	
	          cment.setComments(rs.getString("comments"));
	          cment.setUserName(rs.getString("userName"));
	          cment.setId(rs.getInt("id"));
	          cment.setUserId(rs.getInt("userId"));
	          cment.setImageId(rs.getInt("imageId"));
	          list.add(cment);
	      }
	      rs.close();
         pstm.close();
         System.out.println("Comments Size "+list.size()	);
	      return list;
	      
	      
	  }
}
