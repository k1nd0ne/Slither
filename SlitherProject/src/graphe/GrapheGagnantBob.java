package graphe;

import java.util.ArrayList;
import java.util.Random;

public class GrapheGagnantBob extends Graphe {

	public GrapheGagnantBob(int nbSommet) {
		super(nbSommet);
	}
	
	protected void randomizeArc() {
		ArrayList<Sommet> sommetsCool = new ArrayList<Sommet>(); 
		Random r = new Random(); 
		Arc a;
		int nbSommetCool = sommets.size()/2; 
		if(sommets.size()%2==0) {
			nbSommetCool--;
		}
		
		int i1,i2,aux;
		
		ArrayList<Integer> comp = new ArrayList<Integer>();
		for(int i = 0; i<sommets.size(); i++) {
			comp.add(i);
		}
		while(!estConnexe(comp)) {
			i1 = r.nextInt(nbSommetCool);
			i2 = r.nextInt(nbSommet);
			a = new Arc(sommets.get(i1),sommets.get(i2));
			this.addA(a);
			
			if(!comp.get(i1).equals(comp.get(i2))) {
				aux = comp.get(i2);
				for(int j = 0; j< comp.size(); j++) {
					if(comp.get(j).equals(aux)) {
						comp.set(j, comp.get(i1));
					}
				}
			}
			

		}
	}
	
}
