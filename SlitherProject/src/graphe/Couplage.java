package graphe;

import java.util.HashSet;
import java.util.Set;

public class Couplage {
	protected Set<Arc> coupl;
	
	public Couplage () {
		coupl = new HashSet<Arc>();
	
	}
	
	
	public boolean estexpose (Sommet s) {
		for(Arc a : coupl) {
			if(a.getS1()==s || a.getS2()==s)
					return false;
				
		}
		return true;
		
	}
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