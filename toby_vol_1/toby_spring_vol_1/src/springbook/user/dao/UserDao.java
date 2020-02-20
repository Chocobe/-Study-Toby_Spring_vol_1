package springbook.user.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;

import springbook.user.domain.User;

public class UserDao {
	private DataSource dataSource;
	
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	
// add
	public void add(User user) throws SQLException {
		Connection c = dataSource.getConnection();
		
		PreparedStatement ps = c.prepareStatement("INSERT INTO users(id, name, password) VALUES(?, ?, ?)");
		ps.setString(1, user.getId());
		ps.setString(2, user.getName());
		ps.setString(3, user.getPassword());
		
		ps.executeUpdate();
		
		ps.close();
		c.close();
	}
	
	
// get
	public User get(String id) throws SQLException {
		Connection c = dataSource.getConnection();
		
		PreparedStatement ps = c.prepareStatement("SELECT * FROM users WHERE id=?");
		ps.setString(1, id);
		
		ResultSet rs = ps.executeQuery();
		
		User user = null;
		
		if(rs.next()) {
			user = new User(rs.getString("id"),
							rs.getString("name"),
							rs.getString("password"));
		}
		
		rs.close();
		ps.close();
		c.close();
		
		if(user == null) throw new EmptyResultDataAccessException(1);
		
		return user;
	}
	
	
// DELETE ALL
	public void deleteAll() throws SQLException {
		String sql = "DELETE FROM users";
		
		Connection conn = dataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.executeUpdate();
		
		pstmt.close();
		conn.close();
	}
	
	
// COUNT(*)
	public int getCount() throws SQLException {
		String sql = "SELECT COUNT(*) FROM users";
		
		Connection conn = dataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		ResultSet rset = pstmt.executeQuery();
		rset.next();
		
		int count = rset.getInt(1);
		
		rset.close();
		pstmt.close();
		conn.close();
		
		return count;
	}
}
