package controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.jfoenix.controls.JFXButton;


import connexion.ConnexionDB;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import modele.clients;
import modele.vehicule;


public class Accueilcontroller implements Initializable {
	
	
	
	  @FXML
	    private ImageView im;
	  
	  	@FXML
	    private JFXButton vehi;

	    @FXML
	    private JFXButton reser;

	    @FXML
	    private JFXButton perso;

	    @FXML
	    private JFXButton deco;

	    @FXML
	    private Label user;

	    @FXML
	    private AnchorPane anchorvehi;

	    @FXML
	    private TableView<vehicule> tabTick;

	    @FXML
	    private TableColumn<vehicule, String> immat;

	    @FXML
	    private TableColumn<vehicule, String> marque;

	    @FXML
	    private TableColumn<vehicule, Integer> kilometrage;
	    
	    @FXML
	    private TableColumn<vehicule, String> disponibilite;

	    @FXML
	    private TableColumn<vehicule, Integer> prix;

	    @FXML
	    private TableColumn<vehicule, String> operation1;

	    @FXML
	    private JFXButton ajoutvehi;

	    @FXML
	    private TextField rechvehi;

	    @FXML
	    private JFXButton affichervehi;

	    @FXML
	    private ComboBox<String> combvehi;

	    @FXML
	    private AnchorPane anchorreser;

	    @FXML
	    private TableView<clients> tabTick1;

	    @FXML
	    private TableColumn<clients, String> CniCli;

	    @FXML
	    private TableColumn<clients, String> NomCli;

	    @FXML
	    private TableColumn<clients, Integer> TelCli;

	    @FXML
	    private TableColumn<clients, String> typeLoc;

	    @FXML
	    private TableColumn<clients, DatePicker> datedebut;

	    @FXML
	    private TableColumn<clients, Integer> duree;
	    
	    @FXML
	    private TableColumn<clients, DatePicker> datefin;

	    @FXML
	    private TableColumn<clients, String> immatri;
	    
	    @FXML
	    private TableColumn<clients, String> marque1;
	    
	    @FXML
	    private TableColumn<clients, Integer> prixtot;
	    
	    @FXML
	    private TableColumn<clients, String> operation2;

	    @FXML
	    private JFXButton ajoutreser;

	    @FXML
	    private TextField rechreser;

	    @FXML
	    private JFXButton afficherreser;

	    @FXML
	    private ComboBox<String> combreser;

	    @FXML
	    private AnchorPane anchouser;

	    @FXML
	    private TableView<modele.user> tabTick11;

	    @FXML
	    private TableColumn<modele.user, Integer> iduser;

	    @FXML
	    private TableColumn<modele.user, String> nomuser;

	    @FXML
	    private TableColumn<modele.user, String> poste;

	    @FXML
	    private TableColumn<modele.user, Integer> teluser;
	    
	    @FXML
	    private TableColumn<modele.user, String> operation;

	    @FXML
	    private JFXButton ajoutperso;

	    @FXML
	    private TextField rechperso;

	    @FXML
	    private JFXButton afficherperso;

	    @FXML
	    private ComboBox<String> combperso;
	    
	    @FXML
	    private JFXButton bandit;
	    
	    
	    String query = null;
	    Connection connection = null ;
	    PreparedStatement preparedStatement = null ;
	    ResultSet resultSet = null ;
	    modele.user us = null ;
	    vehicule ve = null;
	    clients cl =null;
	    byte byteim [];
	    Blob b;
	    
	    ObservableList<String>revehi= FXCollections.observableArrayList("Immatriculation","Disponibilité","prix location");
	    ObservableList<String>reclients= FXCollections.observableArrayList("CNI client","Marque","Immatriculation");
	    ObservableList<String> reuser= FXCollections.observableArrayList("Nom User","Poste");
	    ObservableList<modele.user> listTick11 = FXCollections.observableArrayList();
	    ObservableList<vehicule> listTick = FXCollections.observableArrayList();
	    ObservableList<clients> listTick1 = FXCollections.observableArrayList();
	    LocalDate dateaud=LocalDate.now();
	    String dateau =""+dateaud;
	    
