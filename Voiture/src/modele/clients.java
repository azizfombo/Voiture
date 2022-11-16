package modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class clients {

	final private StringProperty cni;
	final private StringProperty nomcli;
	final private IntegerProperty telcli;
	final private StringProperty type;
	final private ObjectProperty<String> datedeb;
	final private IntegerProperty duree;
	final private ObjectProperty<String> datefin;
	final private StringProperty immat;
	final private StringProperty marque;
	final private IntegerProperty prix;
	
	
	public clients() {
		super();
		this.cni = new SimpleStringProperty();
		this.nomcli = new SimpleStringProperty ();
		this.telcli = new SimpleIntegerProperty();
		this.type = new SimpleStringProperty ();
		this.datedeb = new SimpleObjectProperty<String> ();
		this.duree = new SimpleIntegerProperty();
		this.datefin = new SimpleObjectProperty<String> ();
		this.immat = new SimpleStringProperty ();
		this.marque = new SimpleStringProperty ();
		this.prix = new SimpleIntegerProperty();
	}
	
	public clients(String cni, String nomcli, int telcli, String type,
			String datedeb, int duree, String datefin, String immat,
			int prix,String marque) {
		super();
		this.cni = new SimpleStringProperty(cni);
		this.nomcli = new SimpleStringProperty (nomcli);
		this.telcli = new SimpleIntegerProperty(telcli);
		this.type = new SimpleStringProperty (type);
		this.datedeb = new SimpleObjectProperty<String> (datedeb);
		this.duree = new SimpleIntegerProperty(duree);
		this.datefin = new SimpleObjectProperty<String> (datefin);
		this.immat = new SimpleStringProperty (immat);
		this.marque = new SimpleStringProperty (marque);
		this.prix = new SimpleIntegerProperty(prix);
	}

	public final StringProperty cniProperty() {
		return this.cni;
	}
	

	public final String getCni() {
		return this.cniProperty().get();
	}
	

	public final void setCni(final String cni) {
		this.cniProperty().set(cni);
	}
	

	public final StringProperty nomcliProperty() {
		return this.nomcli;
	}
	

	public final String getNomcli() {
		return this.nomcliProperty().get();
	}
	

	public final void setNomcli(final String nomcli) {
		this.nomcliProperty().set(nomcli);
	}
	

	public final IntegerProperty telcliProperty() {
		return this.telcli;
	}
	

	public final int getTelcli() {
		return this.telcliProperty().get();
	}
	

	public final void setTelcli(final int telcli) {
		this.telcliProperty().set(telcli);
	}
	

	public final StringProperty typeProperty() {
		return this.type;
	}
	

	public final String getType() {
		return this.typeProperty().get();
	}
	

	public final void setType(final String type) {
		this.typeProperty().set(type);
	}
	

	public final ObjectProperty<String> datedebProperty() {
		return this.datedeb;
	}
	

	public final String getDatedeb() {
		return this.datedebProperty().get();
	}
	

	public final void setDatedeb(final String datedeb) {
		this.datedebProperty().set(datedeb);
	}
	

	public final IntegerProperty dureeProperty() {
		return this.duree;
	}
	

	public final int getDuree() {
		return this.dureeProperty().get();
	}
	

	public final void setDuree(final int duree) {
		this.dureeProperty().set(duree);
	}
	

	public final ObjectProperty<String> datefinProperty() {
		return this.datefin;
	}
	

	public final String getDatefin() {
		return this.datefinProperty().get();
	}
	

	public final void setDatefin(final String datefin) {
		this.datefinProperty().set(datefin);
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
