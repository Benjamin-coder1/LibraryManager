package com.Servlet;

import java.io.*;
import com.service.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

 
@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException {
		response.setContentType("text/html");
		
		
		// display JSP
		RequestDispatcher dispatcher = 	request.getRequestDispatcher("/WEB-INF/View/dashboard.jsp");
		
		// send parameters 
 		try { 			
			request.setAttribute("nbLivres", LivreServiceImpl.getInstance().count() );
			request.setAttribute("nbMembres", MembreServiceImpl.getInstance().count() );
			request.setAttribute("nbEmprunts", EmpruntServiceImpl.getInstance().count() );
			request.setAttribute("getListCurrent", EmpruntServiceImpl.getInstance().getListCurrent() );
		} 
 		
		catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
  		}	
 	
		// send
		dispatcher.forward(request, response);
					
			
 		
	}

	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException { 
		doGet(request,response);
	}

	
	
}