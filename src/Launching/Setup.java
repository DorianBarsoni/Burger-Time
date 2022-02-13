package Launching;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import Entity.*;
import Plateau.Map;
import Position.Point;
import Window.Fenetre;
import Reseau.*;

public class Setup {
	
	//Classe mettant à jour les différents éléments à l'écran
	
	//Déclaration de tous les éléments présents dans le jeu
	private Fenetre fenetre;
	private Cuisinier cuisinier;
	private Etat etat;
	
	private Pain_inferieur painInferieur1;
	private Steak steak1;
	private Salade salade1;
	private Pain_superieur painSuperieur1;
	
	private Pain_inferieur painInferieur2;
	private Steak steak2;
	private Salade salade2;
	private Pain_superieur painSuperieur2;
	
	private Pain_inferieur painInferieur3;
	private Steak steak3;
	private Salade salade3;
	private Pain_superieur painSuperieur3;
	
	private Pain_inferieur painInferieur4;
	private Steak steak4;
	private Salade salade4;
	private Pain_superieur painSuperieur4;
	
	private Oeuf oeuf;
	private Piment piment;
	private Saucisse saucisse;
	
	private JLabel vies; //Label contenant les vies
	private JLabel[] score; //Label contenant le score
	
	private Serveur serveur;
	
	private Client[] clients;
	private int nbClients;
	private JLabel[] spriteEnnemis;
	public int clientID;
	
	public boolean end;
	
	public Setup(Fenetre fenetre, Map map, Etat etat) { 
		
		end = false;
		
		nbClients = 0;
		
		clients = new Client[4];
		
		this.fenetre = fenetre;
		this.etat = etat;
		
		//Création du cuisinier
		cuisinier = new Cuisinier(map, fenetre);
		
		//Création du premier burger
		painInferieur1 = new Pain_inferieur(map, fenetre, cuisinier, 0, etat, new Point(3, 26));
		steak1 = new Steak(map, fenetre, cuisinier, 0, etat, new Point(3, 23));
		salade1 = new Salade(map, fenetre, cuisinier, 0, etat, new Point(3, 6));
		painSuperieur1 = new Pain_superieur(map, fenetre, cuisinier, 0, etat, new Point(3, 2));
		
		//Création du deuxième burger
		painInferieur2 = new Pain_inferieur(map, fenetre, cuisinier, 1, etat, new Point(20, 23));
		steak2 = new Steak(map, fenetre, cuisinier, 1, etat, new Point(20, 13));
		salade2 = new Salade(map, fenetre, cuisinier, 1, etat, new Point(20, 6));
		painSuperieur2 = new Pain_superieur(map, fenetre, cuisinier, 1, etat, new Point(20, 2));
		
		//Création du troisième burger
		painInferieur3 = new Pain_inferieur(map, fenetre, cuisinier, 2, etat, new Point(35, 26));
		steak3 = new Steak(map, fenetre, cuisinier, 2, etat, new Point(35, 23));
		salade3 = new Salade(map, fenetre, cuisinier, 2, etat, new Point(35, 9));
		painSuperieur3 = new Pain_superieur(map, fenetre, cuisinier, 2, etat, new Point(35, 2));
		
		//Création du quatrième burger
		painInferieur4 = new Pain_inferieur(map, fenetre, cuisinier, 3, etat, new Point(52, 26));
		steak4 = new Steak(map, fenetre, cuisinier, 3, etat, new Point(52, 19));
		salade4 = new Salade(map, fenetre, cuisinier, 3, etat, new Point(52, 12));
		painSuperieur4 = new Pain_superieur(map, fenetre, cuisinier, 3, etat, new Point(52, 2));
		
		//Création d'un ennemi Oeuf
		oeuf = new Oeuf(map, fenetre, 52, 2);
		oeuf.addCuisinier(cuisinier);
		
		//Création d'un ennemi Oeuf
		piment = new Piment(map, fenetre, 59, 26);
		piment.addCuisinier(cuisinier);
		
		//Création d'un ennemi Oeuf
		saucisse = new Saucisse(map, fenetre, 1, 26);
		saucisse.addCuisinier(cuisinier);
		
		//Création du label des vies
		vies = new JLabel(new ImageIcon(getClass().getResource("/images/chiffres/3.png")));
		vies.setBounds(344, 948, 32, 48);
		
		//Création des labels des vies
		score = new JLabel[5];
		
		for(int i=0; i<5; i++) {
			score[i] = new JLabel(new ImageIcon(getClass().getResource("/images/chiffres/0.png")));
			score[i].setBounds(1371 + i*40, 948, 32, 48);
		}
		
		spriteEnnemis = new JLabel[3];
		for(int i=0; i<3; i++) {
			spriteEnnemis[i] = new JLabel(new ImageIcon(getClass().getResource("/images/personnages/noir.png")));
			spriteEnnemis[i].setBounds(1341 + i*139, 616, 70, 140);
		}
		
		this.fenetre.setFenSetup(this); //Passage de setup à fenetre
		this.etat.setSetup(this); //Passage de setup à etat
		setupTitleScreen(); //Mise en place de l'écran titre
	}
	
