package com.excilys.cdb.persistence;

import java.sql.Connection;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;

public class DAOFactory {
	
	  protected static final Connection conn = DBDemo.getInstance();   
	   
	  /**
	  * Retourne un objet Classe interagissant avec la BDD
	  * @return DAO
	  */
	  public static DAO<Company> getCompanyDAO(){
	    return new CompanyDAO();
	  }

	  /**
	  * Retourne un objet Professeur interagissant avec la BDD
	  * @return DAO
	  */
	  public static DAO<Computer> getComputerDAO(){
	    return new ComputerDAO();
	  }
  

}
