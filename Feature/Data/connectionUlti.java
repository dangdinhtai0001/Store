package Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connectionUlti {
	static Connection connection = null;
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		if (connection == null) {
			Class.forName("com.mysql.jdbc.Driver");
			String url= "jdbc:mysql://localhost/store?useUnicode=yes&characterEncoding=UTF-8";
			String user="root";
			String password="";
			connection = DriverManager.getConnection(url, user, password);
		}
		return connection;
	}
}
