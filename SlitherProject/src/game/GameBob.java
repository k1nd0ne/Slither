package game;
import graphe.GrapheGagnantBob;

public class GameBob extends Game{
	public GameBob() {
		this.g = new GrapheGagnantBob(7);
	}
}
