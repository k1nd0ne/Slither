package graphe;

import java.util.ArrayList;

public class GrapheBiparti extends Graphe{
	private ArrayList<Sommet> A;
	private ArrayList<Sommet> B ; 
	public GrapheBiparti() {
		super(); 
		this.A = new ArrayList<Sommet>(); 
		this.B = new ArrayList<Sommet>();	
	}
	
	public void randomize() {
		int NBSOMMETMAX = 10; 
		int nbSommet = (int)(Math.random() * NBSOMMETMAX) + 1;
		Sommet s = new Sommet(((int)Math.random()*400) + 1,((int)Math.random()*400)+1);
		this.addS(s);
		int tailleS = 0;
		int index = 0;
		Sommet i = null; 
		Arc a = null; 
		while(nbSommet > 0) {
			tailleS = sommets.size();
			i = new Sommet((int) (Math.random() * (400))+1,(int) (Math.random() * (400))+1);
			sommets.add(i);
			System.out.println(i);
			a = new Arc(sommets.get(index),i);
			index = (int)(Math.random()*tailleS)+1;
			if(NoInArc(a)) {
				arcs.add(a);
				nbSommet--;
			}
		}
	}
}
