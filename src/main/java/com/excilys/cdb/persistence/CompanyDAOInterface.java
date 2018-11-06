package com.excilys.cdb.persistence;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.excilys.cdb.model.Company;


public interface CompanyDAOInterface<T> {

	/**
	 * Méthode de recherche des informations
	 * 
	 * @return ArrayList<T>
	 * @throws IOException 
	 * @throws DataBaseException 
	 */
	public abstract List<T> findAll() throws IOException;

	Company findByName(String name);

	Optional<Company> find(Long id) throws IOException, SQLException;


}
