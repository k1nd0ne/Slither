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
	public ArrayList<Sommet> getA(){
		return A;
	}
	public ArrayList<Sommet> getB(){
		return B;
	}
	public String toString() {
		String res = "Sommets de A : " + A + "\nSommets de B : " + B + "\nArcs : " + arcs;
		return res;
	}
	public void randomize(int width, int height) {
		super.randomize(width, height);
		System.out.println(this.couplagemax());
	}
	public void addStoA(Sommet s) {
		A.add(s);
	}
	public void addStoB(Sommet s) {
		B.add(s);
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
					// ON DETERMINE B
					for(Sommet s : voisinage(T.getA())){
						if(!T.getB().contains(s)) {
							b = s;
							break;
							
						}
					}
					
					//ON DETERMINE A'
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
					T.add(b, ap);
					if(!m.estexpose(b)) {
						Arc  ba = m.getVoisin(b);
						if(b==ba.getS1()) {
							T.add(ba.getS2(), ba);
						}
						else {
							T.add(ba.getS1(), ba);
						}
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
	public static void main(String[] args) {
		GrapheBiparti gp = new GrapheBiparti(5,5);
		gp.addStoA(new Sommet(35,406));
		gp.addStoA(new Sommet(187,286));
		gp.addStoA(new Sommet(431,295));
		gp.addStoA(new Sommet(134,404));
		gp.addStoA(new Sommet(76,260));

		gp.addStoB(new Sommet(252,231));
		gp.addStoB(new Sommet(187,414));
		gp.addStoB(new Sommet(382,304));
		gp.addStoB(new Sommet(182,219));
		gp.addStoB(new Sommet(379,242));
		
		gp.addA(new Arc(gp.getA().get(3), gp.getB().get(3)));
		gp.addA(new Arc(gp.getA().get(2), gp.getB().get(3)));
		gp.addA(new Arc(gp.getA().get(0), gp.getB().get(4)));
		gp.addA(new Arc(gp.getA().get(0), gp.getB().get(0)));
		gp.addA(new Arc(gp.getA().get(0), gp.getB().get(3)));
		gp.addA(new Arc(gp.getA().get(4), gp.getB().get(0)));
		gp.addA(new Arc(gp.getA().get(1), gp.getB().get(4)));
		gp.addA(new Arc(gp.getA().get(3), gp.getB().get(1)));
		gp.addA(new Arc(gp.getA().get(1), gp.getB().get(0)));
		gp.addA(new Arc(gp.getA().get(4), gp.getB().get(3)));
		gp.addA(new Arc(gp.getA().get(2), gp.getB().get(1)));
		gp.addA(new Arc(gp.getA().get(3), gp.getB().get(2)));
		
		System.out.println(gp.couplagemax());
		
		
		
		
	}
}
