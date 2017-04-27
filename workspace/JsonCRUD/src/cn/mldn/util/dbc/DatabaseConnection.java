package cn.mldn.util.dbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/mldn";
	private static final String USER = "root";
	private static final String PASSWORD = "root";
	private static ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>();
	public static Connection getConnection() {
		Connection conn = threadLocal.get();
		if (conn == null) {
			conn = rebuildConnection();
			threadLocal.set(conn);
		}
		return conn;
	} 
	public static void close() {
		Connection conn = threadLocal.get();
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			threadLocal.remove();
		}
	}
	public static Connection rebuildConnection() {
		try {
			Class.forName(DRIVER);
			return DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