	//Fonction mettant en place l'écran titre
	public void setupTitleScreen() {
		fenetre.fenSelectButtonSetup(535, 474); //Ajout du bouton de sélection
		fenetre.fenTitleSetup(); //Ajout du fond
		fenetre.setVisible(true); //Visibilité de la fenêtre à true
		fenetre.repaint(); //Mise à jour des labels (inutile au lancement mais utile en cas de retour en arrière)
	}
	
	//Fonction supprimant les éléments de l'écran titre
	public void removeTitleScreen() {
		fenetre.fenSelectButtonRemove(); //Suppression du bouton de sélection
		fenetre.fenTitleRemove(); //Suppression 
	}
	
	//Fonction mettant en place l'écran titre solo/multi
	public void setupTitleScreenSoloMulti() {
		fenetre.fenSelectButtonSetup(535, 544); //Ajout du bouton de sélection
		fenetre.fenTitleScreenSoloMultiSetup(); //ajout du fond
		fenetre.repaint(); //Mise à jour des labels
	}
	
	//Fonction supprimant les éléments de l'écran titre solo/multi
	public void removeTitleScreenSoloMulti() {
		fenetre.fenSelectButtonRemove(); //Suppression du bouton de sélection
		fenetre.fenTitleScreenSoloMultiRemove(); //Suppression du fond
	}
	
	public void setupTitleScreenHebergerRegoindre() {
		fenetre.fenSelectButtonSetup(535, 544); //Ajout du bouton de sélection
		fenetre.fenTitleScreenHebergerRejoindreSetup(); //ajout du fond
		fenetre.repaint(); //Mise à jour des labels
	}
	
	public void removeTitleScreenHebergerRejoindre() {
		fenetre.fenSelectButtonRemove(); //Suppression du bouton de sélection
		fenetre.fenTitleScreenHebergerRejoindreRemove();
	}
	
	public void setupWaitingRoom() {
		for(int i=0; i<3; i++) fenetre.add(spriteEnnemis[i]);
		fenetre.fenWaitingRoomSetup();
		fenetre.repaint();
	}
	
	public void removeWaitingRoom() {
		for(int i=0; i<3; i++) fenetre.remove(spriteEnnemis[i]);
		fenetre.fenWaitingRoomRemove();
	}
	
