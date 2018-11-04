package com.excilys.cdb.persistence;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.excilys.cdb.model.Company;



public class CompanyDAO extends DAO<Company>{



	protected CompanyDAO() {
	}
	static CompanyDAO companyDAO = new CompanyDAO();

	public static CompanyDAO getInstance() {
		return companyDAO;
	}

	private static String findQuery = "SELECT id,name FROM company WHERE id= ? ";
	private static String findQueryByName = "SELECT id,name  FROM company WHERE name= ? ";
	private static String findAllQuery ="SELECT id,name FROM company " ;

	@Override
	public Company find(Long id) {
		Company company = new Company();      

		try {

			PreparedStatement findStmt = DAO.connect.prepareStatement(findQuery);
			findStmt.setFloat(1, id);
			ResultSet result = findStmt.executeQuery();
			if(result.first())
				company = new Company(id,result.getString("name"));         
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return company;
	}

	@Override
	public Company findByName(String name) {
		Company company = new Company();      

		try {

			PreparedStatement findStmt = DAO.connect.prepareStatement(findQueryByName);
			findStmt.setString(1, name);
			ResultSet result = findStmt.executeQuery();
			if(result.first()) {
				company.setId(result.getLong("id"));
				company.setName(name);  
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return company;
	}

	@Override
	public List<Company> findAll() {
		List<Company> companies = new ArrayList<Company>();
		Company company = new Company();

		try {
			PreparedStatement findStmt = DAO.connect.prepareStatement(findAllQuery);
			ResultSet result = findStmt.executeQuery();
			while (result.next()) {
				company = new Company(result.getLong("id"), result.getString("name"));
				companies.add(company);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return companies;
	}

	@Override
	public boolean create(Company obj) {
		return false;
	}

	@Override
	public boolean delete(Long id) {
		return false;
	}

	@Override
	public boolean update(Company obj) {
		return false;
	}


}
