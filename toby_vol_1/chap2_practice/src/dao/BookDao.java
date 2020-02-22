package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import vo.Book;

public class BookDao {
	private DataSource dataSource;
	
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	
// INSERT
	public int insertBook(Book book) throws SQLException {
		int result = 0;
		
		String sql = "INSERT INTO book(title, auth, price) ";
		sql += "VALUES(?, ?, ?)";
		
		Connection conn = dataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, book.getTitle());
		pstmt.setString(2, book.getAuth());
		pstmt.setInt(3, book.getPrice());
		
		result = pstmt.executeUpdate();
		
		pstmt.close();
		conn.close();
		
		return result;
	}
	
	
// SELECT
	public Book selectBook(String title) throws SQLException {
		Book book = null;
		
		String sql = "SELECT * FROM book ";
		sql += "WHERE title=?";
		
		Connection conn = dataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, title);
		
		ResultSet rset = pstmt.executeQuery();
		if(rset.next()) {
			book = new Book(rset.getString("title"),
							rset.getString("auth"),
							rset.getInt("price"));
		}
		
		rset.close();
		pstmt.close();
		conn.close();
		
		return book;
	}
	
	
// DELETE ALL
	public int deleteAll() throws SQLException {
		int result = 0;
		
		String sql = "DELETE FROM book";
		
		Connection conn = dataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		result = pstmt.executeUpdate();
		
		pstmt.close();
		conn.close();
		
		return result;
	}
	
	
// COUNT(*)
	public int count() throws SQLException {
		int result = 0;
		
		String sql = "SELECT COUNT(*) FROM book";
		
		Connection conn = dataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rset = pstmt.executeQuery();
		
		if(rset.next()) {
			result = rset.getInt("COUNT(*)");
		}
		
		rset.close();
		pstmt.close();
		conn.close();
		
		return result;
	}
}
