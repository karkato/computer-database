package com.excilys.cdb.persistence;

import java.io.IOException;
import java.util.List;


public interface CompanyDAOInterface<T> {
	
	/**
	 * Méthode de recherche des informations
	 * 
	 * @return ArrayList<T>
	 * @throws IOException 
	 * @throws DataBaseException 
	 */
	public abstract List<T> findAll() throws IOException;
  

}