	//Fonction mettant en place l'écran de jeu
	public void setupInGame() {
		//Ajout du cuisinier sur la fenêtre
		cuisinier.setup();
		
		oeuf.setup();
		piment.setup();
		saucisse.setup();
		
		//Ajout du premier burger sur la fenêtre
		painInferieur1.setupAliment();
		steak1.setupAliment();
		salade1.setupAliment();
		painSuperieur1.setupAliment();
		
		//Ajout du deuxième burger sur la fenêtre
		painInferieur2.setupAliment();
		steak2.setupAliment();
		salade2.setupAliment();
		painSuperieur2.setupAliment();
		
		//Ajout du troisème burger sur la fenêtre
		painInferieur3.setupAliment();
		steak3.setupAliment();
		salade3.setupAliment();
		painSuperieur3.setupAliment();
		
		//Ajout du quatrième burger sur la fenêtre
		painInferieur4.setupAliment();
		steak4.setupAliment();
		salade4.setupAliment();
		painSuperieur4.setupAliment();
		
		//Ajout du nombre de vies
		fenetre.add(vies);
		
		//Ajout des chiffres du score
		for(int i=0; i<5; i++) {
			fenetre.add(score[i]);
		}
		
		//Ajout du fond sur la fenêtre
		fenetre.fenGameSetup();
		//Mise à jour des labels
		fenetre.repaint();
	}
	
	//Fonction suppriman les éléments de la fenêtre de jeu
	public void removeInGame() {
		cuisinier.removeSprite(); //Suppression du cuisinier
		
		oeuf.removeSprite();
		piment.removeSprite();
		saucisse.removeSprite();
		
		//Suppression du burger 1
		painInferieur1.removeAliment();
		steak1.removeAliment();
		salade1.removeAliment();
		painSuperieur1.removeAliment();
		
		//Suppression du burger 2
		painInferieur2.removeAliment();
		steak2.removeAliment();
		salade2.removeAliment();
		painSuperieur2.removeAliment();
		
		//Suppression du burger 3
		painInferieur3.removeAliment();
		steak3.removeAliment();
		salade3.removeAliment();
		painSuperieur3.removeAliment();
		
		//Suppression du burger 4
		painInferieur4.removeAliment();
		steak4.removeAliment();
		salade4.removeAliment();
		painSuperieur4.removeAliment();
		
		fenetre.remove(vies); //Supression du compteur de vie
		
		//Supression du score
		for(int i=0; i<5; i++) {
			fenetre.remove(score[i]);
		}
		
		fenetre.fenGameRemove(); //Supression du fond
	}
	
	//Fonction mettant à jour le compteur de vie
	public void updateVies(int vies) {
		//On prend l'entier vies
		switch(vies) {
			case(3) : //Si il vaut 3
				this.vies.setIcon(new ImageIcon(getClass().getResource("/images/chiffres/3.png"))); //On charge 3.png
				break;
			case(2) : //Si il vaut 2
				this.vies.setIcon(new ImageIcon(getClass().getResource("/images/chiffres/2.png"))); //On charge 2.png
				break;
			case(1) : //Si il vaut 1
				this.vies.setIcon(new ImageIcon(getClass().getResource("/images/chiffres/1.png"))); //On charge 1.png
				break;
		}
		//fenetre.repaint(); //On met à jour les labels
	}
	
	//Fonction mettantà jour le score
	public void updateScore(int score) {
		int[] chScore = new int[5];
		
		//Pour i allant de 4 à 0
		for(int i=4; i>-1; i--) {
			chScore[i] = score%10; //chScore[i] prend la valeur du score modulo 10
			score /= 10; // On divise le score par 10
			
			//On prend l'entire chScore[i]
			switch(chScore[i]) {
				case(0) : //Si il vaut 0
					this.score[i].setIcon(new ImageIcon(getClass().getResource("/images/chiffres/0.png"))); //On charge 0.png
					break;
				case(1) : //Si il vaut 1
					this.score[i].setIcon(new ImageIcon(getClass().getResource("/images/chiffres/1.png"))); //On charge 1.png
					break;
				case(2) : //Si il vaut 2
					this.score[i].setIcon(new ImageIcon(getClass().getResource("/images/chiffres/2.png"))); //On charge 2.png
					break;
				case(3) : //Si il vaut 3
					this.score[i].setIcon(new ImageIcon(getClass().getResource("/images/chiffres/3.png"))); //On charge 3.png
					break;
				case(4) : //Si il vaut 4
					this.score[i].setIcon(new ImageIcon(getClass().getResource("/images/chiffres/4.png"))); //On charge 4.png
					break;
				case(5) : //Si il vaut 5
					this.score[i].setIcon(new ImageIcon(getClass().getResource("/images/chiffres/5.png"))); //On charge 5.png
					break;
				case(6) : //Si il vaut 6
					this.score[i].setIcon(new ImageIcon(getClass().getResource("/images/chiffres/6.png"))); //On charge 6.png
					break;
				case(7) : //Si il vaut 7
					this.score[i].setIcon(new ImageIcon(getClass().getResource("/images/chiffres/7.png"))); //On charge 7.png
					break;
				case(8) : //Si il vaut 8
					this.score[i].setIcon(new ImageIcon(getClass().getResource("/images/chiffres/8.png"))); //On charge 8.png
					break;
				case(9) : //Si il vaut 9
					this.score[i].setIcon(new ImageIcon(getClass().getResource("/images/chiffres/9.png"))); //On charge 9.png
					break;
			}
		}
	}
	
