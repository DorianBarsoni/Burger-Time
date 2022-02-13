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
			
			br = new BufferedReader(new InputStreamReader(socket.getInputStream())); //Cr�ation d'un BufferedReader
			pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true); //Cr�ation d'un PrintWriter autoFlush
			
			id = Integer.parseInt(br.readLine()); //On lit l'id re�u qui devient la notre
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
			String str = "a"; //On initialise str � a
			
			while(true) { //Boucle infinie
				
				if(str.equals("END")) break; //Si on re�oit END on sort de la boucle
				str = br.readLine(); //On lit le String re�u
				switch (str) {
					case("updateSpritesWaitingRoom") :  //Si on re�oit updateSpritesWaitingRoom
						updateSpritesWaitingRoom(Integer.parseInt(br.readLine())); //On appelle updateSpritesWaitingRoom avec 
						break;													   //En lui passant l'entier re�u
				}
			}
			
			if(id != 0  && id != 3) pw.println("END"); //Si notre id n'est pas 0 on envoie END au serveur
			
			str = "a"; //On �galise str � a
			
			setup.enterInGameMulti(); //On appelle enterInGameMulti de setup
			
			while(true) { //Boucle infinie
				
				pw.println(setup.getMyPosX(id)); //On envoie la position x de notre entit�e
				pw.println(setup.getMyPosY(id)); //On envoie la position y de notre entit�e
				
				//System.out.println("[CLIENT " + id + "] : Envoie des pos (" + setup.getMyPosX(id) + "," + setup.getMyPosY(id) + ")");
				
				str = br.readLine(); //On lit le string re�u
				int idRecu = Integer.parseInt(str); //On transforme le string en int id re�u
				str = br.readLine(); //On lit le string re�u
				int x = Integer.parseInt(str); //On transforme le string en int x
				str = br.readLine(); //On lit le string re�u
				int y = Integer.parseInt(str); //On transforme le string en int x
				//System.out.println("[CLIENT " + id + "] : Nouvelles pos de " + idRecu + " recu : (" + x + "," + y + ")");
				setup.updateMyEntities(idRecu, x, y); //On appelle la m�thode updateMyEntities de setup avec l'id re�u 
													  //et le coordonn�es
				//System.out.println("[CLIENT " + id + "] : Actualisation des pos de " + idRecu + " : (" + x + "," + y + ")");
				
				if(setup.end) break; //Si le bool�en end de setup vaut true on sort de la boucle
				pw.println("enCours"); //Sinon on envoie enCours au serveur
			}
			
			pw.println("END"); //On envoie END au serveur
			
			System.out.println(id + " se ferme...");
			
			br.close(); //On ferme le BufferedReader
			pw.close(); //On ferme le PrintWriter
			socket.close(); //On ferme le Socket
			
		} catch (IOException e) {e.printStackTrace();}
	}
	
	//Fonction mettant � jour les sprites dans la salle d'attente
	public void updateSpritesWaitingRoom(int lim) {
		for(int i=0; i<lim; i++) setup.setSpritEnnemi(i);
	}
}
