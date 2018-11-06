package com.excilys.cdb.persistence;


import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.cdb.exceptions.DataBaseException;
import com.excilys.cdb.model.Company;



public class CompanyDAO implements CompanyDAOInterface<Company>{
	private static Connection connect;
	Logger logger = LoggerFactory.getLogger(CompanyDAO.class);

	protected CompanyDAO() {
		DBDemo.getInstance();
		connect =DBDemo.getConnection();
	}
	static CompanyDAO companyDAO = new CompanyDAO();

	public static CompanyDAO getInstance() {
		return companyDAO;
	}

	private static String findQuery = "SELECT id,name FROM company WHERE id = ?";
	private static String findQueryByName = "SELECT id,name FROM company WHERE name = ?";
	private static String findAllQuery = "SELECT id,name FROM company";
	private static String deleteQuery = "DELETE FROM company WHERE id = ?";

	@Override
	public Optional<Company> find(Long id) throws IOException, SQLException {
		CompanyDAO.connect = DBDemo.connectionDB();
		Company company = null;      

		try {
			PreparedStatement findStmt = CompanyDAO.connect.prepareStatement(findQuery);
			findStmt.setFloat(1, id);
			ResultSet result = findStmt.executeQuery();
			if(result.first()) {
				company = new Company(id,result.getString("name"));
				
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return Optional.ofNullable(company);
	}

	@Override
	public Company findByName(String name) {
		Company company = new Company();      

		try {

			PreparedStatement findStmt = CompanyDAO.connect.prepareStatement(findQueryByName);
			findStmt.setString(1, name);
			ResultSet result = findStmt.executeQuery();
			if(result.first()) {
				company.setId(result.getLong("id"));
				company.setName(name);  
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return company;
	}

	@Override
	public List<Company> findAll() throws DataBaseException, IOException, SQLException {
		CompanyDAO.connect = DBDemo.connectionDB();
		ArrayList<Company> list = new ArrayList<>();

		try (PreparedStatement preparedStatement = CompanyDAO.connect.prepareStatement(findAllQuery)) {
			CompanyDAO.connect.setAutoCommit(false);
			ResultSet result = preparedStatement.executeQuery();
			while (result.next()) {
				Company company = new Company(result.getLong("id"), result.getString("name"));
				list.add(company);
			}
			result.close();
			CompanyDAO.connect.commit();
		} catch (SQLException e) {
			logger.error(e.getMessage());
			throw new DataBaseException();
		} finally {
			try {
				CompanyDAO.connect.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new DataBaseException();
			}
		}

		return list;
	}
	public boolean delete(Long id) throws IOException, DataBaseException, SQLException{
		CompanyDAO.connect = DBDemo.connectionDB();
		try (PreparedStatement preparedStatement = CompanyDAO.connect.prepareStatement(deleteQuery);) {

			preparedStatement.setLong(1, id);
			int result = preparedStatement.executeUpdate();
			if(result==1) {
				return true;
			}
			CompanyDAO.connect.close();
		} catch (SQLException e) {
			throw new DataBaseException();
		}
		return false;
	}

}
