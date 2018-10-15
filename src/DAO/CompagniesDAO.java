package DAO;


import java.util.List;

import model.Companies;
import model.Computers;

public class CompagniesDAO extends DAO<Computers>{


	
		    List<Companies> findAll();
		    List<Companies> findById();
		    List<Companies> findByName();
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
