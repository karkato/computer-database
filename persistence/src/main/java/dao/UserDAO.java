package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import model.Role;
import model.User;
import model.User.UserBuilder;

@Repository
public class UserDAO {
	
	private final static String FIND_ALL_USERS_SQL = "SELECT users.id, users.username, users.password, users.role_id, roles.id, roles.role FROM users LEFT JOIN roles ON users.id = roles.id where username = :username";
	private static String findAllQuery = "SELECT users.id, users.username, users.password, users.role_id, roles.id, roles.role FROM users LEFT JOIN roles ON users.id = roles.id";
	private final DataSource dataSource;

	public UserDAO(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public User find(String username) {
		NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("username", username);

		RowMapper<User> rowMapper = new RowMapper<User>() {
			public User mapRow(ResultSet rs, int pRowNum) throws SQLException {
				User user;
				Role role = new Role();
				role.setId(rs.getLong("users.role_id"));
				role.setName(rs.getString("roles.role"));
				UserBuilder usrBuilder = new User.UserBuilder(rs.getString("users.username")).id(rs.getLong("users.id"))
						.password(rs.getString("users.password")).role(role);
				user = usrBuilder.build();

				return user;
			}
		};

		return jdbcTemplate.queryForObject(FIND_ALL_USERS_SQL, params, rowMapper);
	}
	
	public List<User> findAll() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        RowMapper<User> rowMapper = new RowMapper<User>() {
            public User mapRow(ResultSet result, int pRowNum) throws SQLException {
            	
            	Role role = new Role();
            	role.setId(result.getLong("roles.id"));
            	role.setName(result.getString("role."));
            	User user = new UserBuilder(result.getString("users.username")).id(result.getLong("id")).role(role).password(result.getString("users.password")).build();
                return user;
            }
        };
        List<User> list = jdbcTemplate.query(findAllQuery, rowMapper);
        return list;
		
	}


}
