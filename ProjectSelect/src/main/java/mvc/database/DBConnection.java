package mvc.database;

import java.sql.*;

public class DBConnection {
	public static Connection getConnection() throws SQLException, ClassNotFoundException{
		Connection conn = null;
		
		String url = "jdbc:mysql://localhost:3306/const";
		String user = "root";
		String password = "1234";
		
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection(url, user, password);
		
		return conn;
	}
}
