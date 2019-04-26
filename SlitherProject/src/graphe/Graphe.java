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
	/**
	   * Initialisation du graphe
	   * @param nbSommet : le nombre de sommet du graphe
	   */
	public Graphe(int nbSommet) {
		this.nbSommet = nbSommet; 
		sommets = new ArrayList<Sommet>();
		arcs = new ArrayList<Arc>();
		sommetCourant = null;
	}
	/**
	   * Ajout d'un sommet au graphe
	   * @param s : le sommet à ajouter
	   */
	public void addS(Sommet s) {
		sommets.add(s);
	}
	/**
	   * Ajout d'un arc au graphe
	   * @param a : L'arc à ajouter
	   */
	public void addA(Arc a) {
		arcs.add(a);
	}
	public void setSommetCourant(Sommet s) {
		if(sommetCourant!=null) {
			for(Arc a: arcs) {
				if((a.getS1()==s && a.getS2()==sommetCourant) ||(a.getS1()==sommetCourant && a.getS2()==s)) {
					a.setVu();
				}
			}
		}
		this.sommetCourant = s;
		s.setVu();
	}
	/**
	   * Récupérer le sommets courant du graphe
	   * cad le dernier sommet joué par un joueur.
	   */
	public Sommet getSommetCourant() {
		return this.sommetCourant;
	}
	/**
	   * Récuperation des sommets accessible depuis le sommets courant
	   * cad tous les sommets jouables.
	   */
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
	/**
	   * On vérifie si le graphe est fermer
	   * cad qu'il n'y a plus de sommets accessibles.
	   */
	public boolean estFerme() {
		return getSommetsAccessibles().isEmpty();
	}
	public void render(Graphics g) {
		ArrayList<Sommet> sommetsAccessibles = getSommetsAccessibles();
		for(Arc a: arcs){
			a.render(g);
		}
		for(Sommet s: sommets) {
			s.render(g, sommetsAccessibles, sommetCourant);
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
	/**
	   * TODO
	   * @param s : le sommet à ajouter
	   */
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
	/**
	   * Implementation de l'algorithme forceBased.
	   * Le but est de transformer un graphe désordonné afin d'éviter un maximum les croisements entre arêtes
	   * @param k
	   * @param r
	   * @param normeMin
	   * @param l0
	   */
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
			if(etape%10 == 0) {
				this.echanger(600,600);
			}
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
		}while (!norme(deplacement,normeMin) && etape < 10000);
	}
	/**
	   * Echanger deux sommet dans un graphe
	   * Utilisé pour le forcebased
	**/
	private void echanger(int height,int width) {
		Random r = new Random();
		int x = r.nextInt(height - 100);
		int y = r.nextInt(width - 100);
		int i = r.nextInt(sommets.size());
	
		sommets.get(i).setX(x);
		sommets.get(i).setY(y);
			
		
	}
	private double distance(Sommet sommet, Sommet sommet2) {
		double x1 = sommet.getX(); 
		double x2 = sommet2.getX();
		double y1 = sommet.getY(); 
		double y2 = sommet2.getY();
		
		return Math.sqrt((Math.pow(x2 - x1, 2) + Math.pow(y2 - y1,2)));
	}
	
	public void afficherCouplage(Graphics gt) {
			// TODO Auto-generated method stub
		
	}
}
