package com.excilys.cdb.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.excilys.cdb.exceptions.DataBaseException;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.persistence.CompanyDAO;

public class CompanyService {
	
	private static CompanyService companyService = null;
	CompanyDAO companyDao;

	private CompanyService() {
		companyDao = CompanyDAO.getInstance();
	}

	public static CompanyService getInstance() {
		if (companyService == null) {
			companyService = new CompanyService();
		}
		return companyService;
	}

	public <T> List<Company> findAll() throws IOException, DataBaseException, SQLException {
		List<Company> list;
		list = companyDao.findAll();
		return list;
	}

}
