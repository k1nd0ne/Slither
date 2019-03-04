package graphe;

public class GrapheGagnantAlice extends Graphe {

	public GrapheGagnantAlice(int nbSommet) {
		super((nbSommet%2==1?nbSommet+1:nbSommet));
	}
	
	
	protected void randomizeArc() {
		Arc a ;
		for(int i=0;i<nbSommet/2;i++) {
			a = new Arc(sommets.get(i), sommets.get(nbSommet-i-1));
			this.addA(a);
		}
		super.randomizeArc();
	}
		
		
}
