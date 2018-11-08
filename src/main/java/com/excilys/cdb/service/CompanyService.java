package com.excilys.cdb.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import com.excilys.cdb.exceptions.DataBaseException;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.persistence.CompanyDAO;
import com.excilys.cdb.persistence.ComputerDAO;

public class CompanyService {
	
	private static CompanyService companyService = null;
	CompanyDAO companyDao;
	ComputerDAO computerDao;
	@Resource
	private SessionContext context;

	private CompanyService() {
		companyDao = CompanyDAO.getInstance();
		computerDao = ComputerDAO.getInstance();
	}

	public static CompanyService getInstance() {
		if (companyService == null) {
			companyService = new CompanyService();
		}
		return companyService;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public <T> List<Company> findAll() throws IOException, DataBaseException, SQLException {
		List<Company> list;
		try {
			list = companyDao.findAll();
		} catch (DataBaseException dbe) {
			context.setRollbackOnly();
			throw dbe;
		}
		return list;
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public boolean delete(Long id) throws IOException, DataBaseException, SQLException {
		try {
			computerDao.deleteByCompany(id);
			companyDao.delete(id);
			return true;
		} catch (DataBaseException dbe) {
			context.setRollbackOnly();
			throw dbe;
		}
		
	}

}
