package controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import connexion.ConnexionDB;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

public class LoginController implements Initializable {
	
	
		@FXML
		private ImageView image;
	
		@FXML
	    private JFXTextField user;

	    @FXML
	    private JFXButton login;

	    @FXML
	    private JFXPasswordField password;
	    
	    @FXML
	    private JFXComboBox<String> poste;
	    
	    static String well;
		static String post;
		static int id;
		int count;
		ObservableList<String>pos= FXCollections.observableArrayList("GERANT","CAISSE");
		public void slideshow() {
			ArrayList<Image> images = new ArrayList<Image>();
			images.add(new Image("/images/b1.JPG"));
			images.add(new Image("/images/b2.JPG"));
			images.add(new Image("/images/b3.JPG"));
			images.add(new Image("/images/b4.JPG"));
			
			Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2.5), event ->{
				image.setImage(images.get(count));
				count++;
				if (count ==4) {
					count=0;
				}
			}));
			timeline.setCycleCount(Timeline.INDEFINITE);
			timeline.play();
		}

	    

	    @FXML
	    void login(ActionEvent event) throws SQLException {
	    	well = user.getText().toString();
	    	post=poste.getSelectionModel().getSelectedItem();
	    	Connection conn=ConnexionDB.connect();
	    	
	    	PreparedStatement stat=null;
	    	ResultSet rs=null;
	    	String sql="SELECT * FROM user WHERE Nomuser= ? AND password= ? AND poste=?";
	    	
	    		try {
	    			
	    			
	    			stat=conn.prepareStatement(sql);
	    			stat.setString(1, user.getText().toString());
	    			stat.setString(2, password.getText().toString());
	    			stat.setString(3, poste.getSelectionModel().getSelectedItem().toString());
	    			
	    			rs=stat.executeQuery();
	    			
	    			if(poste.getSelectionModel().getSelectedItem().toString().equals("GERANT")) {
	    			
	    			if(rs.next()) {
	    				
	    				id=rs.getInt(1);
	    				
	    				login.getScene().getWindow().hide();
	    				Stage stage= new Stage();
	    				
	    				FXMLLoader loader= new FXMLLoader();
	    				loader.setLocation(getClass().getResource("/view/Accueilview.fxml"));
	    				
	    			Parent	main=loader.load();
	    				
	    				Scene scene= new Scene(main);
	    				stage.setScene(scene);
	    				
	    				stage.show();
	    				stage.setResizable(false);
	    				
	    				Image img = new Image("/images/icons.png");
	    				stage.getIcons().add(img);
	    				stage.setTitle("CAR LOCATION");
	    				
	    				
	    				

	    				//connexion.getScene().getWindow().hide();
	    				
	    				
	    			}else {
	    				
	    				
	    				
	    				Alert alert= new Alert(AlertType.ERROR);
	    				alert.setTitle("Alert");
	    				alert.setHeaderText("ERREUR");
	    				alert.setContentText("MOT DE PASSE OU LOGIN INCORRECTE");
	    				alert.showAndWait();
	    				user.clear();
	    				password.clear();
	    				
	    			}
	    			}else if(poste.getSelectionModel().getSelectedItem().toString().equals("CAISSE")) {
	    				if(rs.next()) {
	    			    	
	    					id=rs.getInt(1);
	        				login.getScene().getWindow().hide();
	        				Stage stage= new Stage();
	        				
	        				FXMLLoader loader= new FXMLLoader();
	        				loader.setLocation(getClass().getResource("/view/Accueilview.fxml"));
	        			Parent	main=loader.load();
	        				
	        				Scene scene= new Scene(main);
	        				stage.setScene(scene);
	        				
	        				stage.show();
	        				stage.setResizable(false);
	        				Image img = new Image("/images/icons.png");
	        				stage.getIcons().add(img);
	        				stage.setTitle("CAR LOCATION");
	        					//connexion.getScene().getWindow().hide();
	        				
	        				
	        			}else {
	        				
	        				
	        				Alert alert= new Alert(AlertType.ERROR);
	        				alert.setTitle("Alert");
	        				alert.setHeaderText("ERREUR");
	        				alert.setContentText("MOT DE PASSE OU LOGIN INCORRECTE");
	        				alert.showAndWait();
	        				user.clear();
		    				password.clear();
	        				
	        			}
	    			}else {
	    				Alert alert= new Alert(AlertType.ERROR);
	    				alert.setTitle("Alert");
	    				alert.setHeaderText("Erreur");
	    				alert.setContentText("VEUILLEZ CHOISIR LE POSTE ENTRE GERANT ET CAISSE");
	    				alert.showAndWait();
	    				
	    			}
	    			
	    			
	    			
	    			
	    		}catch (Exception e) {
	    			// TODO: handle exception
	    			
	    		}
	    	

	    }

	    public static String nom(){
	    	
			return well;
	    	
	    }
	    public static String post() {
	    	return post;
	    }
	    public static int id() {
	    	return id;
	    }
	    
	    @FXML
	    void connexion(MouseEvent event) {

	    }
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		 poste.setItems(pos);
		 slideshow();
	}

}
