package Position;

public class Dijkstra {
	
	public Point sommet;
	public int valeur;
	public Dijkstra predecesseur;
	public Dijkstra[] sommetAdj;
	public int taille_sommetAdj;
	
	public Dijkstra(Point sommet) {
		this.sommet = sommet;
		valeur = 422;
		sommetAdj = new Dijkstra[4];
		taille_sommetAdj = 0;
	}
	
	//Fonction modifiant le prédécesseur de l'instance
	public void predecesseur(Dijkstra predecesseur) {
		this.predecesseur = predecesseur;
	}
	
	//Fonction ajoutant un sommet au tableau sommetAdj
	public void addSommetAdj(Dijkstra d) {
		sommetAdj[taille_sommetAdj] = d;
		taille_sommetAdj++;
	}
}