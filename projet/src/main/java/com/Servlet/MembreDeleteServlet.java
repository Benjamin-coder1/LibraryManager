package com.Servlet;

import java.io.*;
import java.util.List;

import com.model.Emprunt;
import com.service.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

 
@WebServlet("/membre_delete")
public class MembreDeleteServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException {
		response.setContentType("text/html");
	 
		// display JSP
		RequestDispatcher dispatcher = 	request.getRequestDispatcher("/WEB-INF/View/membre_delete.jsp");
		
		
 		try { 	
 			// add as argument member details 
 			request.setAttribute("membre", MembreServiceImpl.getInstance().getById( Integer.valueOf( request.getParameter("id" ))) );
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
 			// delete the member
			MembreServiceImpl.getInstance().delete( Integer.valueOf(request.getParameter("id") )) ;
			
			// free books
			List<Emprunt> emprunts = EmpruntServiceImpl.getInstance().getListCurrentByMembre( Integer.valueOf( request.getParameter("id" )));
			for(Emprunt emprunt : emprunts ) {
				EmpruntServiceImpl.getInstance().returnBook(emprunt.getId());
			}				
			
			// redirect to the member list (asked)
			response.sendRedirect("membre_list");
			
			// don't call doGet
			return;
		} 
		catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
 		// refresh page in case it failed
		doGet(request,response);
	}

	
	
}