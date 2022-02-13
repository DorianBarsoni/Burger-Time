package Plateau;

import Launching.Etat;
import Position.Point;

public class Map {
	
	//Classe contenant la carte du jeu
	
	private Etat etat;
	private Blocs[][] grille;
	//String contenant la carte
	private String modele = "X...........................................................X"
						+   "X...........................................................X"
						+   "X.E...........E...............E..............E........E.....X"
						+   "XXEXXXXXXXXXXXEXXXXXXXXXXXXXXXEXXXXXXXXXXXXXXEXXXXXXXXEXXXXXX"
						+   "X.E...........E...............E..B.........B.E........E.....X"
						+   "X.E...........E...............E..B.........B.E....E...E..E..X"
						+   "X.E...E.......E...............E..B..........XEXXXXEXXXXXXEXXX"
						+   "XXXXXXEXXXXXXXEXXXXXXXXXXXXXXXEXX............E..B.E......E..X"
						+   "X.....E.....B.E............B..E..............E..B.E......E..X"
						+   "X.....E.....B.E............B..E..............E..B.E......E..X"
						+   "X.....E.....B.E.............XXEXXXXXXXXXXXXXXEXX..E......E..X"
						+   "X.....E.....B.E...............E..B.........B.E....E......E..X"
						+   "X.....E.....B.E...............E..B.........B.E....E......E..X"
						+   "X.....E.....B.E.......E.......E..B..........XEXXXXXXXXXXXEXXX"
						+   "X.....E......XEXXXXXXXEXXXXXXXEXX............E...........E..X"
						+   "X.....E.......E..B....E....B..E..............E...........E..X"
						+   "X.....E.......E..B....E....B..E..............E...........E..X"
						+   "X.....E.......E..B....E....B..E..............E...........E..X"
						+   "XXXXXXEXXXXXXXXXX.....E....B..E..............E...........E..X"
						+   "X.....E...............E....B..E............E.E...........E..X"
						+   "X.....E...............E.....XXEXXXXXXXXXXXXEXXXXXXXXXXXXXEXXX"
						+   "X.....E...............E.......E............E....B........E..X"
						+   "X.....E...............E.......E............E....B........E..X"
						+   "X.....E............E..E.......E............E....B........E..X"
						+   "XXXXXXEXXXXXXXXXXXXEXXXXXXXXXXEXXXXXXXXXXXXEXXXX.........E..X"
						+   "X.....E............E..........E............E.............E..X"
						+   "X.....E............E..........E............E.............E..X"
						+   "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX"
						+   "............................................................."
						+   "............................................................."
						+   "............................................................."
						+   "............................................................."
						+   ".............................................................";
	private int longueur = 61;
	private int largeur = 33;
	private int[] limitedHeight; //Tableau contenant les hauteurs de chute maximale des colonnes contenant les aliments
	
	public Map() {
		
		//Tableau contenant le modele
		char[] tab_modele = modele.toCharArray();
		
		//On fixe la hauteur de chute des aliments
		limitedHeight = new int[4];
		for(int i=0; i<4; i++) {
			limitedHeight[i] = 931;
		}
		
		grille = new Blocs[this.largeur][this.longueur];
		
		//Initialisation de la grille
		for(int lig=0; lig<largeur; lig++) {
			for(int col=0; col<longueur; col++) {
				char c = tab_modele[lig*longueur + col];
				switch(c) {
					case 'X' :
						grille[lig][col] = new Mur();
						break;
					case '.' :
						grille[lig][col] = new Vide();
						break;
					case 'E' :
						grille[lig][col] = new Echelle();
						break;
					case 'B' :
						grille[lig][col] = new Border();
						break;
					default : 
						System.out.println("Bloc inexistant");	
						break;
				}
			}
		}
		
	}
	
	public int[][] tab1() {
		int[][] tab = new int [largeur-5][longueur];
		for(int i=0; i<longueur; i++) {
			for(int j=0; j<largeur-5; j++) {
				if(grille[j][i].getClass().getSimpleName().equals("Echelle")) tab[j][i] = 1;
				else tab[j][i] = 0;
			}
			for(int j=0; j<largeur-1; j++) {
				if (grille[j][i].getClass().getSimpleName().equals("Vide") && grille[j+1][i].getClass().getSimpleName().equals("Mur")) tab[j][i] = 1;
			}
		}
		return tab;
	}
	
	//Fonction affichant la grille dans la console
	public void afficheGrille() {
			for(int lig=0; lig<largeur; lig++) {
				for(int col=0; col<longueur; col++) {
					String nom = grille[lig][col].getClass().getSimpleName();
					switch(nom) {
						case "Mur" :
							System.out.print("X");
							break;
						case "Vide" :
							System.out.print(".");
							break;
						case "Echelle" :
							System.out.print("E");
							break;
						case "Border" :
							System.out.print("B");
							break;
						default : 
							System.out.print("A");
							break;		
					}
				}
				System.out.println();
			}
	}
	
	//Fonction retournant un bloc de la grille en fonction de la ligne et de la colonne donnée
	public Blocs getBloc(Point p){
		return grille[p.getY()][p.getX()];
	}
	
	//Fonction permettant d'obtenir la hauteur maximum de chute d'une colonne d'aliment
	public int getLimitedHeight(int i) {
		return limitedHeight[i];
	}
	
	//Fonction arrêtant le jeu une fois tous les burgers empilés
	public int isOver() {
		return limitedHeight[0] + limitedHeight[1] + limitedHeight[2] + limitedHeight[3];
	}
	
	//Fonction permettant de modifier la hauteur de chute maximale d'une colonne d'aliments
	public void setLimitedHeight(int nb, int i) {
		limitedHeight[i] = nb;
		etat.addScore(200); //On ajoute 200pts au score quand un aliment tombe dans un rectangle
	}
	
	//Fonction initialisant etat
	public void setEtat(Etat etat) {
		this.etat = etat;
	}
}
