package graphe;

import java.util.HashSet;
import java.util.Set;

public class Couplage {
	protected Set<Arc> coupl;
	
	public Couplage () {
		coupl = new HashSet<Arc>();
	
	}
	
	/**
	   * Verifie si un sommet est exposé.
	   * On parcours pour cela chaque arête du couplage.
	   * @param s : Le sommet à vérifier
	   * @return boolean vrais si s est exposé, faux sinon.
	   */
	public boolean estexpose (Sommet s) {
		for(Arc a : coupl) {
			if(a.getS1()==s || a.getS2()==s) {
				return false;
			}
		}
		return true;
		
	}
	/**
	   * Augemente le couplage précédent d'un nouvel arc.
	   * @param s : Une liste d'arcs
	   * @return void.
	   */
	public void augmenter(Set<Arc> s) {
		Set<Arc> res = new HashSet<Arc>();
		for(Arc a : coupl) {
			if(!s.contains(a)) {
				res.add(a);
			}
		}
		
		for(Arc a : s) {
			if(!coupl.contains(a)) {
				res.add(a);
			}
		}		
		coupl=res;
	}
	/**
	   * Récupère le voisin d'un sommmet.
	   * @param s : Un sommet. 
	   * @return a : Arc contenant le sommet voisin de s.
	   */
	public Arc getVoisin(Sommet s) {
		for(Arc a : coupl) {
			if(a.getS1() == s) {
				return a;
			}
			if(a.getS2() == s) {
				return a;
			}
		}
		return null;
	}
	public String toString() {
		return coupl.toString();
	}
	
}