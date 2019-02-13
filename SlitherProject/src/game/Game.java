package game;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import graphe.Arc;
import graphe.Graphe;
import graphe.Sommet;
import joueur.Joueur;

public class Game extends Thread implements MouseListener, Runnable {
	private Joueur joueurCourant, adversaire;
	private Graphe g;
	private JFrame fenetre;
	private JPanel pan;
	private Graphics gt;
	
	public Game() {
		this.g = new Graphe();
		g.randomize();
		this.joueurCourant = new Joueur("Bob");
		this.adversaire = new Joueur("Alice");
		
	
		fenetre = new JFrame();
		fenetre.pack();
		fenetre.setSize(500,500);
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pan = new JPanel();
		pan.setBackground(Color.WHITE);
		fenetre.setContentPane(pan);
		fenetre.setVisible(true);
		gt = pan.getGraphics();
		pan.addMouseListener(this);
		render();
		play(); 
		
	}
	public void initialize() {
		render();
	}
	public void play() {
		render();
		ArrayList<Sommet> l = g.getSommetsAccessibles();
		System.out.println("C'est à "+joueurCourant.getNom()+" de jouer");
		while(!l.isEmpty()) {
			joueurCourant.play();
			l = g.getSommetsAccessibles();
		}
		System.out.println("On est sortie de la boucle");
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
	@Override
	public void run() {
		render();
		play(); 
	}
}
