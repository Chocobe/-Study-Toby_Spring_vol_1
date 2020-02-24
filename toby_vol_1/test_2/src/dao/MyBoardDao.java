package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;

import vo.MyBoard;

public class MyBoardDao {
	private DataSource dataSource;
	private JdbcContext jdbcContext;
	
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		
		this.jdbcContext = new JdbcContext();
		jdbcContext.setDataSource(dataSource);
	}
	
	
// insert
	public int insert(MyBoard myBoard) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		int result = 0;
		
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement("INSERT INTO myBoard(id, title, content) VALUES(?, ?, ?)");
			pstmt.setString(1, myBoard.getId());
			pstmt.setString(2, myBoard.getTitle());
			pstmt.setString(3, myBoard.getContent());
			
			result = pstmt.executeUpdate();
			
		} catch(SQLException e) {
			throw e;
			
		} finally {
			if(pstmt != null) { try { pstmt.close(); } catch(SQLException e) {} }
			if(conn != null) { try { conn.close(); } catch(SQLException e) {} }
		}
		
		return result;
	}
	
	
// select
	public MyBoard select(MyBoard myBoard) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		MyBoard selectBoard = null;
		
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement("SELECT * FROM myBoard WHERE id=? AND title=? AND content=?");
			pstmt.setString(1, myBoard.getId());
			pstmt.setString(2, myBoard.getTitle());
			pstmt.setString(3, myBoard.getContent());
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				selectBoard = new MyBoard(rs.getInt("idx"),
										  rs.getString("id"),
										  rs.getString("title"),
										  rs.getString("content"),
										  rs.getDate("writeDate").toLocalDate(),
										  rs.getInt("watch"));
				
			}
			
		} catch(SQLException e) {
			throw e;
			
		} finally {
			if(rs != null) { try { rs.close(); } catch(SQLException e) {} }
			if(pstmt != null) { try { pstmt.close(); } catch(SQLException e) {} }
			if(conn != null) { try { conn.close(); } catch(SQLException e) {} }
		}
		
		return selectBoard;
	}
	
	
// deleteAll
	public void deleteAll() throws SQLException {
		jdbcContext.executeSQL("DELETE FROM myBoard");
	}
	
	
// count
	public int count() throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int count = 0;
		
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement("SELECT COUNT(*) FROM myBoard");
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt("COUNT(*)");
			}
			
		} catch(SQLException e) {
			throw e;
			
		} finally {
			if(rs != null) { try { rs.close(); } catch(SQLException e) {} }
			if(pstmt != null) { try { pstmt.close(); } catch(SQLException e) {} }
			if(conn != null) { try { conn.close(); } catch(SQLException e) {} }
		}
		
		return count;
	}
}
