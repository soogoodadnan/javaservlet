package com.journaldev.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.log4j.Logger;
 
@WebServlet("/uploadServlet")
@MultipartConfig(maxFileSize = 16177215)    // upload file's size up to 16MB
public class FileUploadDBServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static Logger logger = Logger.getLogger(RegisterServlet.class);
    // database connection settings
     
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // gets values of text fields
    	  String message = "";
        String name = request.getParameter("name");
        // obtains the upload file part in this multipart request
        Part filePart = request.getPart("photo");
        
    	String errorMsg = null;
		if(name == null || name.equals("")){
			errorMsg ="Name can't be null or empty";
		}
		if(filePart.getSize()  == 0 ){
			errorMsg = "Please select file from computer";
			System.out.println("Null File");
		}
        
		

		if(errorMsg != null){
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/Upload.jsp");
			PrintWriter out= response.getWriter();
			out.println("<font color=red>"+errorMsg+"</font>");
			rd.include(request, response);
    
	}else{
        InputStream inputStream = null; // input stream of the upload file
        
            // prints out some information for debugging
            System.out.println(filePart.getName());
            System.out.println(filePart.getSize());
            System.out.println(filePart.getContentType());
             
            // obtains input stream of the upload file
            inputStream = filePart.getInputStream();
     
		Connection con = (Connection) getServletContext().getAttribute("DBConnection");
		PreparedStatement ps = null;
         
        try {

        	ps = con.prepareStatement("insert into subjects(name,photo) values (?,?)");
			ps.setString(1, name);
			
			logger.info("User upload subject ="+name);
             
            if (inputStream != null) {
                // fetches input stream of the upload file for the blob column
            	ps.setBlob(2, inputStream);
            }
 
            // sends the statement to the database server
            int row = ps.executeUpdate();
          
            if (row > 0) {
                message = "File uploaded and saved into database";
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
            // sets the message in request scope
            request.setAttribute("Message", message);
             
            // forwards to the message page
            getServletContext().getRequestDispatcher("/home.jsp").forward(request, response);
        }
    }
}
}
