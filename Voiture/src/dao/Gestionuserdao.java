package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import connexion.ConnexionDB;
import modele.user;

public class Gestionuserdao {

	
	
	public static int saveuser(user us) throws SQLException {
		Connection con=ConnexionDB.connect();
		PreparedStatement stat=null;
		int valeur=0;
		try {
			String sql="INSERT INTO user(Nomuser,password,poste,telephone,photo) values(?,?,?,?,?)";
			stat=con.prepareStatement(sql);
			stat.setString(1,us.getNomuser());
			stat.setString(2,us.getPassword());
			stat.setString(3,us.getPoste());
			stat.setInt(4,us.getTelephone());
			
			
			valeur=stat.executeUpdate();
		}
		catch(SQLException e){	
		}
		return valeur;
	}
	public static int Modifieruser(user us) throws SQLException {
		Connection con=ConnexionDB.connect();
		PreparedStatement stat=null;
		int valeur=0;
		try {
			String sql="UPDATE user SET Nomuser=?, password=?, poste=?, telephone=? WHERE iduser=?";
			stat=con.prepareStatement(sql);
			stat.setString(1,us.getNomuser());
			stat.setString(2,us.getPassword());
			stat.setString(3,us.getPoste());
			stat.setInt(4,us.getTelephone());
			stat.setInt(5,us.getIduser());
			valeur=stat.executeUpdate();
			}
			catch(SQLException e){	
			}
			return valeur;		
		}
	
	public static int deletEuser(int iduser) throws SQLException {
		Connection con=ConnexionDB.connect();
		PreparedStatement stat=null;
		int valeur=0;
		
		try {
			String sql="DELETE FROM user WHERE iduser=?";
			stat=con.prepareStatement(sql);
			stat.setInt(1,iduser);
			
			valeur=stat.executeUpdate();
			}
			catch(SQLException e){	
			}
			return valeur;		
		}
	
}
