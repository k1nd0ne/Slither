package game;

import java.awt.Graphics;

import javax.swing.JPanel;

import graphe.Graphe;

public class JPanelGraphe extends JPanel {
	Graphe g;
	JPanelGraphe(Graphe g){
		super();
		this.g = g;
	}
	public void paintComponent(Graphics gt){
	    g.render(gt);
	 } 
}
