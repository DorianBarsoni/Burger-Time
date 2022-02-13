package Position;

public class Point {
	
	//Classe permettant de répertorier les coordonnées des cases de la grille
	//Elle est similaire à Pixel mais pour ne pas confondre les coordonnées
	///D'un pixel et d'une case de la grille j'ai créé 2 classes différentes
	
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
