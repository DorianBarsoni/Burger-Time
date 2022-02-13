package Entity;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import Plateau.Map;
import Position.Pixel;
import Position.Point;
import Window.Fenetre;

public class Saucisse extends Ennemi {
	
	public Saucisse(Map map, Fenetre fen, int x, int y) {
		super(map, fen);
		
		pos = new Point(x, y); //Initialisation des positions (grille) initilale de l'oeuf
		pix_hg = new Pixel(pos.getX()*31, pos.getY()*31 + 30); //Initialisation du pixel horizontal gauche
		pix_hd = new Pixel(pos.getX()*31 + 30, pos.getY()*31+ 30); //Initialisation du pixel horizontal droite
		pix_vh = new Pixel(pos.getX()*31 + 14, pos.getY()*31); //Initialisation du pixel vertical haut
		pix_vb = new Pixel(pos.getX()*31 + 14, pos.getY()*31 + 30); //Initialisation du pixel vertical bas
		sprite = new JLabel(new ImageIcon(getClass().getResource("/images/personnages/saucisse_droit.png"))); //Ajout de l'image du cuisinier au label
		spriteDroitName = "/images/personnages/saucisse_droit.png";
		spriteGaucheName = "/images/personnages/saucisse_gauche.png";
		
		createTableau();
	}

	//Fonction initialisant les éléments de la fenêtre
	public void setup() {
		x = getVb().getX(); //Initialisation de la position x de l'entitée
		y = getVb().getY() - 61; //Initialisation de la position y de l'entitée
		sprite.setBounds(x, y, 31, 62); //Placement de l'entitée
		fen.add(sprite); //Ajout du joueurs sur la fenêtre
		fen.setEnnemie(this); //Passage de l'entitée à la fenêtre
	}

}
