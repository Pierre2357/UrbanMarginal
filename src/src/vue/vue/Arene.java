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
import controleur.Controle;
import controleur.Interface;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Arene extends JFrame {

	private static final long serialVersionUID = 1L;
	private Controle controle;
	private JPanel contentPane;
	private JTextField txtSaisie;
	private JTextArea txtTchat;
	private JPanel jpnMurs;
	private JPanel jpnJeu;

	/**
	 * Create the frame.
	 */
	public Arene(Controle controle, boolean client) {
		
		this.controle = controle;
		
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
		
		jpnJeu = new JPanel();
		jpnJeu.setBounds(0, 0, 800, 600);
		jpnJeu.setOpaque(false);
		jpnJeu.setLayout(null);
		contentPane.add(jpnJeu);
		
		jpnMurs = new JPanel();
		jpnMurs.setBounds(0, 0, 800, 600);
		jpnMurs.setOpaque(false);
		jpnMurs.setLayout(null);
		contentPane.add(jpnMurs);
		
		JLabel lblFond = new JLabel("");
		lblFond.setIcon(new ImageIcon(Arene.class.getResource(Interface.fondArene)));
		lblFond.setBounds(0, 0, 800, 600);
		contentPane.add(lblFond);
		
		if (client) {
			txtSaisie = new JTextField();
		    txtSaisie.addKeyListener(new KeyAdapter() {
		    	@Override
			    public void keyPressed(KeyEvent e) {
				    //Si la touche pressée est "Entrée" et que txtSaisie n'est pas vide
				    if (e.getKeyCode() == KeyEvent.VK_ENTER && !txtSaisie.getText().isBlank()) {
					    envoiTextTxtSaisie (txtSaisie.getText());
				    }
			    }
		    });
		    txtSaisie.setBounds(0, 600, 800, 25);
		    contentPane.add(txtSaisie);
		    txtSaisie.setColumns(1);
		}
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(null);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(0, 629, 800, 200);
		contentPane.add(scrollPane);
		
		txtTchat = new JTextArea();
		txtTchat.setColumns(1);
		txtTchat.setEditable(false);;
		scrollPane.setViewportView(txtTchat);
	}
	
	/**
	 * Méthode pour ajouter les murs
	 * @param mur
	 */
	public void ajoutMurs(Object mur) {
		JLabel lblMur = (JLabel)mur;
		jpnMurs.add(lblMur);
		jpnMurs.repaint();
	}
	
	/**
	 * Méthode pour ajouter les murs
	 * @param label
	 */
	public void ajoutJlabelJeu(JLabel label) {
		jpnJeu.add(label);
		jpnJeu.repaint();
	}
	
	/**
	 * Getter de jpnMurs
	 */
	
	public JPanel getJpnMurs() {
		return jpnMurs;
	}
	
	/**
	 * Setter de jpnMurs
	 * @param panel
	 */
	public void setJpnMurs(JPanel panel) {
		jpnMurs.add(panel);
		jpnMurs.repaint();
	}
	
	/**
	 * Getter de jpnJeu
	 */
	
	public JPanel getJpnJeu() {
		return jpnJeu;
	}
	
	/**
	 * Setter de jpnJeu
	 * @param panel
	 */
	public void setJpnJeu(JPanel panel) {
		jpnJeu.removeAll();
		jpnMurs.add(panel);
		jpnMurs.repaint();
	}
	
	/**
	 * Getter de txtTchat
	 */
	
	public String getTxtTchat() {
		return this.txtTchat.getText();
	}
	
	/**
	 * Setter de txtTchat
	 * @param texte
	 */
	public void setTxtTchat(String texte) {
		this.txtTchat.setText(texte);
		this.txtTchat.setCaretPosition(this.txtTchat.getDocument().getLength());
	}
	
	/**
	 * Ajoute une phrase au tchat
	 * @param phrase
	 */
	public void ajoutTchat(String phrase) {
		this.txtTchat.append(phrase + "\r\n");
		this.txtTchat.setCaretPosition(this.txtTchat.getDocument().getLength());
	}
	
	/**
	 * Envoi du texte de txtSaisie
	 * @param texte
	 */
	private void envoiTextTxtSaisie (String texte) {
		controle.evenementArene(txtSaisie.getText());
		txtSaisie.setText("");
	}
}
