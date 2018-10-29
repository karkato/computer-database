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
import com.excilys.cdb.persistence.DAO;
import com.excilys.cdb.persistence.DAOFactory;
import com.excilys.cdb.persistence.dto.ComputerDTO;

public class AddComputerServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	DAO<Computer> computerDao = DAOFactory.getComputerDAO();
	DAO<Company> companyDao = DAOFactory.getCompanyDAO();
	List<Company> companyPage = companyDao.findAll();
	ComputerDTO computerDto = new ComputerDTO();
	Company company = new Company();
	
	protected void doGet( HttpServletRequest request, HttpServletResponse response )	throws ServletException, IOException {
		
		request.setAttribute("companyPage", companyPage);
		this.getServletContext().getRequestDispatcher( "/WEB-INF/views/addComputer.jsp" ).forward( request, response );

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException,ServletException {
		company=companyDao.findByName(request.getParameter("companyId"));
		computerDto.setName(request.getParameter("computerName"));
		computerDto.setIntroDate(Date.valueOf(request.getParameter("introDate")).toLocalDate());
		computerDto.setDiscDate(Date.valueOf(request.getParameter("discDate")).toLocalDate());
		computerDto.setCompany(company);
		computerDao.create(computerDto);
		
		response.sendRedirect("dashboard");
		//this.getServletContext().getRequestDispatcher( "/WEB-INF/views/dashboard.jsp" ).forward( request, response );
	}

}
