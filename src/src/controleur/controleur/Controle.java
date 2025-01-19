package controleur;

import vue.EntreeJeu;
import vue.Arene;
import vue.ChoixJoueur;
import outils.AsyncResponse;
import outils.Connection;
import outils.ServeurSocket;
import outils.ClientSocket;
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
	private JeuClient leJeuClient;
	
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
			//Fermeture de la fenetre EntreeJeu
			this.frmEntreeJeu.dispose();
		}
		//Sinon (Demande de connexion à un serveur, info correspond ici à l'adresse IP du serveur à rejoindre)
		else {
			//Rejoindre le serveur
			new ClientSocket(this, info, PORT);
		}
	}

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
	 */
	public void evenementChoixJoueur(String pseudo, int numPerso) {
		leJeuClient = (JeuClient) leJeu;
		leJeuClient.envoi(Interface.pseudo + Interface.tidle + pseudo + Interface.tidle + numPerso);
		this.frmArene.setVisible(true);
		this.frmChoixJoueur.dispose();
	}
	
	public void envoi(Connection connection, Object info) {
		connection.envoi(info);
	}
	
	/**
	 * Gère la création d'une frame
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
			this.frmArene = new Arene();
			this.frmArene.setVisible(visible);
			break;
		}
	}
}
