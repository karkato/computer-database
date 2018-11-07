package com.excilys.cdb.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import javax.ejb.SessionContext;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import com.excilys.cdb.exceptions.DataBaseException;
import com.excilys.cdb.exceptions.DataException;
import com.excilys.cdb.exceptions.NameException;
import com.excilys.cdb.exceptions.PageNumberException;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.model.Page;
import com.excilys.cdb.persistence.ComputerDAO;
import com.excilys.cdb.validator.ComputerValidator;
import com.excilys.cdb.validator.PageValidator;

@TransactionManagement(TransactionManagementType.CONTAINER)
public class ComputerService {

	private static ComputerService computerService = null;
	ComputerDAO computerDao;
	private SessionContext context;

	private ComputerService() {
		computerDao = ComputerDAO.getInstance();
	}

	public static ComputerService getInstance() {
		if (computerService == null) {
			computerService = new ComputerService();
		}
		return computerService;
	}
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Optional<Computer> find(Long id) throws IOException, DataBaseException, SQLException{
		Optional<Computer> computer;
		try {
			computer = computerDao.find(id);	
		} catch (DataBaseException dbe) {
			context.setRollbackOnly();
			throw dbe;
		}
		return computer;
	}
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public boolean create(Computer computer) throws IOException, NameException, DataException, DataBaseException, SQLException {
		try {
			ComputerValidator.computerValidator(computer);
			return computerDao.create(computer);
		} catch (DataBaseException dbe) {
			context.setRollbackOnly();
			throw dbe;
		}
	}
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public boolean update(Computer computer) throws IOException, NameException, DataException, DataBaseException, SQLException{
		try {
			ComputerValidator.computerValidator(computer);
			return computerDao.update(computer);
		} catch (DataBaseException dbe) {
			context.setRollbackOnly();
			throw dbe;
		}

	}

	public boolean delete(Long i) throws IOException, DataBaseException, SQLException  {
		return computerDao.delete(i);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void deleteAll(String[] idTab) throws IOException, DataBaseException, NumberFormatException, SQLException {
		try {
			for (int i = 0; i < idTab.length; i++) {
				if (!("".equals(idTab[i])))
					delete(Long.parseLong(idTab[i]));
			}
		} catch (DataBaseException dbe) {
			context.setRollbackOnly();
			throw dbe;
		}
	}
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public <T> List<Computer> findAll() throws IOException, PageNumberException, DataBaseException, SQLException{
		List<Computer> list;
		try {
			PageValidator.previousPageValidator();
			list = computerDao.findAll(Page.getPage(), Page.getPageSize());
			PageValidator.nextPageValidator(list);
		} catch (DataBaseException dbe) {
			context.setRollbackOnly();
			throw dbe;
		}
		return list;
	}
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public <T> List<Computer> findAll(String name) throws  IOException, PageNumberException, DataBaseException, SQLException{
		List<Computer> list;
		try {
			PageValidator.previousPageValidator();
			list = computerDao.findAll(name, Page.getPage(), Page.getPageSize());
			PageValidator.nextPageValidator(list);
		} catch (DataBaseException dbe) {
			context.setRollbackOnly();
			throw dbe;
		}
		return list;
	}
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public int count(String name) throws  IOException, DataBaseException, SQLException{
		int result = 0;
		try {
			result = computerDao.count(name);
		} catch (DataBaseException dbe) {
			context.setRollbackOnly();
			throw dbe;
		}
		return result;
	}

}
