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
	
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	
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
	
	
	public MyBoard select(String id, String title, String content) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		MyBoard result = null;
		
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement("SELECT * FROM myBoard WHERE id=? AND title=? AND content=?");
			pstmt.setString(1, id);
			pstmt.setString(2, title);
			pstmt.setString(3, content);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = new MyBoard(rs.getInt("idx"),
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
		
		if(result == null) {
			throw new EmptyResultDataAccessException(1);
		}
		
		return result;
	}
	
	
// deleteAll TEST
	public void deleteAll() throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement("DELETE FROM myBoard");
			
			pstmt.executeUpdate();
			
		} catch(SQLException e) {
			throw e;
			
		} finally {
			if(pstmt != null) { try { pstmt.close(); } catch(SQLException e) {} }
			if(conn != null) { try { conn.close(); } catch(SQLException e) {} }
		}
	}
	
	
// count TEST
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
