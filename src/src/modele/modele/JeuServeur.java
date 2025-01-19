package modele;

import java.util.ArrayList;
import java.util.Hashtable;
import controleur.Controle;
import controleur.Interface;
import outils.Connection;

/**
 * Gestion du jeu côté serveur
 *
 */
public class JeuServeur extends Jeu {
	
	private String message;

	/**
	 * Collection de murs
	 */
	private ArrayList<Mur> lesMurs = new ArrayList<Mur>() ;
	/**
	 * Collection de joueurs et connexions associés
	 */
	private Hashtable<Connection, Joueur> lesJoueurs = new Hashtable<Connection, Joueur>() ;
	
	/**
	 * Constructeur
	 */
	public JeuServeur(Controle controle) {
		super.controle = controle;
	}
	
	@Override
	public void connexion(Connection connection) {
		lesJoueurs.put(connection, new Joueur());
	}

	@Override
	public void reception(Connection connection, Object info) {
		this.message = (String) info;
		//Découpe message en plusieurs chains String délimités par la présence de tidle (~) dans la variable, ces chaines sont stockées dans un tableau
		String[] tabMessage = this.message.split(Interface.tidle);
		switch (tabMessage [0]) {
		case Interface.pseudo :
			//Crée un joueur avec les informations données
			lesJoueurs.get(connection).initPerso(tabMessage[1], Integer.parseInt(tabMessage[2]));
			break;
		}
	}
	
	@Override
	public void deconnexion() {
	}

	/**
	 * Envoi d'une information vers tous les clients
	 * fais appel plusieurs fois à l'envoi de la classe Jeu
	 */
	public void envoi() {
	}

	/**
	 * Génération des murs
	 */
	public void constructionMurs() {
	}
	
}
