package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import vo.MyBoard;

public class MyBoardDao {
	private DataSource dataSource;
	
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	
// insert
	public int insert(MyBoard myBoard) throws SQLException {
		int result = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement("INSERT INTO myBoard(writerID, title, content) VALUES(?, ?, ?)");
			pstmt.setString(1, myBoard.getWriterID());
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
	
	
// selectLatest
	public MyBoard selectLatest(String writerID,
								String title,
								String content) throws SQLException {
		MyBoard selectMyBoard = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement("SELECT * FROM myBoard WHERE writerID=? AND title=? AND content=?");
			pstmt.setString(1, writerID);
			pstmt.setString(2, title);
			pstmt.setString(3, content);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				selectMyBoard = new MyBoard(rs.getInt("idx"),
											rs.getString("writerID"),
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
		
		return selectMyBoard;
	}
	
	
// deleteAll
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
	
	
// count
	public int count() throws SQLException {
		int count = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
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
