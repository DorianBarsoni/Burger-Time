package Window;

import Entity.*;
import Launching.Etat;
import Launching.Setup;
import Reseau.Client;
import Reseau.Serveur;

import java.awt.event.*;
import javax.swing.*;
import java.util.HashMap;


@SuppressWarnings("serial")
public class Fenetre extends JFrame implements KeyListener {
	
	//Classe créant la fenêtre du jeu
	
	private JLabel fondTitleScreen; //Label contenant  le fond d'écran de l'écran titre
	private JLabel SelectButton; //Bouton de sélection du menu
	private JLabel fondTitleScreenSoloMulti;
	private JLabel fondTitleScreenHebergerRejoindre;
	private JLabel fondWaitingRoom;
	private int xSelectButton, ySelectButton;
	private JLabel fondJeu; //Label contenant le fond d'écran du jeu
	private Joueur joueur; //Entitée rattachée à la fenêtre 
	private Ennemi[] ennemi;
	private int taille_ennemi;
	private Aliment[] aliment; //Tableau contenant les aliments
	private int taille_aliment; //Entier représentant la taille du tableau des aliments
	private Etat state; //Etat du programme
	private Setup setup; //Setup du programme
	public HashMap<Integer, Boolean> toucheAppuyee; //HashMap permettant de capturer les touches en temps réel
	
	
	public Fenetre() {
		
		fondTitleScreen = new JLabel(new ImageIcon(getClass().getResource("/images/fonds/TitleScreen.png"))); //Création du fond de l'écran titre
		fondTitleScreen.setBounds(0, 0, 1920, 1080); //Positionnement
		SelectButton = new JLabel(new ImageIcon(getClass().getResource("/images/fonds/SelectButton.png"))); //Création du bouton de sélection
		fondTitleScreenSoloMulti = new JLabel(new ImageIcon(getClass().getResource("/images/fonds/TitleScreenSoloMulti.png")));
		fondTitleScreenSoloMulti.setBounds(0, 0, 1920, 1080);
		fondJeu = new JLabel(new ImageIcon(getClass().getResource("/images/fonds/map.png"))); //Création du fond du jeu
		fondJeu.setBounds(0, 0, 1920, 1080); //Positionnement
		fondTitleScreenHebergerRejoindre = new JLabel(new ImageIcon(getClass().getResource("/images/fonds/TitleScreenHebergerRejoindre.png")));
		fondTitleScreenHebergerRejoindre.setBounds(0, 0, 1920, 1080);
		fondWaitingRoom = new JLabel(new ImageIcon(getClass().getResource("/images/fonds/WaitingRoom.png")));
		fondWaitingRoom.setBounds(0, 0, 1920, 1080);
		
		setTitle("Burger Time"); //Titre de la fenêtre
		setSize(1920, 1080);
		setExtendedState(JFrame.MAXIMIZED_BOTH); //Dimensionne la fenêtre de la taille de l'écran
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Arret du processus lorsqu'on appuie sur la croix
		setLayout(null); //Aucune stratégie de positionnement sélectionnée
		setLocationRelativeTo(null); //Centre la fenêtre
		//setResizable(false); //Empêche le redimensionnement de la fenêtre
		addKeyListener(this); //Ajout de l'écoute des entrées clavier
		
		toucheAppuyee = new HashMap<Integer, Boolean>(); //Initialisation du HashMap
		toucheAppuyee.put(KeyEvent.VK_LEFT, false); //Touche gauche
		toucheAppuyee.put(KeyEvent.VK_RIGHT, false); //Touche droite
		toucheAppuyee.put(KeyEvent.VK_UP, false); //Touche haut
		toucheAppuyee.put(KeyEvent.VK_DOWN, false); //Touche bas
		
		aliment = new Aliment[16]; //Initialisation du tableau des aliments
		taille_aliment = 0; //Initialisation du compteur du tableau des aliments
		 
		ennemi = new Ennemi[3]; //Initialisation du tableau des ennemis
		taille_ennemi = 0; //Initialisation du compteur du tableau des ennemis
	}
	
	//Fonction ajoutant le fond de l'écran titre
	public void fenTitleSetup() {
		add(fondTitleScreen);
	}
	
	//Fonction retirant le fond du jeu
	public void fenTitleRemove() {
		remove(fondTitleScreen);
	}
	
