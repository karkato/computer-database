package com.excilys.cdb.persistence;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.excilys.cdb.exceptions.DataBaseException;
import com.excilys.cdb.model.Company;


public interface CompanyDAOInterface<T> {

	abstract List<T> findAll() throws IOException, DataBaseException, SQLException;

	Company findByName(String name);

	Optional<Company> find(Long id) throws IOException, SQLException;
	public abstract boolean delete(Long id) throws IOException, DataBaseException, SQLException;


}
