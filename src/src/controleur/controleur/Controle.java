package controleur;

import vue.EntreeJeu;
import vue.Arene;
import vue.ChoixJoueur;
import outils.AsyncResponse;
import outils.Connection;
import outils.ServeurSocket;
import outils.ClientSocket;

public class Controle implements AsyncResponse {
	//Déclare une frame de type EntreeJeu
	private EntreeJeu frmEntreeJeu ;
	private Arene frmArene;
	private ChoixJoueur frmChoixJoueur;
	private String typeJeu;
	private static final int port = 6666;
	
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
		this.frmEntreeJeu = new EntreeJeu(this) ;
		this.frmEntreeJeu.setVisible(true);
	}
	
	/**
	 * Méthode pour écouter les demandes de Entreejeu 
	 * @param info
	 */
	public void evenementEntreeJeu(String info) {
		//Si on reçoit "serveur" (demande de lancer un serveur)
		if (info == "serveur") {
			//Création et affichage de la fenetre Arene
			this.frmArene = new Arene();
			this.frmArene.setVisible(true);
			//Création du serveur
			new ServeurSocket(this, port);
			typeJeu = info;
			//Fermeture de la fenetre EntreeJeu
			this.frmEntreeJeu.dispose();
		}
		//Sinon (Demande de connexion à un serveur, info correspond ici à l'adresse IP du serveur à rejoindre)
		else {
			//Rejoindre le serveur
			new ClientSocket(this, info, port);
			typeJeu = "client";
		}
	}

	@Override
	public void reception(Connection connection, String ordre, Object info) {
		switch (ordre) {
		case "connexion":
			if(typeJeu == "client") {
				//Création et affichage de la fenetre Arene
				this.frmChoixJoueur = new ChoixJoueur();
				this.frmChoixJoueur.setVisible(true);
				//Création de la fenetre Arene sans la rendre visible
				this.frmArene = new Arene();
				this.frmArene.setVisible(false);
				//Fermeture de la fenetre EntreeJeu
				this.frmEntreeJeu.dispose();
			}
		}
	}
}
