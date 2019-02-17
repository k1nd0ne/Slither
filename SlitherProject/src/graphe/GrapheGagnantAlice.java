package graphe;

public class GrapheGagnantAlice extends GrapheBiparti {

	public GrapheGagnantAlice(int nbSommetA) {
		super(nbSommetA, nbSommetA);
	}
	
	
	protected void randomizeArc() {
		Arc a ;
		for(int i=0;i<nbSommetA;i++) {
			a = new Arc(A.get(i),B.get(i));
			this.addA(a);
		}
		super.randomizeArc();
	}
		
		
}
