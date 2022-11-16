package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import connexion.ConnexionDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

public class Modifierusercontroller implements Initializable {
	
	 	@FXML
	    private JFXTextField texparc;

	 	@FXML
	    private JFXButton enreg;

	    @FXML
	    private JFXTextField telephone;

	    @FXML
	    private JFXTextField nomuser;

	    @FXML
	    private JFXPasswordField password;

	    @FXML
	    private JFXComboBox<String> poste;
	    
	    @FXML
	    private ImageView im;
	    private FileInputStream fs;
	    
	    ObservableList<String>po=FXCollections.observableArrayList("GERANT","CAISSE");
	    int id=0;

	    @FXML
	    void Enregitrer(ActionEvent event) throws SQLException {
	    	
	    	
	    	String nomus = nomuser.getText();
	    	String pass = password.getText();
	    	String tel = telephone.getText();
          int telep=Integer.parseInt(tel);	 
          SingleSelectionModel<String> pos=poste.getSelectionModel();
          String po=pos.getSelectedItem();
          
          if(nomus.isEmpty() && pass.isEmpty() && po.isEmpty() && tel.isEmpty()) {
        	   

  			Alert alert= new Alert(AlertType.INFORMATION);
  			alert.setTitle("INFORMATION");
  			alert.setHeaderText("MODIFIACTION UTILISATEUR");
  			alert.setContentText("ENTREZ TOUS LES CHAMPS");
  			alert.showAndWait();	

  		}else {
  			File image = new File(texparc.getText());
  	  		Connection con=ConnexionDB.connect();
  	  		PreparedStatement stat=null;
  	  		String sql="UPDATE user SET Nomuser=?, password=?, poste=?, telephone=?, photo=? WHERE iduser=?";
  	  		try {
  	  			stat=con.prepareStatement(sql);
  	  			stat.setString(1,nomus);
  	  			stat.setString(2,pass);
  	  			stat.setString(3,po);
  	  			stat.setInt(4,telep);
  	  			fs = new FileInputStream(image);
  	  			stat.setBinaryStream(5, fs, image.length());
  	  			stat.setInt(6, id);

  	  		}
  	  		catch(SQLException e){	
  	  			e.printStackTrace();
  	  		} catch (FileNotFoundException e) {
  				// TODO Auto-generated catch block
  	  			e.printStackTrace();
  			}


  	  		if(stat.executeUpdate()>0) {

  	  			Alert alert= new Alert(AlertType.INFORMATION);
  	  			alert.setTitle("INFORMATION");
  	  			alert.setHeaderText("AJOUT UTILISATEUR");
  	  			alert.setContentText("ULTILISATEUR AJOUTE");
  	  			alert.showAndWait();

  	  			nomuser.clear();
  	  	    	password.clear();
  	  	    	telephone.clear();
  	  	    	poste.getSelectionModel().clearSelection();
  	  	    	
  	  	    	
  	  	    	Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
  	  	        stage.close();
  	  	        
  	  		}else {

  	  			Alert alert= new Alert(AlertType.INFORMATION);
  	  			alert.setTitle("INFORMATION");
  	  			alert.setHeaderText("AJOUT UTILISATEUR");
  	  			alert.setContentText("ULTILSATEUR NON AJOUTE");
  	  			alert.showAndWait();	
  	  			
  	  			nomuser.clear();
  	  	    	password.clear();
  	  	    	telephone.clear();
  	  	    	poste.getSelectionModel().clearSelection();


  	  		}
  	  		}
	    }
	    
	    
	    @FXML
	    void parcourir(ActionEvent event) {
	    	FileChooser fc = new FileChooser();
	    	fc.getExtensionFilters().add(new ExtensionFilter("Image Files", "*.png","*.jpg"));
	    	File f = fc.showOpenDialog(null);
	    	if(f != null) {
	    		texparc.setText(f.getAbsolutePath());
	    		Image image = new Image(f.toURI().toString(),im.getFitWidth(),im.getFitHeight(), true, true);
	    		im.setImage(image);
	    	}
	    }
	    

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		poste.setItems(po);
		
		
		

	}
     
	public void setTextField(int idd, String nomus, String pass,  String pos , int tel) {
		// TODO Auto-generated method stub
		
		
	      nomuser.setText(nomus);
	      telephone.setText(String.valueOf(tel));
	     password.setText(pass);
	      poste.setValue(pos);
	      id=idd;
		
	}


}
