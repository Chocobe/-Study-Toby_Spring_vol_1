package springbook.user.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import springbook.user.domain.Level;
import springbook.user.domain.User;


public class UserDaoJdbc implements UserDao {
	private JdbcTemplate jdbcTemplate;
	private RowMapper<User> userMapper = new RowMapper<User>() {
		@Override
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			user.setId(rs.getString("id"));
			user.setName(rs.getString("name"));
			user.setPassword(rs.getString("password"));
			user.setEmail(rs.getString("email"));
			user.setLevel(Level.valueOf(rs.getInt("level")));
			user.setLogin(rs.getInt("login"));
			user.setRecommend(rs.getInt("recommend"));
			
			return user;
		}
	};
	
	
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	
// add
	@Override
	public void add(User user) {
		jdbcTemplate.update("INSERT INTO users(id, name, password, email, level, login, recommend) VALUES(?, ?, ?, ?, ?, ?, ?)", 
							user.getId(), 
							user.getName(), 
							user.getPassword(),
							user.getEmail(),
							user.getLevel().intValue(),
							user.getLogin(),
							user.getRecommend());
	}
	
	
// get
	@Override
	public User get(String id) {
		return jdbcTemplate.queryForObject("SELECT * FROM users WHERE id=?", 
										   new Object[] {id}, 
										   userMapper);
	}
	
	
// getAll
	@Override
	public List<User> getAll() {
		return this.jdbcTemplate.query("SELECT * FROM users", 
									   userMapper);
	}
	
	
// DELETE ALL
	@Override
	public void deleteAll() {
		this.jdbcTemplate.update("DELETE FROM users");
	}
	
	
// COUNT(*)
	@Override
	public int getCount() {
		return jdbcTemplate.queryForInt("SELECT COUNT(*) FROM users");
	}


// update
	@Override
	public void update(User user) {
		jdbcTemplate.update("UPDATE users SET name=?, password=?, email=?, level=?, login=?, recommend=? WHERE id=?", 
							user.getName(), user.getPassword(), user.getEmail(), user.getLevel().intValue(), user.getLogin(), user.getRecommend(), user.getId());
	}
}
