package com.Servlet;

import java.io.*;
import com.service.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;



@WebServlet("/membre_add")
public class MembreAddServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException {
		response.setContentType("text/html");
		
		// display JSP
		RequestDispatcher dispatcher = 	request.getRequestDispatcher("/WEB-INF/View/membre_add.jsp");
		
		// send JSP no arguments needed 
		dispatcher.forward(request, response);
					
			
 		
	}

	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException { 
		
		try {
			// add a new member
			int id = MembreServiceImpl.getInstance().create(request.getParameter("nom"), request.getParameter("prenom"), request.getParameter("adresse"), request.getParameter("email"), request.getParameter("telephone"));
		
			// go to the member detail page
			response.sendRedirect("membre_details?id=" + id);
			
			//don't call again doGet 
			return;
		} 
		catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
				
		// refresh in case it failed 
		doGet(request,response);
	}

	
	
}