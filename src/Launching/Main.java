package Launching;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import Plateau.Map;
import Window.*;


public class Main{
	
	public static void main(String[] args) {
		
		//Création de la map
		Map map = new Map();
		//map.afficheGrille();
		
		//Création de la fenêtre
		Fenetre fenetre = new Fenetre();
		
		//Création de l'état du jeu
		Etat etat = new Etat(fenetre, map);
		
		//Création du setup du jeu (écran titre, entitées...)
		Setup setup = new Setup(fenetre, map, etat);
		
		//Boucle mettant à jour les évênements se déroulant sur la fenêtre avec un temps de 3ms entre chaque éxectution
		ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
		executor.scheduleAtFixedRate(() -> {
			if(etat.getVies() == 0 || map.isOver() == 3484) { //Si on tombe à court de vie ou que les burgers sont assemblés
				etat.addScore(etat.getVies()*200); //On ajoute 200pts au socre par vies restantes
				etat.setEtatLeaving(); //On passe l'état du programme à leaving
				setup.end = true; //On passe l'attribut end de setup à true
			}
			
			if(etat.getEtat().equals("inGame")) { //Si l'état du programme est inGame
				fenetre.updateInGame(); //On appelle la fonction updateInGame() de la fenêtre
			}
			
			else if(etat.getEtat().equals("inGameMulti")) { //Sinon si l'état du programme inGameMulti
				fenetre.updateInGameMulti(); //On appelle la fonction updateInGameMulti()
			}
			
			else if(etat.getEtat().equals("leaving")) fenetre.updateLeaving(); //Sinon si l'état est leaving 
																			   //On appelle updateLeaving()
		}, 0, 3, TimeUnit.MILLISECONDS); //On fixe le temps de répétition à 3 millisecondes
	}
}
