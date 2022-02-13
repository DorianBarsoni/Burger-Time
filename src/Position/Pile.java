package Position;

public class Pile {
	
	public Dijkstra[] pile;
	public int sommet_pile;
	
	public Pile(int taille) {
		pile = new Dijkstra[taille];
		sommet_pile = 0;
	}
	
	public void push(Dijkstra d) {
		pile[sommet_pile] = d;
		sommet_pile++;
	}
	
	public Dijkstra pop() {
		sommet_pile--;
		return pile[sommet_pile];
	}

}
