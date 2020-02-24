package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;

import vo.Book;

public class BookDao {
	private DataSource dataSource;
	private JdbcContext jdbcContext;
	
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		
		this.jdbcContext = new JdbcContext();
		jdbcContext.setDataSource(this.dataSource);
	}
	
	
	public void insertBook(Book book) throws SQLException {
		jdbcContext.workWithStatementStrategy (
				new StatementStrategy() {
					
					@Override
					public PreparedStatement makePrepareStatement(Connection conn) throws SQLException {
						PreparedStatement pstmt = conn.prepareStatement("INSERT INTO book(title, auth, price) VALUES(?, ?, ?)");
						
						pstmt.setString(1, book.getTitle());
						pstmt.setString(2, book.getAuth());
						pstmt.setInt(3, book.getPrice());
						
						return pstmt;
					}
				}
		);
	}
	
	
	public Book selectBook(String title) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		Book resultBook = null;
		
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement("SELECT * FROM book WHERE title=?");
			pstmt.setString(1, title);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				resultBook = new Book(rs.getString("title"),
									  rs.getString("auth"),
									  rs.getInt("price"));
			}
			
		} catch(SQLException e) {
			throw e;
			
		} finally {
			if(rs != null) { try { rs.close(); } catch(SQLException e) {} }
			if(pstmt != null) { try { pstmt.close(); } catch(SQLException e) {} }
			if(conn != null) { try { conn.close(); } catch(SQLException e) {} }
		}
		
		if(resultBook == null) {
			throw new EmptyResultDataAccessException(1);
		}
		
		return resultBook;
	}
	
	
	public void deleteAll() throws SQLException {
		jdbcContext.executeDeleteAllQuery("DELETE FROM book");
	}
	
	
	public int count() throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int count = 0;
		
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement("SELECT COUNT(*) FROM book");
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
