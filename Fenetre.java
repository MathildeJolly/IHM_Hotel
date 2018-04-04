
import java.awt.*;
import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Fenetre extends JFrame {
	private JTextField text = new JTextField("n° de réservation ou nom prénom");
	private JLabel label = new JLabel("Chercher une réservation");
	
	public Fenetre() {
		super("Gestion des réservations");
		this.setSize(520,420);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		AllocationVue vue = new AllocationVue(this);
		vue.getBoutonQuitter()
		.addActionListener(new Controleur(this));
		//this.setResizable(false);
		this.add(vue);

		this.setVisible(true);
	}
}


