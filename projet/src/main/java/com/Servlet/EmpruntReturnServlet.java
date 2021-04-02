package com.Servlet;

import java.io.*;
import java.time.LocalDate;
import com.model.Emprunt;
import com.service.EmpruntServiceImpl;
import com.service.ServiceException;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;



@WebServlet("/emprunt_return")
public class EmpruntReturnServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException {
		response.setContentType("text/html");		
		 
		// display JSP
		RequestDispatcher dispatcher = 	request.getRequestDispatcher("/WEB-INF/View/emprunt_return.jsp");
		
		// what we do depend whether we have an "id" argument 
 		try { 
 			
 			if ( request.getParameter("id") != null ) {
 				// directly return book with the argument "id"
 				EmpruntServiceImpl.getInstance().returnBook(Integer.valueOf(request.getParameter("id")));
 				
 				// coming back to loan list (asked)
 				response.sendRedirect("emprunt_list");
  			}
 			
 			else {
 				// send current list of loan
 				request.setAttribute("ListCurrentEmprunt", EmpruntServiceImpl.getInstance().getListCurrent() );
 				// send
 				dispatcher.forward(request, response);
 			}
			
		} 
 		
		
 		catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  		
 		
 		catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
			
 		
	}

	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException {
		 
		try {
			// directly return book with the argument "id"
			EmpruntServiceImpl.getInstance().returnBook(Integer.valueOf( request.getParameter("id") ) );
			
			// coming back to book list (asked)
			response.sendRedirect("livre_list");
		} 
		
		catch ( NumberFormatException | ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
 	}

	
	
}