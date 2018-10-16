package persistence;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Companies;
import model.Computers;


public class CompaniesDAO extends DAO<Companies>{



	public CompaniesDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	static List<Companies> findById() {
		return null;
	}
	static List<Companies> findByName() {
		return null;
	}
	@Override
	public Companies find(int id) {
		Companies company = new Companies();      

		try {
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM companies WHERE id_comp= " + id);
			if(result.first())
				company = new Companies(id,result.getString("name"));         
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return company;
	}

	@Override
	public List<Companies> findAll() {
		List<Companies> companies = new ArrayList<Companies>();
		Companies company = new Companies();

		try {
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT * FROM company ");
			if (result.first()) {
				company = new Companies(result.getInt("id"), result.getString("name"));
				companies.add(company);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return companies;
	}
	@Override
	public boolean create(Companies obj) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean update(Companies obj) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean delete(Companies obj) {
		// TODO Auto-generated method stub
		return false;

	}

}
