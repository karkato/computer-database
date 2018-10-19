package com.excilys.cdb.persistence;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.excilys.cdb.model.Company;



public class CompanyDAO extends DAO<Company>{



	protected CompanyDAO() {
		super();
	}
	static CompanyDAO companyDAO = new CompanyDAO();

	public static CompanyDAO getInstance() {
		return companyDAO;
	}
	
	@Override
	public Company find(Long id) {
		Company company = new Company();      

		try {
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM company WHERE id= " + id);
			if(result.first())
				company = new Company(id,result.getString("name"));         
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
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT * FROM company ");
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
	public boolean delete(int id) {
		return false;
	}

	@Override
	public boolean update(Company obj) {
		return false;
	}


}
