package com.excilys.cdb.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.cdb.model.Computer;
import com.excilys.cdb.model.Page;
import com.excilys.cdb.persistence.DAO;
import com.excilys.cdb.persistence.DAOFactory;

public class DashboardServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet( HttpServletRequest request, HttpServletResponse response )	throws ServletException, IOException {
		//Computer computer = new Computer();
		Page pages = new Page();
		DAO<Computer> computerdao = DAOFactory.getComputerDAO();
		List<Computer> computer = computerdao.findAll();
		pages.setPage(1);
		pages.setPageSize(10);
		List<Computer> computerPage = pages.getPage(computer);
		request.setAttribute("computer", computerPage);
		this.getServletContext().getRequestDispatcher( "dashboard" ).forward( request, response );

	}

}
