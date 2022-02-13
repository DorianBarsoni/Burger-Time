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
	
	//Classe cr�ant la fen�tre du jeu
	
	private JLabel fondTitleScreen; //Label contenant  le fond d'�cran de l'�cran titre
	private JLabel SelectButton; //Bouton de s�lection du menu
	private JLabel fondTitleScreenSoloMulti;
	private JLabel fondTitleScreenHebergerRejoindre;
	private JLabel fondWaitingRoom;
	private int xSelectButton, ySelectButton;
	private JLabel fondJeu; //Label contenant le fond d'�cran du jeu
	private Joueur joueur; //Entit�e rattach�e � la fen�tre 
	private Ennemi[] ennemi;
	private int taille_ennemi;
	private Aliment[] aliment; //Tableau contenant les aliments
	private int taille_aliment; //Entier repr�sentant la taille du tableau des aliments
	private Etat state; //Etat du programme
	private Setup setup; //Setup du programme
	public HashMap<Integer, Boolean> toucheAppuyee; //HashMap permettant de capturer les touches en temps r�el
	
	
	public Fenetre() {
		
		fondTitleScreen = new JLabel(new ImageIcon(getClass().getResource("/images/fonds/TitleScreen.png"))); //Cr�ation du fond de l'�cran titre
		fondTitleScreen.setBounds(0, 0, 1920, 1080); //Positionnement
		SelectButton = new JLabel(new ImageIcon(getClass().getResource("/images/fonds/SelectButton.png"))); //Cr�ation du bouton de s�lection
		fondTitleScreenSoloMulti = new JLabel(new ImageIcon(getClass().getResource("/images/fonds/TitleScreenSoloMulti.png")));
		fondTitleScreenSoloMulti.setBounds(0, 0, 1920, 1080);
		fondJeu = new JLabel(new ImageIcon(getClass().getResource("/images/fonds/map.png"))); //Cr�ation du fond du jeu
		fondJeu.setBounds(0, 0, 1920, 1080); //Positionnement
		fondTitleScreenHebergerRejoindre = new JLabel(new ImageIcon(getClass().getResource("/images/fonds/TitleScreenHebergerRejoindre.png")));
		fondTitleScreenHebergerRejoindre.setBounds(0, 0, 1920, 1080);
		fondWaitingRoom = new JLabel(new ImageIcon(getClass().getResource("/images/fonds/WaitingRoom.png")));
		fondWaitingRoom.setBounds(0, 0, 1920, 1080);
		
		setTitle("Burger Time"); //Titre de la fen�tre
		setSize(1920, 1080);
		setExtendedState(JFrame.MAXIMIZED_BOTH); //Dimensionne la fen�tre de la taille de l'�cran
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Arret du processus lorsqu'on appuie sur la croix
		setLayout(null); //Aucune strat�gie de positionnement s�lectionn�e
		setLocationRelativeTo(null); //Centre la fen�tre
		//setResizable(false); //Emp�che le redimensionnement de la fen�tre
		addKeyListener(this); //Ajout de l'�coute des entr�es clavier
		
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
	
	//Fonction ajoutant le fond de l'�cran titre
	public void fenTitleSetup() {
		add(fondTitleScreen);
	}
	
	//Fonction retirant le fond du jeu
	public void fenTitleRemove() {
		remove(fondTitleScreen);
	}
	
	//Fonction ajoutant le bouton de s�lection � l'�cran
	public void fenSelectButtonSetup(int x, int y) {
		xSelectButton = x; //Mise a jour de sa coordonn�e x
		ySelectButton = y; //Mise � jour de sa coordonn�e y
		SelectButton.setBounds(xSelectButton, ySelectButton, 850, 15); //Positionnement du bouton
		add(SelectButton); //Ajout
	}
	
	//Fonction supprimant le boutton de s�lection
	public void fenSelectButtonRemove() {
		remove(SelectButton);
	}
	
	//Fonction ajoutant l'�cran titre solo/multi
	public void fenTitleScreenSoloMultiSetup() {
		add(fondTitleScreenSoloMulti);
	}
	
	//Fonction supprimant l'�cran titre solo/multi
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
	
	//Detection lorsqu'une touche alphab�tique est press�e puis relach�e
	@Override
	public void keyTyped(KeyEvent e) {
	}

	//Detection lorsqu'une touche est press�e
	@Override
	public void keyPressed(KeyEvent e) { 
		
		if(state.getEtat().equals("titleScreen")) updateTitleScreen(e); //Si l'�tat est titleScreen on appelle updateTitleScreen en lui passant la touche
		else if(state.getEtat().equals("titleScreenSoloMulti")) updateTitleScreenSoloMulti(e); //Si l'�tat est titleScreenSoloMulti on appelle updateTitleScreenSoloMulti en lui passant la touche
		else if (state.getEtat().equals("HebergerRejoindre")) updateHebergerRejoindre(e);// Si l'�tat est HebergerRejoindre on appelle updateHebergerRejoindre(e)
		else if(state.getEtat().equals("WaitingRoom")) updateWaitingRoom(e); //Si l'�tat est WaitingRoom on appelle updateWaitingRoom(e)
		else if(state.getEtat().equals("inGame") || state.getEtat().equals("inGameMulti")) toucheAppuyee.put(e.getKeyCode(), true); //Si l'�tat est inGame ou inGameMulti on passe � true 
																																	//la touche directionnelle press�e dans le hashMap 
	}

	//Detection lorsqu'une touche est relach�e
	@Override
	public void keyReleased(KeyEvent e) {
		if(state.getEtat().equals("inGame") || state.getEtat().equals("inGameMulti")) toucheAppuyee.put(e.getKeyCode(), false); //Si l'�tat est inGame ou inGameMulti on passe � false 
																																//la touche directionnelle press�e dans le hashMap 
	}

	
	//Fonction mettant � jour l'�cran titre
	public void updateTitleScreen(KeyEvent e) {
		
		int touche = e.getKeyCode(); //On r�cup�re la valeur de la touche pr�ss�e
		switch (touche) {
			case(KeyEvent.VK_DOWN) : //Si c'est la touche directionnelle du bas
				ySelectButton += 220; //On ajoute 220 � la coordonn�e y du bouton de s�lection
				break;
			case(KeyEvent.VK_UP) : //Si c'est la touche directionnelle du haut
				ySelectButton -= 220; //On retire 220 � la coordonn�e y du bouton de s�lection
				break;
			case(KeyEvent.VK_ENTER) : //Si c'est la touche entrer
				//On prend la coordonn�e y du bouton de s�lection
				switch(ySelectButton) {
					case(474) : //Si elle vaut 474
						setup.removeTitleScreen(); //On retire le setup titleScreen
						setup.setupTitleScreenSoloMulti(); //On ajoute le setup titleScreenSoloMulti
						state.setEtatTitleScreenSoloMulti(); //On passe l'�tat � titleScreenSoloMulti
						break;
					case(694) : //Si elle vaut 694
						state.setEtatLeaving(); //On passe l'�tat du programme � leaving
						break;
					case(914) : //Si elle vaut 914
						state.setEtatLeaving(); //On passe l'�tat du programme � leaving
						break;
				}
			break;
		}
		
		if(ySelectButton < 474) ySelectButton = 914; //Si la coordonn�e y du bouton vaut moins de 474 on la met � 914
		if(ySelectButton > 914) ySelectButton = 474; //Si la coordonn�e y du bouton vaut plus que 914 on la met � 474
		SelectButton.setLocation(xSelectButton, ySelectButton); //On met � jour la position du bouton
	}
	
	//Fonction mettant � jour l'�cran titre solo/multi
	public void updateTitleScreenSoloMulti(KeyEvent e) {
		
		int touche = e.getKeyCode(); //On r�cup�re la valeur de la touche press�e
		switch (touche) {
			case(KeyEvent.VK_DOWN) : //Si c'est la touche directionnelle du bas
				ySelectButton += 263; //On ajoute 263 � la coordonn�e y bouton de s�lection
				break;
			case(KeyEvent.VK_UP) : //Si c'est la touche directionnelle du haut
				ySelectButton -= 263; //On retire 263 � la coordonn�e y bouton de s�lection
				break;
			case(KeyEvent.VK_ENTER) : //Si c'est la touche entrer
				//On prend la coordonn�e y du bouton
				switch(ySelectButton) {
					case(544) : //Si elle vaut 544
						setup.removeTitleScreenSoloMulti(); //On retire le setup titleScreenSoloMulti
						setup.setupInGame(); //On ajoute le setup inGame
						state.setEtatInGame(); //On passe l'�tat du programme � inGame
						break;
					case(807) : //Si il vaut 807*
						setup.removeTitleScreenSoloMulti();
						setup.setupTitleScreenHebergerRegoindre();
						state.setEtatTitleScreenHebergerRejoindre(); //On passe l'�tat du programme � leaving
						break;
				}
				break;
			case(KeyEvent.VK_ESCAPE) : //Si c'est la touche echap
				setup.removeTitleScreenSoloMulti(); //On retire le setup titleScreenSoloMulti
				setup.setupTitleScreen(); //On ajoute le setup titleScreen
				state.setEtatTitleScreen(); //On passe l'�tat du programme � titleScreen
				break;
		}
		
		if(ySelectButton < 544 && state.getEtat().equals("titleScreenSoloMulti")) ySelectButton = 807; //Si la coordonn�e y du bouton est inf�rieur � 544
		//et qu'on est dans l'�tat titleScreenSoloMulti (Sinon la coordonn�e y est modifi�e quand on repasse � l'�tat titleScreen) on la passe � 807
		if(ySelectButton > 807) ySelectButton = 544; //Si la coordonn�e y du bouton est sup�rieur � 807 on la passe � 544
		SelectButton.setLocation(xSelectButton, ySelectButton); //On met � jour la position du bouton
	}
	
	public void updateHebergerRejoindre(KeyEvent e) {
		
		int touche = e.getKeyCode(); //On r�cup�re la valeur de la touche press�e
		switch (touche) {
			case(KeyEvent.VK_DOWN) : //Si c'est la touche directionnelle du bas
				ySelectButton += 263; //On ajoute 263 � la coordonn�e y bouton de s�lection
				break;
			case(KeyEvent.VK_UP) : //Si c'est la touche directionnelle du haut
				ySelectButton -= 263; //On retire 263 � la coordonn�e y bouton de s�lection
				break;
			case(KeyEvent.VK_ENTER) : //Si c'est la touche entrer
				//On prend la coordonn�e y du bouton
				switch(ySelectButton) {
					case(544) : //Si elle vaut 544
						setup.creationServeur();
						setup.removeTitleScreenHebergerRejoindre();
						setup.setupWaitingRoom();
						state.setEtatWaitingRoom();
						//state.setEtatLeaving(); //On passe l'�tat du programme � leaving
						break;
					case(807) : //Si il vaut 807
						setup.creationClient();
						setup.removeTitleScreenHebergerRejoindre();
						setup.setupWaitingRoom();
						state.setEtatWaitingRoom();
						
						//state.setEtatLeaving(); //On passe l'�tat du programme � leaving
						break;
				}
				break;
			case(KeyEvent.VK_ESCAPE) : //Si c'est la touche echap
				setup.removeTitleScreenHebergerRejoindre(); //On retire le setup titleScreenSoloMulti
				setup.setupTitleScreenSoloMulti(); //On ajoute le setup titleScreen
				state.setEtatTitleScreenSoloMulti(); //On passe l'�tat du programme � titleScreen
				break;
		}
		
		if(ySelectButton < 544 && state.getEtat().equals("HebergerRejoindre")) ySelectButton = 807; //Si la coordonn�e y du bouton est inf�rieur � 544
		//et qu'on est dans l'�tat titleScreenSoloMulti (Sinon la coordonn�e y est modifi�e quand on repasse � l'�tat titleScreen) on la passe � 807
		if(ySelectButton > 807) ySelectButton = 544; //Si la coordonn�e y du bouton est sup�rieur � 807 on la passe � 544
		SelectButton.setLocation(xSelectButton, ySelectButton); //On met � jour la position du bouton
		
	}
	
	public void updateWaitingRoom(KeyEvent e) {
		int touche = e.getKeyCode(); //On r�cup�re la valeur de la touche press�e
		
		switch(touche) {
			case(KeyEvent.VK_ENTER) :
				if(setup.clientID == 0 ) {
					if(Serveur.numclient != Serveur.max_connexion) Client.pw.println("END");
				}
				
		}
	}
	
	//Fonction update mettant � jour les �v�nements
	public void updateInGame() {
		
		//�v�nements du joueur
		joueur.updateJoueur();
		
		//�v�nement des aliments
		for(int i=0; i<taille_aliment; i++) {
			aliment[i].updateAliment();
		}
		
		//Check si un aliment tombe sur un autre
		for(int i=0; i<(taille_aliment - 1); i++) {
			aliment[i].checkIfAlimentUp(aliment[i+1].tabPixel[0]);
		}
		
		//Mise � jour des ennemis
		for(int i=0; i<taille_ennemi; i++) {
			ennemi[i].updateEnnemi();
			
			if(ennemi[i].checkIfContact()) { //Si un ennemi touche le joueur
				for(int j=0; j<3; j++) {
					ennemi[j].resetPos(); //On remet les ennemis � leurs
				}						  //Places initiales
				joueur.resetPos(); //On remet le cuisinier � sa place initiale
				state.retireVie(); //On retire une vie
			}
		}
	}
	
	public void updateInGameMulti() {
		
		int id = setup.clientID; //On r�cup�re l'id du joueur
		
		switch(id) { //On le prend
			case(0) :
				joueur.updateJoueur(); //Si il vaut 0 on appelle updateJoueur sur joueur
				break;
			default :
				ennemi[id-1].updateJoueur(); //Sinon on l'appelle sur ennemi[id-1]
				break;
		}
		
		
		//�v�nement des aliments
		for(int i=0; i<taille_aliment; i++) {
			aliment[i].updateAliment();
		}
		
		//Check si un aliment tombe sur un autre
		for(int i=0; i<(taille_aliment - 1); i++) {
			aliment[i].checkIfAlimentUp(aliment[i+1].tabPixel[0]);
		}
		
		//Mise � jour des ennemis
		for(int i=0; i<taille_ennemi; i++) {
			
			if(ennemi[i].checkIfContact()) { //Si un ennemi touche le joueur
				for(int j=0; j<3; j++) {
					ennemi[j].resetPos();//On remet les ennemis � leurs
				}						 //Places initiales
				joueur.resetPos(); //On remet le cuisinier � sa place initiale
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
