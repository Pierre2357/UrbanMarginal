package controleur;

import vue.EntreeJeu;
import vue.Arene;
import vue.ChoixJoueur;
import outils.AsyncResponse;
import outils.Connection;
import outils.ServeurSocket;
import outils.ClientSocket;
import javax.swing.JLabel;
import javax.swing.JPanel;
import modele.Jeu;
import modele.JeuServeur;
import modele.JeuClient;

public class Controle implements AsyncResponse {
	//Déclaration des propriétés de Controle
	private EntreeJeu frmEntreeJeu ;
	private Arene frmArene;
	private ChoixJoueur frmChoixJoueur;
	private static final int PORT = 6666;
	private static final String Entree = "EntreeJeu";
	private static final String Choix = "ChoixJoueur";
	private static final String Arene = "Arene";
	private Jeu leJeu;
	private JeuServeur leJeuServeur;
	private JeuClient leJeuClient;
	private boolean client;
	
	/**
	 * Méthode de démarrage
	 * @param args non utilisé
	 */
	public static void main(String[] args) {
		new Controle();
	}
	
	/**
	 * Constructeur
	 */
	private Controle() {
		//Crée une frame de type EntreeJeu et la rends visible
		this.creerFrame(Entree, true);
	}
	
	/**
	 * Méthode pour écouter les demandes de Entreejeu 
	 * @param info
	 */
	public void evenementEntreeJeu(String info) {
		//Si on reçoit "serveur" (demande de lancer un serveur)
		if (info == Interface.serveur) {
			//Création et affichage de la fenetre Arene
			this.creerFrame(Arene, true);
			//Création du serveur
			new ServeurSocket(this, PORT);
			leJeu = new JeuServeur(this);
			this.leJeuServeur = (JeuServeur)leJeu;
			this.leJeuServeur.constructionMurs();
			//Fermeture de la fenetre EntreeJeu
			this.frmEntreeJeu.dispose();
		}
		//Sinon (Demande de connexion à un serveur, info correspond ici à l'adresse IP du serveur à rejoindre)
		else {
			//Rejoindre le serveur
			new ClientSocket(this, info, PORT);
		}
	}
	
	/**
	 * Méthode pour gérer la réception d'une information. utilise la méthode de AsyncReponse.
	 */
	@Override
	public void reception(Connection connection, String ordre, Object info) {
		switch (ordre) {
		case Interface.connexion :
			if(leJeu instanceof JeuServeur) {
				leJeu.connexion(connection);
			}
			else {
				leJeu = new JeuClient(this);
				leJeu.connexion(connection);
				//Création et affichage de la fenetre ChoixJoueur
				this.creerFrame(Choix, true);
				//Création de la fenetre Arene sans la rendre visible
				this.creerFrame(Arene, false);
				//Fermeture de la fenetre EntreeJeu
				this.frmEntreeJeu.dispose();
			}
			break;
		case Interface.reception :
			leJeu.reception(connection, info);
			break;
		case Interface.deconnexion :
			break;
		}
	}
	
	/**
	 * Méthode pour communiquer avec la fenêtre ChoixJoueur
	 * @param pseudo
	 * @param numPerso
	 */
	public void evenementChoixJoueur(String pseudo, int numPerso) {
		leJeuClient = (JeuClient) leJeu;
		leJeuClient.envoi(Interface.pseudo + Interface.tidle + pseudo + Interface.tidle + numPerso);
		this.frmArene.setVisible(true);
		this.frmChoixJoueur.dispose();
	}
	
	/**
	 * méthode pour envoyer une information
	 * @param connection
	 * @param info
	 */
	public void envoi(Connection connection, Object info) {
		connection.envoi(info);
	}
	
	/**
	 * Méthode pour communiquer avec JeuServeur
	 * @param ordre
	 * @param info
	 */
	public void evenementJeuServeur(String ordre, Object info) {
		switch (ordre) {
		case Interface.ajoutMur :
			this.frmArene.ajoutMurs(info);
			break;
		case Interface.ajoutPanelMur :
			leJeu.envoi((Connection) info, this.frmArene.getJpnMurs());
			break;
		case Interface.ajoutLabelJeu :
			this.frmArene.ajoutJlabelJeu((JLabel)info);
			break;
		case Interface.envoiPanelJeu :
			leJeu.envoi((Connection) info, this.frmArene.getJpnJeu());
			break;
		case Interface.ajoutPhrase :
			this.frmArene.ajoutTchat((String) info);
			this.leJeuServeur.envoi(this.frmArene.getTxtTchat());
			break;
		}
	}
	
	/**
	 * Méthode pour communiquer avec JeuClient
	 * @param ordre
	 * @param info
	 */
	public void evenementJeuClient(String ordre, Object info) {
		switch (ordre) {
		case Interface.ajoutPanelMur :
			this.frmArene.setJpnMurs((JPanel)info);
			break;
		case Interface.envoiPanelJeu:
			this.frmArene.setJpnJeu((JPanel)info);
			break;
		case Interface.modifTchat :
			this.frmArene.setTxtTchat((String)info);
			break;
		}
	}
	
	/**
	 * Gère la création d'une frame
	 * @param type
	 * @param visible
	 */
	public void creerFrame(String type, Boolean visible) {
		switch(type) {
		case Entree :
			this.frmEntreeJeu = new EntreeJeu(this);
			this.frmEntreeJeu.setVisible(visible);
			break;
		case Choix :
			this.frmChoixJoueur = new ChoixJoueur(this);
			this.frmChoixJoueur.setVisible(visible);
			break;
		case Arene :
			if(leJeu instanceof JeuClient) {
				this.client = true;
			}
			else {
				this.client = false;
			}
			this.frmArene = new Arene(this, this.client);
			this.frmArene.setVisible(visible);
			break;
		}
	}
	
	/**
	 * méthode pour communiquer avec l'Arene
	 * @param texte
	 */
	public void evenementArene(String texte) {
		this.leJeuClient.envoi(Interface.tchat + Interface.tidle + texte);
	}
}
