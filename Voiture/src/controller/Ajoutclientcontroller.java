package controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import connexion.ConnexionDB;
import dao.Gestionreserdao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import modele.clients;
import modele.vehicule;

public class Ajoutclientcontroller implements Initializable {
	
	  @FXML
	  private JFXComboBox<String> type;

	    @FXML
	    private JFXTextField cnicli;

	    @FXML
	    private JFXComboBox<String> immatri;

	    @FXML
	    private JFXTextField nomcli;

	    @FXML
	    private JFXTextField duree;

	    @FXML
	    private JFXTextField telcli;

	    @FXML
	    private Label datedebut;

	    @FXML
	    private JFXButton enreg;
	    
	    int p=0;
	    String query = null;
	    Connection connection = null ;
	    PreparedStatement preparedStatement = null ;
	    ResultSet resultSet = null ;
	    ObservableList<String> ty = FXCollections.observableArrayList("LCD","LLD");
	    ObservableList<vehicule> im = FXCollections.observableArrayList();
	    ObservableList<vehicule> v = FXCollections.observableArrayList();

	    @FXML
	    void enregistrer(ActionEvent event) throws SQLException {
	    	
	    	String cni = cnicli.getText();
	    	String nom = nomcli.getText();
	    	SingleSelectionModel<String> typp = type.getSelectionModel();
	    	String typ = typp.getSelectedItem();
	    	String tell = telcli.getText();
          int tel =Integer.parseInt(tell);	 
          String dure = duree.getText();
          int dur =Integer.parseInt(dure);	 
          SingleSelectionModel<String> immatr=immatri.getSelectionModel();
          String immat=immatr.getSelectedItem();
          LocalDateTime datedeb = LocalDateTime.now();
          LocalDateTime datefi=datedeb.plus(dur,ChronoUnit.DAYS);
          String datede=""+datedeb;
          String datef=""+datefi;
          
          if(immat.isEmpty() || cni.isEmpty() || nom.isEmpty()) {
        	   

  			Alert alert= new Alert(AlertType.INFORMATION);
  			alert.setTitle("INFORMATION");
  			alert.setHeaderText("AJOUT DU CLIENT");
  			alert.setContentText("ENTREZ TOUS LES CHAMPS");
  			alert.showAndWait();	

  		}else {
  			 query = "SELECT * FROM vehicules WHERE immat ='"+immat+"'";
             connection = ConnexionDB.connect();
             preparedStatement= connection.prepareStatement(query);
             resultSet = preparedStatement.executeQuery();
             while(resultSet.next()) {
            	 v.add(new vehicule(resultSet.getString(1),resultSet.getString(2) ,resultSet.getString(3), resultSet.getInt(4), resultSet.getInt(5)));
             }
             if(dur<15 && typ=="LLD"  ||  dur>=15 && typ=="LCD") {
            	 Alert alert= new Alert(AlertType.INFORMATION);
       			alert.setTitle("INFORMATION");
       			alert.setHeaderText("AJOUT UTILISATEUR");
       			alert.setContentText("ERREUR AU NIVEAU DU TYPE DE LOCATION OU DE LA DUREE");
       			alert.showAndWait();	
             }else {
            	 clients cli= new clients();
       	  		cli.setImmat(immat);
       	    	cli.setCni(cni);
       	    	cli.setDatedeb(datede);
       	    	cli.setDuree(dur);
       	    	cli.setType(typ);
       	    	cli.setDatefin(datef);
       	    	cli.setNomcli(nom);
       	    	cli.setTelcli(tel);
       	    	for (vehicule ve : v) {
       	    		p=dur*ve.getPrix();
       	  	    	cli.setMarque(ve.getMarque());
       	    	}
       	    	cli.setPrix(p);
       	    	
       	  		int status =Gestionreserdao.savereser(cli);
       	  		if(status>0) {

       	  			Alert alert= new Alert(AlertType.INFORMATION);
       	  			alert.setTitle("INFORMATION");
       	  			alert.setHeaderText("MODIFICATION VEHICULE");
       	  			alert.setContentText("VEHICULE AJOUTE");
       	  			alert.showAndWait();
       	  			
       	  		query = "Update vehicules SET dispo='NON' WHERE immat ='"+immat+"'";
                 connection = ConnexionDB.connect();
                 preparedStatement= connection.prepareStatement(query);
       	    	preparedStatement.execute();
                 
       	    	Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
       	        stage.close();
       		}else {

       			Alert alert= new Alert(AlertType.INFORMATION);
       			alert.setTitle("INFORMATION");
       			alert.setHeaderText("AJOUT UTILISATEUR");
       			alert.setContentText("ULTILSATEUR NON AJOUTE");
       			alert.showAndWait();	
       			
       			


       		}
             }
  			
  		}

	    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
        LocalDateTime datedeb = LocalDateTime.now();
		 String datede=""+datedeb;
		datedebut.setText(datede);
		String query = null;
	    Connection connection = null ;
	    PreparedStatement preparedStatement = null ;
	    ResultSet resultSet = null ;
	    query = "SELECT * FROM vehicules WHERE dispo = 'OUI'";
	    ObservableList <String>li = FXCollections.observableArrayList();
        try {
			connection = ConnexionDB.connect();
			preparedStatement= connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				im.add(new vehicule(resultSet.getString(1),resultSet.getString(2) ,resultSet.getString(3), resultSet.getInt(4), resultSet.getInt(5)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        for(vehicule c : im) {
        	
			li.add(c.getImmat());
		}
        immatri.setItems(li);
        type.setItems(ty);
	}

}
