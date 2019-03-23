package game;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import graphe.Graphe;
import joueur.Joueur;
public class JPanelGraphe extends JPanel {
	private Graphe g;
	private Joueur j;
	private JButton b;
	JPanelGraphe(Graphe g,Joueur j,JButton f){
		super();
		this.g = g;
		this.j = j; 
		this.b = f;
	}
	public void paintComponent(Graphics gt){
	    g.render(gt);
	    j.render(gt); 
	    this.add(b);
	 } 
	
	public void setJoueur(Joueur j) {
		this.j = j;
	}
}
