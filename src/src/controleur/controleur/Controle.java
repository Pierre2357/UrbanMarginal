package controleur;

import vue.EntreeJeu;

public class Controle {
	//Déclare une frame de type EntreeJeu
	private EntreeJeu frmEntreeJeu ;
	
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
		this.frmEntreeJeu = new EntreeJeu() ;
		this.frmEntreeJeu.setVisible(true);
	}
}
