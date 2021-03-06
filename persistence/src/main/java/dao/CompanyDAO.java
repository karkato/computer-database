package dao;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import model.Company;


@Repository

public class CompanyDAO implements CompanyDAOInterface<Company>{


	private static String findAllQuery = "SELECT id,name FROM company";
	private static String deleteQuery = "DELETE FROM company WHERE id = :id";

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
}
