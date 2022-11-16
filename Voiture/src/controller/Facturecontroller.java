package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.print.Printer.MarginType;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Facturecontroller implements Initializable {
	
	
	@FXML
    private AnchorPane anchor;

    @FXML
    private Button print;

    @FXML
    private Label destina;

    @FXML
    private Label datedeb;

    @FXML
    private Label prix;

    @FXML
    private Label marque;

    @FXML
    private Label type;

    @FXML
    private Label nom;

    @FXML
    private Label cni;

    @FXML
    private Label tel;

    @FXML
    private Label duree;

    @FXML
    private Label datefin;

    @FXML
    private Label immat;

    @FXML
    void print(ActionEvent event) {
    	
    	print.setVisible(false);
    	doprintSecond(anchor);
    	
    	
    	Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
    	
    }
	
	
	

    void doprintSecond(Node anchor) {
    	PrinterJob job= PrinterJob.createPrinterJob();
    	Printer printer= Printer.getDefaultPrinter();
    	PageLayout pagelayout=printer.createPageLayout(Paper.A3, PageOrientation.LANDSCAPE,MarginType.HARDWARE_MINIMUM);
    	
    	
    	if (job!=null && job.showPrintDialog(anchor.getScene().getWindow())) {
    		boolean printed =job.printPage(pagelayout,anchor);
    		if(printed) {
    			job.endJob();
    		}else {
    			Alert alert= new Alert(AlertType.CONFIRMATION);
    			alert.setTitle("Confirmation");
    			alert.setHeaderText("Impréssion");
    			alert.setContentText("Impréssion raté");	
    		}
    	}else {
    		Alert alert= new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Confirmation");
			alert.setHeaderText("Impréssion");
			alert.setContentText("Impréssion Réussie");
    	}
    	
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}
	
	public void setlab(String cn,String nomc,int telc, String typec,String datedec,int dur,
			String datef,String imma,int pri,String marq) {
		cni.setText(cn);
		nom.setText(nomc);
		tel.setText(String.valueOf(telc));
		type.setText(typec);
		datedeb.setText(datedec);
		duree.setText(String.valueOf(dur)+"  JOURS");
		datefin.setText(datef);
		immat.setText(imma);
		prix.setText(String.valueOf(pri));
		marque.setText(marq);
	
		
	}

}
