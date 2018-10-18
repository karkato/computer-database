package persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Computer;

public class ComputerDAO extends DAO<Computer> {

	public ComputerDAO(Connection conn) {
		super(conn);
	}


	List<Computer> findById() {
		return null;
	}

	List<Computer> findByName() {
		return null;
	}

	boolean insertComputer(Computer computer) {
		return false;
	}

	boolean updateComputer(Computer computer) {
		return false;
	}

	boolean deleteComputer(Computer computer) {
		return false;
	}

	/**
	 * Méthode de recherche
	 * @param id
	 * @return Computers 
	 */
	@Override
	public Computer find(Long id) {
		Computer computer = new Computer();

		try {
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT id,name,introduced,discontinued,company_id FROM computer LEFT JOIN SELECT cpa.name FROM Company AS cpa WHERE id= " + id);
			if (result.first())
				computer = new Computer(id, result.getString("name"));
			if(result.getDate("introduced")!=null) {computer.setIntroDate(result.getDate("introduced").toLocalDate());}
			if(result.getDate("discontinued")!=null) {computer.setDiscDate(result.getDate("discontinued").toLocalDate());}
			//if(result.getInt("company_id")!=0) {computer.setCompany.setName(result.getString("cpa.name"))}
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
	public List<Computer> findAll() {
		List<Computer> computers = new ArrayList<Computer>();
		Computer computer = new Computer();

		try {
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT * FROM computer ");
			while (result.next()) {
				computer = new Computer(result.getLong("id"), result.getString("name"));				
				computers.add(computer);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return computers;
	}

	@Override
	public boolean create(Computer obj) {

		String query ="INSERT INTO computer (name,introduced,discontinued,company_id) VALUES('"+obj.getName() +"','"+obj.getIntroDate() +"','"+obj.getDiscDate()+"',"+obj.getCompany()+ ")";
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

	public boolean update(Computer obj) {
		String query = "UPDATE computer SET name ='"+obj.getName()+"', introduced = "+obj.getIntroDate()+", discontinued = "+obj.getDiscDate() + ", company_id ="+obj.getCompany()+" WHERE id = "+obj.getId();

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
