package Entity;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import Launching.Etat;
import Plateau.Map;
import Position.Pixel;
import Position.Point;
import Window.Fenetre;

public class Salade extends Aliment{
	
	//Voir les commentaires de la classe Pain_inferieur
	
	public Salade(Map map, Fenetre fen, Joueur joueur, int colonne, Etat etat, Point p) {
		
		super(map, fen, joueur, colonne, etat);
		pos = new Point(p.getX(), p.getY());
		
		String[] nom = {"/images/salade/salade1.png",
						"/images/salade/salade2.png",
						"/images/salade/salade3.png",
						"/images/salade/salade4.png",
						"/images/salade/salade5.png",
						"/images/salade/salade6.png"};
		
		for(int i=0; i<6; i++) {
			Aliment[i] = new JLabel(new ImageIcon(getClass().getResource(nom[i])));		
			tabPixel[i] = new Pixel(p.getX()*31 + i*31 + 15, p.getY()*31 + 30);
			x[i] = pos.getX()*31 + i*31 + 14;
			y[i] = pos.getY()*31 + 21;
		}
		
		this.fen.setAliment(this);
	}

}
