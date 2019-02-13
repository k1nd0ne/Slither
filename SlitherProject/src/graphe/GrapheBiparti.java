package graphe;

import java.util.ArrayList;

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
	//Il faut qu'il y'en ai moins dans A donc on echange si ça va pas
	//on randomize des sommets dans A et dans B
	//On relie des arete au hasard da A vers B
	public void randomize() {
		int NBSOMMETMAX = 10;
		
		int nbSommet1 = ((int)Math.random()*NBSOMMETMAX)+1;
		int nbSommet2 = ((int)Math.random()*NBSOMMETMAX)+1;
		int nbSommet = 100;
		if(nbSommet1 > nbSommet2) {
			int aux = nbSommet1; 
			nbSommet1 = nbSommet2;
			nbSommet2 = aux;
		}
		
		for(int i =0; i<nbSommet1;i++) {
			A.add(new Sommet((int)(Math.random() * (400))+1,(int) (Math.random() * (300))+100));
		}
		for(int i =0; i<nbSommet2;i++) {
			B.add(new Sommet((int)(Math.random() * (400))+1,(int) (Math.random() * (300))+100));
		}
		int nbArc = ((int)Math.random()*((nbSommet*(nbSommet - 1))/2))+1;
		int i,j = 0;
		Arc a = null;
		while(nbArc > 0) {
			i = (int)Math.random()*nbSommet1;
			j = (int)Math.random()*nbSommet2;
			a = new Arc(A.get(i), B.get(j));
				arcs.add(a);
				nbArc--;
			
		}
		
	}
}
