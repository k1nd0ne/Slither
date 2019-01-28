package joueur;

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

	
}
