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

import com.excilys.cdb.exceptions.NoNextPageException;
import com.excilys.cdb.exceptions.NoPreviousPageException;
import com.excilys.cdb.mapper.ComputerDTOMapper;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.model.Page;
import com.excilys.cdb.persistence.dto.ComputerDTO;
import com.excilys.cdb.service.ComputerService;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	Logger logger = LoggerFactory.getLogger(DashboardServlet.class);

	@Autowired
	ComputerService cpuService;

	ComputerDTOMapper computerMapper = ComputerDTOMapper.getInstance();
	List<Computer> computers;
	List<Computer> subComputers = new ArrayList<Computer>();
	List<ComputerDTO> subComputersDTO = new ArrayList<ComputerDTO>();
	int counter;


	protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

		ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
		ctx.getAutowireCapableBeanFactory().autowireBean(this);
		try {

			Page.setPage(request.getParameter("page"), request.getParameter("size"));
			if (request.getParameter("search") == null) {
				computers = cpuService.findAll("");
				counter = cpuService.count("");
			} else {
				request.setAttribute("search", request.getParameter("search"));
				computers = cpuService.findAll(request.getParameter("search"));
				counter = cpuService.count(request.getParameter("search"));
			}
			subComputersDTO.clear();
			for (int i = 0; i < computers.size(); i++) {
				subComputersDTO.add(computerMapper.fromComputer(computers.get(i)));
			}

		} catch (NoPreviousPageException e) {
			logger.error(e.getMessage());
		} catch (NoNextPageException e) {
			logger.error(e.getMessage());
		}

		request.setAttribute("computers", subComputersDTO);
		request.setAttribute("counter", counter);
		request.setAttribute("pageIndex", Page.getPage());
		request.setAttribute("pageSize", Page.getPageSize());
		this.getServletContext().getRequestDispatcher("/WEB-INF/views/dashboard.jsp").forward(request, response);
	}


	protected void doPost ( HttpServletRequest request, HttpServletResponse response )	throws ServletException, IOException {


		String[] checkedIds = request.getParameterValues("selection");
		String[] idTab = checkedIds[0].split(",");

		cpuService.deleteAll(idTab);


		response.sendRedirect("dashboard");
	}

}





