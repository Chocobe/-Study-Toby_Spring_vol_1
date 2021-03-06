package springbook.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

public class JdbcContext {
	private DataSource dataSource;
	
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	
	public void workWithStatementStrategy(StatementStrategy strategy) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = dataSource.getConnection();
			pstmt = strategy.makePreparedStatement(conn);
			pstmt.executeUpdate();
			
		} catch(SQLException e) {
			throw e;
			
		} finally {
			if(pstmt != null) { try { pstmt.close(); } catch(SQLException e) {} }
			if(conn != null) { try { conn.close(); } catch(SQLException e) {} }
		}
	}
	
	
	public void executeSql(final String query) throws SQLException {
		workWithStatementStrategy(
				new StatementStrategy() {
					
					@Override
					public PreparedStatement makePreparedStatement(Connection conn) throws SQLException {
						return conn.prepareStatement(query);
					}
				}
		);
	}
}
