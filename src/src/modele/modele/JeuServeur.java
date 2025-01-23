package modele;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Set;
import javax.swing.JLabel;
import controleur.Controle;
import controleur.Interface;
import outils.Connection;

/**
 * Gestion du jeu côté serveur
 *
 */
public class JeuServeur extends Jeu {
	
	private String message;
	private static final String superieur = ">";
	private static final String msgConnect = " vient de se connecter ";

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
	 * @param controle
	 */
	public JeuServeur(Controle controle) {
		super.controle = controle;
	}
	
	@Override
	public void connexion(Connection connection) {
		lesJoueurs.put(connection, new Joueur(this));
	}

	@Override
	public void reception(Connection connection, Object info) {
		this.message = (String) info;
		//Découpe message en plusieurs chains String délimités par la présence de tidle (~) dans la variable, ces chaines sont stockées dans un tableau
		String[] tabMessage = this.message.split(Interface.tidle);
		switch (tabMessage [0]) {
		case Interface.pseudo :
			this.controle.evenementJeuServeur(Interface.ajoutPanelMur, connection);
			//Crée un joueur avec les informations données
		    Collection <Joueur> joueurs = lesJoueurs.values();
			this.lesJoueurs.get(connection).initPerso(tabMessage[1], Integer.parseInt(tabMessage[2]), joueurs, lesMurs);
			this.controle.evenementJeuServeur(Interface.ajoutPhrase, Interface.troisAsterisques + this.lesJoueurs.get(connection).getPseudo() + msgConnect + Interface.troisAsterisques);
			break;
		case Interface.tchat:
			this.controle.evenementJeuServeur(Interface.ajoutPhrase, this.lesJoueurs.get(connection).getPseudo() + superieur + tabMessage[1]);
			break;
		}
	}
	
	@Override
	public void deconnexion() {
	}

	/**
	 * Envoi d'une information vers tous les clients
	 * fais appel plusieurs fois à l'envoi de la classe Jeu
	 * @param info
	 */
	public void envoi(Object info) {
		Set <Connection> connections = this.lesJoueurs.keySet();
		for(Connection laConnection : connections) {
			super.envoi(laConnection, info);
		}
	}

	/**
	 * Génération des murs
	 */
	public void constructionMurs() {
		for (int i=0 ; i<20 ; i++) {
			Mur mur = new Mur();
			this.controle.evenementJeuServeur(Interface.ajoutMur, mur.getJLabel());
			lesMurs.add(mur);
		}
	}
	
	/**
	 * Ajout des JLabels
	 * @param label
	 */
	public void ajoutJLabelJeuArene(JLabel label) {
		this.controle.evenementJeuServeur(Interface.ajoutLabelJeu, label);
	}
	
	/**
	 * envoi a tous les labels de jpnJeu
	 */
	public void envoiJeuATous() {
		Set <Connection> connections = this.lesJoueurs.keySet();
		for(Connection laConnection : connections) {
			this.controle.evenementJeuServeur(Interface.envoiPanelJeu, laConnection);
		}
	}
}
