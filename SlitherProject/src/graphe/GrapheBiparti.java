package graphe;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;


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
	
	private Set<Sommet> voisinage(Set<Sommet> s){
		Set<Sommet> res = new HashSet<Sommet>();
		for (Arc a : arcs) {
			if(s.contains(a.getS1())) {
				res.add(a.getS2());
				
			
			}
			if(s.contains(a.getS2())) {
				res.add(a.getS1());
			}
		}
		return res;
		}
	
	public Couplage couplagemax() {
		Couplage m = new Couplage();
		for(Sommet ao : A) {
			if(m.estexpose(ao)) {
				Arbre T = new Arbre(ao);
				while(!voisinage(T.getA()).equals(T.getB())) {
					Sommet b = new Sommet(0,0);
					for(Sommet s : voisinage(T.getA())){
						if(!T.getB().contains(s)) {
							b = s;
							break;
							
						}
					}
					Arc ap = new Arc(b,b);
					for(Arc a : arcs) {
						if(a.getS1()==b) {
							if(T.getA().contains(a.getS2())){
								ap = a;
								break;
							}
						
						}
						if(a.getS2()==b) {
							if(T.getA().contains(a.getS1())){
								ap = a;
								break;
							}
							
						}
					}
					if(b==ap.getS1()) {
						T.add(ap.getS2(), ap);
					}
					else {
						T.add(ap.getS1(), ap);
					}
					if(!m.estexpose(b)) {
						Arc  ba = m.getVoisin(b);
						T.add(b, ba);
								
					}
					else {
						m.augmenter(T.chemin(b));
						break;
					}
					
				}
			}
		}
		return m;
		
	}
}