	    @FXML
	    void bandi(ActionEvent event) {
	    	
	    	tabTick1.getItems().clear();

			try {
				
				Connection con = ConnexionDB.connect();

				String sql = "SELECT c.*,v.marque FROM clients c,vehicules v WHERE c.immat=v.immat AND c.datefin <= '"+dateau+"'";
				PreparedStatement stat = con.prepareStatement(sql);

				ResultSet rs = stat.executeQuery();
				while(rs.next()) {

					listTick1.add(new clients(rs.getString(1),rs.getString(2) ,rs.getInt(3), rs.getString(4), rs.getString(5),rs.getInt(6), rs.getString(7),rs.getString(8),rs.getInt(9),rs.getString(10)));

				}
				con.close();

			} catch (Exception e) {

				e.printStackTrace();
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
           CniCli.setCellValueFactory(new PropertyValueFactory<clients,String>("cni"));
           NomCli.setCellValueFactory(new PropertyValueFactory<clients,String>("nomcli"));
           TelCli.setCellValueFactory(new PropertyValueFactory<clients,Integer>("telcli")); 
           typeLoc.setCellValueFactory(new PropertyValueFactory<clients,String>("type"));         	
		   datedebut.setCellValueFactory(new PropertyValueFactory<clients,DatePicker>("datedeb"));
		   duree.setCellValueFactory(new PropertyValueFactory<clients,Integer>("duree")); 
           datefin.setCellValueFactory(new PropertyValueFactory<clients,DatePicker>("datefin"));         	
		   immatri.setCellValueFactory(new PropertyValueFactory<clients,String>("immat"));
		   marque1.setCellValueFactory(new PropertyValueFactory<clients,String>("marque"));
		   prixtot.setCellValueFactory(new PropertyValueFactory<clients,Integer>("prix")); 

		    tabTick1.setItems(listTick1);

	    }
	    
	    @FXML
	    void actuperso(ActionEvent event) {
	    	
	    	tabTick11.getItems().clear();

			try {
				
				Connection con = ConnexionDB.connect();

				String sql = "SELECT * FROM user";
				PreparedStatement stat = con.prepareStatement(sql);

				ResultSet rs = stat.executeQuery();
				while(rs.next()) {

					listTick11.add(new modele.user(rs.getInt(1),rs.getString(2) , rs.getString(3), rs.getString(4), rs.getInt(5)));

				}
				con.close();

			} catch (Exception e) {

				e.printStackTrace();
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
           iduser.setCellValueFactory(new PropertyValueFactory<modele.user,Integer>("iduser"));
           nomuser.setCellValueFactory(new PropertyValueFactory<modele.user,String>("Nomuser"));
			poste.setCellValueFactory(new PropertyValueFactory<modele.user,String>("poste"));         	
		    teluser.setCellValueFactory(new PropertyValueFactory<modele.user,Integer>("telephone"));

		    tabTick11.setItems(listTick11);	
		}
	    
	    
	    
	    
	    
	    

	    @FXML
	    void actureser(ActionEvent event) {
	    	
	    	
	    	tabTick1.getItems().clear();

			try {
				
				Connection con = ConnexionDB.connect();

				String sql = "SELECT c.*,v.marque FROM clients c,vehicules v WHERE c.immat=v.immat";
				PreparedStatement stat = con.prepareStatement(sql);

				ResultSet rs = stat.executeQuery();
				while(rs.next()) {

					listTick1.add(new clients(rs.getString(1),rs.getString(2) ,rs.getInt(3), rs.getString(4), rs.getString(5),rs.getInt(6), rs.getString(7),rs.getString(8),rs.getInt(9),rs.getString(10)));

				}
				con.close();

			} catch (Exception e) {

				e.printStackTrace();
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
           CniCli.setCellValueFactory(new PropertyValueFactory<clients,String>("cni"));
           NomCli.setCellValueFactory(new PropertyValueFactory<clients,String>("nomcli"));
           TelCli.setCellValueFactory(new PropertyValueFactory<clients,Integer>("telcli")); 
           typeLoc.setCellValueFactory(new PropertyValueFactory<clients,String>("type"));         	
		   datedebut.setCellValueFactory(new PropertyValueFactory<clients,DatePicker>("datedeb"));
		   duree.setCellValueFactory(new PropertyValueFactory<clients,Integer>("duree")); 
           datefin.setCellValueFactory(new PropertyValueFactory<clients,DatePicker>("datefin"));         	
		   immatri.setCellValueFactory(new PropertyValueFactory<clients,String>("immat"));
		   marque1.setCellValueFactory(new PropertyValueFactory<clients,String>("marque"));
		   prixtot.setCellValueFactory(new PropertyValueFactory<clients,Integer>("prix")); 

		    tabTick1.setItems(listTick1);
	    }
	    
	    
	    
	    
	    
	    
	    
	    

	    @FXML
	    void actuvehi(ActionEvent event) {
	    	
	    	tabTick.getItems().clear();

			try {
				
				Connection con = ConnexionDB.connect();

				String sql = "SELECT * FROM vehicules";
				PreparedStatement stat = con.prepareStatement(sql);

				ResultSet rs = stat.executeQuery();
				while(rs.next()) {

					listTick.add(new vehicule(rs.getString(1),rs.getString(2) ,rs.getString(3), rs.getInt(4), rs.getInt(5)));

				}
				con.close();

			} catch (Exception e) {

				e.printStackTrace();
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
           immat.setCellValueFactory(new PropertyValueFactory<vehicule,String>("immat"));
           marque.setCellValueFactory(new PropertyValueFactory<vehicule,String>("marque"));
           disponibilite.setCellValueFactory(new PropertyValueFactory<vehicule,String>("dispo")); 
           kilometrage.setCellValueFactory(new PropertyValueFactory<vehicule,Integer>("kilo"));         	
		   prix.setCellValueFactory(new PropertyValueFactory<vehicule,Integer>("prix"));

		    tabTick.setItems(listTick);
	    }
	    
	    
	    
	    
	    
	    

	    @FXML
	    void add(ActionEvent event) throws IOException {
	    	reser.getScene().getWindow();
	    	Stage stage= new Stage();
			
			FXMLLoader loader= new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/Ajoutclientview.fxml"));
			Parent	main1=loader.load();
			
			Scene scene= new Scene(main1);
			stage.setScene(scene);
			stage.show();
			stage.setResizable(false);
			Image img = new Image("/images/icons.png");
			stage.getIcons().add(img);
			stage.setTitle("CAR LOCATION");
	    }
	    
	    
	    
	    
	    

	    @FXML
	    void addperso(ActionEvent event) throws IOException {
	    	perso.getScene().getWindow();
	    	Stage stage= new Stage();
			
			FXMLLoader loader= new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/Ajoutuserview.fxml"));
			Parent	main1=loader.load();
			
			Scene scene= new Scene(main1);
			stage.setScene(scene);
			stage.show();
			stage.setResizable(false);
			Image img = new Image("/images/icons.png");
			stage.getIcons().add(img);
			stage.setTitle("CAR LOCATION");
	    }
	    
	    
	    
	    
	    
	    

	    @FXML
	    void addvehi(ActionEvent event) throws IOException {
	    	vehi.getScene().getWindow();
	    	Stage stage= new Stage();
			
			FXMLLoader loader= new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/Ajoutvehiview.fxml"));
			Parent	main1=loader.load();
			
			Scene scene= new Scene(main1);
			stage.setScene(scene);
			stage.show();
			stage.setResizable(false);
			Image img = new Image("/images/icons.png");
			stage.getIcons().add(img);
			stage.setTitle("CAR LOCATION");
	    }
	    
	    
	    
	    
	    
	    
	    

	    @FXML
	    void btndeco(ActionEvent event) throws Exception {
	    	
	    	Alert alert= new Alert(AlertType.CONFIRMATION);
			alert.setTitle("DECONNEXION");
			alert.setHeaderText("DECONNEXION");
			alert.setContentText("VOULEZ VOUS VRAIMENT VOUS DECONNECTER ?");
			Optional <ButtonType>de=alert.showAndWait();
			if(de.get()==ButtonType.OK) {
				deco.getScene().getWindow().hide();
				deco.getScene().getWindow();
		    	Stage stage= new Stage();
				
				FXMLLoader loader= new FXMLLoader();
				loader.setLocation(getClass().getResource("/view/LoginView.fxml"));
			Parent	main1=loader.load();
				
				Scene scene= new Scene(main1);
				stage.setScene(scene);
				stage.show();
				stage.setResizable(false);
				Image img = new Image("/images/icons.png");
				stage.getIcons().add(img);
				stage.setTitle("CAR LOCATION");
			}
	    }
	    
	    
	    
	    
	    
	    

	    @FXML
	    void btnperso(ActionEvent event) {
	    	anchorreser.setVisible(false);
	    	anchorvehi.setVisible(false);
	    	anchouser.setVisible(true);
	    	
	    	
	    	tabTick11.getItems().clear();

			try {
				
				Connection con = ConnexionDB.connect();

				String sql = "SELECT * FROM user";
				PreparedStatement stat = con.prepareStatement(sql);

				ResultSet rs = stat.executeQuery();
				while(rs.next()) {

					listTick11.add(new modele.user(rs.getInt(1),rs.getString(2) , rs.getString(3), rs.getString(4), rs.getInt(5)));

				}
				con.close();

			} catch (Exception e) {

				e.printStackTrace();
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
           iduser.setCellValueFactory(new PropertyValueFactory<modele.user,Integer>("iduser"));
           nomuser.setCellValueFactory(new PropertyValueFactory<modele.user,String>("Nomuser"));
			poste.setCellValueFactory(new PropertyValueFactory<modele.user,String>("poste"));         	
		    teluser.setCellValueFactory(new PropertyValueFactory<modele.user,Integer>("telephone"));

		    tabTick11.setItems(listTick11);;			
		}
	    
	    
	    
	    
	    

	    @FXML
	    void btnreser(ActionEvent event) {
	    	anchorreser.setVisible(true);
	    	anchorvehi.setVisible(false);
	    	anchouser.setVisible(false);
	    	
	    	tabTick1.getItems().clear();

			try {
				
				Connection con = ConnexionDB.connect();

				String sql = "SELECT c.*,v.marque FROM clients c,vehicules v WHERE c.immat=v.immat";
				PreparedStatement stat = con.prepareStatement(sql);

				ResultSet rs = stat.executeQuery();
				while(rs.next()) {

					listTick1.add(new clients(rs.getString(1),rs.getString(2) ,rs.getInt(3), rs.getString(4), rs.getString(5),rs.getInt(6), rs.getString(7),rs.getString(8),rs.getInt(9),rs.getString(10)));

				}
				con.close();

			} catch (Exception e) {

				e.printStackTrace();
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
           CniCli.setCellValueFactory(new PropertyValueFactory<clients,String>("cni"));
           NomCli.setCellValueFactory(new PropertyValueFactory<clients,String>("nomcli"));
           TelCli.setCellValueFactory(new PropertyValueFactory<clients,Integer>("telcli")); 
           typeLoc.setCellValueFactory(new PropertyValueFactory<clients,String>("type"));         	
		   datedebut.setCellValueFactory(new PropertyValueFactory<clients,DatePicker>("datedeb"));
		   duree.setCellValueFactory(new PropertyValueFactory<clients,Integer>("duree")); 
           datefin.setCellValueFactory(new PropertyValueFactory<clients,DatePicker>("datefin"));         	
		   immatri.setCellValueFactory(new PropertyValueFactory<clients,String>("immat"));
		   marque1.setCellValueFactory(new PropertyValueFactory<clients,String>("marque"));
		   prixtot.setCellValueFactory(new PropertyValueFactory<clients,Integer>("prix")); 

		    tabTick1.setItems(listTick1);
	    	
	    }
	    
	    
	    
	    
	    

	    @FXML
	    void btnvehi(ActionEvent event) {
	    	anchorreser.setVisible(false);
	    	anchorvehi.setVisible(true);
	    	anchouser.setVisible(false);
	    	
	    	tabTick.getItems().clear();

			try {
				
				Connection con = ConnexionDB.connect();

				String sql = "SELECT * FROM vehicules";
				PreparedStatement stat = con.prepareStatement(sql);

				ResultSet rs = stat.executeQuery();
				while(rs.next()) {

					listTick.add(new vehicule(rs.getString(1),rs.getString(2) ,rs.getString(3), rs.getInt(4), rs.getInt(5)));

				}
				con.close();

			} catch (Exception e) {

				e.printStackTrace();
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
           immat.setCellValueFactory(new PropertyValueFactory<vehicule,String>("immat"));
           marque.setCellValueFactory(new PropertyValueFactory<vehicule,String>("marque"));
           disponibilite.setCellValueFactory(new PropertyValueFactory<vehicule,String>("dispo")); 
           kilometrage.setCellValueFactory(new PropertyValueFactory<vehicule,Integer>("kilo"));         	
		   prix.setCellValueFactory(new PropertyValueFactory<vehicule,Integer>("prix"));

		    tabTick.setItems(listTick);
	    
	    }
	    
	    
	    
	    
	    

	    @FXML
	    void rechperso(KeyEvent event) {
	    	
	    	
	    	if(event.getCode()==KeyCode.ENTER) {
	    		String cbr= combperso.getValue();
	    		String rech=rechperso.getText();
	    		if(rech.equals("")) {
	    			tabTick11.getItems().clear();
	    			Alert alert= new Alert(AlertType.ERROR);
	    			alert.setTitle("Information");
	    			alert.setHeaderText("recherche");
	    			alert.setContentText(" VEUILLEZ ENTRER LA CATEGORIE DE RECHERCHE");
	    			alert.showAndWait();
	    			
	    		}else if(cbr.equals("Nom User")){
	    			tabTick11.getItems().clear();
	    				
	    				try {
	    					String sql ="SELECT * FROM user WHERE Nomuser=?";
	    				
	    						Connection con=ConnexionDB.connect();
	    						PreparedStatement stat;
	    						stat = con.prepareStatement(sql);
	    						stat.setString(1, rech.toString());
	    						ResultSet rs=stat.executeQuery();
	    						
	    						while(rs.next()) {

	    							listTick11.add(new modele.user(rs.getInt(1),rs.getString(2) , rs.getString(3), rs.getString(4),rs.getInt(5)));

	    						}
	    						con.close();
	    						
	    						
	    					}catch (SQLException e2) {
	    						// TODO: handle exception
	    						e2.printStackTrace();   
	    					}
	    				
	    				  iduser.setCellValueFactory(new PropertyValueFactory<modele.user,Integer>("iduser"));
	    		           nomuser.setCellValueFactory(new PropertyValueFactory<modele.user,String>("Nomuser"));
	    					poste.setCellValueFactory(new PropertyValueFactory<modele.user,String>("poste"));         	
	    				    teluser.setCellValueFactory(new PropertyValueFactory<modele.user,Integer>("telephone"));

	    				    tabTick11.setItems(listTick11);		
	    				rechperso.clear();
	    				combperso.getSelectionModel().clearSelection();
	    		}else {
		    		tabTick11.getItems().clear();

	    			try {
	    				
	    				String sql ="SELECT * FROM user WHERE poste=?";
	    				Connection con=ConnexionDB.connect();
	    				PreparedStatement stat;
	    				stat = con.prepareStatement(sql);
	    				stat.setString(1, rechperso.getText().toString());
	    				ResultSet rs=stat.executeQuery();
	    				
	    				while(rs.next()) {

							listTick11.add(new modele.user(rs.getInt(1),rs.getString(2) , rs.getString(3), rs.getString(4),rs.getInt(5)));

						}
						con.close();
						
						
					}catch (SQLException e2) {
						// TODO: handle exception
						e2.printStackTrace();   
					}
				
				  iduser.setCellValueFactory(new PropertyValueFactory<modele.user,Integer>("iduser"));
		           nomuser.setCellValueFactory(new PropertyValueFactory<modele.user,String>("Nomuser"));
					poste.setCellValueFactory(new PropertyValueFactory<modele.user,String>("poste"));         	
				    teluser.setCellValueFactory(new PropertyValueFactory<modele.user,Integer>("telephone"));

				    tabTick11.setItems(listTick11);;		
				rechperso.clear();
				combperso.getSelectionModel().clearSelection();
		    	}
	    	}
	    	
	    }
	    
	    
	    
	    
	    
	    
	    

	    @FXML
	    void rechreser(KeyEvent event) {
	    	
	    	if(event.getCode()==KeyCode.ENTER) {
	    		String cbr= combreser.getValue();
	    		String rech=rechreser.getText();
	    		if(rech.equals("")) {
	    			tabTick1.getItems().clear();
	    			Alert alert= new Alert(AlertType.ERROR);
	    			alert.setTitle("Information");
	    			alert.setHeaderText("recherche");
	    			alert.setContentText(" VEUILLEZ ENTRER LA CATEGORIE DE RECHERCHE");
	    			alert.showAndWait();
	    			
	    		}else if(cbr.equals("CNI client")){
	    			tabTick1.getItems().clear();
	    				
	    				try {
	    					String sql ="SELECT c.*,v.marque FROM clients c,vehicules v WHERE c.immat=v.immat AND cniclient=?";
	    				
	    						Connection con=ConnexionDB.connect();
	    						PreparedStatement stat;
	    						stat = con.prepareStatement(sql);
	    						stat.setString(1, rech.toString());
	    						ResultSet rs=stat.executeQuery();
	    						
	    						while(rs.next()) {

	    							listTick1.add(new clients(rs.getString(1),rs.getString(2) , rs.getInt(3), rs.getString(4), rs.getString(5),rs.getInt(6),rs.getString(7) , rs.getString(8),rs.getInt(9),rs.getString(10)));

	    						}
	    						con.close();
	    						
	    						
	    					}catch (SQLException e2) {
	    						// TODO: handle exception
	    						e2.printStackTrace();   
	    					}
	    				
	    				CniCli.setCellValueFactory(new PropertyValueFactory<clients,String>("cni"));
	    		           NomCli.setCellValueFactory(new PropertyValueFactory<clients,String>("nomcli"));
	    		           TelCli.setCellValueFactory(new PropertyValueFactory<clients,Integer>("telcli")); 
	    		           typeLoc.setCellValueFactory(new PropertyValueFactory<clients,String>("type"));         	
	    				   datedebut.setCellValueFactory(new PropertyValueFactory<clients,DatePicker>("datedeb"));
	    				   duree.setCellValueFactory(new PropertyValueFactory<clients,Integer>("duree")); 
	    		           datefin.setCellValueFactory(new PropertyValueFactory<clients,DatePicker>("datefin"));         	
	    				   immatri.setCellValueFactory(new PropertyValueFactory<clients,String>("immat"));
	    				   marque1.setCellValueFactory(new PropertyValueFactory<clients,String>("marque"));
	    				   prixtot.setCellValueFactory(new PropertyValueFactory<clients,Integer>("prix")); 

	    				    tabTick1.setItems(listTick1);	
	    				rechreser.clear();
	    				combreser.getSelectionModel().clearSelection();
	    		}else if (cbr.equals("Immatriculation")) {
		    		tabTick1.getItems().clear();

	    			try {
	    				
	    				String sql ="SELECT c.*,v.marque FROM clients c,vehicules v WHERE c.immat=v.immat AND c.immat=?";
	    				Connection con=ConnexionDB.connect();
	    				PreparedStatement stat;
	    				stat = con.prepareStatement(sql);
	    				stat.setString(1, rechreser.getText().toString());
	    				ResultSet rs=stat.executeQuery();
	    				
	    				while(rs.next()) {

							listTick1.add(new clients(rs.getString(1),rs.getString(2) , rs.getInt(3), rs.getString(4), rs.getString(5),rs.getInt(6),rs.getString(7) , rs.getString(8),rs.getInt(9),rs.getString(10)));

						}
						con.close();
						
						
					}catch (SQLException e2) {
						// TODO: handle exception
						e2.printStackTrace();   
					}
				
				CniCli.setCellValueFactory(new PropertyValueFactory<clients,String>("cni"));
		           NomCli.setCellValueFactory(new PropertyValueFactory<clients,String>("nomcli"));
		           TelCli.setCellValueFactory(new PropertyValueFactory<clients,Integer>("telcli")); 
		           typeLoc.setCellValueFactory(new PropertyValueFactory<clients,String>("type"));         	
				   datedebut.setCellValueFactory(new PropertyValueFactory<clients,DatePicker>("datedeb"));
				   duree.setCellValueFactory(new PropertyValueFactory<clients,Integer>("duree")); 
		           datefin.setCellValueFactory(new PropertyValueFactory<clients,DatePicker>("datefin"));         	
				   immatri.setCellValueFactory(new PropertyValueFactory<clients,String>("immat"));
				   marque1.setCellValueFactory(new PropertyValueFactory<clients,String>("marque"));
				   prixtot.setCellValueFactory(new PropertyValueFactory<clients,Integer>("prix")); 

				    tabTick1.setItems(listTick1);	
				rechreser.clear();
				combreser.getSelectionModel().clearSelection();
		    	}else {
		    		tabTick1.getItems().clear();

	    			try {
	    				
	    				String sql ="SELECT c.*,v.marque FROM clients c,vehicules v WHERE c.immat=v.immat AND marque=?";
	    				Connection con=ConnexionDB.connect();
	    				PreparedStatement stat;
	    				stat = con.prepareStatement(sql);
	    				stat.setString(1, rechreser.getText().toString());
	    				ResultSet rs=stat.executeQuery();
	    				
	    				while(rs.next()) {

							listTick1.add(new clients(rs.getString(1),rs.getString(2) , rs.getInt(3), rs.getString(4), rs.getString(5),rs.getInt(6),rs.getString(7) , rs.getString(8),rs.getInt(9),rs.getString(10)));

						}
						con.close();
						
						
					}catch (SQLException e2) {
						// TODO: handle exception
						e2.printStackTrace();   
					}
				
				CniCli.setCellValueFactory(new PropertyValueFactory<clients,String>("cni"));
		           NomCli.setCellValueFactory(new PropertyValueFactory<clients,String>("nomcli"));
		           TelCli.setCellValueFactory(new PropertyValueFactory<clients,Integer>("telcli")); 
		           typeLoc.setCellValueFactory(new PropertyValueFactory<clients,String>("type"));         	
				   datedebut.setCellValueFactory(new PropertyValueFactory<clients,DatePicker>("datedeb"));
				   duree.setCellValueFactory(new PropertyValueFactory<clients,Integer>("duree")); 
		           datefin.setCellValueFactory(new PropertyValueFactory<clients,DatePicker>("datefin"));         	
				   immatri.setCellValueFactory(new PropertyValueFactory<clients,String>("immat"));
				   marque1.setCellValueFactory(new PropertyValueFactory<clients,String>("marque"));
				   prixtot.setCellValueFactory(new PropertyValueFactory<clients,Integer>("prix")); 

				    tabTick1.setItems(listTick1);	
				rechreser.clear();
				combreser.getSelectionModel().clearSelection();
		    	}
	    	}	
	    	

	    }
	    
	    
	    
	    
	    
	    
	    
	    

	    @FXML
	    void rechvehi(KeyEvent event) {
	    	
	    	
	    	if(event.getCode()==KeyCode.ENTER) {
	    		String cbr= combvehi.getValue();
	    		String rech=rechvehi.getText();
	    		if(rech.equals("")) {
	    			tabTick.getItems().clear();
	    			Alert alert= new Alert(AlertType.ERROR);
	    			alert.setTitle("Information");
	    			alert.setHeaderText("recherche");
	    			alert.setContentText(" VEUILLEZ ENTRER LA CATEGORIE DE RECHERCHE");
	    			alert.showAndWait();
	    			
	    		}else if(cbr.equals("Immatriculation")){
	    			tabTick.getItems().clear();
	    				
	    				try {
	    					String sql ="SELECT * FROM vehicules WHERE immat=?";
	    				
	    						Connection con=ConnexionDB.connect();
	    						PreparedStatement stat;
	    						stat = con.prepareStatement(sql);
	    						stat.setString(1, rech.toString());
	    						ResultSet rs=stat.executeQuery();
	    						
	    						while(rs.next()) {

	    							listTick.add(new vehicule(rs.getString(1),rs.getString(2) , rs.getString(3), rs.getInt(4),rs.getInt(5)));

	    						}
	    						con.close();
	    						
	    						
	    					}catch (SQLException e2) {
	    						// TODO: handle exception
	    						e2.printStackTrace();   
	    					}
	    				
	    				  immat.setCellValueFactory(new PropertyValueFactory<vehicule,String>("immat"));
	    		          marque.setCellValueFactory(new PropertyValueFactory<vehicule,String>("marque"));
	    		          disponibilite.setCellValueFactory(new PropertyValueFactory<vehicule,String>("dispo"));         	
	    				  kilometrage.setCellValueFactory(new PropertyValueFactory<vehicule,Integer>("kilo"));
	    				  prix.setCellValueFactory(new PropertyValueFactory<vehicule,Integer>("prix"));

	    				    tabTick.setItems(listTick);		
	    				rechvehi.clear();
	    				combvehi.getSelectionModel().clearSelection();
	    		}else if (cbr.equals("Disponibilité")){
		    		tabTick.getItems().clear();

	    			try {
	    				
	    				String sql ="SELECT * FROM vehicules WHERE dispo=?";
	    				Connection con=ConnexionDB.connect();
	    				PreparedStatement stat;
	    				stat = con.prepareStatement(sql);
	    				stat.setString(1, rechvehi.getText().toString());
	    				ResultSet rs=stat.executeQuery();
	    				
	    				while(rs.next()) {

	    					listTick.add(new vehicule(rs.getString(1),rs.getString(2) , rs.getString(3), rs.getInt(4),rs.getInt(5)));

						}
						con.close();
						
						
					}catch (SQLException e2) {
						// TODO: handle exception
						e2.printStackTrace();   
					}
				
	    			immat.setCellValueFactory(new PropertyValueFactory<vehicule,String>("immat"));
  		          marque.setCellValueFactory(new PropertyValueFactory<vehicule,String>("marque"));
  		          disponibilite.setCellValueFactory(new PropertyValueFactory<vehicule,String>("dispo"));         	
  				  kilometrage.setCellValueFactory(new PropertyValueFactory<vehicule,Integer>("kilo"));
  				  prix.setCellValueFactory(new PropertyValueFactory<vehicule,Integer>("prix"));

  				    tabTick.setItems(listTick);		
  				rechvehi.clear();
  				combvehi.getSelectionModel().clearSelection();
		    	}else {
		    		
		    		tabTick.getItems().clear();

	    			try {
	    				
	    				String sql ="SELECT * FROM vehicules WHERE prixlocation=?";
	    				Connection con=ConnexionDB.connect();
	    				PreparedStatement stat;
	    				stat = con.prepareStatement(sql);
	    				stat.setString(1, rechvehi.getText().toString());
	    				ResultSet rs=stat.executeQuery();
	    				
	    				while(rs.next()) {

	    					listTick.add(new vehicule(rs.getString(1),rs.getString(2) , rs.getString(3), rs.getInt(4),rs.getInt(5)));

						}
						con.close();
						
						
					}catch (SQLException e2) {
						// TODO: handle exception
						e2.printStackTrace();   
					}
				
	    			immat.setCellValueFactory(new PropertyValueFactory<vehicule,String>("immat"));
  		          marque.setCellValueFactory(new PropertyValueFactory<vehicule,String>("marque"));
  		          disponibilite.setCellValueFactory(new PropertyValueFactory<vehicule,String>("dispo"));         	
  				  kilometrage.setCellValueFactory(new PropertyValueFactory<vehicule,Integer>("kilo"));
  				  prix.setCellValueFactory(new PropertyValueFactory<vehicule,Integer>("prix"));

  				    tabTick.setItems(listTick);		
  				rechvehi.clear();
  				combvehi.getSelectionModel().clearSelection();
		    		
		    		
		    	}
	    	}

	    }
	    
	    
	    
	    
	    
	    
	    

		private void action() throws SQLException {
	    	connection = ConnexionDB.connect();
	           actuperso(null);
	           
	           iduser.setCellValueFactory(new PropertyValueFactory<modele.user,Integer>("iduser"));
	           nomuser.setCellValueFactory(new PropertyValueFactory<modele.user,String>("Nomuser"));
				poste.setCellValueFactory(new PropertyValueFactory<modele.user,String>("poste"));         	
			    teluser.setCellValueFactory(new PropertyValueFactory<modele.user,Integer>("telephone"));

	          

	           
	           //BOUTON EDITER ET SUPPRIMER
	            Callback<TableColumn<modele.user, String>, TableCell<modele.user, String>> cellFoctory = (TableColumn<modele.user, String> param) -> {
	               final TableCell<modele.user, String> cell = new TableCell<modele.user, String>() {
	                   @Override
	                   public void updateItem(String item, boolean empty) {
	                       super.updateItem(item, empty);
	                       //that cell created only on non-empty rows
	                       if (empty) {
	                           setGraphic(null);
	                           setText(null);

	                       } else {

	                           FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH_ALT);
	                           FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE_ALT);
	                           deleteIcon.setStyle(
	                                   " -fx-cursor: hand ;"
	                                   + "-glyph-size:24px;"
	                                   + "-fx-fill:#555555;"
	                           );
	                           editIcon.setStyle(
	                                   " -fx-cursor: hand ;"
	                                   + "-glyph-size:24px;"
	                                   + "-fx-fill:#555555;"
	                           );
	                           
	                           
	                           deleteIcon.setOnMouseClicked((MouseEvent event) -> {
	                               
	                               try {
	                              	 Alert alert= new Alert(AlertType.CONFIRMATION);
	                       			alert.setTitle("CONFIRMATION");
	                       			alert.setHeaderText("SUPPRESSION");
	                       			alert.setContentText("VOULEZ VOUS SUPPRIMMER CET UTILISATEUR?");
	                       			Optional<ButtonType> rep = alert.showAndWait();	
	                       			if(rep.get()==ButtonType.OK) 	{
	                              	 
	                              	 
	                                   us = tabTick11.getSelectionModel().getSelectedItem();
	                                   query = "DELETE FROM user WHERE iduser ="+us.getIduser();
	                                   connection = ConnexionDB.connect();
	                                   preparedStatement= connection.prepareStatement(query);
	                                   preparedStatement.execute();
	                                   actuperso(null);
	                       			}
	                                   
	                               } catch (SQLException ex) {
	                            	   Logger.getLogger(Accueilcontroller.class.getName()).log(Level.SEVERE, null, ex);
	                               }
	                               
	                              
	                               
	                             

	                           });
	                          editIcon.setOnMouseClicked((MouseEvent event) -> {
	                        	  us =tabTick11.getSelectionModel().getSelectedItem();
	                              FXMLLoader loader = new FXMLLoader ();
	                              loader.setLocation(getClass().getResource("/view/Modifieruserview.fxml"));
	                              
	                             try {
	                                  loader.load();
	                              } catch (IOException ex) {
	                                  Logger.getLogger(Modifierusercontroller.class.getName()).log(Level.SEVERE, null, ex);
	                              }
	                              
	                             Modifierusercontroller mod = loader.getController();
	                             mod.setTextField(us.getIduser(),us.getNomuser(),us.getPassword(), us.getPoste(),us.getTelephone());
	                             Parent parent = loader.getRoot();
	                 			
	                             Stage stage = new Stage();
	                             stage.setScene(new Scene(parent));
	                             stage.show();
	                             
	                             stage.setResizable(false);
	                           });

	                  
	                           HBox managebtn = new HBox(editIcon, deleteIcon);
	                           managebtn.setStyle("-fx-alignment:center");
	                           HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
	                           HBox.setMargin(editIcon, new Insets(2, 2, 0, 2));
	                           setGraphic(managebtn);

	                           setText(null);

	                       }
	                   }

	               };

	               return cell;
	           };
	           
	           
	            operation.setCellFactory(cellFoctory);
	            tabTick11.setItems(listTick11); 
	            
	       }
		
		
		
		
		private void action1() throws SQLException {
	    	connection = ConnexionDB.connect();
	           actuperso(null);
	           immat.setCellValueFactory(new PropertyValueFactory<vehicule,String>("immat"));
	           marque.setCellValueFactory(new PropertyValueFactory<vehicule,String>("marque"));
	           disponibilite.setCellValueFactory(new PropertyValueFactory<vehicule,String>("dispo")); 
	           kilometrage.setCellValueFactory(new PropertyValueFactory<vehicule,Integer>("kilometrage"));         	
			   prix.setCellValueFactory(new PropertyValueFactory<vehicule,Integer>("prixlocation"));
	           
	           //BOUTON EDITER ET SUPPRIMER
	            Callback<TableColumn<vehicule, String>, TableCell<vehicule, String>> cellFoctory = (TableColumn<vehicule, String> param) -> {
	               final TableCell<vehicule, String> cell = new TableCell<vehicule, String>() {
	                   @Override
	                   public void updateItem(String item, boolean empty) {
	                       super.updateItem(item, empty);
	                       //that cell created only on non-empty rows
	                       if (empty) {
	                           setGraphic(null);
	                           setText(null);

	                       } else {

	                           FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH_ALT);
	                           FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE_ALT);
	                           deleteIcon.setStyle(
	                                   " -fx-cursor: hand ;"
	                                   + "-glyph-size:24px;"
	                                   + "-fx-fill:#555555;"
	                                   + "-fx-background-color: #FF0000;"
	                           );
	                           editIcon.setStyle(
	                                   " -fx-cursor: hand ;"
	                                   + "-glyph-size:24px;"
	                                   + "-fx-fill:#555555;"
	                           );
	                           
	                           deleteIcon.setOnMouseClicked((MouseEvent event) -> {
                                   ve = tabTick.getSelectionModel().getSelectedItem();
	                               
	                               try {
	                              	 Alert alert= new Alert(AlertType.CONFIRMATION);
	                       			alert.setTitle("CONFIRMATION");
	                       			alert.setHeaderText("SUPPRESSION");
	                       			alert.setContentText("VOULEZ VOUS SUPPRIMMER LE VEHICULE "+ve.getImmat() +" ?");
	                       			Optional<ButtonType> rep = alert.showAndWait();	
	                       			if(rep.get()==ButtonType.OK) 	{
	                              	 
	                              	 
	                                   query = "DELETE FROM vehicules WHERE immat ='"+ve.getImmat()+"'";
	                                   connection = ConnexionDB.connect();
	                                   preparedStatement= connection.prepareStatement(query);
	                                   preparedStatement.execute();
	                                   actuvehi(null);
	                       			}
	                                   
	                               } catch (SQLException ex) {
	                            	   Logger.getLogger(Accueilcontroller.class.getName()).log(Level.SEVERE, null, ex);
	                               }
	                               
	                              
	                               
	                             

	                           });
	                          editIcon.setOnMouseClicked((MouseEvent event) -> {
	                        	  ve =tabTick.getSelectionModel().getSelectedItem();
	                              FXMLLoader loader = new FXMLLoader ();
	                              loader.setLocation(getClass().getResource("/view/Modifiervehiview.fxml"));
	                              
	                             try {
	                                  loader.load();
	                              } catch (IOException ex) {
	                                  Logger.getLogger(Modifiervehicontroller.class.getName()).log(Level.SEVERE, null, ex);
	                              }
	                              
	                             Modifiervehicontroller mod = loader.getController();
	                             mod.setTextField(ve.getImmat(),ve.getMarque(), ve.getDispo(),ve.getKilo(),ve.getPrix());
	                             Parent parent = loader.getRoot();
	                 			
	                             Stage stage = new Stage();
	                             stage.setScene(new Scene(parent));
	                            // stage.initStyle(StageStyle.UTILITY);
	                             stage.show();
	                             
	                             stage.setResizable(false);
	                           });

	                  
	                           HBox managebtn = new HBox(editIcon, deleteIcon);
	                           managebtn.setStyle("-fx-alignment:center");
	                           HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
	                           HBox.setMargin(editIcon, new Insets(2, 2, 0, 2));
	                           setGraphic(managebtn);

	                           setText(null);

	                       }
	                   }

	               };

	               return cell;
	           };
	           
	           
	            operation1.setCellFactory(cellFoctory);
	            tabTick.setItems(listTick); 
	            
	       }


		private void action2() throws SQLException {
	    	connection = ConnexionDB.connect();
	           actureser(null);
	           CniCli.setCellValueFactory(new PropertyValueFactory<clients,String>("cni"));
	           NomCli.setCellValueFactory(new PropertyValueFactory<clients,String>("nomcli"));
	           TelCli.setCellValueFactory(new PropertyValueFactory<clients,Integer>("telcli")); 
	           typeLoc.setCellValueFactory(new PropertyValueFactory<clients,String>("type"));         	
			   datedebut.setCellValueFactory(new PropertyValueFactory<clients,DatePicker>("datedeb"));
			   duree.setCellValueFactory(new PropertyValueFactory<clients,Integer>("duree")); 
	           datefin.setCellValueFactory(new PropertyValueFactory<clients,DatePicker>("datefin"));         	
			   immatri.setCellValueFactory(new PropertyValueFactory<clients,String>("immat"));
			   marque1.setCellValueFactory(new PropertyValueFactory<clients,String>("marque"));
			   prixtot.setCellValueFactory(new PropertyValueFactory<clients,Integer>("prix")); 
	           
	           //BOUTON EDITER ET SUPPRIMER
	            Callback<TableColumn<clients, String>, TableCell<clients, String>> cellFoctory = (TableColumn<clients, String> param) -> {
	               final TableCell<clients, String> cell = new TableCell<clients, String>() {
	                   @Override
	                   public void updateItem(String item, boolean empty) {
	                       super.updateItem(item, empty);
	                       //that cell created only on non-empty rows
	                       if (empty) {
	                           setGraphic(null);
	                           setText(null);

	                       } else {

	                           FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH_ALT);
	                           FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE_ALT);
	                           FontAwesomeIconView printIcon = new FontAwesomeIconView(FontAwesomeIcon.PRINT);
	                           deleteIcon.setStyle(
	                                   " -fx-cursor: hand ;"
	                                   + "-glyph-size:24px;"
	                                   + "-fx-fill:#555555;"
	                                   + "-fx-background-color: #FF0000;"
	                           );
	                           editIcon.setStyle(
	                                   " -fx-cursor: hand ;"
	                                   + "-glyph-size:24px;"
	                                   + "-fx-fill:#555555;"
	                           );
	                           printIcon.setStyle(
	                                   " -fx-cursor: hand ;"
	                                   + "-glyph-size:24px;"
	                                   + "-fx-fill:#555555;"
	                           );
	                           
	                           deleteIcon.setOnMouseClicked((MouseEvent event) -> {
                                   cl = tabTick1.getSelectionModel().getSelectedItem();
	                               
	                               try {
	                              	 Alert alert= new Alert(AlertType.CONFIRMATION);
	                       			alert.setTitle("CONFIRMATION");
	                       			alert.setHeaderText("SUPPRESSION");
	                       			alert.setContentText("VOULEZ VOUS SUPPRIMMER LE CLIENT "+cl.getNomcli() +" ?");
	                       			Optional<ButtonType> rep = alert.showAndWait();	
	                       			if(rep.get()==ButtonType.OK) 	{
	                       				
	                       				query = "Update vehicules SET dispo='OUI' WHERE immat ='"+cl.getImmat()+"'";
		                                connection = ConnexionDB.connect();
		                                preparedStatement= connection.prepareStatement(query);
		                         	   	preparedStatement.execute();
	                              	 
	                              	 
	                                   query = "DELETE FROM clients WHERE cniclient ='"+cl.getCni()+"'";
	                                   connection = ConnexionDB.connect();
	                                   preparedStatement= connection.prepareStatement(query);
	                                   preparedStatement.execute();
	                                   actureser(null);
	                       			}
	                                   
	                               } catch (SQLException ex) {
	                            	   Logger.getLogger(Accueilcontroller.class.getName()).log(Level.SEVERE, null, ex);
	                               }
	                               
	                              
	                               
	                             

	                           });
	                          editIcon.setOnMouseClicked((MouseEvent event) -> {
	                        	  cl =tabTick1.getSelectionModel().getSelectedItem();
	                              FXMLLoader loader = new FXMLLoader ();
	                              loader.setLocation(getClass().getResource("/view/Modifierreserview.fxml"));
	                              
	                             try {
	                                  loader.load();
	                              } catch (IOException ex) {
	                                  Logger.getLogger(Modifierresercontroller.class.getName()).log(Level.SEVERE, null, ex);
	                              }
	                              
	                             Modifierresercontroller mod = loader.getController();
	                             mod.setTextField(cl.getCni(),cl.getNomcli(), cl.getTelcli(),cl.getType(),cl.getDatedeb(),cl.getDuree(),cl.getImmat());
	                             Parent parent = loader.getRoot();
	                 			
	                             Stage stage = new Stage();
	                             stage.setScene(new Scene(parent));
	                            // stage.initStyle(StageStyle.UTILITY);
	                             stage.show();
	                             
	                             stage.setResizable(false);
	                           });
	                          printIcon.setOnMouseClicked((MouseEvent event) -> {
	                        	  cl =tabTick1.getSelectionModel().getSelectedItem();
	                              FXMLLoader loader = new FXMLLoader ();
	                              loader.setLocation(getClass().getResource("/view/Factureview.fxml"));
	                             try {
	                             	
	                       				 loader.load();
	                       			
	                              } catch (IOException ex) {
	                                  Logger.getLogger(Accueilcontroller.class.getName()).log(Level.SEVERE, null, ex);
	                              }
	                              
	                             Facturecontroller fact = loader.getController();
	                            fact.setlab(cl.getCni(),cl.getNomcli(),cl.getTelcli(),cl.getType(),cl.getDatedeb(),cl.getDuree(),cl.getDatefin(),cl.getImmat(),cl.getPrix(),cl.getMarque());
	                              Parent parent = loader.getRoot();
	                              Stage stage = new Stage();
	                              stage.setScene(new Scene(parent));
	                              stage.show();
	                              stage.setResizable(false);
	                          });
	                  
	                           HBox managebtn = new HBox(printIcon,editIcon, deleteIcon);
	                           managebtn.setStyle("-fx-alignment:center");
	                           HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
	                           HBox.setMargin(editIcon, new Insets(2, 2, 0, 2));
	                           HBox.setMargin(printIcon, new Insets(2, 4, 0, 4));
	                           setGraphic(managebtn);

	                           setText(null);

	                       }
	                   }

	               };

	               return cell;
	           };
	           
	           
	            operation2.setCellFactory(cellFoctory);
	            tabTick1.setItems(listTick1); 
	            
	       }

		

		
	    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		user.setText(LoginController.nom().toUpperCase());
		if(LoginController.post()=="CAISSE") {
			vehi.setDisable(true);
			perso.setDisable(true);
			anchorreser.setVisible(true);
		}else {
			anchorreser.setVisible(true);
		}
		combperso.setItems(reuser);
		combreser.setItems(reclients);
		combvehi.setItems(revehi);
		
		try {
			action();
			action1();
			action2();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		query="SELECT photo FROM user WHERE iduser="+LoginController.id();
		try {
			connection = ConnexionDB.connect();
			preparedStatement= connection.prepareStatement(query);
	 	   	resultSet=preparedStatement.executeQuery();
	 	   	while(resultSet.next()) {
	 	   		b= resultSet.getBlob("photo");
	 	   		byteim = b.getBytes(1,(int) b.length());
	 	   	Image image = new Image(new ByteArrayInputStream(byteim), im.getFitWidth(),im.getFitHeight(), true, true);
    		im.setImage(image);
	 	   	}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
