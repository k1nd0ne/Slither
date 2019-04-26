package graphe;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Arbre {
	
	protected Arc verspere;
	protected Sommet scourant;
	protected ArrayList<Arbre> fils;
	
	/**
	   * Initialisation d'un arbre
	   * @param ao : Le premier sommet de l'arbre
	   */
	public Arbre(Sommet ao) {
		verspere = null;
		scourant = ao;
		fils = new ArrayList<Arbre>();
		
	}
	/**
	   * Récupère tous les sommets de l'arbre 
	   * @return res : La liste des sommets de l'arbre.
	   */
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
	/**
	   * Ajoute une branche à l' arbre
	   * @param b : Un sommet.
	   * @param ab : Un arc 
	   */
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
	/**
	   * Recupère le chemin issus d'un sommet
	   * @param b : Un sommet.
	   * @return res : L'ensemble du chemin
	   */
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
	
	public String toString(int niveau) {
		String res =  niveau + " : " + scourant;
		for(Arbre f : fils ) {
			res += "\t"+ f.toString(niveau + 1);
		}
		return res;
	}
	public String toString() {
		return toString(0);
	}
}
