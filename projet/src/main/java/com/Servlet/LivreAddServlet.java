package com.Servlet;

import java.io.*;
import com.service.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;



@WebServlet("/livre_add")
public class LivreAddServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException {
		response.setContentType("text/html");
		
		// display JSP
		RequestDispatcher dispatcher = 	request.getRequestDispatcher("/WEB-INF/View/livre_add.jsp");		

		// send JSP no arguments needed 
		dispatcher.forward(request, response);
					
			
 		
	}

	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException { 
		 
		try {
			// create a new book 
			int id = LivreServiceImpl.getInstance().create(request.getParameter("titre"), request.getParameter("auteur"), request.getParameter("isbn") );
			
			// go on this new book detail list 
			response.sendRedirect("livre_details?id=" + id);
			
			// don't call doGet
			return;
		} 
		
		catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// refresh page 
		doGet(request,response);
		
	}

	
	
}