package vue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import controleur.Controle;
import controleur.Interface;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class EntreeJeu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtIP;
	private Controle controle;

	/**
	 * Create the frame.
	 */
	public EntreeJeu(Controle controle) {
		this.controle = controle;
		setTitle("Urban Marginal");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 167);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnStart = new JButton("Lancer");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnStart_click();
			}
		});
		btnStart.setBounds(162, 11, 112, 23);
		contentPane.add(btnStart);
		
		JButton btnConnect = new JButton("Se connecter");
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnConnect_click();
			}
		});
		btnConnect.setBounds(162, 61, 112, 23);
		contentPane.add(btnConnect);
		
		JButton btnExit = new JButton("Quitter");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnExit_click();
			}
		});
		btnExit.setBounds(162, 95, 112, 23);
		contentPane.add(btnExit);
		
		JLabel lblStart = new JLabel("Lancer un serveur :");
		lblStart.setBounds(10, 15, 94, 14);
		contentPane.add(lblStart);
		
		JLabel lblConnect = new JLabel("Se connecter à un serveur :");
		lblConnect.setBounds(10, 40, 157, 14);
		contentPane.add(lblConnect);
		
		JLabel lblIP = new JLabel("IP serveur :");
		lblIP.setBounds(10, 65, 64, 14);
		contentPane.add(lblIP);
		
		txtIP = new JTextField();
		txtIP.setText("127.0.0.1");
		txtIP.setBounds(66, 62, 86, 20);
		contentPane.add(txtIP);
		txtIP.setColumns(10);
	}
	
	/**
	 * click sur le bouton start
	 */
	private void btnStart_click() {
		this.controle.evenementEntreeJeu(Interface.serveur);
	}
	
	/**
	 * click sur le bouton connect
	 */
	private void btnConnect_click() {
		this.controle.evenementEntreeJeu(txtIP.getText());
	}
	
	/**
	 * click sur le bouton exit
	 */
	private void btnExit_click() {
		System.exit(0);
	}
}
