package springbook.user.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import springbook.user.domain.User;

public class UserDao {
	private JdbcTemplate jdbcTemplate;
	private RowMapper<User> userMapper = new RowMapper<User>() {
		@Override
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			user.setId(rs.getString("id"));
			user.setName(rs.getString("name"));
			user.setPassword(rs.getString("password"));
			
			return user;
		}
	};
	
	
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	
// add
	public void add(final User user) throws SQLException {
		jdbcTemplate.update("INSERT INTO users(id, name, password) VALUES(?, ?, ?)", user.getId(), user.getName(), user.getPassword());
	}
	
	
// get
	public User get(String id) throws SQLException {
		return jdbcTemplate.queryForObject("SELECT * FROM users WHERE id=?", 
										   new Object[] {id}, 
										   userMapper);
	}
	
	
// getAll
	public List<User> getAll() {
		return this.jdbcTemplate.query("SELECT * FROM users", 
									   userMapper);
	}
	
	
// DELETE ALL
	public void deleteAll() throws SQLException {
		this.jdbcTemplate.update("DELETE FROM users");
	}
	
	
// COUNT(*)
	public int getCount() throws SQLException {
		return jdbcTemplate.queryForInt("SELECT COUNT(*) FROM users");
	}
}
