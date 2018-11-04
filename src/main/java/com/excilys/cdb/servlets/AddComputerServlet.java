package com.excilys.cdb.servlets;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.persistence.ComputerDAOInterface;
import com.excilys.cdb.persistence.CompanyDAOInterface;

public class AddComputerServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ComputerDAOInterface<Computer> computerDao = CompanyDAOInterface.getComputerDAO();
	ComputerDAOInterface<Company> companyDao = CompanyDAOInterface.getCompanyDAO();
	List<Company> companyPage = companyDao.findAll();
	Computer computer = new Computer();
	Company company = new Company();

	protected void doGet( HttpServletRequest request, HttpServletResponse response )	throws ServletException, IOException {
		
		request.setAttribute("companyPage", companyPage);
		this.getServletContext().getRequestDispatcher( "/WEB-INF/views/addComputer.jsp" ).forward( request, response );

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException,ServletException {
		company=companyDao.find((long) Integer.parseInt(request.getParameter("companyId")));
		computer.setName(request.getParameter("computerName"));
		String introduction = request.getParameter("introDate");
		String discontinuation = request.getParameter("discDate");
		computer.setName(request.getParameter("computerName"));
		computer.setIntroDate(Date.valueOf(introduction).toLocalDate());
		computer.setDiscDate(Date.valueOf(discontinuation).toLocalDate());
		computer.setCompany(company);
		boolean created = computerDao.create(computer);
		if(created) {
			System.out.println("Computer created and added to the database");	
			response.sendRedirect("dashboard");
		}else {
			System.out.println("Problem occured : computer not created");
			response.sendRedirect("500");
		}

	}

}
