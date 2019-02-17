package game;

import java.awt.Graphics;

import javax.swing.JPanel;

import graphe.Graphe;
import joueur.Joueur;
public class JPanelGraphe extends JPanel {
	private Graphe g;
	private Joueur j;
	JPanelGraphe(Graphe g,Joueur j){
		super();
		this.g = g;
		this.j = j; 
	}
	public void paintComponent(Graphics gt){
	    g.render(gt);
	    j.render(gt); 
	 } 
	
	public void setJoueur(Joueur j) {
		this.j = j;
	}
}
