package com.excilys.cdb.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.cdb.exceptions.DataBaseException;
import com.excilys.cdb.exceptions.DataException;
import com.excilys.cdb.mapper.ComputerDTOMapper;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.persistence.dto.ComputerDTO;
import com.excilys.cdb.service.CompanyService;
import com.excilys.cdb.service.ComputerService;

public class AddComputerServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(AddComputerServlet.class);
	CompanyService cpaService;
	ComputerService cpuService;
	ComputerDTOMapper mapper;
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		cpaService = CompanyService.getInstance();
		mapper=ComputerDTOMapper.getInstance();
		List<Company> companies = cpaService.findAll();
		request.setAttribute("companies", companies);

		this.getServletContext().getRequestDispatcher("/WEB-INF/views/addComputer.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ComputerDTO computerDto = new ComputerDTO();
		computerDto.name = request.getParameter("computerName");
		computerDto.introduced = request.getParameter("introduced");
		computerDto.discontinued = request.getParameter("discontinued");
		computerDto.companyId = request.getParameter("companyId");
		cpuService = ComputerService.getInstance();
		try {
			cpuService.create(mapper.computerDtoToComputer(computerDto));
			response.sendRedirect("dashboard");
		} catch (DataException de) {
			request.setAttribute("internError", de.getMessage());
			this.getServletContext().getRequestDispatcher("/WEB-INF/views/addComputer.jsp").forward(request, response);
		} 
		catch (DataBaseException e) {
			this.getServletContext().getRequestDispatcher("/WEB-INF/views/500.jsp").forward(request, response);
		} catch (SQLException e) {
			logger.error(e.getMessage());		
		}
	}

}
