package com.Servlet;

import java.io.*;
import com.service.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;



@WebServlet("/livre_list")
public class LivreListServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException {
		response.setContentType("text/html");
		
	 
		// display JSP
		RequestDispatcher dispatcher = 	request.getRequestDispatcher("/WEB-INF/View/livre_list.jsp");
		
		 
 		try { 	
 			// add as argument book list 
 			request.setAttribute("ListLivre", LivreServiceImpl.getInstance().getList() );
		} 
 		
		catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
  		}	
 	
		// send JSP
		dispatcher.forward(request, response);
					
			
 		
	}

	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException { 
		doGet(request,response);
	}

	
	
}