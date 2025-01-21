package modele;

import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import controleur.Interface;
import vue.Arene;

/**
 * Gestion des murs
 *
 */
public class Mur extends Objet {
	
	/**
	 * Constantes qui aideront pour la création et le placement des murs
	 */
	private static final int largeureMur = 34;
	private static final int hauteureMur = 35;
	private static final int minPosX = largeureMur;
	private static final int maxPosX = 750;
	private static final int minPosY = hauteureMur;
	private static final int maxPosY = 550;

	/**
	 * Constructeur
	 */
	public Mur() {
		Random rand = new Random();
		//Met PosX à une valeure aléatoire comprise entre minPosX et maxPosX
		super.posX = rand.nextInt(minPosX, maxPosX);
		//Met PosY à une valeure aléatoire comprise entre minPosY et maxPosY
		super.posY = rand.nextInt(minPosY, maxPosY);
		//Valorise les dimentions de l'objet
		super.largeure = largeureMur;
		super.hauteure = hauteureMur;
		//Création et paramétrage du JLabel qui représentera le mur
		super.jLabel = new JLabel();
		jLabel.setIcon(new ImageIcon(Arene.class.getResource(Interface.mur)));
		jLabel.setBounds(super.posX, super.posY, super.largeure, super.hauteure);
	}
	
}
