package com.excilys.cdb.persistence;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;

public class ComputerDAO implements ComputerDAOInterface<Computer> {

	static ComputerDAO computerDAO = new ComputerDAO();
	private static Connection connect;

	protected ComputerDAO() {
		DBDemo.getInstance();
		connect=DBDemo.getConnection();

	}


	public static ComputerDAO getInstance() {
		return computerDAO;
	}

	private final static String findQuery = "SELECT cpu.id, cpu.name, cpu.introduced, cpu.discontinued, cpu.company_id,cpa.name FROM computer AS cpu LEFT JOIN company AS cpa ON cpu.company_id = cpa.id WHERE cpu.id = ?";
	private final static String findAllQuery ="SELECT cpu.id, cpu.name, cpu.introduced, cpu.discontinued, cpu.company_id,cpa.name FROM computer AS cpu LEFT JOIN company AS cpa ON cpu.company_id = cpa.id " ;
	private final static String createQuery ="INSERT INTO computer (name,introduced,discontinued,company_id) VALUES(?,?,?,?)";
	private final static String updateQuery = "UPDATE computer SET name = ?, introduced =? , discontinued =? , company_id =?, WHERE id =? ";	
	private final static String deleteQuery = "DELETE FROM computer WHERE id = ?";
	private final static String coutQuery = "SELECT COUNT (computer,id) FROM computer";

	Logger logger = LoggerFactory.getLogger(ComputerDAO.class);
	public Optional<Computer> find(Long id) throws  IOException{
		ComputerDAO.connect = DBDemo.connectionDB();
		Computer computer = null;
		try (PreparedStatement preparedStatement = ComputerDAO.connect.prepareStatement(findQuery)) {
			preparedStatement.setLong(1, id);
			ResultSet result = preparedStatement.executeQuery();
			while (result.next()) {
				computer = new Computer();
				computer.setId(result.getLong("id"));
				computer.setName(result.getString("cpu.name"));
				if (result.getDate("introduced") != null) {
					computer.setIntroDate(result.getDate("introduced").toLocalDate());
				}
				if (result.getDate("discontinued") != null) {
					computer.setDiscDate(result.getDate("discontinued").toLocalDate());
				}
				computer.setCompany(new Company());
				if (result.getInt("company_id") != 0) {
					computer.getCompany().setId(result.getLong("company_id"));
					computer.getCompany().setName(result.getString("cpa.name"));
				}
			}
			result.close();
			ComputerDAO.connect.close();
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return Optional.ofNullable(computer);
	}


	public List<Computer> findAll() throws IOException {
		List<Computer> computers = new ArrayList<Computer>();
		ComputerDAO.connect=DBDemo.connectionDB();
		Computer computer;
		Company company ;

		try {			
			PreparedStatement findAllStmt = ComputerDAO.connect.prepareStatement(findAllQuery);
			ResultSet result = findAllStmt.executeQuery();
			while (result.next()) {
				computer = new Computer();
				company= new Company();
				computer.setId(result.getLong("cpu.id"));
				computer.setName(result.getString("cpu.name"));
				if (result.getDate("cpu.introduced") != null) {
					computer.setIntroDate((result.getDate("cpu.introduced").toLocalDate())); 
				}
				if (result.getDate("discontinued") != null) {
					computer.setDiscDate((result.getDate("cpu.discontinued").toLocalDate()));
				}
				company.setName(result.getString("cpa.name"));
				computer.setCompany(company);
				computers.add(computer);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return computers;
	}

	public boolean create(Computer obj) throws IOException {
		ComputerDAO.connect=DBDemo.connectionDB();
		try {
			PreparedStatement addStmt = ComputerDAO.connect.prepareStatement(createQuery);
			addStmt.setString(1,obj.getName());
			addStmt.setDate(2,Date.valueOf(obj.getIntroDate()));
			addStmt.setDate(3,Date.valueOf(obj.getDiscDate()));
			addStmt.setFloat(4,obj.getCompany().getId());
			int result = addStmt.executeUpdate();
			if (result == 1) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("exception due a la requête");
		}
		return false;
	}

	public boolean update(Computer obj) throws IOException {
		int result;
		ComputerDAO.connect = DBDemo.connectionDB();
		try {
			PreparedStatement updateStmt = ComputerDAO.connect.prepareStatement(updateQuery);
			updateStmt.setString(1, obj.getName());
			updateStmt.setDate(2, Date.valueOf(obj.getIntroDate()));
			updateStmt.setDate(3, Date.valueOf(obj.getDiscDate()));
			updateStmt.setFloat(4, obj.getCompanyId());
			updateStmt.setFloat(5, obj.getId());
			result = updateStmt.executeUpdate();
			if (result == 1) {
				ComputerDAO.connect.close();
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Problème avec la requete d'update");
		}

		return false;
	}


	public boolean delete(Long id) throws IOException {
		int result;
		ComputerDAO.connect = DBDemo.connectionDB();
		try {
			PreparedStatement deleteStmt = ComputerDAO.connect.prepareStatement(deleteQuery);
			deleteStmt.setFloat(1, id);
			result = deleteStmt.executeUpdate();
			if (result == 1) {
				ComputerDAO.connect.close();
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Problème avec la requête delete");
		}

		return false;
	}





	@Override
	public List<Computer> findAll(String name, int page, int size) throws IOException {
		return null;
	}


	@Override
	public List<Computer> findAll(int page, int size) throws IOException {
		return null;
	}


	@Override
	public int count() throws IOException {
		return 0;
	}

}
