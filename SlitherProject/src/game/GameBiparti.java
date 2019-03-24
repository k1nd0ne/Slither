package game;

import graphe.GrapheBiparti;
import joueur.IA;
import joueur.Joueur;

public class GameBiparti extends Game{

	public GameBiparti(boolean iaj1, boolean iaj2) {
		
		this.g = new GrapheBiparti(3,3);
		if(iaj1) {
			this.joueurCourant = new IA("Bob", (GrapheBiparti) this.g);
		}
		else {
			this.joueurCourant = new Joueur("Bob");
		}
		if(iaj2) {
			this.joueurCourant = new IA("Alice", (GrapheBiparti) this.g);
		}
		else {
			this.joueurCourant = new Joueur("Alice");
		}
		
	}
}
