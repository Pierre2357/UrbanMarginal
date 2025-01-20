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
	private static final int maxPosX = 800 - largeureMur;
	private static final int minPosY = hauteureMur;
	private static final int maxPosY = 600 - hauteureMur;

	/**
	 * Constructeur
	 */
	public Mur() {
		Random rand = new Random();
		//Met PosX à une valeure aléatoire comprise entre minPosX et maxPosX
		super.posX = rand.nextInt(minPosX, maxPosX + 1);
		//Met PosY à une valeure aléatoire comprise entre minPosY et maxPosY
		super.posY = rand.nextInt(minPosY, maxPosY + 1);
		//Création et paramétrage du JLabel qui représentera le mur
		super.jLabel = new JLabel();
		jLabel.setIcon(new ImageIcon(Arene.class.getResource(Interface.mur)));
		jLabel.setBounds(super.posX, super.posY, largeureMur, hauteureMur);
	}
	
}
