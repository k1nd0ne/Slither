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
		forceBased(10,10,10,10);
	}
	protected boolean estConnexe(ArrayList<Integer> comp) {
		for(int i=0; i<comp.size()-1;i++) {
			if(!comp.get(i).equals(comp.get(i+1))) {
				return false;
			}
		}
		
		return true;
		
	}
	
	public static boolean norme(double[][] deplacement,double normeMin,int n) {
		for(int i =0;i<n;i++) {
			if(Math.sqrt(Math.pow(deplacement[i][0],2) + Math.pow(deplacement[i][1],2)) > normeMin) {
				return false;
			}
		}
		return true;
	}
	
	protected void deplacer(double tab[][]) {
		int i = 0;
		for (Arc a : this.arcs) {
			a.s1.setX(a.s1.getX() - (int)tab[i][0]);
			a.s1.setY(a.s1.getY() - (int)tab[i][0]);
			
			a.s2.setX(a.s2.getX() - (int)tab[i][1]);
			a.s2.setY(a.s2.getY() - (int)tab[i][1]);
		}
	}
	protected void forceBased(int k, int r,double normeMin, double l0) {
		int n = sommets.size();
		int m = arcs.size();
		double[][] deplacement = new double[m][2];
		for(int i =0;i<m;i++) {
			deplacement[i][0] = 0;
			deplacement[i][1] = 0;
		}
		
		do {
			this.deplacer(deplacement);
			double dist = 0;
			for(int i=0;i<n;i++) {
				for(int j = 0;j<n;j++) {
					dist = distance(sommets.get(i),sommets.get(j));
					deplacement[i][0] += ((sommets.get(i).getX() - sommets.get(j).getX()) / dist) * (k/Math.pow(dist, 2.));
					deplacement[i][1] += ((sommets.get(i).getY() - sommets.get(j).getY()) / dist) * (k/Math.pow(dist, 2.));
				}
			}
			for(int i = 0; i< m; i++) {
				Sommet A = arcs.get(i).s1;
				Sommet B = arcs.get(i).s2;
				int i1 = 0 ,i2 = 0;
				
				deplacement[i1][0] += ((B.getX() - A.getX()) / dist) * r*(dist-l0);
				deplacement[i1][1] += ((B.getY() - A.getY()) / dist) * r*(dist-l0);
				
				Sommet Aux;
				Aux = A;
				A = B; 
				B = Aux;
				
				deplacement[i2][0] += ((B.getX() - A.getX()) / dist) * r*(dist-l0);
				deplacement[i2][1] += ((B.getY() - A.getY()) / dist) * r*(dist-l0);
				i1++;
				i2++;
			}
		}while (norme(deplacement,normeMin,n) == false);
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
		g.randomize(fenetre.getWidth(), fenetre.getHeight());
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel pan = new JPanel();
		pan.setBackground(Color.WHITE);
		fenetre.setContentPane(pan);
		
		fenetre.setVisible(true);
		Graphics gt = pan.getGraphics();
		while(!g.estFerme()) {
			g.render(gt);
		}
		System.out.println("graphe fermé !");
		g.render(gt);
	}
}
