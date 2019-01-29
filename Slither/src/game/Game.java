package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import graphe.Arc;
import graphe.Graphe;
import graphe.Sommet;
import joueur.Joueur;

public class Game implements MouseListener{
	private Joueur joueurCourant, adversaire;
	private Graphe g;
	private JFrame fenetre;
	private JPanelGraphe pan;
	private Graphics gt;
	
	public Game() {
		this.g = new Graphe();
		this.joueurCourant = new Joueur("Bob");
		this.adversaire = new Joueur("Alice");
		Sommet s0 = new Sommet(180,120);
		Sommet s = new Sommet(120,120);
		Sommet s2 = new Sommet(120,180);
		Sommet s3 = new Sommet(120,240);
		Sommet s4 = new Sommet(180,180);
		Sommet s5 = new Sommet(180,240);
		
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
		
		
		fenetre = new JFrame();
		fenetre.setSize(500,500);
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pan = new JPanelGraphe(g);
		pan.setBackground(Color.WHITE);
		fenetre.setContentPane(pan);
		fenetre.setVisible(true);
		gt = pan.getGraphics();
		pan.addMouseListener(this);
	}
	public void play() {
		while(!g.estFerme()) {
			joueurCourant.play();
		}
	}
	public void render() {
		g.render(gt);
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		
		boolean correctlyClicked = g.gstMouseEvent(e);
		System.out.println(correctlyClicked);
		if(correctlyClicked) {
			joueurCourant.setPlaying();
			adversaire.setPlaying();
			
			Joueur temp = joueurCourant;
			joueurCourant = adversaire;
			adversaire = temp;
			render();
			
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
		Game g = new Game();
		g.render();
		g.play();
	                  
		System.out.println("jeu fini");
	}
}
