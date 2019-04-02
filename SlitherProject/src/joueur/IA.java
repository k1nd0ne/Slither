package joueur;
import java.util.ArrayList;

import graphe.Arc;
import graphe.Couplage;
import graphe.Graphe;
import graphe.GrapheBiparti;
import graphe.Sommet;

public class IA extends Joueur {
	private Couplage c;
	
	public IA(String nom,GrapheBiparti g) {
		super(nom);
		System.out.println("Dans IA : " + g.couplagemax());
		this.c = g.couplagemax();
	}
	
	public Sommet play(ArrayList<Sommet> SommetsAccessible,Sommet sommetCourant) {
		System.out.println("je suis dans IA : ");
		System.out.println("sommet courant : " + sommetCourant);
		System.out.println("sommets accessibles : " + SommetsAccessible);
		System.out.println("le couplage actuel est : " + c);
		Sommet res = SommetsAccessible.get(0);
		if(sommetCourant==null) {
			System.out.println(c);
			System.out.print("On commence, on choisit un sommet exposé.");
			
			for(Sommet a : SommetsAccessible) {
				if(c.estexpose(a)) {
					res = a;
				}
			}
		}
		else if(c.estexpose(sommetCourant)) {
			System.out.println("Le sommet courant est exposé, j'ai donc pris un sommet au hasard.");
		}
		else {
			
			Arc a = c.getVoisin(sommetCourant);
			if(a.getS1() == sommetCourant) {
				res = a.getS2(); 
			}
			else {
				res = a.getS1();
			}
			if(!SommetsAccessible.contains(res)) {
				res = SommetsAccessible.get(0);
				System.out.println("Le sommet courant n'est pas exposé mais n'est pas accessible, j'ai donc pris un sommet au hasard.");
			}
			else {
				System.out.println("Le sommet courant n'est pas exposé, j'ai donc pris le sommet associé dans le couplage");
			}
		}
		
		return res;
	}
}
