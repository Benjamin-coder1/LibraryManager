package com.Servlet;

import java.io.*;
import com.service.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;



@WebServlet("/emprunt_list")
public class EmpruntListServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException {
		response.setContentType("text/html");
		
	 
		// display JSP
		RequestDispatcher dispatcher = 	request.getRequestDispatcher("/WEB-INF/View/emprunt_list.jsp");
		
		
		// ad arguments 
 		try { 	
 			if (request.getParameter("show") == null ) {
 				// display only current loans (no matter "show" value)
 				request.setAttribute("ListEmprunt", EmpruntServiceImpl.getInstance().getListCurrent() );
 			}
 			else {
 				// display all loans 
 				request.setAttribute("ListEmprunt", EmpruntServiceImpl.getInstance().getList() ); 				
 			}
		} 
 		
		catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
  		}	
 	
		// send jSP
		dispatcher.forward(request, response);
					
			
 		
	}

	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException { 
		doGet(request,response);
	}

	
	
}