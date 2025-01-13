package vue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class EntreeJeu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Create the frame.
	 */
	public EntreeJeu() {
		setTitle("Urban Marginal");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 167);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnStart_click();
			}
		});
		btnStart.setBounds(185, 11, 89, 23);
		contentPane.add(btnStart);
		
		JButton btnConnect = new JButton("Connect");
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnConnect_click();
			}
		});
		btnConnect.setBounds(185, 61, 89, 23);
		contentPane.add(btnConnect);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnExit_click();
			}
		});
		btnExit.setBounds(185, 95, 89, 23);
		contentPane.add(btnExit);
		
		JLabel lblStart = new JLabel("Start a server:");
		lblStart.setBounds(10, 15, 89, 14);
		contentPane.add(lblStart);
		
		JLabel lblConnect = new JLabel("Connect an existing server:");
		lblConnect.setBounds(10, 40, 157, 14);
		contentPane.add(lblConnect);
		
		JLabel lblIP = new JLabel("IP server:");
		lblIP.setBounds(10, 65, 57, 14);
		contentPane.add(lblIP);
		
		textField = new JTextField();
		textField.setText("127.0.0.1");
		textField.setBounds(65, 62, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
	}
	
	/**
	 * click sur le bouton start
	 */
	private Arene frmArene;
	private void btnStart_click() {
		//Ouvre crée et rends visible une frame Arene 
		this.frmArene = new Arene() ;
		this.frmArene.setVisible(true);
		//Ferme la frame actuelle
		this.dispose();
	}
	
	/**
	 * click sur le bouton connect
	 */
	private ChoixJoueur frmChoixJoueur;
	private void btnConnect_click() {
		//Ouvre crée et rends visible une frame ChoixJoueur
		this.frmChoixJoueur = new ChoixJoueur() ;
		this.frmChoixJoueur.setVisible(true);
		//Ferme la frame actuelle
		this.dispose();
	}
	
	/**
	 * click sur le bouton exit
	 */
	private void btnExit_click() {
		System.exit(0);
	}
}
