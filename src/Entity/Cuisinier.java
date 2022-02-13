package Entity;

import javax.swing.*;
import Window.*;
import Plateau.*;
import Position.*;

public class Cuisinier extends Joueur {
	
	//Classe de cr�aion d'un cuisinier
	
	public Cuisinier(Map map, Fenetre fen) {
		super(map, fen); //Appel du super constructeur
		
		pos = new Point(30, 26); //Initialisation des positions (grille) initilale du cuisinier
		pix_hg = new Pixel(pos.getX()*31, pos.getY()*31 + 30); //Initialisation du pixel horizontal gauche
		pix_hd = new Pixel(pos.getX()*31 + 30, pos.getY()*31+ 30); //Initialisation du pixel horizontal droite
		pix_vh = new Pixel(pos.getX()*31 + 14, pos.getY()*31); //Initialisation du pixel vertical haut
		pix_vb = new Pixel(pos.getX()*31 + 14, pos.getY()*31 + 30); //Initialisation du pixel vertical bas
		sprite = new JLabel(new ImageIcon(getClass().getResource("/images/personnages/cuisinier.png"))); //Ajout de l'image du cuisinier au label
		spriteDroitName = "/images/personnages/cuisinier.png";
		spriteGaucheName = "/images/personnages/cuisinier_gauche.png";
		
	}
	
	//Fonction initialisant les �l�ments de la fen�tre
	public void setup() {
		x = getVb().getX(); //Initialisation de la position x de l'entit�e
		y = getVb().getY() - 61; //Initialisation de la position y de l'entit�e
		sprite.setBounds(x, y, 31, 62); //Placement de l'entit�e
		fen.add(sprite); //Ajout du joueurs sur la fen�tre
		fen.setJoueur(this); //Passage de l'entit�e � la fen�tre
	}
}
