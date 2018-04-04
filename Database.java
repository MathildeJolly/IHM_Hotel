import java.sql.*;
import java.util.*;

public class Database {
	private Connection connection;
	private Connection connection2;
	private ArrayList<Reservation> reservation = new ArrayList<Reservation>();

	public Database() {
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			try {
				this.connection = DriverManager.getConnection("jdbc:mariadb://dwarves.iut-fbleau.fr/projetihm","projetihm","mhitejorp");
			} catch(SQLException e){
				System.err.println("Probleme de statement connexion ProjetIHL");
			}
		} catch(ClassNotFoundException e){
			System.err.println("Probleme de classe  connexion ProjetIHL");
		}
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			try {
				this.connection2 = DriverManager.getConnection("jdbc:mariadb://dwarves.iut-fbleau.fr/jolly","jolly","mdp");
			} catch(SQLException e){
				System.err.println("Probleme de statement connexion Jolly");
			}
		} catch(ClassNotFoundException e){
			System.err.println("Probleme de classe  connexion Jolly");
		}
	}

	public boolean checkUser(String client) {

		PreparedStatement requete;
		ResultSet result = null;
		Boolean res = false;
		this.reservation = new ArrayList<Reservation>();

		try {
			requete = this.connection.prepareStatement("SELECT * FROM Reservation JOIN Client where  ( CONCAT(prenom, ' ', nom) = '" +
				client.trim()+"' OR CONCAT(nom, ' ', prenom) = '" +
				client.trim()+"' ) and Client.id = Reservation.client");
				result = requete.executeQuery(); 

			while(result.next()) {
				res = true;

				int idReservation = result.getInt("Reservation.id");
				String ref = result.getString("Reservation.reference");
				String debut = result.getString("debut");
				int nuits = result.getInt("Reservation.nuits");
				int categorie = result.getInt("Reservation.categorie");
				String prenom = result.getString("prenom");
				String nom = result.getString("nom");

				Reservation reservation = new Reservation(idReservation, ref, debut, nuits, categorie, prenom, nom);
				this.reservation.add(reservation);
			}
		} catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return res;
	}

	public boolean checkRef(String client) {
		PreparedStatement requete;
		ResultSet result = null;
		Boolean res = false;
		this.reservation = new ArrayList<Reservation>();

		try {
			requete = this.connection.prepareStatement("SELECT * FROM Reservation JOIN Client where reference = '" +
				client.trim()+"' and Client.id = Reservation.client");

			result = requete.executeQuery(); 

			if(result.next()) {
				res = true;

				int idReservation = result.getInt("Reservation.id");
				String ref = result.getString("Reservation.reference");
				String debut = result.getString("debut");
				int nuits = result.getInt("Reservation.nuits");
				int categorie = result.getInt("Reservation.categorie");
				String prenom = result.getString("prenom");
				String nom = result.getString("nom");

				Reservation reservation = new Reservation(idReservation, ref, debut, nuits, categorie, prenom, nom);
				this.reservation.add(reservation);

			}
			
		} catch(SQLException e) {
			System.out.println(e.getMessage()+"Pb de ref ou d'ID client");
		}
		return res;
	}

	public boolean refDone(String client) {

		PreparedStatement requete;
		ResultSet result = null;
		Boolean res = false;

		try {
			requete = this.connection2.prepareStatement("SELECT * FROM ChambreReservee where referenceReservation = '" +
				client.trim()+"'");

			result = requete.executeQuery(); 

			if(result.next()) {
				res = true;
			}
			
		} catch(SQLException e) {
			System.out.println(e.getMessage()+"SELECT * FROM ChambreReservee where referenceReservation = "+client);
		}
		return res;
	}


	public boolean showChambre(String client) {
		PreparedStatement requete;
		ResultSet result = null;
		Boolean res = false;

		try {
			requete = this.connection.prepareStatement("SELECT reference, nom, prenom FROM Reservation JOIN Client where reference = '" +
				client.trim()+"'");

			result = requete.executeQuery(); 

			if(result.first()) {
				res = true;
			}
			
		} catch(SQLException e) {
			System.out.println(e.getMessage()+"");
		}
		return res;
	}

	public ArrayList<Reservation> getReservation() {
		return this.reservation;
	}

	public Reservation getReservation(String reference) {
		return this.reservation.get(0);
	}
	
	
	public List<Chambre> getChambreByCategorie(int categorie, Reservation singleRes) {

		PreparedStatement requete;
		ResultSet result = null;
		List<Chambre> chambres= new ArrayList<Chambre>();

		try {
			
			String req = "SELECT * FROM Chambre where categorie="+categorie+" and Chambre.num NOT " +
					"IN ( SELECT c.num FROM ChambreReservee cr , Chambre c WHERE c.num = cr.idChambre " +
					"and '"+singleRes.getDebut()+"' >= DATE(cr.date_debut) and '"+singleRes.getDebut()+"' <= " +
					"DATE_ADD(cr.date_debut, INTERVAL cr.nbr_jour DAY) )";

			requete = this.connection2.prepareStatement(req);
			result = requete.executeQuery(); 

			while(result.next()) {

					int id = result.getInt("num");				

					Chambre chambre = new Chambre(id,categorie);
					chambres.add(chambre);
					System.out.println("Numero chambre dispo : " + chambre.getIdChambre());
				}
		} catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return chambres;
	}
	
	public void choisirChambre(Chambre chambre, Reservation singleRes) {
		PreparedStatement requete;
		ResultSet result = null;
		
		try {
			requete = this.connection2.prepareStatement("INSERT INTO ChambreReservee (idChambre, referenceReservation, date_debut , nbr_jour) " +
					"VALUES ("+chambre.getIdChambre()+",'"+singleRes.getReference()+"','"+singleRes.getDebut()+"',"+singleRes.getNuits()+")");
			result = requete.executeQuery(); 

		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
	}
}
