package MyPackage;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySqlConnection {

	Connection con = null;

	public Connection getCon() throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/PsychologyInfo", "root", "Veer@2024");
		System.out.println("Connection is building...");
		return con;

	}
}