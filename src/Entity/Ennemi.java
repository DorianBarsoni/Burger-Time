	package Entity;

import Plateau.Map;
import Position.*;
import Window.Fenetre;

public class Ennemi extends Joueur{
	
	protected Cuisinier cuisinier;
	private Dijkstra[][] tableau = new Dijkstra[28][61];
	private int attente;
	
	public Ennemi(Map map, Fenetre fen) {
		super(map, fen);
		attente = 0;
	}
	
	public void updateEnnemi(){
		attente++; //Attribut permettant de ralentir le déplacement ennemi 
		if(attente%2 == 0) {
			Point posCuisinier = cuisinier.getVb().fromPixelToPoint(); //On prend les pos du cuisinier
			Point posCuisinierR = new Point(posCuisinier.getY(), posCuisinier.getX()); //On inverse le x et le y
			
			Point posEnnemi = this.getVb().fromPixelToPoint(); //On prend les pos de l'ennemi
			Point posEnnemiR = new Point(posEnnemi.getY(), posEnnemi.getX()); //On inverse le x et le y
			
			Point deplacement = cheminLePlusCourt(posEnnemiR, posCuisinierR); //On obtient le point de deplacement
			deplacement(deplacement); //On se deplace
		}
		
		if(detectionChute()) deplacementBas(); //On détecte les chutes
	}
	
	public void addCuisinier(Cuisinier cuisinier) {
		this.cuisinier = cuisinier;
	}

	//Fonction permettant le déplacement sur un point adjacent
	public void deplacement(Point p) {
		
		int dx = p.getY() - this.getVb().fromPixelToPoint().getX(); //dx le différenciel x
		int dy = p.getX() - this.getVb().fromPixelToPoint().getY(); //dy le différenciel y
		
		if(dy > 0) { //Si dy est positif
			this.deplacementBas(); //On se déplace vers le bas
		}
		else if(dy < 0) { //Sinon s'il est négatif
			this.deplacementHaut(); //On se déplace vers le haut
		}
		else if (dx > 0) { //Sinon si dx est positif
			this.deplacementDroit(); //On se déplace vers la droite
		}
		else if (dx < 0) { //Sinon s'il est négatif
			this.deplacementGauche(); //On se déplace vers la gauche
		}
	}
	
	protected void createTableau() {
		int[][] tab = map.tab1();
		
		for(int largeur=0; largeur<28; largeur++) {
			for(int longueur=0; longueur<61; longueur++) {
				if(tab[largeur][longueur] == 1) tableau[largeur][longueur] = new Dijkstra(new Point(largeur, longueur));
				else tableau[largeur][longueur] = new Dijkstra(new Point(-1, -1));
			}
		}
		
		for(int largeur=0; largeur<28; largeur++) {
			for(int longueur=0; longueur<61; longueur++) {
				if(tab[largeur][longueur] == 1) {
					if(tab[largeur][longueur - 1] == 1) { //Case de gauche
						tableau[largeur][longueur].addSommetAdj(tableau[largeur][longueur - 1]);
					}
					if(tab[largeur][longueur + 1] == 1) { //Case de droite
						tableau[largeur][longueur].addSommetAdj(tableau[largeur][longueur + 1]);
					}
					if(tab[largeur - 1][longueur] == 1) { //Case du haut
						tableau[largeur][longueur].addSommetAdj(tableau[largeur - 1][longueur]);
					}
					if(tab[largeur + 1][longueur] == 1) { //Case du bas
						tableau[largeur][longueur].addSommetAdj(tableau[largeur + 1][longueur]);
					}
				}
			}
		}
	}
	
	public void resetValeurs() {
		for(int i=0; i<28; i++) {
			for(int j=0; j<61; j++) {
				tableau[i][j].valeur = 422;
			}
		}
	}
	
	public Point cheminLePlusCourt(Point Depart, Point Arrive) {
		
		Dijkstra Debut = tableau[Depart.getX()][Depart.getY()];
		Dijkstra Fin = tableau[Arrive.getX()][Arrive.getY()];
		Pile pile = new Pile(422);
		
		if(Fin.sommet.isEqual(new Point(-1, -1))) return Debut.sommet;
		
		Debut.valeur = 0;
		Debut.predecesseur = Debut;
		pile.push(Debut);
		
		Dijkstra SommetCourant;
		
		while(pile.sommet_pile != 0) {
			SommetCourant = pile.pop();
			
			for(int i=0; i<SommetCourant.taille_sommetAdj; i++) {
				if(SommetCourant.sommetAdj[i] != SommetCourant.predecesseur && SommetCourant != Fin) {
					if(SommetCourant.sommetAdj[i].valeur > SommetCourant.valeur + 1) {
						pile.push(SommetCourant.sommetAdj[i]);
						SommetCourant.sommetAdj[i].predecesseur = SommetCourant;
						SommetCourant.sommetAdj[i].valeur = SommetCourant.valeur + 1;
					}
				}
			}
		}
		
		SommetCourant = Fin;
		
		while(SommetCourant.predecesseur != Debut) {
			SommetCourant = SommetCourant.predecesseur;
		}
		
		resetValeurs();
		//for(int i=taille-1; i>-1; i--) tab[i].affichePoint();
		return SommetCourant.sommet;
	}
	
	public boolean checkIfContact() {
		
		if(this.getVb().getY() == cuisinier.getVb().getY()) {
			if(cuisinier.getHg().getX() <= this.getHg().getX() && this.getHg().getX() <= cuisinier.getHd().getX()) return true;
			else if(cuisinier.getHg().getX() <= this.getHd().getX() && this.getHd().getX() <= cuisinier.getHd().getX()) return true;
		}
		return false;
	}
}


