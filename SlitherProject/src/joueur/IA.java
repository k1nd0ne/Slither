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
		this.c = g.couplagemax();
	}
	
	public Sommet play(ArrayList<Sommet> SommetsAccessible,Sommet sommetCourant) {
		Sommet res = null;
		if(sommetCourant == null) {
			res = SommetsAccessible.get(0);
			for(Sommet a : SommetsAccessible) {
				if(c.estexpose(a)) {
					res = a;
				}
			}
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
			}
		}
		
		return res;
	}

}
