package Reseau;

import java.io.*;
import java.net.*;

import Launching.Setup;

public class Serveur extends Thread {
	
	//Classe créant le serveur multijoueur
	
	public static int numclient = 0;
	public static int max_connexion = 4;
	public static ConnexionClient[] cc = new ConnexionClient[max_connexion];
	private Setup setup;
	
	public Serveur(Setup setup) {
		this.setup = setup;
	}
   
	public void run() {
	   
		try {
			System.out.println("Serveur se crée...");
			ServerSocket s = new ServerSocket(12345); //Création du serveur
			
			while (numclient < max_connexion) { //Tant que le nombre de connexion max n'est pas atteint
				Socket soc = s.accept(); //On accepte une connexion entrante
				cc[numclient] = new ConnexionClient(numclient, soc); //On crée la connexion dans le tableau cc
				System.out.println("NOUVELLE CONNEXION - SOCKET " + numclient + " =>" + soc);
				
				System.out.println("Connexion " + numclient);
				cc[numclient].start(); //On démarre le thread
				numclient++; //On incrémente numclient
			}
			
			while(true) if(setup.end) break; //On arrête pas le serveur tant que le programme ne se termine pas
			
			System.out.println("FERMETURE SERVEUR");
			s.close();
		} catch (IOException e) {e.printStackTrace();}
	}
} 

class ConnexionClient extends Thread {
	
	//Classe permettant la connexion entre le serveur et le client
	
	private int id;
	private Socket socket;
	private BufferedReader br;
	private PrintWriter pw;
	
	public ConnexionClient(int id, Socket socket) {
		this.id = id;
		this.socket = socket;
		
		try {
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
		} catch (IOException e) {e.printStackTrace();}
	}
	
	public void run() {
		
		try {
			
			pw.println(id); //On envoie notre id au client
			
			String str = "a"; //On initialise str à a
				
			while(true) { //Boucle infinie
				
				if(str.equals("END")) break; //Si str vaut END on sort de la boucle
				str = br.readLine(); //str lit la valeur reçue
				
				switch(str) { //On prend str
					case("updateSpritesWaitingRoom") : //Si il vaut updateSpritesWaitingRoom
						for(int i=0; i<Serveur.numclient; i++) { //Pour chaque client
							Serveur.cc[i].pw.println("updateSpritesWaitingRoom"); //On envoie updateSpritesWaitingRoom
							Serveur.cc[i].pw.println(id); //On envoie notre id
						}
						break;
				}
				
				if(Serveur.numclient == Serveur.max_connexion) { 
					if(id == 3) Client.pw.println("END");
					//break; //Si le nombre max de connexion est atteint on sort de la boucle
				}
			}
			
			if(id == 0) { //Si notre id vaut 0
				for(int i=0; i<Serveur.numclient; i++) { //Pour chaque client
					Serveur.cc[i].pw.println("END"); //On envoie END
					System.out.println("Envoie de END à " + i);
				}		
			}
			
			
			str = "a"; //On égale str à a
			
			while(true) { //Boucle infinie
				
				int x = Integer.parseInt(br.readLine()); //On lit la coordonnée x
				int y = Integer.parseInt(br.readLine()); //On lit la coordonnée y
				//System.out.println("[SERVEUR " + id + "] : Pos de " + id + " reçues : (" + x + "," + y + ")");
				
				for(int i=0; i<Serveur.numclient; i++) { //Pour chaque client
					if(i != id) {
						//System.out.println("[SERVEUR " + id + "] : Envoie des pos de " + id + " à " + i + " : (" + x + "," + y + ")");
						Serveur.cc[i].pw.println(id); //On evoie notre id
						Serveur.cc[i].pw.println(x); //On envoie la coordonnée x
						Serveur.cc[i].pw.println(y); //On envoie la coordonnée x
					}
				}
				
				str = br.readLine(); //On lit le string reçu
				if(str.equals("END")) break; //Si il vaut ENd on sort de la boucle
			}
			
			System.out.println("Connexion avec " + id + " se ferme...");
			
			br.close(); //On ferme le BufferedReader
			pw.close(); //On ferme le PrintWriter
			socket.close(); //On ferme le Socket
				
		} catch (IOException e) {e.printStackTrace();}
	}
}
	
	

	