	//Fonction créant un serveur
	public void creationServeur() {
		serveur = new Serveur(this);
		serveur.start();
		
		creationClient();
	}
	
	//Fonction créant un client
	public void creationClient() {
		if(nbClients == 4) {
			System.out.println("Nombre de clients maximum atteint");
			return;
		}
		clients[nbClients] = new Client(this);
		clients[nbClients].start();
		nbClients++;
	}
	
	//Fonction mettant à jour les sprites en fonction de l'id reçu
	public void setSpritEnnemi(int id) {
		
		switch(id) {
			case(0) :
				spriteEnnemis[id].setIcon(new ImageIcon(getClass().getResource("/images/personnages/oeuf_grand.png")));
				break;
			case(1) :
				spriteEnnemis[id].setIcon(new ImageIcon(getClass().getResource("/images/personnages/piment_grand.png")));
				break;
			case(2) :
				spriteEnnemis[id].setIcon(new ImageIcon(getClass().getResource("/images/personnages/saucisse_grand.png")));
				break;
		}
	}
	
	//Fonction mettant à jour l'id du client
	public void setClientID(int id) {
		clientID = id;
	}
	
	//Fonction mettant à jour le programme pour passer en mode jeu multijoueur
	public void enterInGameMulti() {
		removeWaitingRoom();
		setupInGame();
		etat.setEtatInGameMulti();
	}
	
	//Fonction retournant la pos x d'une entitée en fonction de l'id reçu
	public int getMyPosX(int id) {
		switch(id) {
			case(0) :
				return cuisinier.getVb().getX();
			case(1) :
				return oeuf.getVb().getX();
			case(2) :
				return piment.getVb().getX();
			case(3) :
				return saucisse.getVb().getX();
			default :
				System.out.println("ERREUR : ID INEXISTANTE");
				return 0;
		}
	}
	
	//Fonction retournant la pos y d'une entitée en fonction de l'id reçu
	public int getMyPosY(int id) {
		switch(id) {
			case(0) :
				return cuisinier.getVb().getY();
			case(1) :
				return oeuf.getVb().getY();
			case(2) :
				return piment.getVb().getY();
			case(3) :
				return saucisse.getVb().getY();
			default :
				System.out.println("ERREUR : ID INEXISTANTE");
				return 0;
		}
	}
	
	//Fonction mettant à jour la position d'une entitée en fonction de l'id et des coordonnées reçues
	public void updateMyEntities(int idRecu, int x, int y) {
		switch(idRecu) { //En fonction de l'id reçu
			case(0) :
				cuisinier.setPixelPos(x, y);
				break; //Mise à jour des pos du cuisinier
			case(1) :
				oeuf.setPixelPos(x, y);
				break; //Mise a jour des pos de l'oeuf
			case(2) :
				piment.setPixelPos(x, y);
				break; //Mise a jour des pos du piment
			case(3) :
				saucisse.setPixelPos(x, y);
				break; //Mise à jour des pos de la saucisse
		}
	}
	
}
