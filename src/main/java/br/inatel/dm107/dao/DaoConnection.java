package br.inatel.dm107.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DaoConnection {
	
	private static final String URL = "dbc:mysql://localhost:3306/logistica";
	private static final String USER = "root";
	private static final String PWD = "root";
	
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL, USER, PWD);
	}

}
