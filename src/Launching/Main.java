package Launching;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import Plateau.Map;
import Window.*;


public class Main{
	
	public static void main(String[] args) {
		
		//Cr�ation de la map
		Map map = new Map();
		//map.afficheGrille();
		
		//Cr�ation de la fen�tre
		Fenetre fenetre = new Fenetre();
		
		//Cr�ation de l'�tat du jeu
		Etat etat = new Etat(fenetre, map);
		
		//Cr�ation du setup du jeu (�cran titre, entit�es...)
		Setup setup = new Setup(fenetre, map, etat);
		
		//Boucle mettant � jour les �v�nements se d�roulant sur la fen�tre avec un temps de 3ms entre chaque �xectution
		ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
		executor.scheduleAtFixedRate(() -> {
			if(etat.getVies() == 0 || map.isOver() == 3484) { //Si on tombe � court de vie ou que les burgers sont assembl�s
				etat.addScore(etat.getVies()*200); //On ajoute 200pts au socre par vies restantes
				etat.setEtatLeaving(); //On passe l'�tat du programme � leaving
				setup.end = true; //On passe l'attribut end de setup � true
			}
			
			if(etat.getEtat().equals("inGame")) { //Si l'�tat du programme est inGame
				fenetre.updateInGame(); //On appelle la fonction updateInGame() de la fen�tre
			}
			
			else if(etat.getEtat().equals("inGameMulti")) { //Sinon si l'�tat du programme inGameMulti
				fenetre.updateInGameMulti(); //On appelle la fonction updateInGameMulti()
			}
			
			else if(etat.getEtat().equals("leaving")) fenetre.updateLeaving(); //Sinon si l'�tat est leaving 
																			   //On appelle updateLeaving()
		}, 0, 3, TimeUnit.MILLISECONDS); //On fixe le temps de r�p�tition � 3 millisecondes
	}
}
