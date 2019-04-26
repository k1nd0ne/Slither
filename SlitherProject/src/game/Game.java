package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.FormatterClosedException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import graphe.Graphe;
import graphe.Sommet;
import joueur.IA;
import joueur.Joueur;

public class Game implements MouseListener{
	protected Joueur joueurCourant, adversaire;
	protected Graphe g;
	private JFrame fenetre;
	protected JPanelGraphe pan;
	private JButton forceBased;
	private Graphics gt;
	
	public Game(){
		this.g = new Graphe(10);
		this.joueurCourant = new Joueur("Bob");
		this.adversaire = new Joueur("Alice");
	}
	/**
	   * Initialisation de la fenêtre de jeux avant le lancement de la partie
	   * Mise en place des actions pour l'utilisateurs joueur
	   */
	public void initBis() {
		forceBased = new JButton("Appliquer Force-Based");
		forceBased.setBounds(10, 500, 200, 30);
		fenetre = new JFrame();
		fenetre.setBackground(Color.white);
		fenetre.setSize(600,600);
		fenetre.setResizable(false);
		pan = new JPanelGraphe(g,joueurCourant,forceBased);
		pan.setBackground(Color.WHITE);
		fenetre.setContentPane(pan);
		fenetre.setVisible(true);
		gt = pan.getGraphics();
		pan.addMouseListener(this);
		g.randomize(pan.getWidth(), pan.getHeight()-200);
		forceBased.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refresh();
				g.forceBased(6000, 0.3, 20, 100);
				g.render(gt);
			}
		});
	}
	/**
	   * Appel de la méthode précédente.
	   * Lancement de la partie.
	   */
	public void init() {	
		initBis();
		play();
	}
	/**
	   * Mettre à jour les composant graphique.
	   * Utilisé lors de l'application de l'algorithme forceBased
	   */
	public void refresh() {
		forceBased.setVisible(false);
		pan.updateUI();
		pan.update(gt);
	}
	
	/**
	   * Affiche le graphe pour une partie.
	   * Affiche le joueurs courant qui doit effectuer une action
	   */
	public void render() {
		g.render(gt);
		joueurCourant.render(gt);
	}
	
	/**
	   * Si un joueur clique correctement sur le graphe
	   * Alors il a effectuer sont tour de jeu.
	   * On met à jour les informations sur le joueur courant en vérifiant que la partie n'est pas terminée
	   * @param e : Un clic sur la fenetre de jeu
	   */
	public void mouseClicked(MouseEvent e) {
		
		boolean correctlyClicked = g.gstMouseEvent(e);
		if(correctlyClicked) {
			g.afficherCouplage(gt);
			joueurCourant.setPlaying();
			adversaire.setPlaying();
			Joueur temp = joueurCourant;
			joueurCourant = adversaire;
			adversaire = temp;
			pan.setJoueur(joueurCourant);
			render();
			if(g.estFerme()) {
				gstEnd();
			}
			else {
				play();
			}
		}
	}
	/**
	   * Gestionnaire de fin de partie
	   * Affiche le nom du joueur gagnant
	   * Affiche les arêtes du couplage maximum.
	   * Permetre au joueur de quitter la partie
	   */
	public void gstEnd() {
		g.afficherCouplage(gt);
		String gagnant = adversaire.getNom();
		gt.setColor(Color.BLACK);
		gt.clearRect(100, 8, 200,15);
		gt.drawString("JEU FINI : "+gagnant+ " gagne !", 100, 50);
		JButton b1 = new JButton("Quitter");
		fenetre.getContentPane().add(b1);
		b1.setBounds(10, 550, 100, 30);
		b1.setVisible(true);
		b1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				fenetre.setVisible(false);
			}
		});
	}
	/**
	   * Effectue un simple tour de jeux pour un joueur ou un robot.
	   * Vérfication de l'état de la partie à chaque tour.
	   */
	public void play() {
		Sommet s = joueurCourant.play(g.getSommetsAccessibles(), g.getSommetCourant());
		if(s != null) {
			g.setSommetCourant(s);
			Joueur temp2 = joueurCourant;
			joueurCourant = adversaire;
			adversaire = temp2;
			pan.setJoueur(joueurCourant);
			render();
			if(g.estFerme()) {
				gstEnd();
			}
			else {
				play();
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
	}
}
