package com.excilys.cdb.persistence;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.excilys.cdb.exceptions.DataException;
import com.excilys.cdb.model.Computer;


public interface ComputerDAOInterface<T> {


	public abstract boolean create(T obj) throws DataException, IOException;

	public abstract boolean delete(Long id) throws IOException;

	
	public abstract boolean update(T obj) throws DataException, IOException;

	
	public abstract Optional<Computer> find(Long id) throws IOException;

	
	public abstract List<T> findAll(String name, int page, int size) throws IOException;

	
	public abstract List<T> findAll(int page, int size) throws IOException;
	
	int count() throws IOException;
}

