package Plateau;

public class Border extends Blocs {

	//Bloc invisible empechant les entitées de passer
	
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
