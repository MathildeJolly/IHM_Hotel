import java.awt.*;
import javax.swing.*;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AllocationVue extends JPanel {
	private Fenetre fenetre;
	private JTextField client = new JTextField(" ");
	private JPanel panneau1 = new JPanel(); // partie recherche
	private JPanel panneau2 = new JPanel(); // partie qui apparait après la recherche
	private JPanel englobe = new JPanel(); // contient panneaux 1, 2
	private JPanel pquitter = new JPanel();
	private JButton bquitter = new JButton("Quitter");
	private JPanel tout = new JPanel(); // contient englobe et bouton quitter
	private JTextArea reservation = new JTextArea();
	private JLabel chambreProposee;
	private JLabel clientReference;

	public AllocationVue(Fenetre fenetre2) {

		panneau1.setLayout(new GridBagLayout()); // gestionnaire de mise en page
		panneau2.setLayout(new GridBagLayout()); // gestionnaire de mise en page
		englobe.setLayout(new BorderLayout());	// contiendra les deux panneaux
		pquitter.setLayout(new BorderLayout()); // contient bouton quitter
		tout.setLayout(new BorderLayout());	// contiendra tout
		chambreProposee = new JLabel("Chambre propos\u00e9e: ");
		clientReference = new JLabel("Nom et Prénom du client : ");

		bquitter.setForeground(new Color(255,255,255));
		bquitter.setBackground(new Color(174,40,60));
		bquitter.setPreferredSize(new Dimension(100,25));

		reservation.setEditable(false);
		fenetre = fenetre2;
		Controleur event = new Controleur(client, panneau2,reservation, chambreProposee, clientReference,fenetre); // pour visible(true) futur
		this.setBackground(new Color(209, 224, 224));

		Controleur quit = new Controleur(fenetre);

////////////////////// MISE EN PAGE ////////////////////////////////////////////////
		GridBagConstraints c1 = new GridBagConstraints(); // Nom du client ou numéro de réservation
		c1.gridx=3;
		c1.gridy=0;
		c1.gridwidth=5;
		c1.gridheight=1;
		c1.ipady=30;
		c1.fill=GridBagConstraints.NORTH;
		c1.insets=new Insets(1,1,1,1);
		JLabel label = new JLabel("Nom du client ou numéro de r\u00e9servation");
		panneau1.add((label), c1);
		Font font1 = new Font("Arial",Font.BOLD,20);
		label.setFont(font1);

		GridBagConstraints c3 = new GridBagConstraints(); // JTextField pour nom client/reservation 
		c3.gridx=1;
		c3.gridy=4;
		c3.gridwidth=GridBagConstraints.CENTER;
		c3.gridheight=2;
		c3.ipady=30;
		c3.fill=GridBagConstraints.HORIZONTAL;
		c3.insets = new Insets(1,1,1,1);
		Font font2 = new Font("Arial", Font.PLAIN, 40);
		client.setFont(font2);
		panneau1.add(client, c3);

		GridBagConstraints c4 = new GridBagConstraints(); // VALIDER
		c4.gridx=1;
		c4.gridy=6;
		c4.ipady=20;
		c4.gridwidth=GridBagConstraints.CENTER;
		c4.gridheight=2;
		c4.fill=GridBagConstraints.HORIZONTAL;
		c4.insets = new Insets(10,1,1,1);
		JButton valider = new JButton("VALIDER");
		valider.setForeground(new Color(255, 255, 255));
		valider.setBackground(new Color(36, 143, 36));
		panneau1.add(valider, c4);
		valider.addActionListener(event);
		panneau2.add(clientReference,c4);
		panneau2.add(reservation, c4);

//////////////////////////////////////////////////////////////////////////////////////////////////
		panneau2.setVisible(false); // est invisible jusqu'à valider nom client
////////////////////// MISE EN PAGE ////////////////////////////////////////////////

		GridBagConstraints c5 = new GridBagConstraints(); // Chambre réservée
		c5.gridx=3;
		c5.gridy=8;
		c5.fill=GridBagConstraints.NONE;
		c5.insets=new Insets(40,1,10,1);
		panneau2.add(chambreProposee, c5);

		GridBagConstraints c6 = new GridBagConstraints();  // CHOISIR CETTE CHAMBRE
		c6.gridx=3;
		c6.gridy=9;
		c6.fill=GridBagConstraints.NONE;
		c6.insets=new Insets(1,1,1,1);
		JButton choisir = new JButton("CHOISIR CETTE CHAMBRE");
		choisir.setForeground(new Color(255, 255, 255));
		choisir.setBackground(new Color(36, 143, 36));
		panneau2.add(choisir, c6);
		choisir.addActionListener(event);
		
		
		GridBagConstraints c7 = new GridBagConstraints(); // CHANGER DE CHAMBRE
		c7.gridx=4;
		c7.gridy=9;
		c7.fill=GridBagConstraints.NONE;
		c7.insets=new Insets(1,100,1,1);
		JButton changer = new JButton("CHANGER DE CHAMBRE");
		changer.setForeground(new Color(255, 255, 255));
		changer.setBackground(new Color(153, 0, 0));
		changer.addActionListener(event);
		
		panneau2.add(changer, c7);
//////////////////////////////////////////////////////////////////////////////////////////////////

		pquitter.add(bquitter,BorderLayout.NORTH);
		bquitter.addActionListener(quit);

		tout.add(englobe,BorderLayout.CENTER);
		tout.add(pquitter,BorderLayout.EAST);
		englobe.add(panneau1,BorderLayout.NORTH);
		englobe.add(panneau2,BorderLayout.SOUTH);

		this.add(englobe);
		this.add(tout);
	}

	public JButton getBoutonQuitter() {
		return this.bquitter;
	}
}	