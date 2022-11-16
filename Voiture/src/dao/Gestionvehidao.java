package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import connexion.ConnexionDB;
import modele.vehicule;

public class Gestionvehidao {
	
	public static int savevehi(vehicule ve) throws SQLException {
		Connection con=ConnexionDB.connect();
		PreparedStatement stat=null;
		int valeur=0;
		try {
			String sql="INSERT INTO vehicules(immat,marque,dispo,kilometrage,prixlocation) values(?,?,?,?,?)";
			stat=con.prepareStatement(sql);
			stat.setString(1,ve.getImmat());
			stat.setString(2,ve.getMarque());
			stat.setString(3,ve.getDispo());
			stat.setInt(4,ve.getKilo());
			stat.setInt(5,ve.getPrix());
			
			
			valeur=stat.executeUpdate();
		}
		catch(SQLException e){	
		}
		return valeur;
	}
	public static int Modifiervehi(vehicule ve,String im) throws SQLException {
		Connection con=ConnexionDB.connect();
		PreparedStatement stat=null;
		int valeur=0;
		try {
			String sql="UPDATE vehicules SET immat=?, marque=?, dispo=?, kilometrage=?, prixlocation=? WHERE immat=?";
			stat=con.prepareStatement(sql);
			stat.setString(1,ve.getImmat());
			stat.setString(2,ve.getMarque());
			stat.setString(3,ve.getDispo());
			stat.setInt(4,ve.getKilo());
			stat.setInt(5,ve.getPrix());
			stat.setString(6,im);
			valeur=stat.executeUpdate();
			}
			catch(SQLException e){	
			}
			return valeur;		
		}
	
	public static int deletevehi(String immat) throws SQLException {
		Connection con=ConnexionDB.connect();
		PreparedStatement stat=null;
		int valeur=0;
		
		try {
			String sql="DELETE FROM vehicules WHERE immat=?";
			stat=con.prepareStatement(sql);
			stat.setString(1,immat);
			
			valeur=stat.executeUpdate();
			}
			catch(SQLException e){	
			}
			return valeur;		
		}
}
