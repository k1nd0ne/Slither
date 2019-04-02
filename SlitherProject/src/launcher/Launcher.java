package launcher;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import game.Game;
import game.GameAlice;
import game.GameBiparti;
import game.GameBob;



public class Launcher{
	private JLabel menuItem;
	private JLabel menuItem2;
	private JLabel menuItem3;
	private JLabel menuItem4;
	private JButton quit;
	private JFrame fenetre;  
	private JPanel panel; 
	public Launcher() {
		
		quit = new JButton("Quitter");
		
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
		ImageIcon imageTitre = new ImageIcon("src/images/title.png");
		JLabel titre = new JLabel("", imageTitre, JLabel.CENTER);
		ImageIcon image = new ImageIcon("src/images/background.jpeg");
		ImageIcon b1 = new ImageIcon("src/images/b1.png");
		ImageIcon b2 = new ImageIcon("src/images/b2.png");
		ImageIcon b3 = new ImageIcon("src/images/b3.png");
		ImageIcon b4 = new ImageIcon("src/images/b4.png");
		menuItem = new JLabel("", b1, JLabel.CENTER);
		menuItem2 = new JLabel("", b2, JLabel.CENTER);
		menuItem3 = new JLabel("", b3, JLabel.CENTER);
		menuItem4 = new JLabel("", b4, JLabel.CENTER);
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
		titre.setBounds(0,200 ,300, 30);
		menuItem.setBounds(40,250,200,30);
		menuItem2.setBounds(45,300,200,30);
		menuItem3.setBounds(45,350,200,30);
		menuItem4.setBounds(45,400,200,30);
		quit.setBounds(120,450,50,20);
		menuItem.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	Game g = new Game(); 
				g.init();
				g.render();
            }
        });

		menuItem2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	GameBiparti g = new GameBiparti(true,false); 
				g.init(false, true);
				g.render();
            }

        });
		
		menuItem3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	Game g = new GameAlice(); 
				g.init();
				g.render();
            }

        });
		
		menuItem4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	Game g = new GameBob(); 
				g.init();
				g.render();
            }

        });
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		}
	
	public static void main(String args[]) {
		Launcher l = new Launcher();
	}
}
