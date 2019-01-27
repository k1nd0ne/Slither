package graphe;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Graphe implements MouseListener{
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
	
	@Override
	public void mouseClicked(MouseEvent e) {
		ArrayList<Sommet> sommetsAccessibles = getSommetsAccessibles();
		int xMouse = e.getX();
		int yMouse = e.getY();
		Sommet res = null;
		for(Sommet s : sommetsAccessibles) {
			int xS = s.getX();
			int yS = s.getY();
			if((Math.abs(yS-yMouse) <= 5) &&  (Math.abs(xS-xMouse) <= 5)) {
				res = s;
			}
		}
		if(res != null) {
			this.setSommetCourant(res);
		}
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	public static void main(String[] args) {
		Graphe g = new Graphe();
		
		Sommet s0 = new Sommet(60,40);
		Sommet s = new Sommet(40,40);
		Sommet s2 = new Sommet(40,60);
		Sommet s3 = new Sommet(40,80);
		Sommet s4 = new Sommet(60,60);
		Sommet s5 = new Sommet(60,80);
		Arc a1 = new Arc (s,s2);
		Arc a2 = new Arc(s2, s3);
		Arc a3 = new Arc(s0,s);
		Arc a4 = new Arc(s0,s2);
		Arc b1 = new Arc (s0,s4);
		Arc b2 = new Arc(s2, s4);
		Arc b3 = new Arc(s4,s5);
		Arc b4 = new Arc(s3,s5);
		
		g.addS(s);
		g.addS(s0);
		g.addS(s2);
		g.addS(s3);
		g.addS(s4);
		g.addS(s5);
		
		g.addA(a1);
		g.addA(a2);
		g.addA(a3);
		g.addA(a4);
		g.addA(b1);
		g.addA(b2);
		g.addA(b3);
		g.addA(b4);
		
		
		JFrame fenetre = new JFrame();
		fenetre.setSize(500,500);
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel pan = new JPanel();
		pan.addMouseListener(g);
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
