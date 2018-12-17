package dao;


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

import model.Company;


@Repository

public class CompanyDAO implements CompanyDAOInterface<Company>{


	private static String findAllQuery = "SELECT id,name FROM company";
	private static String deleteQuery = "DELETE FROM company WHERE id = :id";
	private static String updateQuery = "UPDATE company SET name = :name WHERE id = :id";
	private final static String findQuery = "SELECT id, name FROM company WHERE id = :id";
	private final static String createQuery = "INSERT INTO company (name) VALUES(?)";

	@Autowired
	DataSource dataSource;

	@Override
	public List<Company> findAll() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        RowMapper<Company> rowMapper = new RowMapper<Company>() {
            public Company mapRow(ResultSet result, int pRowNum) throws SQLException {
            	Company company = new Company();
            	company.setId(result.getLong("id"));
            	company.setName(result.getString("name"));
                return company;
            }
        };
        List<Company> list = jdbcTemplate.query(findAllQuery, rowMapper);
        return list;
		
	}

	@Override
	public void delete(Long id) {
		
		NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        jdbcTemplate.update(deleteQuery,params);
	}
	
	public void update(Company company) {
		NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("name", company.getName());
		params.addValue("id", company.getId());
		jdbcTemplate.update(updateQuery, params);
	}
	
	public Optional<Company> find(Long id){
		NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id);

		RowMapper<Company> rowMapper = new RowMapper<Company>() {
			public Company mapRow(ResultSet result, int pRowNum) throws SQLException {
				Company company = new Company();
				company.setId(result.getLong("id"));
				company.setName(result.getString("name"));
				return company;
			}
		};
		return Optional.ofNullable(jdbcTemplate.queryForObject(findQuery, params, rowMapper));
	}
	public void create(Company company) {

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		Object[] params = { new SqlParameterValue(Types.VARCHAR, company.getName())};
		jdbcTemplate.update(createQuery, params);
	}
	
}
