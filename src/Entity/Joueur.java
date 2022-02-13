package Entity;

import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import Plateau.Map;
import Position.Pixel;
import Position.Point;
import Window.Fenetre;

public class Joueur extends Entitee{

	protected Pixel pix_hg; //Pixel horizontal gauche (vérifiant le déplacement horizontal gauche)
	protected Pixel pix_hd; //Pixel horizontal droit (vérifiant le déplacement horizontal droit)
	protected Pixel pix_vh; //Pixel vertical haut (vérifiant le déplacement vertical haut)
	protected Pixel pix_vb; //Pixel vertical bas (vérifiant le déplacement vertical bas)
	protected JLabel sprite; //Image du l'entitée
	protected String spriteDroitName, spriteGaucheName;
	protected int x, y; //Position (pixel) de l'entitée
	
	public Joueur(Map map, Fenetre fen) {
		super(map, fen);
	}
	
	public void removeSprite() {
		fen.remove(sprite);
	}
	
	//Getter du pixel horizontal gauche
	public Pixel getHg() {
		return pix_hg;
	}
	
	//Getter du pixel horizontal droit
	public Pixel getHd() {
		return pix_hd;
	}
	
	//Getter du pixel vertical haut
	public Pixel getVh() {
		return pix_vh;
	}
	
	//Getter du pixel vertical bas
	public Pixel getVb() {
		return pix_vb;
	}
	
	//Fonction vérifiant si un déplacement en haut est possible
	public boolean demandeDeplacementHaut() {
		Pixel pix =  new Pixel( pix_vh.getX(), pix_vh.getY() - 1 ); //Création d'un pixel dont le pixel vertical haut est un pixel plus haut que le pixel passé en argument
		Point p = pix.fromPixelToPoint(); //Convertion des coordonnées pixel au coordonnées de la grille
		if(map.getBloc(p).estEscaladable()) return true; //Si le bloc est escaladable en retourne true
		else return false; //Sinon on retourne false
	}
	
	//Fonction effectuant le déplacement en haut
	public void deplacementHaut() {
		if(demandeDeplacementHaut()) {
			pix_hg.setY(pix_hg.getY() - 1); //On décrémente
			pix_hd.setY(pix_hd.getY() - 1); //Tous les pixels
			pix_vh.setY(pix_vh.getY() - 1); //De l'entitée
			pix_vb.setY(pix_vb.getY() - 1); //De 1
			
			y = getVb().getY() - 61; //On met à jour la position y de l'entitée
			sprite.setLocation(x, y); //On replace l'image de l'entitée
		}
	}
	
	//Même fonction que précédemment pour la demande de déplacement bas
	public boolean demandeDeplacementBas() {
		Pixel pix = new Pixel( pix_vb.getX(), pix_vb.getY() + 1 );
		Point p = pix.fromPixelToPoint();
		if(map.getBloc(p).estEscaladable() || map.getBloc(p).estVide()) return true;
		else return false;
	}
	
	//Même fonction que précédemment pour le déplacement bas
	public void deplacementBas() {
		if(demandeDeplacementBas()) {
			pix_hg.setY(pix_hg.getY() + 1);
			pix_hd.setY(pix_hd.getY() + 1);
			pix_vh.setY(pix_vh.getY() + 1);
			pix_vb.setY(pix_vb.getY() + 1);
			
			y = getVb().getY() - 61;
			sprite.setLocation(x, y);
		}
	}
	
	//Même fonction que précédemment pour la demande de déplacement droit
	public boolean demandeDeplacementDroit() {
		Pixel pix =  new Pixel( pix_hd.getX() + 1, pix_hd.getY());
		Point p = pix.fromPixelToPoint();
		if(map.getBloc(p).estAccessible()) return true;
		else return false;
	}
	
