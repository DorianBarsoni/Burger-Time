package Plateau;

public class Echelle extends Blocs{
	
	//Bloc permettant de monter
	
	@Override
	public boolean estAccessible() {
		return true;
	}

	@Override
	public boolean estEscaladable() {
		return true;
	}
	
	@Override
	public boolean estVide() {
		return false;
	}
	
}
