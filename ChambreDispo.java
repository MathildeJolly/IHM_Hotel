import java.awt.BorderLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;

public class ChambreDispo extends JFrame {

    private JTable tableau;
    private Reservation singleRes;
    private Controleur controleur ;

    public ChambreDispo(List<Chambre> chambres, Database db, Reservation singleRes, Controleur c) {
        this.singleRes = singleRes;

        String[] idChambre = new String[] {
            "Id chambre"
        };
        controleur = c ;
        
        Object[][] numChambre = new Object[chambres.size()][1] ;
        
        for (int i = 0; i < chambres.size(); i++) {
        	numChambre[i][0] = chambres.get(i).getIdChambre();
		}
        
        tableau = new JTable(numChambre, idChambre);
        tableau.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
	    JButton valider = new JButton("CHOISIR UNE AUTRE CHAMBRE");

        JPanel propositionChambre = new JPanel();
        propositionChambre.setLayout(new BorderLayout());
        propositionChambre.add(new JScrollPane(tableau),BorderLayout.NORTH);
        propositionChambre.add(valider, BorderLayout.SOUTH);

        this.add(propositionChambre);

        this.setTitle("Chambres disponibles de categorie ");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);       
        this.pack();
        this.setVisible(true);
        controleur.setFrame(this);
        controleur.setTableau(tableau);
        controleur.setPropositionChambre(propositionChambre);
        valider.addActionListener(controleur);
    }
}