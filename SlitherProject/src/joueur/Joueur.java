package joueur;

import java.awt.Color;
import java.awt.Graphics;

public class Joueur {

	private String nom;
	private boolean isPlaying;
	
	public Joueur(String nom) {
		this.nom = nom;
	}
	
	public void play() {
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
}