	//Fonction ajoutant le bouton de sélection à l'écran
	public void fenSelectButtonSetup(int x, int y) {
		xSelectButton = x; //Mise a jour de sa coordonnée x
		ySelectButton = y; //Mise à jour de sa coordonnée y
		SelectButton.setBounds(xSelectButton, ySelectButton, 850, 15); //Positionnement du bouton
		add(SelectButton); //Ajout
	}
	
	//Fonction supprimant le boutton de sélection
	public void fenSelectButtonRemove() {
		remove(SelectButton);
	}
	
	//Fonction ajoutant l'écran titre solo/multi
	public void fenTitleScreenSoloMultiSetup() {
		add(fondTitleScreenSoloMulti);
	}
	
	//Fonction supprimant l'écran titre solo/multi
	public void fenTitleScreenSoloMultiRemove() {
		remove(fondTitleScreenSoloMulti);
	}
	
	public void fenTitleScreenHebergerRejoindreSetup() {
		add(fondTitleScreenHebergerRejoindre);
	}
	
	public void fenTitleScreenHebergerRejoindreRemove() {
		remove(fondTitleScreenHebergerRejoindre);
	}
	
	public void fenWaitingRoomSetup() {
		add(fondWaitingRoom);
	}
	
	public void fenWaitingRoomRemove() {
		remove(fondWaitingRoom);
	}
	
	//Fonction ajoutant le fond du jeu
	public void fenGameSetup() {
		add(fondJeu);
	}
	
	//Fonction retirant le fond du jeu
	public void fenGameRemove() {
		remove(fondJeu);
	}
	
	//Setter d'entitee
	public void setJoueur(Joueur joueur) {
		this.joueur = joueur;
	}
	
	//Fonction ajoutant un ennemi dans le tableau d'ennemi
	public void setEnnemie(Ennemi ennemi) {
		this.ennemi[taille_ennemi] = ennemi;
		taille_ennemi++;
	}
	
	//Fonction ajoutant un aliment dans le tableau des aliments
	public void setAliment(Aliment aliment) {
		this.aliment[taille_aliment] = aliment;
		taille_aliment++;
	}
	
	//Fonction initialisant etat
	public void setFenEtat(Etat etat) {
		this.state = etat;
	}
	
	//Fonction initialisant setup
	public void setFenSetup(Setup setup) {
		this.setup = setup;
	}
	
	//Detection lorsqu'une touche alphabêtique est pressée puis relachée
	@Override
	public void keyTyped(KeyEvent e) {
	}

	//Detection lorsqu'une touche est pressée
	@Override
	public void keyPressed(KeyEvent e) { 
		
		if(state.getEtat().equals("titleScreen")) updateTitleScreen(e); //Si l'état est titleScreen on appelle updateTitleScreen en lui passant la touche
		else if(state.getEtat().equals("titleScreenSoloMulti")) updateTitleScreenSoloMulti(e); //Si l'état est titleScreenSoloMulti on appelle updateTitleScreenSoloMulti en lui passant la touche
		else if (state.getEtat().equals("HebergerRejoindre")) updateHebergerRejoindre(e);// Si l'état est HebergerRejoindre on appelle updateHebergerRejoindre(e)
		else if(state.getEtat().equals("WaitingRoom")) updateWaitingRoom(e); //Si l'état est WaitingRoom on appelle updateWaitingRoom(e)
		else if(state.getEtat().equals("inGame") || state.getEtat().equals("inGameMulti")) toucheAppuyee.put(e.getKeyCode(), true); //Si l'état est inGame ou inGameMulti on passe à true 
																																	//la touche directionnelle pressée dans le hashMap 
	}

