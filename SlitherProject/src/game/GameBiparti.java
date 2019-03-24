package game;

import graphe.GrapheBiparti;

public class GameBiparti extends Game{

	public GameBiparti() {
		this.g = new GrapheBiparti(15,15);
	}
}
