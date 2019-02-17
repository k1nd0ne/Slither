package graphe;

import java.util.ArrayList;
import java.util.Random;


public class GrapheBiparti extends Graphe{
	protected int nbSommetA;
	protected int nbSommetB;
	protected ArrayList<Sommet> A;
	protected ArrayList<Sommet> B ; 
	public GrapheBiparti(int nbSommetA,int nbSommetB) {
		super(nbSommetB+nbSommetA); 
		if(nbSommetA > nbSommetB) {
			int aux = nbSommetA; 
			nbSommetA = nbSommetB;
			nbSommetB = aux;
		}
		this.nbSommetA = nbSommetA; 
		this.nbSommetB = nbSommetB;
		this.A = new ArrayList<Sommet>(); 
		this.B = new ArrayList<Sommet>();
	}
	protected void randomizeSommets(int width, int height){
		
		Random r = new Random();
		for(int i =0; i<nbSommetA;i++) {
			Sommet s = new Sommet(r.nextInt(width),r.nextInt(height)+100);
			this.addS(s);
			A.add(s);
		}
		
		for(int i=0; i<nbSommetB;i++) {
			Sommet s = new Sommet(r.nextInt(width),r.nextInt(height)+100);
			this.addS(s);
			B.add(s);
		}
	}
	
	
	protected void randomizeArc() {
		Random r = new Random();
		ArrayList<Integer>comp = new ArrayList<Integer>();
		for(int i= 0; i<sommets.size();i++) {
			comp.add(i);
		}
		int i1,i2,aux; 
		Arc a; 
		while(!estConnexe(comp)) {
			i1 = r.nextInt(nbSommetA);
			i2 = r.nextInt(nbSommetB);
			a = new Arc(A.get(i1),B.get(i2));
			this.addA(a);
			if(!comp.get(i1).equals(comp.get(nbSommetA+i2))) {
				aux = comp.get(nbSommetA+i2);
				for(int j = 0; j< comp.size(); j++) {
					if(comp.get(j).equals(aux)) {
						comp.set(j, comp.get(i1));
					}
				}
			}
			

		}
		
	}
	
}
