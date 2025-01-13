package vue;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;

public class ChoixJoueur extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtPseudo;

	/**
	 * Create the frame.
	 */
	public ChoixJoueur() {
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
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(ChoixJoueur.class.getResource("/fonds/fondchoix.jpg")));
		lblNewLabel.setBounds(0, 0, 400, 275);
		contentPane.add(lblNewLabel);
		
		JLabel lblPercedent = new JLabel("");
		lblPercedent.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				lblPrecedent_click();
			}
		});
		lblPercedent.setBounds(59, 144, 46, 46);
		contentPane.add(lblPercedent);
		
		JLabel lblSuivant = new JLabel("");
		lblSuivant.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				lblSuivant_click();
			}
		});
		lblSuivant.setBounds(288, 144, 46, 46);
		contentPane.add(lblSuivant);
		
		JLabel lblGO = new JLabel("");
		lblGO.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				lblGO_click();
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
	}
	
	/**
	 * click sur lblPrecedent (correspondant à la flèche vers la gauche)
	 */
	private void lblPrecedent_click(){
		txtPseudo.setText("Precedent");
	}
	
	/**
	 * click sur lblSuivant (correspondant à la flèche vers la droite)
	 */
	private void lblSuivant_click(){
		txtPseudo.setText("Suivant");
	}
	
	/**
	 * click sur lblGO
	 */
	private Arene frmArene;
	private void lblGO_click(){
		this.frmArene = new Arene() ;
		this.frmArene.setVisible(true);
		this.dispose();
	}
}