	//Même fonction que précédemment le déplacement droit
	public void deplacementDroit() {
		if(demandeDeplacementDroit()) {
			pix_hg.setX(pix_hg.getX() + 1);
			pix_hd.setX(pix_hd.getX() + 1);
			pix_vh.setX(pix_vh.getX() + 1);
			pix_vb.setX(pix_vb.getX() + 1);
			
			x = getVb().getX();
			sprite.setIcon(new ImageIcon(getClass().getResource(spriteDroitName)));
			sprite.setLocation(x, y);
		}
	}
	
	//Même fonction que précédemment pour la demande de déplacement gauche
	public boolean demandeDeplacementGauche() {
		Pixel pix =  new Pixel( pix_hg.getX() - 1, pix_hg.getY());
		Point p = pix.fromPixelToPoint();
		if(map.getBloc(p).estAccessible()) return true;
		else return false;
	}
	
	//Même fonction que précédemment pour le déplacement gauche
	public void deplacementGauche() {
		if(demandeDeplacementGauche()) {
			pix_hg.setX(pix_hg.getX() - 1);
			pix_hd.setX(pix_hd.getX() - 1);
			pix_vh.setX(pix_vh.getX() - 1);
			pix_vb.setX(pix_vb.getX() - 1);
			
			x = getVb().getX();
			sprite.setIcon(new ImageIcon(getClass().getResource(spriteGaucheName)));
			sprite.setLocation(x, y);
		}
	}
	
	//Fonction détectant si l'entitée chute
	public boolean detectionChute() {
		Pixel pix =  new Pixel( pix_vb.getX(), pix_vb.getY() + 1 ); //Création d'un pixel de 1 pixel plus bas que le pixel vertical bas
		Point p = pix.fromPixelToPoint(); //Convertion des coordonnées pixel au coordonnées de la grille
		if(map.getBloc(p).estVide()) { //Si le bloc est vide
			return true; //On renvoie true
		}
		return false; //Sinon on renvoie faux
	}
	
	//Fonction mettant à jour les déplacement de l'entitée
	public void updateJoueur() {
		if(fen.toucheAppuyee.get(KeyEvent.VK_LEFT)) { //Si on presse la flèche gauche
			deplacementGauche(); //On se déplace à gauche
		}
		if (fen.toucheAppuyee.get(KeyEvent.VK_RIGHT)) { //Si on presse la flèche droite
			deplacementDroit(); //On se déplace à droite
		}
		if (fen.toucheAppuyee.get(KeyEvent.VK_UP)) { //Si on presse la flèche haut
			deplacementHaut(); //On se déplace en haut
		}
		if (fen.toucheAppuyee.get(KeyEvent.VK_DOWN)) { //Si on presse la flèche bas
			deplacementBas(); //On se déplace en bas
		}
		if(detectionChute()) { //Si on détecte une chute
			deplacementBas(); //On se déplace en bas
		}
	}
	
	//Retourne un joueur à sa position initiale
	public void resetPos() {
		pix_hg = new Pixel(pos.getX()*31, pos.getY()*31 + 30); //Initialisation du pixel horizontal gauche
		pix_hd = new Pixel(pos.getX()*31 + 30, pos.getY()*31+ 30); //Initialisation du pixel horizontal droite
		pix_vh = new Pixel(pos.getX()*31 + 14, pos.getY()*31); //Initialisation du pixel vertical haut
		pix_vb = new Pixel(pos.getX()*31 + 14, pos.getY()*31 + 30); //Initialisation du pixel vertical bas
		
		y = getVb().getY() - 61;
		x = getVb().getX();
		
		sprite.setLocation(x, y);
	}
	
	//Déplace une entitée à la position donnée
	public void setPixelPos(int x, int y) {
		pix_vb.setX(x);
		pix_vb.setY(y);
		
		pix_vh.setX(x);
		pix_vh.setY(y - 30);
		
		pix_hd.setX(x + 16);
		pix_hd.setY(y);
		
		pix_hg.setX(x - 14);
		pix_hg.setY(y);
		
		this.x = x - 14;
		this.y = y - 61;
		sprite.setLocation(this.x, this.y);
	}
}
