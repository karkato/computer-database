package com.excilys.cdb.persistence;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.excilys.cdb.exceptions.DataBaseException;
import com.excilys.cdb.exceptions.DataException;
import com.excilys.cdb.model.Computer;


public interface ComputerDAOInterface<T> {


	public abstract boolean create(T obj) throws DataException, IOException, DataException, DataBaseException, SQLException;

	public abstract boolean delete(Long id) throws IOException, DataBaseException, SQLException;

	
	public abstract boolean update(T obj) throws DataException, IOException, DataBaseException, SQLException;

	
	public abstract Optional<Computer> find(Long id) throws IOException, DataBaseException, SQLException;

	
	public abstract List<T> findAll(String name, int page, int size) throws IOException, DataBaseException, SQLException;

	
	public abstract List<T> findAll(int page, int size) throws IOException, DataBaseException, SQLException;
	
	int count() throws IOException, DataBaseException, SQLException;

}

