package com.excilys.cdb.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.excilys.cdb.exceptions.DataException;
import com.excilys.cdb.mapper.CompanyDTOMapper;
import com.excilys.cdb.mapper.ComputerDTOMapper;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.persistence.dto.CompanyDTO;
import com.excilys.cdb.persistence.dto.ComputerDTO;
import com.excilys.cdb.service.CompanyService;
import com.excilys.cdb.service.ComputerService;

@WebServlet("/addComputer")

public class AddComputerServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(AddComputerServlet.class);
	
	@Autowired
	CompanyService cpaService;
	@Autowired
	ComputerService cpuService;
	
	ComputerDTOMapper computerMapper=ComputerDTOMapper.getInstance();
	CompanyDTOMapper companyMapper=CompanyDTOMapper.getInstance();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
	      ctx.getAutowireCapableBeanFactory().autowireBean(this);
			List<Company> companies = cpaService.findAll();
			List<CompanyDTO> subCompaniesDTO = new ArrayList<CompanyDTO>();
			for (int i = 0; i < companies.size(); i++) {
				subCompaniesDTO.add(companyMapper.fromCompany(companies.get(i)));
			}
			request.setAttribute("companies", companies);
		this.getServletContext().getRequestDispatcher("/WEB-INF/views/addComputer.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ComputerDTO computerDto = new ComputerDTO();
		computerDto.name = request.getParameter("computerName");
		computerDto.introduced = request.getParameter("introDate");
		computerDto.discontinued = request.getParameter("discDate");
		computerDto.companyId = request.getParameter("companyId");
	
		try {
			cpuService.create(computerMapper.toComputer(computerDto));
			response.sendRedirect("dashboard");
		} catch (DataException de) {
			request.setAttribute("internError", de.getMessage());
			this.getServletContext().getRequestDispatcher("/WEB-INF/views/addComputer.jsp").forward(request, response);
		} 
	}

}
