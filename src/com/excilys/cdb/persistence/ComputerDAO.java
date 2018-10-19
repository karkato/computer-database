package com.excilys.cdb.persistence;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.excilys.cdb.model.Computer;

public class ComputerDAO extends DAO<Computer> {

	protected ComputerDAO() {
		super();
	}
	static ComputerDAO computerDAO = new ComputerDAO();

	public static ComputerDAO getInstance() {
		return computerDAO;
	}

	private static String findQuery = "SELECT id,name,introduced,discontinued,company_id FROM computer WHERE id= ?";
	private static String findAllQuery ="SELECT id,name,introduced,discontinued,company_id FROM computer " ;
	private static String createQuery ="INSERT INTO computer (name,introduced,discontinued,company_id) VALUES(?,?,?,?))";
	private static String updateQuery = "UPDATE computer SET name = ?, introduced =? , discontinued =? , company_id =?, WHERE id =? ";	
	private static String deleteQuery = "DELETE FROM computer WHERE id = ?";
	
	/**
	 * Méthode de recherche
	 * @param id
	 * @return Computers 
	 */
	@Override
	public Computer find(Long id) {
		Computer computer = new Computer();

		try {
			PreparedStatement findStmt = this.connect.prepareStatement(findQuery);
			findStmt.setFloat(1, id);
			ResultSet result = findStmt.executeQuery();
			if (result.first())
				computer = new Computer();
			computer.setId(id);
			computer.setName(result.getString("name"));
			if(result.getDate("introduced")!=null) {computer.setIntroDate(result.getDate("introduced").toLocalDate());}
			if(result.getDate("discontinued")!=null) {computer.setDiscDate(result.getDate("discontinued").toLocalDate());}
			//if(result.getInt("company_id")!=0) {computer.setCompany.setName(result.getString("cpa.name"))}
			findStmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return computer;
		
	}

	/**
	 * Méthode de recherche toute les entités
	 *
	 * @return List<Computers> 
	 */

	public List<Computer> findAll() {
		List<Computer> computers = new ArrayList<Computer>();
		Computer computer = new Computer();

		try {			
			PreparedStatement findAllStmt = this.connect.prepareStatement(findAllQuery);
			ResultSet result = findAllStmt.executeQuery();
			while (result.next()) {
				computer = new Computer();
				computer.setId(result.getLong("id"));
				computer.setName(result.getString("name"));
				if (result.getDate("introduced").toLocalDate() != null) {
				computer.setIntroDate((result.getDate("introduced").toLocalDate())); 
				}
				if (result.getDate("discontinued").toLocalDate() != null) {
				computer.setDiscDate((result.getDate("discontinued").toLocalDate()));
				}
				computer.setCompanyId((result.getLong("company_id")));
				computers.add(computer);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return computers;
	}

	@Override
	public boolean create(Computer obj) {

		try {
			PreparedStatement addStmt = this.connect.prepareStatement(createQuery);
			addStmt.setString(1, obj.getName());
			addStmt.setDate(2, Date.valueOf(obj.getIntroDate()));
			addStmt.setDate(3, Date.valueOf(obj.getDiscDate()));
			addStmt.setFloat(4, obj.getCompanyId());
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("exception due a la requête");
		}
		return true;
	}

	@Override

	public boolean update(Computer obj) {

		try {
			PreparedStatement updateStmt = this.connect.prepareStatement(updateQuery);
			updateStmt.setString(1, obj.getName());
			updateStmt.setDate(2, Date.valueOf(obj.getIntroDate()));
			updateStmt.setDate(3, Date.valueOf(obj.getDiscDate()));
			updateStmt.setFloat(4, obj.getCompanyId());
			updateStmt.setFloat(5, obj.getId());
			

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Problème avec la requete d'update");
		}

		return true;
	}


	@Override
	public boolean delete(int id) {

		try {
			PreparedStatement deleteStmt = this.connect.prepareStatement(deleteQuery);
			deleteStmt.setFloat(1, id);

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Problème avec la requête delete");
		}

		return true;
	}

}
