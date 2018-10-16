package model;

import java.util.List;

import persistence.DAO;
import persistence.DAOFactory;

public class test {


	/**
	 * Connect to the DB and do some stuff
	 */
	public static void main(String[] args) {


		//DBDemo app = new DBDemo();
		//app.run();

		DAO<Computers> computerdao = DAOFactory.getComputerDAO();

		for(int i=1;i<10;i++) {
			Computers computer = computerdao.find(i);
			System.out.println("\tComputer N:"+ computer.getId_pc()+ "-Name :"+computer.getName_pc());
		}

		System.out.println("\n\t****************************************");

		DAO<Companies> companydao = DAOFactory.getCompanyDAO();


		Companies company = companydao.find(1);
		System.out.println("\tCompany of "+company.getName_comp());
		
		
		System.out.println("\n\t****************************************");

		//DAO<Computers> computerdao = DAOFactory.getComputerDAO();

		System.out.print("\tComputer list : ");
		List<Companies> companies = companydao.findAll();
		for(Companies pc : companies) {
		System.out.println("\t"+pc);
		}

	}
}
