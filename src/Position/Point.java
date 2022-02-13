package Position;

public class Point {
	
	//Classe permettant de r�pertorier les coordonn�es des cases de la grille
	//Elle est similaire � Pixel mais pour ne pas confondre les coordonn�es
	///D'un pixel et d'une case de la grille j'ai cr�� 2 classes diff�rentes
	
	private int x;
	private int y;
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public void affichePoint() {
		System.out.println("(" + this.getX() + "," + this.getY() + ")");
	}
	
	public boolean isEqual(Point p) {
		if( (this.getX() == p.getX() ) && (this.getY() == p.getY()) ) return true;
		return false;
	}
}