	//Detection lorsqu'une touche est relachée
	@Override
	public void keyReleased(KeyEvent e) {
		if(state.getEtat().equals("inGame") || state.getEtat().equals("inGameMulti")) toucheAppuyee.put(e.getKeyCode(), false); //Si l'état est inGame ou inGameMulti on passe à false 
																																//la touche directionnelle pressée dans le hashMap 
	}

	
	//Fonction mettant à jour l'écran titre
	public void updateTitleScreen(KeyEvent e) {
		
		int touche = e.getKeyCode(); //On récupère la valeur de la touche préssée
		switch (touche) {
			case(KeyEvent.VK_DOWN) : //Si c'est la touche directionnelle du bas
				ySelectButton += 220; //On ajoute 220 à la coordonnée y du bouton de sélection
				break;
			case(KeyEvent.VK_UP) : //Si c'est la touche directionnelle du haut
				ySelectButton -= 220; //On retire 220 à la coordonnée y du bouton de sélection
				break;
			case(KeyEvent.VK_ENTER) : //Si c'est la touche entrer
				//On prend la coordonnée y du bouton de sélection
				switch(ySelectButton) {
					case(474) : //Si elle vaut 474
						setup.removeTitleScreen(); //On retire le setup titleScreen
						setup.setupTitleScreenSoloMulti(); //On ajoute le setup titleScreenSoloMulti
						state.setEtatTitleScreenSoloMulti(); //On passe l'état à titleScreenSoloMulti
						break;
					case(694) : //Si elle vaut 694
						state.setEtatLeaving(); //On passe l'état du programme à leaving
						break;
					case(914) : //Si elle vaut 914
						state.setEtatLeaving(); //On passe l'état du programme à leaving
						break;
				}
			break;
		}
		
		if(ySelectButton < 474) ySelectButton = 914; //Si la coordonnée y du bouton vaut moins de 474 on la met à 914
		if(ySelectButton > 914) ySelectButton = 474; //Si la coordonnée y du bouton vaut plus que 914 on la met à 474
		SelectButton.setLocation(xSelectButton, ySelectButton); //On met à jour la position du bouton
	}
	
	//Fonction mettant à jour l'écran titre solo/multi
	public void updateTitleScreenSoloMulti(KeyEvent e) {
		
		int touche = e.getKeyCode(); //On récupère la valeur de la touche pressée
		switch (touche) {
			case(KeyEvent.VK_DOWN) : //Si c'est la touche directionnelle du bas
				ySelectButton += 263; //On ajoute 263 à la coordonnée y bouton de sélection
				break;
			case(KeyEvent.VK_UP) : //Si c'est la touche directionnelle du haut
				ySelectButton -= 263; //On retire 263 à la coordonnée y bouton de sélection
				break;
			case(KeyEvent.VK_ENTER) : //Si c'est la touche entrer
				//On prend la coordonnée y du bouton
				switch(ySelectButton) {
					case(544) : //Si elle vaut 544
						setup.removeTitleScreenSoloMulti(); //On retire le setup titleScreenSoloMulti
						setup.setupInGame(); //On ajoute le setup inGame
						state.setEtatInGame(); //On passe l'état du programme à inGame
						break;
					case(807) : //Si il vaut 807*
						setup.removeTitleScreenSoloMulti();
						setup.setupTitleScreenHebergerRegoindre();
						state.setEtatTitleScreenHebergerRejoindre(); //On passe l'état du programme à leaving
						break;
				}
				break;
			case(KeyEvent.VK_ESCAPE) : //Si c'est la touche echap
				setup.removeTitleScreenSoloMulti(); //On retire le setup titleScreenSoloMulti
				setup.setupTitleScreen(); //On ajoute le setup titleScreen
				state.setEtatTitleScreen(); //On passe l'état du programme à titleScreen
				break;
		}
		
		if(ySelectButton < 544 && state.getEtat().equals("titleScreenSoloMulti")) ySelectButton = 807; //Si la coordonnée y du bouton est inférieur à 544
		//et qu'on est dans l'état titleScreenSoloMulti (Sinon la coordonnée y est modifiée quand on repasse à l'état titleScreen) on la passe à 807
		if(ySelectButton > 807) ySelectButton = 544; //Si la coordonnée y du bouton est supérieur à 807 on la passe à 544
		SelectButton.setLocation(xSelectButton, ySelectButton); //On met à jour la position du bouton
	}
	
