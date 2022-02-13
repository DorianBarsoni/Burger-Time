package Entity;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import Launching.Etat;
import Plateau.Map;
import Position.Pixel;
import Position.Point;
import Window.Fenetre;

public class Steak  extends Aliment{
	
	//Voir les commentaires de la classe Pain_inferieur
	
	public Steak(Map map, Fenetre fen, Joueur joueur, int colonne, Etat etat, Point p) {
		
		super(map, fen, joueur, colonne, etat);
		pos = new Point(p.getX(), p.getY());
		
		String[] nom = {"/images/steak/steak_1.png",
						"/images/steak/steak_2.png",
						"/images/steak/steak_3.png",
						"/images/steak/steak_4.png",
						"/images/steak/steak_5.png",
						"/images/steak/steak_6.png"};
		
		for(int i=0; i<6; i++) {
			Aliment[i] = new JLabel(new ImageIcon(getClass().getResource(nom[i])));		
			tabPixel[i] = new Pixel(p.getX()*31 + i*31 + 15, p.getY()*31 + 30);
			x[i] = pos.getX()*31 + i*31 + 14;
			y[i] = pos.getY()*31 + 21;
		}
		
		this.fen.setAliment(this);
	}
}
