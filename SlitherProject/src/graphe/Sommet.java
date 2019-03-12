package graphe;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Sommet {
	private int x,y;
	private boolean dejaVu;
	public String toString() {
		return "("+x+","+y+")";
	}
	public Sommet(int x, int y) {
		this.x = x;
		this.y = y;
		dejaVu = false;
	}
	public boolean getDejaVu() {
		return dejaVu;
	}
	public void setVu(){
		dejaVu = true;
	}
	public int getX() {
		return this.x;
	}
	public int getY() {
		return this.y;
	}
	public void setX(int x) {
		this.x = x; 
	}
	public void setY(int y) {
		this.y = y; 
	}
	public void render(Graphics g,ArrayList<Sommet> sommetsAccessibles) {
		if(dejaVu) {
			g.setColor(Color.RED);
		}
		else if(sommetsAccessibles.contains(this)) {
			g.setColor(Color.GREEN);
		}
		else {
			g.setColor(Color.BLACK);
		}
		g.fillOval(x-5, y-5, 10, 10);
	}
	
	public static void main(String[] args) {
		Sommet s = new Sommet(40,40);
		Sommet sVu = new Sommet(40,60);
		sVu.setVu();
		Sommet sAcc = new Sommet(40,80);
		System.out.println((s.getDejaVu() == !sVu.getDejaVu()));
		ArrayList<Sommet> sommetsAccessibles = new ArrayList<Sommet>();
		sommetsAccessibles.add(sAcc);
		
		JFrame fenetre = new JFrame();
		fenetre.setSize(500,500);
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel pan = new JPanel();
		pan.setBackground(Color.WHITE);
		fenetre.setContentPane(pan);
		
		fenetre.setVisible(true);
		Graphics g = pan.getGraphics();
		while(true) {
			s.render(g,sommetsAccessibles);
			sVu.render(g,sommetsAccessibles);
			sAcc.render(g,sommetsAccessibles);
		}
	}
}
