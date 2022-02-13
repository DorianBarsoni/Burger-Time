package Plateau;

public class Vide extends Blocs{

	//Bloc de vide permettant au joueur de tomber
	
	@Override
	public boolean estAccessible() {
		return true;
	}

	@Override
	public boolean estEscaladable() {
		return false;
	}
	
	@Override
	public boolean estVide() {
		return true;
	}
}
