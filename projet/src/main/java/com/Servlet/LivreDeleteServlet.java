package com.Servlet;

import java.io.*;
import java.util.List;

import com.model.Emprunt;
import com.service.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

 
@WebServlet("/livre_delete")
public class LivreDeleteServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException {
		response.setContentType("text/html");
		
		 
		// display JSP
		RequestDispatcher dispatcher = 	request.getRequestDispatcher("/WEB-INF/View/livre_delete.jsp");
		
		
 		try { 	
 			// send as argument the list of all the books 
 			request.setAttribute("livre", LivreServiceImpl.getInstance().getById( Integer.valueOf( request.getParameter("id" ))) );
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
			// delete book 
			LivreServiceImpl.getInstance().delete( Integer.valueOf(request.getParameter("id") )) ;
			
			
			// free loan 
			List<Emprunt> emprunts = EmpruntServiceImpl.getInstance().getListCurrentByLivre( Integer.valueOf( request.getParameter("id" )));
			for(Emprunt emprunt : emprunts ) {
				EmpruntServiceImpl.getInstance().returnBook(emprunt.getId());
			}
			
			// come back to the book list (asked) 
			response.sendRedirect("livre_list");
			
			// don't call doGet 
			return;
		} 
		
		catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// refresh the page if delete fail (moreover we will know it failed like that)
		doGet(request,response);
	}

	
	
}