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
	
	private Boolean mursOk = false;
	private Connection connection;
	
	/**
	 * Constructeur
	 * @param controle
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
		//vérifie si info est un JPanel
		if (info instanceof JPanel) {
			//si mursOk est faux (si les murs n'ont pas encore été plaçés)
			if(!mursOk) {
				this.controle.evenementJeuClient(Interface.ajoutPanelMur, info);
				mursOk = true;
			}
			else {
				this.controle.evenementJeuClient(Interface.envoiPanelJeu, info);
			}
		}
		else if (info instanceof String) {
			this.controle.evenementJeuClient(Interface.modifTchat, info);
		}
	}
	
	@Override
	public void deconnexion() {
	}
	/**
	 * Envoi d'une information vers le serveur
	 * fais appel une fois à l'envoi dans la classe Jeu
	 * @param info
	 */
	public void envoi(String info) {
		super.envoi(connection, info);
	}
}
