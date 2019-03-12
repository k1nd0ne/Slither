package graphe;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Arbre {
	
	protected Arc verspere;
	protected Sommet scourant;
	protected ArrayList<Arbre> fils;
	
	public String toString() {
		return toString(0);
	}
	public String toString(int niveau) {
		String res =  niveau + " : " + scourant;
		for(Arbre f : fils ) {
			res += "\t"+ f.toString(niveau + 1);
		}
		return res;
	}
	public Arbre(Sommet ao) {
		verspere = null;
		scourant = ao;
		fils = new ArrayList<Arbre>();
		
	}
	public Set<Sommet> getA(){
		Set<Sommet> res = new HashSet<Sommet>();
		res.add(scourant);
		for(Arbre a : fils) {
			res.addAll(a.getB());			
		}
		return res;		
	}
	public Set<Sommet> getB(){
		Set<Sommet> res = new HashSet<Sommet>();
		for(Arbre a : fils) {
			res.addAll(a.getA());			
		}
		return res;		
	}
	public void add(Sommet b, Arc ab ) {
		Sommet a = ab.getS1();
		if(a==b) {
			a = ab.getS2();
		}
		if(a == scourant) {
			Arbre f = new Arbre(b);	
			f.verspere=ab;
			fils.add(f);
		}
		else {
			for(Arbre f : fils) {
				f.add(b, ab);
			}
		}			
	}
	public Set<Arc> chemin(Sommet b){
		Set<Arc> res = new HashSet<Arc>();
		if (scourant == b ) {
			res.add(verspere);
		}
		else {
			for(Arbre f : fils) {
				res.addAll(f.chemin(b));				
			}
			if(!res.isEmpty() && verspere != null) {
				res.add(verspere);
			}		
		}
		return res;	
	}
}
