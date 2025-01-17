package vue;

import java.awt.Cursor;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import controleur.Controle;

public class ChoixJoueur extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtPseudo;
	private JLabel lblPersonnage;
	private Controle controle; 
	private int numPerso = 1;
	private static final int maxNumPerso = 3;
	private static final int minNumPerso = 1;

	/**
	 * Create the frame.
	 */
	public ChoixJoueur(Controle controle) {
		this.controle = controle;
		this.getContentPane().setPreferredSize(new Dimension(400, 275));
		this.pack();
		this.setResizable(false);
		setTitle("Choix");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 417, 300);
		contentPane = new JPanel();
		contentPane.setBorder(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblPersonnage = new JLabel("");
		lblPersonnage.setBounds(143, 114, 120, 119);
		lblPersonnage.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblPersonnage);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(ChoixJoueur.class.getResource("/fonds/fondchoix.jpg")));
		lblNewLabel.setBounds(0, 0, 400, 275);
		contentPane.add(lblNewLabel);
		
		JLabel lblPercedent = new JLabel("");
		lblPercedent.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				lblPrecedent_click();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				sourisDoigt();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				sourisNormale();
			}
		});
		lblPercedent.setBounds(59, 144, 46, 46);
		contentPane.add(lblPercedent);
		
		JLabel lblSuivant = new JLabel("");
		lblSuivant.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				lblSuivant_click();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				sourisDoigt();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				sourisNormale();
			}
		});
		lblSuivant.setBounds(288, 144, 46, 46);
		contentPane.add(lblSuivant);
		
		JLabel lblGO = new JLabel("");
		lblGO.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				lblGO_click();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				sourisDoigt();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				sourisNormale();
			}
		});
		lblGO.setBounds(309, 201, 66, 63);
		contentPane.add(lblGO);
		
		txtPseudo = new JTextField();
		txtPseudo.setBounds(143, 244, 120, 20);
		contentPane.add(txtPseudo);
		txtPseudo.setColumns(10);
		
		JLabel lblFond = new JLabel("");
		lblFond.setIcon(new ImageIcon(ChoixJoueur.class.getResource("/fonds/fondchoix.jpg")));
		lblFond.setBounds(0, 0, 400, 261);
		
		affichePerso();
	}
	
	/**
	 * click sur lblSuivant (correspondant à la flèche vers la droite)
	 */
	private void lblSuivant_click(){
		numPerso += 1;
		if (numPerso > maxNumPerso) {
			numPerso = minNumPerso;
		}
		affichePerso();
	}
	
	/**
	 * click sur lblPrecedent (correspondant à la flèche vers la gauche)
	 */
	private void lblPrecedent_click(){
		numPerso -= 1;
		if (numPerso < minNumPerso) {
			numPerso = maxNumPerso;
		}
		affichePerso();
	}
	
	/**
	 * click sur lblGO
	 */
	private void lblGO_click(){
		if(txtPseudo.getText().isBlank()) {
			JOptionPane.showMessageDialog(null, "La saisie du pseudo est obligatoire");
			txtPseudo.requestFocus();
		}
		else {
			this.controle.evenementChoixJoueur(txtPseudo.getText(), numPerso);
		}
	}
	
	public void affichePerso() {
		lblPersonnage.setIcon(new ImageIcon(ChoixJoueur.class.getResource("/personnages/perso"+numPerso+"marche1d1.gif")));
	}
	
	/**
	 * donner une forme de doigt à la souris
	 */
	private void sourisDoigt() {
		contentPane.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}
	
	/**
	 * donner une forme normale à la souris
	 */
	private void sourisNormale() {
		contentPane.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}
}
