import javax.swing.*;
import java.awt.*;

public class Reservation {

	private int idReservation;
	private String reference;
	private String debut;
	private int nuits;
	private int categorie;
	private String prenom;
	private String nom;

	public Reservation(int idReservation, String reference, String debut, int nuits, int categorie, String prenom, String nom) {
		this.idReservation = idReservation;
		this.reference = reference;
		this.debut = debut;
		this.nuits = nuits;
		this.categorie = categorie;
		this.prenom = prenom;
		this.nom = nom;
	}

	public String toString() {
		return "\nReference : "+this.reference+"\n\n";
	
	}

	public int getIdReservation() {
		return idReservation;
	}

	public void setIdReservation(int idReservation) {
		this.idReservation = idReservation;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getDebut() {
		return debut;
	}

	public void setDebut(String debut) {
		this.debut = debut;
	}

	public int getNuits() {
		return nuits;
	}

	public void setNuits(int nuits) {
		this.nuits = nuits;
	}

	public int getCategorie() {
		return categorie;
	}

	public void setCategorie(int categorie) {
		this.categorie = categorie;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
}