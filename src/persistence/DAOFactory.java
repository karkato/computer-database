package persistence;

import java.sql.Connection;

import model.Company;
import model.Computer;

public class DAOFactory {
	
	  protected static final Connection conn = DBDemo.getInstance();   
	   
	  /**
	  * Retourne un objet Classe interagissant avec la BDD
	  * @return DAO
	  */
	  public static DAO<Company> getCompanyDAO(){
	    return new CompanyDAO(conn);
	  }

	  /**
	  * Retourne un objet Professeur interagissant avec la BDD
	  * @return DAO
	  */
	  public static DAO<Computer> getComputerDAO(){
	    return new ComputerDAO(conn);
	  }
  

}
