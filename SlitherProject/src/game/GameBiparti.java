package game;

import graphe.GrapheBiparti;
import joueur.IA;
import joueur.Joueur;

public class GameBiparti extends Game{

	public GameBiparti(boolean iaj1, boolean iaj2) {
		
		this.g = new GrapheBiparti(5,5);
		if(iaj1) {
			this.joueurCourant = new IA("Bob", (GrapheBiparti) this.g);
		}
		else {
			this.joueurCourant = new Joueur("Bob");
		}
		if(iaj2) {
			this.adversaire = new IA("Alice", (GrapheBiparti) this.g);
		}
		else {
			this.adversaire = new Joueur("Alice");
		}
		System.out.println("Classe de Bob : " + joueurCourant.getClass().getCanonicalName());
		System.out.println("Classe de Alice : " + adversaire.getClass().getCanonicalName());
	}
	public void init(boolean iaj1, boolean iaj2) {
		super.initBis();
		if(iaj1) {
			this.joueurCourant = new IA("Bob", (GrapheBiparti) this.g);
		}
		else {
			this.joueurCourant = new Joueur("Bob");
		}
		if(iaj2) {
			this.adversaire = new IA("Alice", (GrapheBiparti) this.g);
		}
		else {
			this.adversaire = new Joueur("Alice");
		}
		play();
	}
}
