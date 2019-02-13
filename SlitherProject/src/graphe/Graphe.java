package graphe;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Graphe{
	ArrayList<Sommet> sommets;
	ArrayList<Arc> arcs;
	Sommet sommetCourant;
	
	public Graphe() {
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
		for(Arc a: arcs) {
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
	//On commence avec un sommet. à chaque itération, on créer un nouveau sommet et une nouvelle arete. 
	//On connecte l'arete créer avec le sommet créer et un sommet aléatoire de la liste de sommet.
	public void randomize() {
		int NBSOMMETMAX = 10; 
		int nbSommet = (int)(Math.random() * NBSOMMETMAX) + 1;
		Sommet s = new Sommet(((int)Math.random()*400) + 1,((int)Math.random()*400)+1);
		this.addS(s);
		int tailleS = 0;
		int index = 0;
		Sommet i = null; 
		Arc a = null; 
		while(nbSommet > 0) {
			tailleS = sommets.size();
			i = new Sommet((int) (Math.random() * (400))+1,(int) (Math.random() * (400))+1);
			sommets.add(i);
			System.out.println(i);
			index = (int)Math.random()*tailleS;
			a = new Arc(sommets.get(index),i); 
			if(NoInArc(a)) {
				arcs.add(a);
				nbSommet--;
			}
			
		}
	}
	
	private boolean NoInArc(Arc a) {
		for(Arc b : arcs) {
			if(a.getS1() == b.getS1() && a.getS2() == b.getS2()) {
				return false;
			}
		}
		return true;
	}
	
	public static void main(String[] args) {
		Graphe g = new Graphe();
		g.randomize();
		JFrame fenetre = new JFrame();
		fenetre.setSize(500,500);
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
