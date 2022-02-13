package Entity;

import javax.swing.JLabel;

import Launching.Etat;
import Position.*;
import Plateau.Map;
import Window.Fenetre;

public class Aliment extends Entitee{

	//Classe d'un aliment
	
	protected JLabel[] Aliment; //Tableau contenant les labels de l'aliment
	public Pixel[] tabPixel; //Tableau contenant un pixel de chaque label
	private Joueur joueur; //Joueur
	private int[] applatit = {0, 0, 0, 0, 0, 0}; //Tableau contenant les zones applatit de l'aliment
	private int verif; //Entier v�rifiant si un aliment est enti�rement applatit
	protected int[] x = {0, 0, 0, 0, 0, 0} , y = {0, 0, 0, 0, 0, 0}; //Tableaux contenant les positions x et y des labels
	private int attente; //Entier permettant de ralentir la chute des aliments
	private boolean priventFall; //Boolean emp�chant la chute des aliments
	private int colonne; //Entier r�pertoriant la colonne de l'aliment
	private Etat etat;
	
	public Aliment(Map map, Fenetre fen, Joueur joueur, int colonne, Etat etat) {
		//Initialisation des variables
		super(map, fen);
		Aliment = new JLabel[6];
		tabPixel = new Pixel[6];
		this.joueur = joueur;
		verif = 0;
		attente = 0;
		priventFall = false;
		this.colonne = colonne;
		this.etat = etat;
	}
	
	//Fonction Initialisant l'aliment sur la fen�tre
	public void setupAliment() {
		for(int i=0; i<6; i++) {
			Aliment[i].setBounds(x[i], y[i], 31, 10);
			fen.add(Aliment[i]);
		}
	}
	
	public void removeAliment() {
		for(int i=0; i<6; i++){
			fen.remove(Aliment[i]);
		}
	}
	
	//Fonction mettant � jour l'aliment sur la fen�tre
	public void updateAliment() {
		
		//Pour chaque label
		for(int i=0; i<6; i++) {
			//Si le joueur marche sur le pixel d'un label
			if( tabPixel[i].isEqual( joueur.getVb() ) ) {
				//Si le label n'est pas applatit
				if(applatit[i] == 0) {
					applatit[i] = 1; //On l'applatit
					verif++; //On incr�mente v�rif
					tabPixel[i].setY(tabPixel[i].getY() + 7); //On modifie la position du pixel
					y[i] = y[i] + 7; //On modifie la position du label
					Aliment[i].setLocation(x[i], y[i]); //On met le label � jour
				}
			}
			if(verif == 6) chute(); //Si verif vaut si on fait chuter l'aliment
		}
	}
	
	public void chute() {
		//On initialise un pixel au dessus du pixel du label
		Pixel pixelHaut = new Pixel(tabPixel[0].getX(), tabPixel[0].getY() - 1);
		
		//On initialise un pixel en dessous du pixel du label
		Pixel pixelBas = new Pixel(tabPixel[0].getX(), tabPixel[0].getY() + 1);
		
		//Convertion en coordonn�es Point
		Point BlocHaut = pixelHaut.fromPixelToPoint();
		Point BlocBas = pixelBas.fromPixelToPoint();
		
		
		//Si le bloc au dessus n'est pas du vide et que le bloc en dessous n'est pas un mur
		if( !(map.getBloc(BlocHaut).getClass().getSimpleName().equals("Vide") && map.getBloc(BlocBas).getClass().getSimpleName().equals("Mur")) ) {
			
			//Si l'aliment ne peut plus tomber on arr�te
			if(priventFall) return;
			
			//On incr�mente attente
			attente++;
			
			//Si attente modulo 8 vaut 0
			if(attente%8 == 0) {
				//Pour chaque label et pixel
				for(int i=0; i<6; i++) {
					//On les fait chuter de 1 pixel
					tabPixel[i].setY(tabPixel[i].getY() + 1);
					y[i]++;
					Aliment[i].setLocation(x[i], y[i]);
				}
			}
		}
		//Sinon
		else {
			
			//Pour chaque label
			for(int j=0; j<6; j++) {
				applatit[j] = 0; //On le d�sapplatit
			}
			verif = 0; //On fixe verif � 0
			etat.addScore(100); //On ajoute 100 au score une fois l'aliment au sol
		}
		
		//Si le label atteint 0 du label atteint la hauteur limite
		if(tabPixel[0].getY() == map.getLimitedHeight(colonne) ) {
			priventFall = true; //On l'emp�che de chuter
			map.setLimitedHeight(map.getLimitedHeight(colonne) - 15, colonne); //On r�duit la limite de chute de 15
		}
	}
	
	//Fonction permettant d'applatir un aliment
	public void applatir() {
		
		//Pour chaque label
		for(int i=0; i<6; i++) {
			
			//Si le label n'est pas applatit
			if(applatit[i] == 0) {
				//On l'applatit et on le met � jour sur la fen�tre
				applatit[i] = 1;
				tabPixel[i].setY( tabPixel[i].getY() + 7);
				y[i] = y[i] + 7;
				Aliment[i].setLocation(x[i], y[i]);
			}
		}
		if(verif != 6) etat.addScore(100); //Si verif est diff�rent de 6 on ajoute 100pts au score
		//(le if est obligatoire car dans certain cas applatir() est appel� plusieurs fois lors d'une m�me chute)
		verif = 6; //On fixe verif � 6	
	}
	
	//Fonction v�rifiant si un aliment tombe sur un autre
	public void checkIfAlimentUp(Pixel p) {
		//On prend un pixel situ� � 10 pixel au dessus du label 0
		Pixel pixelHaut = new Pixel(tabPixel[0].getX(), tabPixel[0].getY() - 10);
		
		//Si le pixel de l'aliment appelant la fonction est �gal au pixel 0 de l'aliment donn� en paramettre
		//On applatit l'aliment appelant la fonction
		if(pixelHaut.isEqual(p)) applatir();
	}
}
