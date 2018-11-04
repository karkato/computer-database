package com.excilys.cdb.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.cdb.exceptions.DataBaseException;
import com.excilys.cdb.exceptions.PageNumberException;
import com.excilys.cdb.mapper.ComputerDTOMapper;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.model.Page;
import com.excilys.cdb.persistence.dto.ComputerDTO;
import com.excilys.cdb.service.ComputerService;

public class DashboardServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(DashboardServlet.class);
	ComputerService cpuService;
	ComputerDTOMapper mapper;
	List<Computer> computers;
	List<Computer> subComputers = new ArrayList<Computer>();
	List<ComputerDTO> subComputersDTO = new ArrayList<ComputerDTO>();
	int counter;


	protected void doGet( HttpServletRequest request, HttpServletResponse response )	throws ServletException, IOException {


		try {
			cpuService = ComputerService.getInstance();
			mapper = ComputerDTOMapper.getInstance();

			counter = cpuService.count();
			Page.setPage(request.getParameter("pageNumber"), request.getParameter("pageElement"));
			if (request.getParameter("search") == null) {
				try {
					computers = cpuService.findAll("");
				} catch (PageNumberException e) {
					e.printStackTrace();
				}
			} else {
				request.setAttribute("search", request.getParameter("search"));
				try {
					computers = cpuService.findAll(request.getParameter("search"));
				} catch (PageNumberException e) {
					e.printStackTrace();
				}
			}
			subComputersDTO.clear();
			for (int i = 0; i < computers.size(); i++) {
				subComputersDTO.add(mapper.computerDtoFromComputer(computers.get(i)));
			}

		} catch (DataBaseException dbe) {
			this.getServletContext().getRequestDispatcher("/WEB-INF/views/500.jsp").forward(request, response);
		}

		request.setAttribute("computerPage", subComputersDTO);
		request.setAttribute("counter", counter);
		request.setAttribute("pageIndex", Page.getPage());
		request.setAttribute("pageSize", Page.getPageSize());

		this.getServletContext().getRequestDispatcher("/WEB-INF/views/dashboard.jsp").forward(request, response);

	}


	//Delete part --> next sprint
	protected void doPost ( HttpServletRequest request, HttpServletResponse response )	throws ServletException, IOException {

		cpuService = ComputerService.getInstance();

		String[] checkedIds = request.getParameterValues("selection");
		String[] idTab = checkedIds[0].split(",");

		try {
			cpuService.deleteAll(idTab);
		} catch (DataBaseException e) {
			this.getServletContext().getRequestDispatcher("/WEB-INF/views/500.jsp").forward(request, response);
		}

		response.sendRedirect("dashboard");
	}

}





