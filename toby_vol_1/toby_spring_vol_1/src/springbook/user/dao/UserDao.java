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
	private JdbcContext jdbcContext;
	
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		
		this.jdbcContext = new JdbcContext();
		this.jdbcContext.setDataSource(dataSource);
	}
	
	
// add
	public void add(final User user) throws SQLException {
		jdbcContext.workWithStatementStrategy(
				new StatementStrategy() {
					@Override
					public PreparedStatement makePreparedStatement(Connection conn) throws SQLException {
						PreparedStatement pstmt = conn.prepareStatement("INSERT INTO users(id, name, password) VALUES(?, ?, ?)");
						pstmt.setString(1, user.getId());
						pstmt.setString(2, user.getName());
						pstmt.setString(3, user.getPassword());
						
						return pstmt;
					}
				}
		);
	}
	
	
// get
	public User get(String id) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		User user = null;
		
		try {
			String sql = "SELECT * FROM users ";
			sql += "WHERE id=?";
			
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				user = new User(rset.getString("id"),
								rset.getString("name"),
								rset.getString("password"));
			}
			
		} catch(SQLException e) {
			throw e;
			
		} finally {
			if(rset != null) {
				try {
					rset.close();
					
				} catch(SQLException e) { }
			}
			
			if(pstmt != null) {
				try {
					pstmt.close();
					
				} catch(SQLException e) { }
			}
			
			if(conn != null) {
				try {
					conn.close();
					
				} catch(SQLException e) { }
			}
		}
		
		if(user == null) {
			throw new EmptyResultDataAccessException(1);
		}

		return user;
	}
	
	
// DELETE ALL
	public void deleteAll() throws SQLException {
		jdbcContext.workWithStatementStrategy(
				new StatementStrategy() {
					
					@Override
					public PreparedStatement makePreparedStatement(Connection conn) throws SQLException {
						return conn.prepareStatement("DELETE FROM users");
					}
				}
		);
	}
	
	
// jdbcContextWithStatementStrategy
	public void jdbcContextWithStatementStrategy(StatementStrategy stmt) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = dataSource.getConnection();
			pstmt = stmt.makePreparedStatement(conn);
			
			pstmt.executeUpdate();
			
		} catch(SQLException e) {
			throw e;
			
		} finally {
			if(pstmt != null) { try { pstmt.close(); } catch(SQLException e) {} }
			if(conn != null) { try { conn.close(); } catch(SQLException e) {} }
		}
	}
	
	
// COUNT(*)
	public int getCount() throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		try {
			String sql = "SELECT COUNT(*) FROM users";
			
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
			rset.next();
			return rset.getInt(1);
			
		} catch(SQLException e) {
			throw e;
			
		} finally {
			if(rset != null) {
				try {
					rset.close();
					
				} catch(SQLException e) { }
			}
			
			if(pstmt != null) {
				try {
					pstmt.close();
					
				} catch(SQLException e) { }
			}
			
			if(conn != null) {
				try {
					conn.close();
					
				} catch(SQLException e) { }
			}
		}
	}
}
