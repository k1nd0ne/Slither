package graphe;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Arc {
	Sommet s1,s2;
	boolean dejaVu;
	public Arc(Sommet s1, Sommet s2) {
		this.s1 = s1;
		this.s2 = s2;
		this.dejaVu = false;
	}
	/**
	   * Passe la valeur d'un sommet non vu à vrai.
	   */
	public void setVu() {
		this.dejaVu = true;
	}
	/**
	   * Récupère Le premier sommet d'un arc
	   * @return s1 : le premier sommet de l'arc courant.
	   */
	public Sommet getS1() {
		return s1;
	}
	/**
	   * Récupère Le premier sommet d'un arc
	   * @return s2 : le second sommet de l'arc courant.
	   */
	public Sommet getS2() {
		return s2;
	}
	/**
	   * Tracer les arcs et les affichers graphiquement.
	   * Utiliser des couleurs différentes selon l'état des sommets.
	   * @param g :Outil de dessin
	   */
	public void render(Graphics g) {
		if(dejaVu) {
			g.setColor(Color.RED);
		}
		else {
			g.setColor(Color.BLACK);
		}
		g.drawLine(s1.getX(), s1.getY(), s2.getX(), s2.getY());
	}
	
	public String toString() {
		return "[" + s1.toString() + " ; " + s2.toString() + " ]";
	}
	/**
	   * Utilisé pour dessiner les arêtes du couplage en fin de jeux
	   * repasse en vert une arête.
	   * @param g : Outil de dessin.
	   */
	public void paint(Graphics g) {
		g.setColor(Color.GREEN);
		g.drawLine(s1.getX(), s1.getY(), s2.getX(), s2.getY());
	}
}
