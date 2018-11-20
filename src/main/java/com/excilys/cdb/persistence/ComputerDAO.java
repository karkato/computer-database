package com.excilys.cdb.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameterValue;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;


@Repository
public class ComputerDAO implements ComputerDAOInterface<Computer> {

	static ComputerDAO computerDAO = new ComputerDAO();
	@Autowired
	DataSource dataSource;

	private final static String findQuery = "SELECT cpu.id, cpu.name, cpu.introduced, cpu.discontinued, cpu.company_id,cpa.name FROM computer AS cpu LEFT JOIN company AS cpa ON cpu.company_id = cpa.id WHERE cpu.id = :id";
	private final static String createQuery = "INSERT INTO computer (name,introduced,discontinued,company_id) VALUES(?,?,?,?)";
	private final static String updateQuery = "UPDATE computer SET name = :name, introduced = :introduced, discontinued = :discontinued, company_id = :company_id WHERE id = :id";	
	private final static String deleteQuery = "DELETE FROM computer WHERE id = :id";
	private final static String countQuery = "SELECT COUNT(cpu.id) FROM computer AS cpu LEFT JOIN company AS cpa ON cpu.company_id = cpa.id WHERE UPPER(cpu.name) LIKE UPPER(:name) OR UPPER(cpa.name) LIKE UPPER(:name)";
	private final static String findByName = "SELECT cpu.id, cpu.name, cpu.introduced, cpu.discontinued, cpu.company_id,cpa.name FROM computer AS cpu LEFT JOIN company AS cpa ON cpu.company_id = cpa.id WHERE UPPER(cpu.name) LIKE UPPER(:name) OR UPPER(cpa.name) LIKE UPPER(:name) ORDER BY cpu.name LIMIT :limit OFFSET :offset";
	private final static String deleteByCompanyQuery = "DELETE FROM computer WHERE company_id= :company_id";


	@Override
	public void create(Computer computer) {

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		Object[] params = { new SqlParameterValue(Types.VARCHAR, computer.getName()),
				new SqlParameterValue(Types.DATE, computer.getIntroDate() == null ? null : computer.getIntroDate()),
				new SqlParameterValue(Types.DATE,
						computer.getDiscDate() == null ? null : computer.getDiscDate()),
				new SqlParameterValue(Types.LONGNVARCHAR,
						computer.getCompany().getId() == 0 ? null : computer.getCompany().getId()) };
		jdbcTemplate.update(createQuery, params);
	}

	@Override
	public void update(Computer computer){

		NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("name", computer.getName(), Types.VARCHAR);
		params.addValue("introduced", computer.getIntroDate() == null ? null : computer.getIntroDate(), Types.DATE);
		params.addValue("discontinued", computer.getDiscDate() == null ? null : computer.getDiscDate(),
				Types.DATE);
		params.addValue("company_id", computer.getCompany().getId() == 0 ? null : computer.getCompany().getId(),
				Types.LONGNVARCHAR);
		params.addValue("id", computer.getId(), Types.LONGNVARCHAR);
		jdbcTemplate.update(updateQuery, params);

	}

	public void delete(Long id){
		NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id);
		jdbcTemplate.update(deleteQuery, params);
	}

	public Optional<Computer> find(Long id){
		NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id);

		RowMapper<Computer> rowMapper = new RowMapper<Computer>() {
			public Computer mapRow(ResultSet result, int pRowNum) throws SQLException {
				Computer computer = new Computer();
				computer.setId(result.getLong("id"));
				computer.setName(result.getString("name"));
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
				return computer;
			}
		};
		return Optional.ofNullable(jdbcTemplate.queryForObject(findQuery, params, rowMapper));
	}

	@Override
	public List<Computer> findAll(String name, int page, int size) {

		NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("name", "%" + name + "%");
		params.addValue("limit", size);
		params.addValue("offset", (page - 1) * size);
		RowMapper<Computer> rowMapper = new RowMapper<Computer>() {
			public Computer mapRow(ResultSet result, int pRowNum) throws SQLException {
				Computer computer = new Computer();
				computer.setId(result.getLong("id"));
				computer.setName(result.getString("name"));
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
				return computer;
			}
		};
		List<Computer> list = jdbcTemplate.query(findByName, params, rowMapper);
		return list;
	}

	@Override
	public int count(String name) {
		NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("name", "%" + name + "%");
		int count = jdbcTemplate.queryForObject(countQuery, params, Integer.class);
		return count;
	}

	@Override
	public void deleteByCompany(Long companyId) {

		NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("company_id", companyId);
		jdbcTemplate.update(deleteByCompanyQuery, params);
	}
}
