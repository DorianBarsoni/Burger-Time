package Plateau;

public abstract class Blocs {
	
	//Les blocs constituent la grille de jeu et son
	//Caract�ris�s par leur accessibilit�
	//Si ils sont escaladables ou non, ainsi que
	//Si ils sont vides ou non
	
	public abstract boolean estAccessible();
	public abstract boolean estEscaladable();
	public abstract boolean estVide();
}
