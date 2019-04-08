package joueur;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import graphe.Sommet;

public class Joueur {

	private String nom;
	private boolean isPlaying;
	
	public Joueur(String nom) {
		this.nom = nom;
	}
	
	public Sommet play(ArrayList<Sommet> SommetsAccessible,Sommet sommetCourant) {
		return null;
	}
	
	public void setPlaying() {
		this.isPlaying = !isPlaying;
	}
	
	public String getNom() {
		return nom;
	}

	public String toString() {
		return this.getNom();
	}

	public void render(Graphics g) {
		g.setColor(Color.BLACK);
		g.clearRect(100, 8, 200,15);
		g.drawString("C'est à "+getNom()+" de jouer !", 100, 20);
		
	}
	public void init() {
		
	}
}
