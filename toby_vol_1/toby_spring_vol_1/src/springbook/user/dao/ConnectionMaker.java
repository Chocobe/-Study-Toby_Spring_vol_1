package springbook.user.dao;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionMaker {
	abstract public Connection makeConnection() throws ClassNotFoundException, SQLException;
}
