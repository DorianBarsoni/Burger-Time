package Entity;

import javax.swing.*;

import Launching.Etat;
import Plateau.Map;
import Position.*;
import Window.Fenetre;

public class Pain_inferieur extends Aliment{
	
	//Classe créant un pain inférieur

	public Pain_inferieur(Map map, Fenetre fen, Joueur joueur, int colonne, Etat etat, Point p) {
		
		super(map, fen, joueur, colonne, etat); //Appele du super constructeur
		pos = new Point(p.getX(), p.getY()); //initialisation de la position du pain
		
		//Tableau de string contenant les noms des images à associer aux labels
		String[] nom = {"/images/pain_inferieur/pain_inferieur_1.png",
						"/images/pain_inferieur/pain_inferieur_2.png",
						"/images/pain_inferieur/pain_inferieur_3.png",
						"/images/pain_inferieur/pain_inferieur_4.png",
						"/images/pain_inferieur/pain_inferieur_5.png",
						"/images/pain_inferieur/pain_inferieur_6.png"};
		
		//Pour chaque label
		for(int i=0; i<6; i++) {
			Aliment[i] = new JLabel(new ImageIcon(getClass().getResource(nom[i]))); //On associe son image
			tabPixel[i] = new Pixel(p.getX()*31 + i*31 + 15, p.getY()*31 + 30); //On positionne ses pixels (prévu pour les chutes)
			x[i] = pos.getX()*31 + i*31 + 14; //On positionne le label
			y[i] = pos.getY()*31 + 21; //On positionne le label
		}
		
		this.fen.setAliment(this); //On ajoute l'aliment créé aux tableaux d'aliments dans Fenetre
	}

}
