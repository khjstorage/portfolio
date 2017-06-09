package paran.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBConnector {

	private String DB_URL = null;
	private String DB_USER = null;
	private String DB_PASSWORD = null;

	public Connection conn = null;
	public Statement stmt = null;
	public PreparedStatement pstmt = null;
	public ResultSet rs = null;

	public DBConnector() {
		DB_URL = "jdbc:mysql://192.168.0.67:3306/paran";
		DB_USER = "kimhyunjin";
		DB_PASSWORD = "910509";
	}

	public DBConnector(String url, String user, String password) {
		DB_URL = url;
		DB_USER = user;
		DB_PASSWORD = password;
	}

	public void connect() {
		if (conn == null) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			} catch (Exception e) {
				System.out.println("DB 立加");
				e.printStackTrace();
			}
		}
	}

	public void disconnect() {
		try {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (stmt != null)
				stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			if (conn != null)
				conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("DB 立加秦力");
	}

}
