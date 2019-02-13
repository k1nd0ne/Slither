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
		pan = new JPanelGraphe(g);
		fenetre.setContentPane(pan);
		fenetre.setVisible(true);
		gt = pan.getGraphics();
		pan.addMouseListener(this);
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
			if(g.estFerme()) {
				String gagnant = joueurCourant.getNom();
				JLabel titre = new JLabel("JEUX FINIS : "+gagnant+ " gagne !");
				JButton b1 = new JButton("Quitter");
				fenetre.getContentPane().add(titre);
				fenetre.getContentPane().add(b1);
				b1.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						fenetre.setVisible(false);
					}
				});
				fenetre.setVisible(true);
				gt = pan.getGraphics();
				render();
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
