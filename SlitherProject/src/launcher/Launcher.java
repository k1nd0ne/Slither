package launcher;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import game.Game;
import graphe.Graphe;
import graphe.GrapheBiparti;


public class Launcher {
	private JMenuBar menuBar; 
	private JMenu menu; 
	private JMenuItem menuItem;
	private JMenuItem menuItem2;
	private JFrame fenetre;  
	private JPanel panel; 
	public Launcher() {
		menuBar = new JMenuBar();
		menuItem  = new JMenuItem("Graphe aléatoire");
		menuItem2 = new JMenuItem("Graphe aléatoire bipartie"); 
		menu = new JMenu("Nouveau...");
		menu.add(menuItem);
		menu.add(menuItem2);
		menuBar.add(menu);
		
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Game g = new Game(new Graphe()); 
				g.render();
			}
		});
		

		menuItem2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Game g = new Game(new GrapheBiparti()); 
				g.render();
			}
		});
		
		
		
		
		fenetre = new JFrame("Menu principal");
		fenetre.setSize(300, 400);
		panel = new JPanel();
		fenetre.setContentPane(panel);
		menuBar.add(menu);
		fenetre.add(menuBar);
		JLabel titre = new JLabel("SLITHER GAME");
		fenetre.getContentPane().add(titre);
		fenetre.setVisible(true);	
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
	
	public static void main(String args[]) {
		Launcher l = new Launcher();
	}
}
