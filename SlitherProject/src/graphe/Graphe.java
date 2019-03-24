package graphe;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Graphe{
	protected ArrayList<Sommet> sommets;
	protected ArrayList<Arc> arcs;
	protected Sommet sommetCourant;
	protected int nbSommet;
	public Graphe(int nbSommet) {
		this.nbSommet = nbSommet; 
		sommets = new ArrayList<Sommet>();
		arcs = new ArrayList<Arc>();
		sommetCourant = null;
	}
	public void addS(Sommet s) {
		sommets.add(s);
	}
	public void addA(Arc a) {
		arcs.add(a);
	}
	public void setSommetCourant(Sommet s) {
		this.sommetCourant = s;
		s.setVu();
	}
	public ArrayList<Sommet> getSommetsAccessibles(){
		ArrayList<Sommet> res = new ArrayList<Sommet>();
		if(sommetCourant == null) {
			return sommets;
		}
		else {
			for(Arc a : arcs) {
				
				if(a.getS1().equals(sommetCourant) && !a.getS2().getDejaVu()) {
					res.add(a.getS2());
				}
				else if(a.getS2().equals(sommetCourant) && !a.getS1().getDejaVu()) {
					res.add(a.getS1());
				}
			}
			return res;
		}
	}
	public boolean estFerme() {
		return getSommetsAccessibles().isEmpty();
	}
	public void render(Graphics g) {
		ArrayList<Sommet> sommetsAccessibles = getSommetsAccessibles();
		for(Arc a: arcs){
			a.render(g);
		}
		for(Sommet s: sommets) {
			s.render(g, sommetsAccessibles);
		}
	}
	
	public boolean gstMouseEvent(MouseEvent e) {
		ArrayList<Sommet> sommetsAccessibles = getSommetsAccessibles();
		int xMouse = e.getX();
		int yMouse = e.getY();
		Sommet res = null;
		for(Sommet s : sommetsAccessibles) {
			int xS = s.getX();
			int yS = s.getY();
			if((Math.abs(yS-yMouse) <= 5) && (Math.abs(xS-xMouse) <= 5)) {
				res = s;
			}
		}
		if(res != null) {
			this.setSommetCourant(res);
			return true;
		}
		else {
			return false;
		}
	}
	
	protected void randomizeSommets(int width, int height){
		Random r = new Random();
		for(int i=0;i<nbSommet;i++) {
			this.addS(new Sommet(r.nextInt(width),r.nextInt(height)+100));
		}
	}
	
	
	
	protected void randomizeArc() {
		Random r = new Random();
		ArrayList<Integer> comp = new ArrayList<Integer>();
		
		for(int i = 0; i<sommets.size(); i++) {
			comp.add(i);
		}
		int i1;
		int aux;
		int i2; 
		Arc a;
		while(!estConnexe(comp)) {
			i1 = r.nextInt(nbSommet);
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
	
	public void randomize(int width, int height) {
		randomizeSommets(width, height);
		randomizeArc();
		//this.forceBased(2000, 0.3, 20, 150);
	}
	protected boolean estConnexe(ArrayList<Integer> comp) {
		for(int i=0; i<comp.size()-1;i++) {
			if(!comp.get(i).equals(comp.get(i+1))) {
				return false;
			}
		}
		
		return true;
		
	}
	
	public boolean norme(ArrayList<ArrayList<Double>> deplacement,double normeMin) {
		boolean res = true;
		for(int i =0;i<sommets.size();i++) {
			res = res && Math.sqrt(Math.pow(deplacement.get(i).get(0),2) + Math.pow(deplacement.get(i).get(1),2)) <= normeMin;
		}
		return res;
	}
	private boolean distanceCorrect(Sommet a) {
		for(Sommet b : this.sommets) {
			if(distance(b,a) < 5) {
				return false;
			}
		}
		return true;
	}
	protected void deplacer(ArrayList<ArrayList<Double>> tab) {
		for (int i=0; i<sommets.size() ; i++) {
			if(sommets.get(i).getX() + (int) Math.round(tab.get(i).get(0)) > 0 && sommets.get(i).getX() + (int) Math.round(tab.get(i).get(0)) < 500 && sommets.get(i).getY() + (int) Math.round(tab.get(i).get(1)) > 0 && sommets.get(i).getY() + (int) Math.round(tab.get(i).get(1)) < 450 ){
				
				
				int n1 = sommets.get(i).getX() + (int) Math.round(tab.get(i).get(0)); 
				int n2 = sommets.get(i).getY() + (int) Math.round(tab.get(i).get(1));
					Sommet a = new Sommet(n1,n2);
					if(distanceCorrect(a)){
						sommets.get(i).setX(sommets.get(i).getX() + (int) Math.round(tab.get(i).get(0)));
						sommets.get(i).setY(sommets.get(i).getY() + (int) Math.round(tab.get(i).get(1)));
					}
			}
			
			
		}
		
	}
	public void forceBased(double k, double r,double normeMin, double l0) {
		int etape = 0;
		int n = sommets.size();
		ArrayList<ArrayList<Double>> deplacement = new ArrayList<ArrayList<Double>>();
		
		for(int i =0;i<n;i++) {
			deplacement.add(new ArrayList<Double>());
			deplacement.get(i).add(0.);
			deplacement.get(i).add(0.);
		}
		do {
			this.deplacer(deplacement);
			for(int i=0; i<n; i++) {
				deplacement.get(i).set(0, 0.);
				deplacement.get(i).set(1, 0.);
			}
			for(int i=0;i<n;i++) {
				for(int j = 0;j<n;j++) {
					double dist = distance(sommets.get(i),sommets.get(j));
					if(dist>0) {
						deplacement.get(i).set(0, deplacement.get(i).get(0) + (((double) sommets.get(i).getX() - sommets.get(j).getX()) / dist) * (k/Math.pow(dist, 2.))) ;
						deplacement.get(i).set(1, deplacement.get(i).get(1) + (((double) sommets.get(i).getY() - sommets.get(j).getY()) / dist) * (k/Math.pow(dist, 2.))) ;
					}
				}
			}
			
			for(int i = 0; i<arcs.size(); i++) {
				Sommet A = arcs.get(i).getS1(); 
				Sommet B = arcs.get(i).getS2();
				int i1 = sommets.indexOf(A);
				int i2 = sommets.indexOf(B);
				
				double dist = distance(A,B);
				if(dist>0) {
					deplacement.get(i1).set(0, deplacement.get(i1).get(0) + ((B.getX() - A.getX()) / dist) * r*(dist-l0));
					deplacement.get(i1).set(1, deplacement.get(i1).get(1) + ((B.getY() - A.getY()) / dist) * r*(dist-l0));
					
					deplacement.get(i2).set(0, deplacement.get(i2).get(0) + ((A.getX() - B.getX()) / dist) * r*(dist-l0));
					deplacement.get(i2).set(1, deplacement.get(i2).get(1) + ((A.getY() - B.getY()) / dist) * r*(dist-l0));
				}
			}
			for(int i=0; i<sommets.size(); i++) {
				deplacement.get(i).set(0, deplacement.get(i).get(0)*0.8);
				deplacement.get(i).set(1, deplacement.get(i).get(1)*0.8);
			}
			etape++;
		}while (!norme(deplacement,normeMin) && etape < 1000);
	}
	
	private double distance(Sommet sommet, Sommet sommet2) {
		double x1 = sommet.getX(); 
		double x2 = sommet2.getX();
		double y1 = sommet.getY(); 
		double y2 = sommet2.getY();
		
		return Math.sqrt((Math.pow(x2 - x1, 2) + Math.pow(y2 - y1,2)));
	}
	public static void main(String[] args) {
		Graphe g = new Graphe(10);
		JFrame fenetre = new JFrame();
		fenetre.setSize(500,500);
		Sommet s1 = new Sommet(100,100);
		g.addS(s1);
		Sommet s2 = new Sommet(400,400);
		g.addS(s2);
		g.addA(new Arc(s1,s2));
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel pan = new JPanel();
		pan.setBackground(Color.WHITE);
		fenetre.setContentPane(pan);
		fenetre.setVisible(true);
		Graphics gt = pan.getGraphics();
			g.forceBased(2000, 0.3, 20, 10);
		while(true)
		g.render(gt);
	}
}
