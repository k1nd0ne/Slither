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
import game.GameAlice;
import game.GameBiparti;
import game.GameBob;



public class Launcher {
	private JMenuBar menuBar; 
	private JMenu menu; 
	private JMenuItem menuItem;
	private JMenuItem menuItem2;
	private JMenuItem menuItem3;
	private JMenuItem menuItem4;
	private JFrame fenetre;  
	private JPanel panel; 
	public Launcher() {
		menuBar = new JMenuBar();
		menuItem  = new JMenuItem("Graphe aléatoire");
		menuItem2 = new JMenuItem("Graphe aléatoire bipartie"); 
		menuItem3 = new JMenuItem("Graphe Gagnant pour Alice");
		menuItem4 = new JMenuItem("Graphe Gagnant pour Bob");
		menu = new JMenu("Nouveau...");
		menu.add(menuItem);
		menu.add(menuItem2);
		menu.add(menuItem3);
		menu.add(menuItem4);
		menuBar.add(menu);
		
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Game g = new Game(); 
				g.init();
				g.render();
			}
		});
		

		menuItem2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Game g = new GameBiparti(); 
				g.init();
				g.render();
			}
		});
		
		menuItem3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Game g = new GameAlice(); 
				g.init();
				g.render();
			}
		});
		
		menuItem4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Game g = new GameBob(); 
				g.init();
				g.render();
			}
		});
		
		
		
		
		fenetre = new JFrame("Menu principal");
		fenetre.setSize(300, 400);
		fenetre.setResizable(false);
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
