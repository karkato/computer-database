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
	Page pages = new Page();
	int pageIndex=1;
	int pageElmts=25;
	int pageIndexMax;
	DAO<Computer> computerdao = DAOFactory.getComputerDAO();
	List<Computer> computer = computerdao.findAll();
	
	protected void doGet( HttpServletRequest request, HttpServletResponse response )	throws ServletException, IOException {

		
		String pageElement = request.getParameter("pageElement");
		String pageNumber = request.getParameter("pageNumber");
		pageIndexMax=computer.size();
		if(pageElement == null || pageNumber == null ) {
			pageElement = "25";
			pageNumber ="1";	
		}
		pageIndex =Integer.parseInt(pageNumber);
		pageElmts =Integer.parseInt(pageElement);
		if(pageIndex <=1 ) {
			pageIndex =1;
		}else if(pageIndex > pageIndexMax){
			pageIndex = pageIndexMax;
		}
		pages.setPage(pageIndex);
		pages.setPageSize(pageElmts);
		pageIndexMax=computer.size();
		pageIndex= pages.getPage();
		pageElmts=pages.getPageSize();
		
		List<Computer> computerPage = pages.getPage(computer);
		request.setAttribute("computerPage", computerPage);
		request.setAttribute("pageElmts", pageElmts);
		request.setAttribute("pageIndex", pageIndex);
		request.setAttribute("pageIndexMax", pageIndexMax);
		
		this.getServletContext().getRequestDispatcher( "/WEB-INF/views/dashboard.jsp" ).forward( request, response );

	}
	
	
	/*protected void doPost ( HttpServletRequest request, HttpServletResponse response )	throws ServletException, IOException {
		
		FOR THE DELETE PART
		this.getServletContext().getRequestDispatcher( "/WEB-INF/views/dashboard.jsp" ).forward( request, response );
		
	}
	*/
	
	

}
