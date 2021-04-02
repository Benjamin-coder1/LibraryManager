package com.Servlet;

import java.io.*;
import com.model.Livre;
import com.service.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

 
@WebServlet("/livre_details")
public class LivreDetailsServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException {
		response.setContentType("text/html"); 
	 
		
		// display JSP
		RequestDispatcher dispatcher = 	request.getRequestDispatcher("/WEB-INF/View/livre_details.jsp");
		
		
 		try { 	
 			// add as arguments book details 
 			request.setAttribute("livre", LivreServiceImpl.getInstance().getById( Integer.valueOf( request.getParameter("id" ))) );
 			
 			// add as argument the list of loaner 
 			request.setAttribute("emprunteur", EmpruntServiceImpl.getInstance().getListCurrentByLivre( Integer.valueOf( request.getParameter("id" )))  );
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
			// update data by the "Enregistrer" button 
			LivreServiceImpl.getInstance().update(new Livre( Integer.valueOf(request.getParameter("id")), request.getParameter("titre"),request.getParameter("auteur"),request.getParameter("isbn") ) ) ;
		} 
		catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// refresh the page 
		doGet(request,response);
	}

	
	
}