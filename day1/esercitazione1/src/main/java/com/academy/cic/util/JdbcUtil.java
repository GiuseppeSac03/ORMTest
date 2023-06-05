package com.academy.cic.util;

<<<<<<< HEAD
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcUtil {
	
	private static Connection connection;
	
	static{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}
		catch (ClassNotFoundException e) {
			System.out.println("Driver class not found");
		}
	}
	
	public static Connection getConnection() throws SQLException {
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/students", "JavaUser", "TheSuper260417!");
		return connection;
	}
	
	public static void closeConnection(Connection con) throws SQLException {
		if (con != null)
			con.close();
	}
	
	public static void closePreparedStatement(PreparedStatement stmt) throws SQLException {
		if (stmt != null) 
			stmt.close();
	}
	
	public static void closeResultSet(ResultSet rs) throws SQLException {
		if (rs != null)
			rs.close();
	}

}
=======

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcUtil {
	
	private static final String DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://localhost:3306/students";
	private static final String DB_USERNAME = "JavaUser";
	private static final String DB_PASSWORD = "DanieleDenza1";
	
	static {
		try {
			Class.forName(DRIVER_CLASS_NAME);
		} catch (ClassNotFoundException e) {
			System.err.println("Failed to load driver class");
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
	}
	
	public static void closeConnection(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				System.err.println("Failed to close connection");
				e.printStackTrace();
			}
		}
	}
	
	public static void closePreparedStatement(PreparedStatement stmt) {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				System.err.println("Failed to close prepared statement");
				e.printStackTrace();
			}
		}
	}
	
	public static void closeResultSet(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				System.err.println("Failed to close result set");
				e.printStackTrace();
			}
		}
	}

}
>>>>>>> refs/remotes/origin/main