	public void updateHebergerRejoindre(KeyEvent e) {
		
		int touche = e.getKeyCode(); //On récupère la valeur de la touche pressée
		switch (touche) {
			case(KeyEvent.VK_DOWN) : //Si c'est la touche directionnelle du bas
				ySelectButton += 263; //On ajoute 263 à la coordonnée y bouton de sélection
				break;
			case(KeyEvent.VK_UP) : //Si c'est la touche directionnelle du haut
				ySelectButton -= 263; //On retire 263 à la coordonnée y bouton de sélection
				break;
			case(KeyEvent.VK_ENTER) : //Si c'est la touche entrer
				//On prend la coordonnée y du bouton
				switch(ySelectButton) {
					case(544) : //Si elle vaut 544
						setup.creationServeur();
						setup.removeTitleScreenHebergerRejoindre();
						setup.setupWaitingRoom();
						state.setEtatWaitingRoom();
						//state.setEtatLeaving(); //On passe l'état du programme à leaving
						break;
					case(807) : //Si il vaut 807
						setup.creationClient();
						setup.removeTitleScreenHebergerRejoindre();
						setup.setupWaitingRoom();
						state.setEtatWaitingRoom();
						
						//state.setEtatLeaving(); //On passe l'état du programme à leaving
						break;
				}
				break;
			case(KeyEvent.VK_ESCAPE) : //Si c'est la touche echap
				setup.removeTitleScreenHebergerRejoindre(); //On retire le setup titleScreenSoloMulti
				setup.setupTitleScreenSoloMulti(); //On ajoute le setup titleScreen
				state.setEtatTitleScreenSoloMulti(); //On passe l'état du programme à titleScreen
				break;
		}
		
		if(ySelectButton < 544 && state.getEtat().equals("HebergerRejoindre")) ySelectButton = 807; //Si la coordonnée y du bouton est inférieur à 544
		//et qu'on est dans l'état titleScreenSoloMulti (Sinon la coordonnée y est modifiée quand on repasse à l'état titleScreen) on la passe à 807
		if(ySelectButton > 807) ySelectButton = 544; //Si la coordonnée y du bouton est supérieur à 807 on la passe à 544
		SelectButton.setLocation(xSelectButton, ySelectButton); //On met à jour la position du bouton
		
	}
	
	public void updateWaitingRoom(KeyEvent e) {
		int touche = e.getKeyCode(); //On récupère la valeur de la touche pressée
		
		switch(touche) {
			case(KeyEvent.VK_ENTER) :
				if(setup.clientID == 0 ) {
					if(Serveur.numclient != Serveur.max_connexion) Client.pw.println("END");
				}
				
		}
	}
	
	//Fonction update mettant à jour les événements
	public void updateInGame() {
		
		//Évènements du joueur
		joueur.updateJoueur();
		
		//Évènement des aliments
		for(int i=0; i<taille_aliment; i++) {
			aliment[i].updateAliment();
		}
		
		//Check si un aliment tombe sur un autre
		for(int i=0; i<(taille_aliment - 1); i++) {
			aliment[i].checkIfAlimentUp(aliment[i+1].tabPixel[0]);
		}
		
		//Mise à jour des ennemis
		for(int i=0; i<taille_ennemi; i++) {
			ennemi[i].updateEnnemi();
			
			if(ennemi[i].checkIfContact()) { //Si un ennemi touche le joueur
				for(int j=0; j<3; j++) {
					ennemi[j].resetPos(); //On remet les ennemis à leurs
				}						  //Places initiales
				joueur.resetPos(); //On remet le cuisinier à sa place initiale
				state.retireVie(); //On retire une vie
			}
		}
	}
	
	public void updateInGameMulti() {
		
		int id = setup.clientID; //On récupère l'id du joueur
		
		switch(id) { //On le prend
			case(0) :
				joueur.updateJoueur(); //Si il vaut 0 on appelle updateJoueur sur joueur
				break;
			default :
				ennemi[id-1].updateJoueur(); //Sinon on l'appelle sur ennemi[id-1]
				break;
		}
		
		
		//Évènement des aliments
		for(int i=0; i<taille_aliment; i++) {
			aliment[i].updateAliment();
		}
		
		//Check si un aliment tombe sur un autre
		for(int i=0; i<(taille_aliment - 1); i++) {
			aliment[i].checkIfAlimentUp(aliment[i+1].tabPixel[0]);
		}
		
		//Mise à jour des ennemis
		for(int i=0; i<taille_ennemi; i++) {
			
			if(ennemi[i].checkIfContact()) { //Si un ennemi touche le joueur
				for(int j=0; j<3; j++) {
					ennemi[j].resetPos();//On remet les ennemis à leurs
				}						 //Places initiales
				joueur.resetPos(); //On remet le cuisinier à sa place initiale
				state.retireVie(); //On retire une vie
			}
		}
	}
	
	//Fonction terminant le programme
	public void updateLeaving() {
		System.out.println("Score : " + state.getScore());
		dispose();
		System.exit(0);
	}
}
