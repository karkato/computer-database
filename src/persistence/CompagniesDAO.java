package persistence;


import java.util.List;

import model.Companies;

public interface CompagniesDAO {


	
		    List<Companies> findAll();
		    List<Companies> findById();
		    List<Companies> findByName();

}
