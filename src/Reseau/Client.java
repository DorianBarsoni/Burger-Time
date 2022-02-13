package Reseau;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

import Launching.Setup;

public class Client extends Thread {
	
	public BufferedReader br;
	public static PrintWriter pw;
	private int id;
	private Setup setup;
	
	public Client(Setup setup) {
		this.setup = setup;
	}
	
	public void run() {
		
		try {
			Socket socket = new Socket(InetAddress.getByName("localhost") , 12345); //Connexion avec le serveur
			
			br = new BufferedReader(new InputStreamReader(socket.getInputStream())); //Création d'un BufferedReader
			pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true); //Création d'un PrintWriter autoFlush
			
			id = Integer.parseInt(br.readLine()); //On lit l'id reçu qui devient la notre
			System.out.println("[CLIENT] : " + id);
			
			setup.setClientID(id); //On evoie l'id au setup
			
			switch(id) {
				case(0) :
					System.out.println("[CLIENT " + id + "] : Je suis cuisinier");
					break;
				default :
					System.out.println("[CLIENT " + id + "] : Je suis ennemi");
			}
			
			
			pw.println("updateSpritesWaitingRoom"); //On envoie updateSpritesWaitingRoom au serveur
			String str = "a"; //On initialise str à a
			
			while(true) { //Boucle infinie
				
				if(str.equals("END")) break; //Si on reçoit END on sort de la boucle
				str = br.readLine(); //On lit le String reçu
				switch (str) {
					case("updateSpritesWaitingRoom") :  //Si on reçoit updateSpritesWaitingRoom
						updateSpritesWaitingRoom(Integer.parseInt(br.readLine())); //On appelle updateSpritesWaitingRoom avec 
						break;													   //En lui passant l'entier reçu
				}
			}
			
			if(id != 0  && id != 3) pw.println("END"); //Si notre id n'est pas 0 on envoie END au serveur
			
			str = "a"; //On égalise str à a
			
			setup.enterInGameMulti(); //On appelle enterInGameMulti de setup
			
			while(true) { //Boucle infinie
				
				pw.println(setup.getMyPosX(id)); //On envoie la position x de notre entitée
				pw.println(setup.getMyPosY(id)); //On envoie la position y de notre entitée
				
				//System.out.println("[CLIENT " + id + "] : Envoie des pos (" + setup.getMyPosX(id) + "," + setup.getMyPosY(id) + ")");
				
				str = br.readLine(); //On lit le string reçu
				int idRecu = Integer.parseInt(str); //On transforme le string en int id reçu
				str = br.readLine(); //On lit le string reçu
				int x = Integer.parseInt(str); //On transforme le string en int x
				str = br.readLine(); //On lit le string reçu
				int y = Integer.parseInt(str); //On transforme le string en int x
				//System.out.println("[CLIENT " + id + "] : Nouvelles pos de " + idRecu + " recu : (" + x + "," + y + ")");
				setup.updateMyEntities(idRecu, x, y); //On appelle la méthode updateMyEntities de setup avec l'id reçu 
													  //et le coordonnées
				//System.out.println("[CLIENT " + id + "] : Actualisation des pos de " + idRecu + " : (" + x + "," + y + ")");
				
				if(setup.end) break; //Si le booléen end de setup vaut true on sort de la boucle
				pw.println("enCours"); //Sinon on envoie enCours au serveur
			}
			
			pw.println("END"); //On envoie END au serveur
			
			System.out.println(id + " se ferme...");
			
			br.close(); //On ferme le BufferedReader
			pw.close(); //On ferme le PrintWriter
			socket.close(); //On ferme le Socket
			
		} catch (IOException e) {e.printStackTrace();}
	}
	
	//Fonction mettant à jour les sprites dans la salle d'attente
	public void updateSpritesWaitingRoom(int lim) {
		for(int i=0; i<lim; i++) setup.setSpritEnnemi(i);
	}
}
