package Plateau;

public class Mur extends Blocs{
	
	//Bloc empechant le joueur de passer ou  de tomber
	
	@Override
	public boolean estAccessible() {
		return false;
	}

	@Override
	public boolean estEscaladable() {
		return false;
	}
	
	@Override
	public boolean estVide() {
		return false;
	}
}
