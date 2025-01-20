package modele;

import javax.swing.JLabel;

/**
 * Informations communes � tous les objets (joueurs, murs, boules)
 * permet de m�moriser la position de l'objet et de g�rer les  collisions
 *
 */
public abstract class Objet {
	
	/**
	 * JLabel correspondant à un objet
	 */
	protected JLabel jLabel;

	/**
	 * position X de l'objet
	 */
	protected int posX ;
	/**
	 * position Y de l'objet
	 */
	protected int posY ;
	
	/**
	 * contr�le si l'objet actuel touche l'objet pass� en param�tre
	 * @param objet contient l'objet � contr�ler
	 * @return true si les 2 objets se touchent
	 */
	public Boolean toucheObjet (Objet objet) {
		return null;
	}
	
	/**
	 * Getter du Jlabel
	 * @return 
	 */
	public JLabel getJLabel() {
		return jLabel;
	}
}
