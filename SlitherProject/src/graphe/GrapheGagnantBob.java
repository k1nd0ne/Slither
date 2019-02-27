package graphe;

import java.util.ArrayList;
import java.util.Random;

public class GrapheGagnantBob extends GrapheBiparti {

	public GrapheGagnantBob(int nbSommetA) {
		super(nbSommetA, nbSommetA);
		}
	
	protected void randomizeArc() {
		ArrayList<Sommet> sommetsCool = new ArrayList<Sommet>(); 
		Random r = new Random(); 
		Arc a;
		int nbSommetCool = A.size() - 1; 
		for(int i=0; i<nbSommetCool;i++) {
			 if(i%2 == 0) {
				 sommetsCool.add(sommets.get(i));				 
			 }
			 else {
				 sommetsCool.add(sommets.get(sommets.size() -1 -i));
			 }
		 }
		int i1,i2,aux;
		
		ArrayList<Integer> comp = new ArrayList<Integer>();
		for(int i = 0; i<sommets.size(); i++) {
			comp.add(i);
		}
		while(!estConnexe(comp)) {
			i1 = r.nextInt(nbSommetCool);
			if(A.contains(sommetsCool.get(i1))) {
				i2 = r.nextInt(nbSommetB)+nbSommetA;
				
			}
			else {
				i2 = r.nextInt(nbSommetA);
			}
			i1 = sommets.indexOf(sommetsCool.get(i1));
			a = new Arc(sommetsCool.get(i1),sommets.get(i2));
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
