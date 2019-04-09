package launcher;

import java.awt.BorderLayout;
import java.awt.Color;
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
	private JLabel menuItem5;
	private JButton quit;
	private JButton quit2;
	private JButton authors;
	private JFrame fenetre;  
	private JPanel panel; 
	private JPanel authPan;
	private JFrame authFen;
	public Launcher() {
		
		quit2 = new JButton("Quitter");
		quit = new JButton("Quitter");
		authors = new JButton("Auteurs");
		
		quit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				fenetre.setVisible(false);
				System.exit(0);
			}
		});
		
		quit2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				authFen.setVisible(false);
			}
		});
		
		
		//INITIALISATION FENETRE AUTEURS.
		authFen = new JFrame("Auteurs");
		authFen.setSize(500, 300);
		authFen.setResizable(false);
		authPan = new JPanel();
		JLabel authorItem = new JLabel("Slither Game Version 1.0");
		JLabel authorItem2 = new JLabel("----Développement----");
		JLabel authorItem3 = new JLabel("Félix GUYARD");
		JLabel authorItem4 = new JLabel("Lucas PICASARRI-ARIETA");
		JLabel authorItem5 = new JLabel("Boris MARCELLIN");
		authFen.setContentPane(authPan);
		authFen.add(quit2);
		authFen.add(authorItem);
		authFen.add(authorItem2);
		authFen.add(authorItem3);
		authFen.add(authorItem4);
		authFen.add(authorItem5);		
		
		
		//INITIALISATION FENETRE PRINCIPALE
		fenetre = new JFrame("Menu principal");
		fenetre.setSize(500, 650);
		fenetre.setResizable(false);
		panel = new JPanel();
		panel.setBackground(Color.darkGray);
		
		fenetre.setContentPane(panel);
		ImageIcon imageTitre = new ImageIcon("src/images/title.png");
		JLabel titre = new JLabel("", imageTitre, JLabel.CENTER);
		ImageIcon image = new ImageIcon("src/images/background.jpeg");
		ImageIcon b1 = new ImageIcon("src/images/b1.png"); 
		ImageIcon b2 = new ImageIcon("src/images/b2.png");  
		ImageIcon b3 = new ImageIcon("src/images/b3.png");
		ImageIcon b4 = new ImageIcon("src/images/b4.png"); 
		ImageIcon b5 = new ImageIcon("src/images/b5.png");
		menuItem = new JLabel("", b1, JLabel.CENTER);
		menuItem2 = new JLabel("", b2, JLabel.CENTER);
		menuItem3 = new JLabel("", b3, JLabel.CENTER);
		menuItem4 = new JLabel("", b4, JLabel.CENTER);
		menuItem5 = new JLabel("", b5, JLabel.CENTER);
	    JLabel label = new JLabel("", image, JLabel.CENTER);
		fenetre.add(label, BorderLayout.CENTER );
		fenetre.add(titre);	
		fenetre.add(menuItem);
		fenetre.add(menuItem2);
		fenetre.add(menuItem3);
		fenetre.add(menuItem4);
		fenetre.add(menuItem5);
		fenetre.add(quit);
		fenetre.add(authors);
		fenetre.setVisible(true);	
		
		
		//INITIALISATION DES POSITIONS
		label.setBounds(0,0 ,500, 200);
		titre.setBounds(0,200 ,500, 30);
		menuItem.setBounds(120,250,260,40);
		menuItem2.setBounds(100,320,300,40);
		menuItem3.setBounds(100,390,300,40);
		menuItem4.setBounds(135,460,230,40);
		menuItem5.setBounds(135,530,230,40);
		quit.setBounds(225,600,70,20);
		quit2.setBounds(225,200,70,20);
		authors.setBounds(425, 605, 70, 20);
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
            	GameBiparti g = new GameBiparti(false,true); 
				g.init(false, true);
				g.render();
            }

        });
		
		menuItem3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	GameBiparti g = new GameBiparti(true,false); 
				g.init(true, false);
				g.render();
            }

        });
		
		menuItem4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	GameAlice g = new GameAlice(); 
				g.init();
				g.render();
            }

        });
		menuItem5.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	GameBob g = new GameBob(); 
				g.init();
				g.render();
            }

        });
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		authors.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				authFen.setVisible(true);
				
			}
			
		});
		

		
		}
	
	public static void main(String args[]) {
		Launcher l = new Launcher();
	}
}
