package persistence;

import java.sql.Connection;

import model.Companies;
import model.Computers;

public class DAOFactory {
	
	  protected static final Connection conn = DBDemo.getInstance();   
	   
	  /**
	  * Retourne un objet Classe interagissant avec la BDD
	  * @return DAO
	  */
	  public static DAO<Companies> getCompanyDAO(){
	    return new CompanyDAO(conn);
	  }

	  /**
	  * Retourne un objet Professeur interagissant avec la BDD
	  * @return DAO
	  */
	  public static DAO<Computers> getComputerDAO(){
	    return new ComputerDAO(conn);
	  }
  

}
