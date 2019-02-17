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
	//On commence avec un sommet. à chaque itération, on créer un nouveau sommet et une nouvelle arete. 
	//On connecte l'arete créer avec le sommet créer et un sommet aléatoire de la liste de sommet.
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
	}
	protected boolean estConnexe(ArrayList<Integer> comp) {
		for(int i=0; i<comp.size()-1;i++) {
			if(!comp.get(i).equals(comp.get(i+1))) {
				return false;
			}
		}
		
		return true;
		
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
