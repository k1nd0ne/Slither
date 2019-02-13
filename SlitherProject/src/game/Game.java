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
		g.randomize();		
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
