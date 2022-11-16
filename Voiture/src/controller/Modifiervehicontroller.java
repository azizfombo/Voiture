package controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import dao.Gestionvehidao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import modele.vehicule;

public class Modifiervehicontroller implements Initializable {
	
	
	  @FXML
	    private JFXButton enreg;

	    @FXML
	    private JFXTextField kilo;

	    @FXML
	    private JFXTextField immat;

	    @FXML
	    private JFXTextField marque;

	    @FXML
	    private JFXComboBox<String> dispo;

	    @FXML
	    private JFXTextField prix;

	    ObservableList<String>disp=FXCollections.observableArrayList("OUI","NON");
	    String imm=null;	    
	    @FXML
	    void Enregitrer(ActionEvent event) throws SQLException {
	    	
	    	String imma = immat.getText();
	    	String marq = marque.getText();
	    	String kill = kilo.getText();
          int kil =Integer.parseInt(kill);	 
          String pri = prix.getText();
          int pr =Integer.parseInt(pri);	 
          SingleSelectionModel<String> dis=dispo.getSelectionModel();
          String di=dis.getSelectedItem();
          
          if(imma.isEmpty() && marq.isEmpty() && di.isEmpty()) {
        	   

  			Alert alert= new Alert(AlertType.INFORMATION);
  			alert.setTitle("INFORMATION");
  			alert.setHeaderText("MODIFIACTION VEHICULES");
  			alert.setContentText("ENTREZ TOUS LES CHAMPS");
  			alert.showAndWait();	

  		}else {
  		vehicule ve = new vehicule();
  		ve.setImmat(imma);
    	ve.setMarque(marq);
    	ve.setDispo(di);
    	ve.setKilo(kil);
    	ve.setPrix(pr);
  		int status =Gestionvehidao.Modifiervehi(ve,imm);
  		if(status>0) {

  			Alert alert= new Alert(AlertType.INFORMATION);
  			alert.setTitle("INFORMATION");
  			alert.setHeaderText("MODIFICATION VEHICULE");
  			alert.setContentText("VEHICULE MODIFIE");
  			alert.showAndWait();

  			immat.clear();
  	    	marque.clear();
  	    	kilo.clear();
  	    	prix.clear();
  	    	dispo.getSelectionModel().clearSelection();
  	    	
  	    	
  	    	
  	    	Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
  	        stage.close();
  		}else {

  			Alert alert= new Alert(AlertType.INFORMATION);
  			alert.setTitle("INFORMATION");
  			alert.setHeaderText("MODIFICATION VEHICULE");
  			alert.setContentText("VEHICULE NON MODIFIE");
  			alert.showAndWait();	
  		}
  		}

	    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		dispo.setItems(disp);
	}
	
	public void setTextField(String imma, String marq,  String disp , int kil, int pri) {
		// TODO Auto-generated method stub
		
		
	      immat.setText(imma);
	      marque.setText(marq);
	      dispo.setValue(disp);
	      kilo.setText(String.valueOf(kil));
	      prix.setText(String.valueOf(pri));
	      imm=imma;
		
		
	}

}
