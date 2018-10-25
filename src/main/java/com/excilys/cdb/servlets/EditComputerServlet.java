package com.excilys.cdb.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditComputerServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet( HttpServletRequest request, HttpServletResponse response )	throws ServletException, IOException {
		String message = "Transmission de variables : ok ! ";
		request.setAttribute("test", message);
		this.getServletContext().getRequestDispatcher("/WEB-INF/views/editComputer.jsp" ).forward( request, response );

	}
}
