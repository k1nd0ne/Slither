package graphe;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Arc {
	Sommet s1,s2;
	
	public Arc(Sommet s1, Sommet s2) {
		this.s1 = s1;
		this.s2 = s2;
	}
	public Sommet getS1() {
		return s1;
	}
	public Sommet getS2() {
		return s2;
	}
	public void render(Graphics g) {
		if(s1.getDejaVu() && s2.getDejaVu()) {
			g.setColor(Color.RED);
		}
		else {
			g.setColor(Color.BLACK);
		}
		g.drawLine(s1.getX(), s1.getY(), s2.getX(), s2.getY());
	}
	
	public static void main(String[] args) {
		Sommet s = new Sommet(40,40);
		Sommet sVu = new Sommet(40,60);
		sVu.setVu();
		Sommet sAcc = new Sommet(40,80);
		sAcc.setVu();
		Arc a1 = new Arc (s,sVu);
		Arc a2 = new Arc(sVu, sAcc);
		
		
		JFrame fenetre = new JFrame();
		fenetre.setSize(500,500);
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel pan = new JPanel();
		pan.setBackground(Color.WHITE);
		fenetre.setContentPane(pan);
		
		fenetre.setVisible(true);
		Graphics g = pan.getGraphics();
		while(true) {
			a1.render(g);
			a2.render(g);
		}
	}
}
