package Position;

public class Pixel {
	
	//Classe permettant de répertorier les coordonnées d'un pixel
	
	private int x;
	private int y;
	
	public Pixel(int x, int y) {
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
	
	public boolean isEqual(Pixel pix) {
		if( (this.getX() == pix.getX() ) && (this.getY() == pix.getY()) ) return true;
		return false;
	}
	
	//Fonction convertissant les coordonnées Pixel en coordonnées de la grille
	public Point fromPixelToPoint() {
		Point p = new Point( (int) Math.floor( this.getX()/31 ), (int) Math.floor( this.getY()/31 ) ); //Division des coordonnées x et y du pixel par 31, arrondie vers le bas et cast en int
		return p;
	}
}
