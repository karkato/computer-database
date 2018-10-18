package persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

	/**
	 * Méthode de recherche
	 * @param id
	 * @return Computers 
	 */
	@Override
	public Computers find(int id) {
		Computers computer = new Computers();

		try {
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT * FROM computer WHERE id= " + id);
			if (result.first())
				computer = new Computers(id, result.getString("name"));
			if(result.getDate("introduced")!=null) {computer.setIntroDate(result.getDate("introduced").toLocalDate());}
			if(result.getDate("discontinued")!=null) {computer.setDiscDate(result.getDate("discontinued").toLocalDate());}
			if(result.getInt("company_id")!=0) {computer.setManufacturer(result.getInt("company_id"));}
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
	@Override
	public List<Computers> findAll() {
		List<Computers> computers = new ArrayList<Computers>();
		Computers computer = new Computers();

		try {
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT * FROM computer ");
			while (result.next()) {
				computer = new Computers(result.getInt("id"), result.getString("name"), null, null, 0);				
				computers.add(computer);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return computers;
	}

	@Override
	public boolean create(Computers obj) {

		String query ="INSERT INTO computer (name,introduced,discontinued,company_id) VALUES('"+obj.getNamePc() +"','"+obj.getIntroDate() +"','"+obj.getDiscDate()+"',"+obj.getManufacturer()+ ")";
		try {
			Statement stmt= connect.createStatement();
			stmt.executeUpdate(query,Statement.RETURN_GENERATED_KEYS);
			stmt.getGeneratedKeys(); 

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("exception due a la requête");
		}
		return true;
	}

	@Override
	// à compléter
	public boolean update(Computers obj) {
		String query = "UPDATE computer SET name ='"+obj.getNamePc()+"', introduced = "+obj.getIntroDate()+", discontinued = "+obj.getDiscDate() + ", company_id ="+obj.getManufacturer()+" WHERE id = "+obj.getIdPc();

		try {
			Statement stmt= connect.createStatement();
			stmt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
			//System.out.println(query);
			stmt.getGeneratedKeys(); 

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("exception due a la requête");
		}

		return true;
	}


	@Override
	public boolean delete(int id) {

		String query = "DELETE FROM computer WHERE id = "+id;
		//ResultSet result;

		try {
			Statement stmt= connect.createStatement();
			stmt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
			//System.out.println(query);
			stmt.getGeneratedKeys(); 

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("exception due a la requête");
		}

		return true;
	}

}
