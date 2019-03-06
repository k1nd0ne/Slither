package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import graphe.Graphe;
import joueur.Joueur;

public class Game implements MouseListener{
	private Joueur joueurCourant, adversaire;
	protected Graphe g;
	private JFrame fenetre;
	protected JPanelGraphe pan;
	private Graphics gt;
	public Game() {
		this.g = new Graphe(50);
		
	}
	public void init() {
		this.joueurCourant = new Joueur("Bob");
		this.adversaire = new Joueur("Alice");
		fenetre = new JFrame();
		fenetre.setSize(600,600);
		fenetre.setResizable(false);
		pan = new JPanelGraphe(g,joueurCourant);
		fenetre.setContentPane(pan);
		fenetre.setVisible(true);
		gt = pan.getGraphics();
		pan.addMouseListener(this);
		g.randomize(pan.getWidth(), pan.getHeight()-200);
	}
	public void render() {
		g.render(gt);
		joueurCourant.render(gt);
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
			pan.setJoueur(joueurCourant);
			render();
			if(g.estFerme()) {
				String gagnant = adversaire.getNom();
				gt.setColor(Color.BLACK);
				gt.clearRect(100, 8, 200,15);
				gt.drawString("JEU FINI : "+gagnant+ " gagne !", 100, 20);
				JButton b1 = new JButton("Quitter");
				fenetre.getContentPane().add(b1);
				b1.setBounds(10, 500, 100, 30);
				b1.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						fenetre.setVisible(false);
					}
				});

			}
			
			
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
		System.out.println("jeu fini");
	}
}
