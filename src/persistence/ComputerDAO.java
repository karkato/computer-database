package persistence;
import java.util.List;

import model.Computers;

public interface ComputerDAO {
	

	    List<Computers> findAll();
	    List<Computers> findById();
	    List<Computers> findByName();
	    boolean insertComputer(Computers computer);
	    boolean updateComputer(Computers computer);
	    boolean deleteComputer(Computers computer);

}
