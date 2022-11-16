package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import connexion.ConnexionDB;
import modele.clients;

public class Gestionreserdao {
	
	public static int savereser(clients cl) throws SQLException {
		Connection con=ConnexionDB.connect();
		PreparedStatement stat=null;
		int valeur=0;
		try {
			String sql="INSERT INTO clients(cniclient,nomclient,telclient,typelocation,datedebut,duree,datefin,immat,prixtot) values(?,?,?,?,?,?,?,?,?)";
			stat=con.prepareStatement(sql);
			stat.setString(1,cl.getCni());
			stat.setString(2,cl.getNomcli());
			stat.setInt(3,cl.getTelcli());
			stat.setString(4,cl.getType());
			stat.setString(5,cl.getDatedeb());
			stat.setInt(6,cl.getDuree());
			stat.setString(7,cl.getDatefin());
			stat.setString(8,cl.getImmat());
			stat.setInt(9,cl.getPrix());
			
			
			valeur=stat.executeUpdate();
		}
		catch(SQLException e){	
		}
		return valeur;
	}
	public static int Modifierreser(clients cl,String c) throws SQLException {
		Connection con=ConnexionDB.connect();
		PreparedStatement stat=null;
		int valeur=0;
		try {
			String sql="UPDATE clients SET cniclient=?, nomclient=?, telclient=?, typelocation=?, duree=?, datefin=?, immat=?, prixtot=? WHERE cniclient=?";
			stat=con.prepareStatement(sql);
			stat.setString(1,cl.getCni());
			stat.setString(2,cl.getNomcli());
			stat.setInt(3,cl.getTelcli());
			stat.setString(4,cl.getType());
			stat.setInt(5,cl.getDuree());
			stat.setString(6,cl.getDatefin());
			stat.setString(7,cl.getImmat());
			stat.setInt(8,cl.getPrix());
			stat.setString(9,c);
			valeur=stat.executeUpdate();
			}
			catch(SQLException e){	
			}
			return valeur;		
		}
	
	public static int deletereser(String cni) throws SQLException {
		Connection con=ConnexionDB.connect();
		PreparedStatement stat=null;
		int valeur=0;
		
		try {
			String sql="DELETE FROM clients WHERE cniclient=?";
			stat=con.prepareStatement(sql);
			stat.setString(1,cni);
			
			valeur=stat.executeUpdate();
			}
			catch(SQLException e){	
			}
			return valeur;		
		}
}
