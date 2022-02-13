package Launching;

import Plateau.Map;
import Window.Fenetre;

//Fonction contenant l'�tat du jeu

public class Etat {
	
	private String etat; //Nom de l'�tat
	private Setup setup; //Setup
	private int vies; //Nombre de vies
	private int score; //Score de la partie
	
	public Etat(Fenetre fen, Map map) {
		vies = 3;
		score = 0;
		setEtatTitleScreen(); //On initialise l'�tat � titleScreen
		map.setEtat(this); //On donne l'�tat � map
		fen.setFenEtat(this);
	}
	
	//Fonction Initialisant le setup de Etat
	public void setSetup(Setup setup) {
		this.setup = setup;
	}
	
	//Fonction retournant le string de Etat
	public String getEtat() {
		return etat;
	}
	
	//Fonction passant l'�tat � titleScreen
	public void setEtatTitleScreen() {
		etat = "titleScreen";
	}
	
	//Fonction passant l'�tat � titleScreenSoloMulti
	public void setEtatTitleScreenSoloMulti() {
		etat = "titleScreenSoloMulti";
	}
	
	public void setEtatTitleScreenHebergerRejoindre() {
		etat = "HebergerRejoindre";
	}
	
	public void setEtatWaitingRoom() {
		etat = "WaitingRoom";
	}
	
	//Fonction passant l'�tat � inGame
	public void setEtatInGame() {
		etat = "inGame";
	}
	
	public void setEtatInGameMulti() {
		etat = "inGameMulti";
	}
	
	//Fonction passant l'�tat � leaving
	public void setEtatLeaving() {
		etat = "leaving";
	}
	
	//Fonction retournant le nombre de vies
	public int getVies() {
		return vies;
	}
	
	public int getScore() {
		return score;
	}
	
	//Fonction retirant une vie
	public void retireVie() {
		vies--;
		setup.updateVies(vies);
	}
	
	//Fonction augmentant le score
	public void addScore(int points){
		score += points;
		//System.out.println("Score : " + score);
		setup.updateScore(score);
	}
}
