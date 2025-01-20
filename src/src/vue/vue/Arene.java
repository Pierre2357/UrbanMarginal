package vue;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import controleur.Interface;

public class Arene extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtSaisie;
	private JPanel jpnMurs;

	/**
	 * Create the frame.
	 */
	public Arene() {
		this.getContentPane().setPreferredSize(new Dimension(800, 825));
		this.pack();
		this.setResizable(false);
		setTitle("Arene");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 820, 870);
		contentPane = new JPanel();
		contentPane.setBorder(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		jpnMurs = new JPanel();
		jpnMurs.setBounds(0, 0, 800, 600);
		jpnMurs.setOpaque(false);
		jpnMurs.setLayout(null);
		contentPane.add(jpnMurs);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Arene.class.getResource(Interface.fondArene)));
		lblNewLabel.setBounds(0, 0, 800, 600);
		contentPane.add(lblNewLabel);
		
		txtSaisie = new JTextField();
		txtSaisie.setBounds(0, 600, 800, 25);
		contentPane.add(txtSaisie);
		txtSaisie.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(null);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(0, 629, 800, 200);
		contentPane.add(scrollPane);
		
		JTextArea txtTchat = new JTextArea();
		txtTchat.setColumns(10);
		scrollPane.setViewportView(txtTchat);
	}
	
	/**
	 * Méthode pour ajouter les murs
	 */
	public void ajoutMurs(Object mur) {
		JLabel lblMur = (JLabel)mur;
		jpnMurs.add(lblMur);
		jpnMurs.repaint();
	}
	
	/**
	 * Getter de jpnMurs
	 */
	
	public JPanel getJpnMurs() {
		return jpnMurs;
	}
	
	/**
	 * Setter de jpnMurs
	 */
	public void setJpnMurs(JPanel panel) {
		jpnMurs.add(panel);
		jpnMurs.repaint();
	}
}
