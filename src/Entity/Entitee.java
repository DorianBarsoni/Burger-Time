 package Entity;

import Window.*;
import Plateau.*;
import Position.*;

public abstract class Entitee {
	
	protected Map map; //Carte du jeu
	protected Fenetre fen; //Fenêtre du jeu
	protected Point pos; //Position initiale sur la grille du jeu
	
	
	public Entitee(Map map, Fenetre fen) {
		this.map = map;
		this.fen = fen;
	}
}
	

