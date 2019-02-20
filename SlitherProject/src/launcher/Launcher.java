package launcher;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
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
	private JButton menuItem;
	private JButton menuItem2;
	private JButton menuItem3;
	private JButton menuItem4;
	private JButton quit;
	private JFrame fenetre;  
	private JPanel panel; 
	public Launcher() {
		
		menuItem  = new JButton("Graphe aléatoire");
		menuItem2 = new JButton("Graphe aléatoire bipartie"); 
		menuItem3 = new JButton("Graphe Gagnant pour Alice");
		menuItem4 = new JButton("Graphe Gagnant pour Bob");
		quit = new JButton("Quitter");
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
		
		quit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				fenetre.setVisible(false);
				System.exit(0);
			}
		});

		
		
		fenetre = new JFrame("Menu principal");
		fenetre.setSize(300, 500);
		fenetre.setResizable(false);
		panel = new JPanel();
		fenetre.setContentPane(panel);
		JLabel titre = new JLabel("SLITHER GAME");
		ImageIcon image = new ImageIcon("src/images/background.png");
		JLabel label = new JLabel("", image, JLabel.CENTER);
		fenetre.add(label, BorderLayout.CENTER );
		fenetre.add(titre);	
		fenetre.add(menuItem);
		fenetre.add(menuItem2);
		fenetre.add(menuItem3);
		fenetre.add(menuItem4);
		fenetre.add(quit);
		fenetre.setVisible(true);	
		label.setBounds(0,0 ,300, 200);
		titre.setBounds(100,200 ,100, 20);
		menuItem.setBounds(60,250,150,20);
		menuItem2.setBounds(50,300,175,20);
		menuItem3.setBounds(50,350,175,20);
		menuItem4.setBounds(50,400,175,20);
		quit.setBounds(120,450,50,20);
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
	
	public static void main(String args[]) {
		Launcher l = new Launcher();
	}
}
