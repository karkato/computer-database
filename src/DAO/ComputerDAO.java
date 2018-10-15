package DAO;
import java.util.List;

import model.Computers;

public class ComputerDAO extends DAO<Computers> {
	

	    List<Computers> findAll();
	    List<Computers> findById();
	    List<Computers> findByName();
	    boolean insertComputer(Computers computer) {
			return false;
		}
	    boolean updateComputer(Computers computer) {
			return false;
		}
	    boolean deleteComputer(Computers computer) {
			return false;
		}
		@Override
		public Computers find(long id) {
			// TODO Auto-generated method stub
			return null;
		}
		@Override
		public Computers create(Computers obj) {
			// TODO Auto-generated method stub
			return null;
		}
		@Override
		public Computers update(Computers obj) {
			// TODO Auto-generated method stub
			return null;
		}
		@Override
		public void delete(Computers obj) {
			// TODO Auto-generated method stub
			
		}

}
