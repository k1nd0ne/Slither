package graphe;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class GrapheBiparti extends Graphe{
	private ArrayList<Sommet> A;
	private ArrayList<Sommet> B ; 
	public GrapheBiparti() {
		super(); 
		this.A = new ArrayList<Sommet>(); 
		this.B = new ArrayList<Sommet>();
		randomize();
		this.sommets = A;
		this.sommets.addAll(B);
	}
	public void randomize() {
		Random r = new Random();
		int NBSOMMETMAX = 10;
		int nbSommet1 = r.nextInt(NBSOMMETMAX) + 1;
		int nbSommet2 = r.nextInt(NBSOMMETMAX) + 1;
		if(nbSommet1 > nbSommet2) {
			int aux = nbSommet1; 
			nbSommet1 = nbSommet2;
			nbSommet2 = aux;
		}
		
		for(int i =0; i<nbSommet1;i++) {
			A.add(new Sommet((int)(Math.round(Math.random() * 400) + 1),(int)(Math.round(Math.random() * 300) + 100)));
		}
		for(int i =0; i<nbSommet2;i++) {
			B.add(new Sommet((int)(Math.round(Math.random() * 400) + 1),(int)(Math.round(Math.random() * 300) + 100)));
		}
		int nbArc = (int) (Math.round(Math.random() * 10 )+ 1);
		int i,j = 0;
		Arc a = null;
		while(nbArc > 0) {
			i = ThreadLocalRandom.current().nextInt(0, nbSommet1);
			j = ThreadLocalRandom.current().nextInt(0, nbSommet2);
			a = new Arc(A.get(i), B.get(j));
					arcs.add(a);
					nbArc--;
			
		}
		
	}
}
