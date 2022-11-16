package modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class user {
	final private IntegerProperty iduser;
	final private StringProperty nomuser;
	final private StringProperty password;
	final private StringProperty poste;
	final private IntegerProperty telephone;
	// Constructeur par défaut
	public user() {
		super();
		this.iduser =new SimpleIntegerProperty();
		this.nomuser = new SimpleStringProperty();
		this.password = new SimpleStringProperty();
		this.poste = new SimpleStringProperty();
		this.telephone = new SimpleIntegerProperty();
	}
	//Constructeur d'instanciation
	public user(Integer iduser, String nomuser, String password,
			String poste, Integer telephone) {
		super();
		this.iduser =new SimpleIntegerProperty(iduser);
		this.nomuser = new SimpleStringProperty(nomuser);
		this.password = new SimpleStringProperty(password);
		this.poste = new SimpleStringProperty(poste);
		this.telephone = new SimpleIntegerProperty(telephone);
	}
	public final IntegerProperty iduserProperty() {
		return this.iduser;
	}
	
	public final int getIduser() {
		return this.iduserProperty().get();
	}
	
	public final void setIduser(final int iduser) {
		this.iduserProperty().set(iduser);
	}
	
	public final StringProperty nomuserProperty() {
		return this.nomuser;
	}
	
	public final String getNomuser() {
		return this.nomuserProperty().get();
	}
	
	public final void setNomuser(final String nomuser) {
		this.nomuserProperty().set(nomuser);
	}
	
	public final StringProperty passwordProperty() {
		return this.password;
	}
	
	public final String getPassword() {
		return this.passwordProperty().get();
	}
	
	public final void setPassword(final String password) {
		this.passwordProperty().set(password);
	}
	
	public final StringProperty posteProperty() {
		return this.poste;
	}
	
	public final String getPoste() {
		return this.posteProperty().get();
	}
	
	public final void setPoste(final String poste) {
		this.posteProperty().set(poste);
	}
	
	public final IntegerProperty telephoneProperty() {
		return this.telephone;
	}
	
	public final int getTelephone() {
		return this.telephoneProperty().get();
	}
	
	public final void setTelephone(final int telephone) {
		this.telephoneProperty().set(telephone);
	}
	
}
