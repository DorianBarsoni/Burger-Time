package Plateau;

public class Border extends Blocs {

	//Bloc invisible empechant les entit�es de passer
	
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
