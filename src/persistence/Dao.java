package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;

public class Dao {
	static boolean firstTime = true;
	Connection con;
	PreparedStatement stmt;
	ResultSet rs;

	public void open() throws Exception {
		if (firstTime) {
			Map<String, String> env = System.getenv();
			for (String envName : env.keySet()) {
				System.out.format("%s=%s%n", envName, env.get(envName));
			}
			firstTime = false;
		}

		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ajax",
				"teste", "teste");
	}

	public void close() throws Exception {
		con.close();
	}

}