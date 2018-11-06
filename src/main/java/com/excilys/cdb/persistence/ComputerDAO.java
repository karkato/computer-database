package com.excilys.cdb.persistence;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.cdb.exceptions.DataBaseException;
import com.excilys.cdb.exceptions.DataException;
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
	private final static String findAllQuery ="SELECT cpu.id, cpu.name, cpu.introduced, cpu.discontinued, cpu.company_id,cpa.name FROM computer AS cpu LEFT JOIN company AS cpa ON cpu.company_id = cpa.id LIMIT ? OFFSET ? " ;
	private final static String createQuery ="INSERT INTO computer (name,introduced,discontinued,company_id) VALUES(?,?,?,?)";
	private final static String updateQuery = "UPDATE computer SET name = ?, introduced =? , discontinued =? , company_id =?, WHERE id =? ";	
	private final static String deleteQuery = "DELETE FROM computer WHERE id = ?";
	private final static String countQuery = "SELECT COUNT(computer.id) FROM computer";
	private final static String findByName ="SELECT cpu.id, cpu.name, cpu.introduced, cpu.discontinued, cpu.company_id,cpa.name FROM computer AS cpu LEFT JOIN company AS cpa ON cpu.company_id = cpa.id WHERE UPPER(cpu.name) LIKE UPPER(?) LIMIT ? OFFSET ?";

	Logger logger = LoggerFactory.getLogger(ComputerDAO.class);

	@Override
	public boolean create(Computer computer) throws DataException, IOException, DataBaseException, SQLException {

		ComputerDAO.connect = DBDemo.connectionDB();
		try (PreparedStatement preparedStatement = ComputerDAO.connect.prepareStatement(createQuery);) {
			preparedStatement.setString(1, computer.getName());

			if (computer.getIntroDate() == null) {
				preparedStatement.setDate(2, null);
			} else {
				preparedStatement.setDate(2, Date.valueOf(computer.getIntroDate()));
			}

			if (computer.getDiscDate() == null) {
				preparedStatement.setDate(3, null);
			} else {
				preparedStatement.setDate(3, Date.valueOf(computer.getDiscDate()));
			}

			if (computer.getCompany().getId() != 0) {
				preparedStatement.setLong(4, computer.getCompany().getId());
			} else {
				preparedStatement.setBinaryStream(4, null);
			}

			int result = preparedStatement.executeUpdate();
			if(result==1) {
				ComputerDAO.connect.close();
				return true;
			}
		} catch (SQLException e) {
			throw new DataBaseException();
		}

		return false;
	}

	@Override
	public boolean update(Computer computer) throws DataException, IOException, DataBaseException, SQLException{

		ComputerDAO.connect = DBDemo.connectionDB();
		try (PreparedStatement preparedStatement = ComputerDAO.connect.prepareStatement(updateQuery);) {

			preparedStatement.setString(1, computer.getName());
			if (computer.getIntroDate() == null) {
				preparedStatement.setDate(2, null);
			} else {
				preparedStatement.setDate(2, Date.valueOf(computer.getIntroDate()));
			}

			if (computer.getDiscDate() == null) {
				preparedStatement.setDate(3, null);
			} else {
				preparedStatement.setDate(3, Date.valueOf(computer.getDiscDate()));
			}

			if (computer.getCompany().getId() != 0) {
				preparedStatement.setLong(4, computer.getCompany().getId());
			} else {
				preparedStatement.setBinaryStream(4, null);
			}
			preparedStatement.setLong(5, computer.getId());

			int result = preparedStatement.executeUpdate();
			if(result==1) {
				ComputerDAO.connect.close();
				return true;
			}
		} catch (SQLException e) {
			throw new DataBaseException();
		}

		return false;
	}

	public boolean delete(Long id) throws IOException, DataBaseException, SQLException{
		ComputerDAO.connect = DBDemo.connectionDB();
		try (PreparedStatement preparedStatement = ComputerDAO.connect.prepareStatement(deleteQuery);) {

			preparedStatement.setLong(1, id);
			int result = preparedStatement.executeUpdate();
			if(result==1) {
				return true;
			}
			ComputerDAO.connect.close();
		} catch (SQLException e) {
			throw new DataBaseException();
		}
		return false;
	}

	public Optional<Computer> find(Long id) throws  IOException, DataBaseException, SQLException{
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
			throw new DataBaseException();
		}
		return Optional.ofNullable(computer);
	}

	@Override
	public ArrayList<Computer> findAll(String name,int page, int size) throws IOException, DataBaseException, SQLException {
		ComputerDAO.connect = DBDemo.connectionDB();
		ArrayList<Computer> list = new ArrayList<>();
		try (PreparedStatement preparedStatement = ComputerDAO.connect.prepareStatement(findByName)) {

			preparedStatement.setNString(1,"%"+name+"%");
			preparedStatement.setInt(2,size);
			preparedStatement.setInt(3,(page-1)*size);
			ResultSet result = preparedStatement.executeQuery();
			while (result.next()) {
				Computer computer = new Computer();
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
				list.add(computer);
			}
			result.close();
			ComputerDAO.connect.close();	
		} catch (SQLException e) {
			logger.error(e.getMessage());
			throw new DataBaseException();
		}
		return list;
	}

	@Override
	public ArrayList<Computer> findAll(int page, int size) throws IOException, DataBaseException, SQLException {
		ComputerDAO.connect = DBDemo.connectionDB();
		ArrayList<Computer> list = new ArrayList<>();
		try (
				PreparedStatement preparedStatement = ComputerDAO.connect.prepareStatement(findAllQuery)
				) {
			preparedStatement.setInt(1,size);
			preparedStatement.setInt(2,(page-1)*size);
			ResultSet result = preparedStatement.executeQuery();
			while (result.next()) {
				Computer computer = new Computer();
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
				list.add(computer);
			}
			result.close();
			ComputerDAO.connect.close();
		} catch (SQLException e) {
			logger.error(e.getMessage());
			throw new DataBaseException();
		}
		return list;
	}

	@Override
	public int count() throws IOException, DataBaseException, SQLException{
		ComputerDAO.connect = DBDemo.connectionDB();
		int count = 0;
		try (PreparedStatement preparedStatement = ComputerDAO.connect.prepareStatement(countQuery)) {
			
			ResultSet result = preparedStatement.executeQuery();
			while (result.next()) {
				count = result.getInt(1);
			}
			result.close();
		} catch (SQLException e) {
			logger.error(e.getMessage());
			throw new DataBaseException();

		}
		return count;
	}


}
