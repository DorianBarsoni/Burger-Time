package Launching;

import Plateau.Map;
import Window.Fenetre;

//Fonction contenant l'état du jeu

public class Etat {
	
	private String etat; //Nom de l'état
	private Setup setup; //Setup
	private int vies; //Nombre de vies
	private int score; //Score de la partie
	
	public Etat(Fenetre fen, Map map) {
		vies = 3;
		score = 0;
		setEtatTitleScreen(); //On initialise l'état à titleScreen
		map.setEtat(this); //On donne l'état à map
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
	
	//Fonction passant l'état à titleScreen
	public void setEtatTitleScreen() {
		etat = "titleScreen";
	}
	
	//Fonction passant l'état à titleScreenSoloMulti
	public void setEtatTitleScreenSoloMulti() {
		etat = "titleScreenSoloMulti";
	}
	
	public void setEtatTitleScreenHebergerRejoindre() {
		etat = "HebergerRejoindre";
	}
	
	public void setEtatWaitingRoom() {
		etat = "WaitingRoom";
	}
	
	//Fonction passant l'état à inGame
	public void setEtatInGame() {
		etat = "inGame";
	}
	
	public void setEtatInGameMulti() {
		etat = "inGameMulti";
	}
	
	//Fonction passant l'état à leaving
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
