package com.Servlet;

import java.io.*;
import java.time.LocalDate;
import com.service.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;



@WebServlet("/emprunt_add")
public class EmpruntAddServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException {
		response.setContentType("text/html");
		
		 				
		// display JSP
		RequestDispatcher dispatcher = 	request.getRequestDispatcher("/WEB-INF/View/emprunt_add.jsp");
		
		// add arguments
 		try { 
			request.setAttribute("livreDispo", LivreServiceImpl.getInstance().getListDispo() );			
			request.setAttribute("membreEmpruntPossible", MembreServiceImpl.getInstance().getListMembreEmpruntPossible() );			 
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
			// create a new loan
			if (request.getParameter("idMembre") != null && request.getParameter("idLivre") != null ) {
				// a book and a member have right been selected 
				EmpruntServiceImpl.getInstance().create(Integer.valueOf(request.getParameter("idMembre")), Integer.valueOf(request.getParameter("idLivre")), LocalDate.now() );
				
				// redirect to the list of the loans (asked)
				response.sendRedirect("emprunt_list");
				
				// don't call DoGet
				return;				
			}
			
			
		} 
		catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// refresh page in case it fail
		doGet(request,response);
		
		
		
	}


	
	
}