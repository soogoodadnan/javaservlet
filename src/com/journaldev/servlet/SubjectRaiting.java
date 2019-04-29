package com.journaldev.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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

@WebServlet("/SubjectRaiting")
public class SubjectRaiting extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static Logger logger = Logger.getLogger(SubmitComments.class);

    public SubjectRaiting() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // gets values of text fields
    	Images displayImage;
		HttpSession session = request.getSession();
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/Result");
		PrintWriter out= response.getWriter();
		
    	  String message = "";
        String raiting = request.getParameter("tableTextId");
        
        String comment = "asdds";
        
    	String errorMsg = null;
		if(comment == null || comment.equals("")){
			errorMsg ="Rait First";
		}
		
        
		

		if(errorMsg != null){
			out.println("<font color=red>"+errorMsg+"</font>");
			rd.include(request, response);
    
	}else{

		User user = (User) 	session.getAttribute("User");
		 displayImage = (Images) 	session.getAttribute("ResultImages");
    
		Connection con = (Connection) getServletContext().getAttribute("DBConnection");
		PreparedStatement ps = null;
         
        try {

        	
        	ps = con.prepareStatement("insert into Comments(imageId, userId,userName,comments,raiting) values (?,?,?,?,?)");
			ps.setInt(1, displayImage.getId());
			ps.setInt(2, user.getId());
			ps.setString(3, user.getName());
			ps.setString(4, comment);
			ps.setInt(5, Integer.parseInt(raiting));
            // sends the statement to the database server
            int row = ps.executeUpdate();
          
            if (row > 0) {
//                message = "Comment saved into database";
            }
            
        } catch (SQLException ex) {
            message = "ERROR: " + ex.getMessage();
            ex.printStackTrace();
        } finally {
            if (ps != null) {
                // closes the database connection
                try {
                	ps.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        
            session.setAttribute("id",    String.valueOf(displayImage.getId()));
            out.println("<font color=green>"+message+"</font>");
            rd.forward(request, response);
        
        }
    }
    }}
