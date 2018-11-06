package com.excilys.cdb.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.excilys.cdb.exceptions.DataBaseException;
import com.excilys.cdb.exceptions.DataException;
import com.excilys.cdb.exceptions.NameException;
import com.excilys.cdb.exceptions.PageNumberException;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.model.Page;
import com.excilys.cdb.persistence.ComputerDAO;
import com.excilys.cdb.validator.ComputerValidator;
import com.excilys.cdb.validator.PageValidator;

public class ComputerService {
	
	private static ComputerService computerService = null;
	ComputerDAO computerDao;

	private ComputerService() {
		computerDao = ComputerDAO.getInstance();
	}

	public static ComputerService getInstance() {
		if (computerService == null) {
			computerService = new ComputerService();
		}
		return computerService;
	}

	public Optional<Computer> find(Long id) throws IOException, DataBaseException, SQLException{
		return computerDao.find(id);
	}

	public boolean create(Computer computer) throws IOException, NameException, DataException, DataBaseException, SQLException {
		ComputerValidator.computerValidator(computer);
		return computerDao.create(computer);
	}

	public boolean update(Computer computer) throws IOException, NameException, DataException, DataBaseException, SQLException{
		ComputerValidator.computerValidator(computer);
		return computerDao.update(computer);
	}

	public boolean delete(Long i) throws IOException, DataBaseException, SQLException  {
		return computerDao.delete(i);
	}
	
	public void deleteAll(String[] idTab) throws IOException, NumberFormatException, DataBaseException, SQLException {
		for (int i = 0; i < idTab.length; i++) {
			if(!("".equals(idTab[i])))
			delete(Long.parseLong(idTab[i]));
		}
	}

	public <T> List<Computer> findAll() throws IOException, PageNumberException, DataBaseException, SQLException{
		PageValidator.previousPageValidator();
		List<Computer> list = computerDao.findAll(Page.getPage(),Page.getPageSize());
		PageValidator.nextPageValidator(list);
		return list;
	}
	
	public <T> List<Computer> findAll(String name) throws  IOException, PageNumberException, DataBaseException, SQLException{
		PageValidator.previousPageValidator();
		List<Computer> list = computerDao.findAll(name,Page.getPage(),Page.getPageSize());
		PageValidator.nextPageValidator(list);
		return list;
	}
	public int count() throws  IOException, DataBaseException, SQLException{
		return computerDao.count();
	}

}
