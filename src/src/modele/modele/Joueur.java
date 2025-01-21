package modele;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import vue.Arene;

/**
 * Gestion des joueurs
 *
 */
public class Joueur extends Objet {
	/**
	 * Constantes en dur
	 */
	private static final String marche = "marche";
	private static final String mort = "mort";
	private static final String touche = "touche";
	private static final int dix = 10;

	/**
	 * vie de départ pour tous les joueurs
	 */
	private static final int MAXVIE = 10 ;
	/**
	 * gain de points de vie lors d'une attaque
	 */
	private static final int GAIN = 1 ; 
	/**
	 * perte de points de vie lors d'une attaque
	 */
	private static final int PERTE = 2 ; 
	
	/**
	 * Dimensions du JLabel des personnages
	 */
	private static final int hauteurePerso1 = 44 ; 
	private static final int largeurePerso1 = 39 ; 
	private static final int hauteurePerso2 = 41 ; 
	private static final int largeurePerso2 = 34 ; 
	private static final int hauteurePerso3 = 47 ; 
	private static final int largeurePerso3 = 30 ; 
	private static final int hauteuretxt = 8 ;  
	private static final int largeuretxt = 60 ;
	
	private static final int minPosX = 30;
	private static final int maxPosX = 740;
	private static final int minPosY = 30;
	private static final int maxPosY = 540;
	
	/**
	 * Label contenant pseudo et PV du personnage
	 */
	private JLabel message;
	
	/**
	 * pseudo saisi
	 */
	private String pseudo ;
	/**
	 * n° correspondant au personnage (avatar) pour le fichier correspondant
	 */
	private int numPerso ; 
	/**
	 * instance de JeuServeur pour communiquer avec lui
	 */
	private JeuServeur jeuServeur ;
	/**
	 * numéro d'�tape dans l'animation (de la marche, touché ou mort)
	 */
	private int etape ;
	/**
	 * la boule du joueur
	 */
	private Boule boule ;
	/**
	 * Nombre de points de vie actuel du joueur
	 */
	private int vie;
	/**
	 * tourné vers la gauche (0) ou vers la droite (1)
	 */
	private int orientation;
	/**
	 * Constructeur
	 */
	public Joueur(JeuServeur jeuServeur) {
		this.jeuServeur = jeuServeur;
		this.vie = MAXVIE;
		this.etape = 1;
		this.orientation = 1;
	}

	/**
	 * Initialisation d'un joueur (pseudo et numéro, calcul de la 1ère position, affichage, création de la boule)
	 */
	public void initPerso(String pseudo, int numPerso, Collection<Joueur> Joueurs, ArrayList<Mur> Murs) {
		this.pseudo = pseudo;
		this.numPerso = numPerso;
		//Création du JLabel qui représente le perso
		super.jLabel = new JLabel();
		this.message = new JLabel();
		this.message.setFont(new Font("Dialog", Font.BOLD, 8));
		message.setText(this.pseudo + " : " + this.vie);
		System.out.println("joueur "+pseudo+" - num perso "+numPerso+" créé");
		this.affiche(marche, this.etape);
		this.premierePosition(Joueurs, Murs);
		this.jeuServeur.ajoutJLabelJeuArene(this.jLabel);
		this.message.setBounds(this.jLabel.getX() - dix, this.jLabel.getY() + super.hauteure, largeuretxt, hauteuretxt);
		this.jeuServeur.ajoutJLabelJeuArene(this.message);
		this.jeuServeur.envoiJeuATous();
	}

	/**
	 * Calcul de la première position aléatoire du joueur (sans chevaucher un autre joueur ou un mur)
	 */
	private void premierePosition(Collection<Joueur> Joueurs, ArrayList<Mur> Murs) {
		Random rand = new Random();
		super.jLabel.setBounds(rand.nextInt(minPosX, maxPosX), rand.nextInt(minPosY, maxPosY), super.largeure, super.hauteure);
		while(this.toucheJoueur(Joueurs) || this.toucheMur(Murs)) {
			super.posX = rand.nextInt(minPosX, maxPosX);
			super.posY = rand.nextInt(minPosY, maxPosY);
			super.jLabel.setBounds(super.posX, super.posY, super.largeure, super.hauteure);
		}
	}
	
	/**
	 * Affiche le personnage et son message
	 */
	public void affiche(String etat, int etape) {
		switch(this.numPerso) {
		case 1 : 
			super.largeure = largeurePerso1;
			super.hauteure = hauteurePerso1;
			break;
		case 2 : 
			super.largeure = largeurePerso2;
			super.hauteure = hauteurePerso2;
			break;
		case 3 : 
			super.largeure = largeurePerso3;
			super.hauteure = hauteurePerso3;
		break;
		}
		super.jLabel.setIcon(new ImageIcon(Arene.class.getResource("/personnages/perso" + this.numPerso + etat + etape + "d" + this.orientation + ".gif")));
		this.jeuServeur.envoiJeuATous();
	}

	/**
	 * Gère une action reçue et qu'il faut afficher (déplacement, tire de boule...)
	 */
	public void action() {
	}

	/**
	 * Gère le déplacement du personnage
	 */
	private void deplace() { 
	}

	/**
	 * Contrôle si le joueur touche un des autres joueurs
	 * @return true si deux joueurs se touchent
	 */
	private Boolean toucheJoueur(Collection <Joueur> liste) {
		Object[] joueurs =liste.toArray();
		for (int i = 0; i < liste.size(); i++) {
			if (joueurs[i] != this){
				if(this.toucheObjet((Objet) joueurs[i])) {

					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Gain de points de vie après avoir touché un joueur
	 */
	public void gainVie() {
	}
	
	/**
	 * Perte de points de vie après avoir été touché 
	 */
	public void perteVie() {
	}
	
	/**
	 * Contrôle si le joueur touche un mur
	 * @return true si un joueur touche un mur
	 */
	private Boolean toucheMur(ArrayList <Mur> liste) {
		for (int i = 0; i < liste.size(); i++) {
			if (this.toucheObjet(liste.get(i))){
				return true;
			}
		}
		return false;
	}
	/**
	 * vrai si la vie est à 0
	 * @return true si vie = 0
	 */
	public Boolean estMort() {
		return null;
	}
	
	/**
	 * Le joueur se déconnecte et disparait
	 */
	public void departJoueur() {
	}
	
}
