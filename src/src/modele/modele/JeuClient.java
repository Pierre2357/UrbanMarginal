package modele;

import javax.swing.JPanel;
import controleur.Controle;
import controleur.Interface;
import outils.Connection;

/**
 * Gestion du jeu côté client
 *
 */
public class JeuClient extends Jeu {
	
	private Connection connection;
	
	/**
	 * Controleur
	 */
	public JeuClient(Controle controle) {
		super.controle = controle;
	}
	
	@Override
	public void connexion(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void reception(Connection connection, Object info) {
		if (info instanceof JPanel) {
			this.controle.evenementJeuClient(Interface.ajoutPanelMur, info);
		}
	}
	
	@Override
	public void deconnexion() {
	}
	/**
	 * Envoi d'une information vers le serveur
	 * fais appel une fois à l'envoi dans la classe Jeu
	 */
	public void envoi(String info) {
		super.envoi(connection, info);
	}
}
