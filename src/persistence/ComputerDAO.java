package persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Computers;

public class ComputerDAO extends DAO<Computers> {

	public ComputerDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}


	List<Computers> findById() {
		return null;
	}

	List<Computers> findByName() {
		return null;
	}

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
	public Computers find(int id) {
		Computers computer = new Computers();

		try {
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT * FROM computer WHERE id= " + id);
			if (result.first())
				computer = new Computers(id, result.getString("name"), null, null, null);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return computer;
	}

	@Override
	public List<Computers> findAll() {
		List<Computers> computers = new ArrayList<Computers>();
		Computers computer = new Computers();

		try {
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT * FROM computer ");
			while (result.next()) {
				computer = new Computers(result.getInt("id"), result.getString("name"), null, null, null);
				computers.add(computer);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return computers;
	}

	@Override
	public boolean create(Computers obj) {

		try {
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
					.executeQuery("INSERT INTO computer VALUES(" +obj.getId_pc()+","+obj.getName_pc() +","+obj.getIntro_date() +","+obj.getDisc_date()+","+obj.getManufacturer()+ ")");
			if (result.first()) {}
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return true;
	}

	@Override
	// à compléter
	public boolean update(Computers obj) {
		try {
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
					.executeQuery("UPDATE computer SET () WHERE id = "+obj.getId_pc());
			if (result.first()) {}
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return true;
	}

	@Override
	public boolean delete(Computers obj) {
		try {
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
					.executeQuery("DELETE FROM computer WHERE id = "+obj.getId_pc());
			if (result.first()) {}
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return true;
	}

}
