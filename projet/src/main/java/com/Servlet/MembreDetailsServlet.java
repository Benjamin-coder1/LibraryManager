package com.Servlet;

import java.io.*;
import com.model.Abonnement;
import com.model.Membre;
import com.service.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

 
@WebServlet("/membre_details")
public class MembreDetailsServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException {
		response.setContentType("text/html");		 
		
		// display JSP
		RequestDispatcher dispatcher = 	request.getRequestDispatcher("/WEB-INF/View/membre_details.jsp");
		
		
 		try { 	
 			// add as arguments member details
 			request.setAttribute("membre", MembreServiceImpl.getInstance().getById( Integer.valueOf( request.getParameter("id" ))) );
 			
 			// add as argument member loans 
 			request.setAttribute("emprunts", EmpruntServiceImpl.getInstance().getListCurrentByMembre( Integer.valueOf( request.getParameter("id" ))) );
 		} 
 		
		catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
  		}	
 	
		// send JSP
		dispatcher.forward(request, response);
					
			
 		
	}

	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException { 
		
		 
 		try {
 			// update member details
			MembreServiceImpl.getInstance().update(new Membre( Integer.valueOf(request.getParameter("id")) , request.getParameter("nom"), request.getParameter("prenom"),request.getParameter("adresse"),request.getParameter("email"), request.getParameter("telephone"), Abonnement.valueOf( request.getParameter("abonnement")) )) ;
		} 
		catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
 		// refresh the page 
		doGet(request,response);
		
		
	}

	
	
}