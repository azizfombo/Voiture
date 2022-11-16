package modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class vehicule {
	final private StringProperty immat;
	final private StringProperty marque;
	final private StringProperty dispo;
	final private IntegerProperty kilo;
	final private IntegerProperty prix;
	// Constructeur par défaut
	public vehicule() {
		super();
		this.immat = new SimpleStringProperty();
		this.marque = new SimpleStringProperty();
		this.dispo = new SimpleStringProperty();
		this.kilo = new SimpleIntegerProperty();
		this.prix =new SimpleIntegerProperty();
	}
	//Constructeur d'instanciation
	public vehicule(String immat, String marque,String dispo,Integer kilo,Integer prix) {
		super();
		this.immat = new SimpleStringProperty(immat);
		this.marque = new SimpleStringProperty(marque);
		this.dispo = new SimpleStringProperty(dispo);
		this.kilo = new SimpleIntegerProperty(kilo);
		this.prix =new SimpleIntegerProperty(prix);
	}
	public final StringProperty immatProperty() {
		return this.immat;
	}
	
	public final String getImmat() {
		return this.immatProperty().get();
	}
	
	public final void setImmat(final String immat) {
		this.immatProperty().set(immat);
	}
	
	public final StringProperty marqueProperty() {
		return this.marque;
	}
	
	public final String getMarque() {
		return this.marqueProperty().get();
	}
	
	public final void setMarque(final String marque) {
		this.marqueProperty().set(marque);
	}
	
	public final StringProperty dispoProperty() {
		return this.dispo;
	}
	
	public final String getDispo() {
		return this.dispoProperty().get();
	}
	
	public final void setDispo(final String dispo) {
		this.dispoProperty().set(dispo);
	}
	
	public final IntegerProperty kiloProperty() {
		return this.kilo;
	}
	
	public final int getKilo() {
		return this.kiloProperty().get();
	}
	
	public final void setKilo(final int kilo) {
		this.kiloProperty().set(kilo);
	}
	
	public final IntegerProperty prixProperty() {
		return this.prix;
	}
	
	public final int getPrix() {
		return this.prixProperty().get();
	}
	
	public final void setPrix(final int prix) {
		this.prixProperty().set(prix);
	}
	
	
	
}
