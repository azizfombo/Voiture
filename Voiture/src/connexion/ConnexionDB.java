package connexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import connexion.ConnexionDB;

public class ConnexionDB {
	private static final  String url ="jdbc:mysql://localhost:3306/car";
	private static final  String user = "root";
	private static final String password ="";

	public static Connection connect() throws SQLException{


		try {



			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection(url, user, password);
			return con;

		}catch (ClassNotFoundException | SQLException e) {

			Logger.getLogger(ConnexionDB.class.getName()).log(Level.SEVERE, null, e);	

			e.printStackTrace();

		}

		return null;

	}
}
