import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

public class Controleur implements ActionListener  {
	
	private JTextField text;
	private JPanel panneau;
	private Database db = new Database();
	private JTextArea liste;
	private Fenetre fenetre;
	private JOptionPane popup = new JOptionPane();
	private Reservation singleRes ;
	private JLabel chambrepropose;
	private Chambre chambre;
	private List<Chambre> chambres;    
	private JTable tableau;
	private JPanel propositionChambre;
	private JFrame frame;
	private JLabel reservationClient;

	public Controleur(JTextField text, JPanel panneau,JTextArea jlabel, JLabel jLabel2, JLabel reservationClient , Fenetre fenetre) {
		this.text = text;
		this.panneau = panneau;
		this.liste=jlabel;
		this.chambrepropose=jLabel2;
		this.reservationClient = reservationClient;
		this.fenetre = fenetre;
	}

	public Controleur(JTable tableau, JPanel propositionChambre, JFrame frame) {
		this.tableau = tableau;
		this.propositionChambre = propositionChambre;
		this.frame = frame;
	}

	public Controleur(Fenetre fenetre) {
		this.fenetre = fenetre;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getActionCommand().equals("VALIDER")) {

			this.liste.setText(null);
			System.out.println(this.liste.getText());

			ArrayList<Reservation> reservation = null;
			String clientOrRef = text.getText();

			if(clientOrRef.matches(".*\\d+.*")) { // Si il y a des numéros c'est que c'est une réference

           		if(db.checkRef(clientOrRef)) { // Si la reference existe et qu'aucune chambre n'a été attribuéé, on affiche le panneau	
           				
           				if (db.refDone(clientOrRef) == true)
           					popup.showMessageDialog(panneau, "L'attribution de chambre a d\u00e9jà \u00e9t\u00e9 faite", "Reservation introuvable", JOptionPane.INFORMATION_MESSAGE);
           				else {
							reservation = db.getReservation(); 
		           			singleRes = reservation.get(0); 
		           			panneau.setVisible(true);
		           			this.reservationClient.setText("\n Réservation de "+ singleRes.getPrenom()+" "+singleRes.getNom()+"\n");
           				}
           		
           		} else {

           			panneau.setVisible(false);
           			popup.showMessageDialog(panneau, "Le numero de r\u00e9servation est introuvable", "Reservation introuvable", JOptionPane.INFORMATION_MESSAGE);
           		
           		}

           	} else {

	            if(db.checkUser(clientOrRef)) { // Si le client existe, on affiche le panneau

	            	reservation = db.getReservation();
	            	this.reservationClient.setText("\n"+ reservation.toString().replace("["," ").replace(","," ").replace("]"," ")+"\n");
	            	panneau.setVisible(true);

	            	if(reservation.size()>1){

	            		afficherPopUp(null);

	            	} else {

	            		singleRes = reservation.get(0);
	            	}

	            } else {

	            	panneau.setVisible(false);
	            	popup.showMessageDialog(panneau, "Le client est introuvable", "Client introuvable", JOptionPane.INFORMATION_MESSAGE);
	            
	            }
	        }

	        if(singleRes  != null ){

	        	chambres = db.getChambreByCategorie(singleRes.getCategorie(),singleRes);
	        	chambre = chambres.get(0);
	        	this.chambrepropose.setText("Chambre proposee : "+chambre.getIdChambre());

	        }

	    } else if(e.getActionCommand().equals("Quitter")) {

	    	System.exit(0);

	    } else if(e.getActionCommand().equals("CHOISIR CETTE CHAMBRE")) {

	    	try {

	    		db.choisirChambre(chambre,singleRes);
	    		popup.showMessageDialog(panneau, "La chambre numero "+chambre.getIdChambre()+" a bien \u00e9t\u00e9 r\u00e9serv\u00e9e ","Reservation", JOptionPane.INFORMATION_MESSAGE);
	    		chambrepropose.setText("");
	    		String clientOrRef= " ";
	    		fenetre.dispose();
	    		new Fenetre();

	    	} catch (NullPointerException e1) {
	    		System.out.println("Erreur pour choisir cette chambre : " +e1);
	    	}

	    }

	    else if(e.getActionCommand().equals("CHANGER DE CHAMBRE")) {

	    	try {

	    		new ChambreDispo(chambres,db,singleRes,this);

	    	} catch (NullPointerException e1) {
	    		System.out.println("Erreur pour changer de chambre " +e1);
	    	}

	    } if (e.getActionCommand().equals("CHOISIR UNE AUTRE CHAMBRE")) {

	    	try {
	    		
	    		int selected = tableau.getSelectedRow();
	    		System.out.println(selected);
	    		System.out.println(singleRes);
	    		db.choisirChambre(chambres.get(selected), singleRes);
	    		popup.showMessageDialog(propositionChambre, "La chambre num\u00e9ro "+chambres.get(selected).getIdChambre()+" a \u00e9t\u00e9 r\u00e9serv\u00e9e ","R\u00e9servation", JOptionPane.INFORMATION_MESSAGE);
	    		frame.dispose();
	    		fenetre.dispose();
	    		new Fenetre();
	    		
	    	} catch(NullPointerException e1) {
	    		System.out.println("Erreur pour choisir cette chambre " +e1);
	    	}
	    }
	}

	private void afficherPopUp(String chaine) {
		this.chambrepropose.setText("");
		String ref = JOptionPane.showInputDialog(chaine == null ? "Entrer une r\u00e9ference :\n" : chaine);

		if(ref != null) {

			if(db.checkRef(ref)){

				if(db.refDone(ref) == true) {
					popup.showMessageDialog(panneau, "L'attribution de chambre a d\u00e9jà \u00e9t\u00e9 faite", "Reservation introuvable", JOptionPane.INFORMATION_MESSAGE);
					} else {
						singleRes = db.getReservation(ref);
						System.out.println(singleRes);
					} 
				} else {
				afficherPopUp("Le numero de réservation est introuvable \nEntrez une nouvelle référence") ;
			}
		}
	}


	public JTable getTableau() {
		return tableau;
	}

	public void setTableau(JTable tableau) {
		this.tableau = tableau;
	}

	public JPanel getPropositionChambre() {
		return propositionChambre;
	}

	public void setPropositionChambre(JPanel propositionChambre) {
		this.propositionChambre = propositionChambre;
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
}
