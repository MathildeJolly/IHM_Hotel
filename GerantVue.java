import java.awt.GridLayout;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;

public class GerantVue extends JFrame {
	private JPanel tout = new JPanel(new BorderLayout());
	private JTextField tf1 = new JTextField("20/10/2017");
	private JTextField tf2 = new JTextField("du 16/12/2016 au 2/1/2017");
	private JTextField tf3 = new JTextField("24/12/2042");
	private JLabel l1 = new JLabel(" TAUX D'OCCUPATION (DATE FIXE)");
	private JLabel l2 = new JLabel(" TAUX D'OCCUPATION (PERIODE)");
	private JLabel l3 = new JLabel(" TAUX DE NON-PRESENTATION");
	private JButton b1 = new JButton("VALIDER");
	private JButton b2 = new JButton("VALIDER");
	private JButton b3 = new JButton("VALIDER");
	
	private static final long serialVersionUID = 1L;
	public GerantVue() {
		JPanel header = new JPanel();
		header.setLayout(new GridLayout(3,3));
		Controleur c = new Controleur(tf1);
	
		Font f1 = new Font("Arial", Font.PLAIN, 33);
		b1.setFont(f1);
		b2.setFont(f1);
		b3.setFont(f1);
		b1.setBackground(new Color(38,177,184));
		b2.setBackground(new Color(38,177,184));
		b3.setBackground(new Color(38,177,184));
		b1.setForeground(new Color(255,255,255));
		b2.setForeground(new Color(255,255,255));
		b3.setForeground(new Color(255,255,255));

		Font f2 = new Font("Helvetica", Font.ITALIC, 33);
		tf1.setFont(f2);
		tf2.setFont(f2);
		tf3.setFont(f2);
		tf1.setForeground(new Color(124,132,140));
		tf2.setForeground(new Color(124,132,140));
		tf3.setForeground(new Color(124,132,140));

		Font f3 = new Font("Verdana", Font.PLAIN, 18);
		l1.setFont(f3);
		l2.setFont(f3);
		l3.setFont(f3);
		l1.setForeground(new Color(255, 255, 255));
		l2.setForeground(new Color(255, 255, 255));
		l3.setForeground(new Color(255, 255, 255));

		header.add(l1);
		header.add(tf1);
		header.add(b1);
		header.add(l2);
		header.add(tf2);
		header.add(b2);
		header.add(l3);
		header.add(tf3);
		header.add(b3);

		header.setBackground(new Color(38, 89, 184));
		tout.setBackground(new Color(176, 213, 214));
		tout.add(header, BorderLayout.CENTER);
		tout.add(new JLabel("  "), BorderLayout.NORTH);
		tout.add(new JLabel("  "), BorderLayout.SOUTH);
		tout.add(new JLabel("                           "), BorderLayout.EAST);
		tout.add(new JLabel("                           "), BorderLayout.WEST);

		getContentPane().add(tout);
		pack();
		validate();
		setTitle("Statistiques");
		setVisible(true);
		setSize(1200, 400);
		setLocationRelativeTo(null);
	}
}