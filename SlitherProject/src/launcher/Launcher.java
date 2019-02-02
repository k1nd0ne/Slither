package launcher;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import game.Game;

public class Launcher {
	private JFrame fenetre; 
	private JPanel panel; 
	private JButton b1;
	private JButton b2;
	private JButton b3;
	
	
	public Launcher() {
		fenetre = new JFrame("---Menu principal---");
		fenetre.setSize(400, 200);
		panel = new JPanel();
		fenetre.setContentPane(panel);
		JLabel titre = new JLabel("---SLITHER GAME---");
		b1 = new JButton("PlayerVsIA");
		b2 = new JButton("PlayerVsPlayer");
		b3 = new JButton("Graphe aleatoire");
		
		b3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Game g = new Game();
				g.render();
				g.play();
				System.out.println("jeu fini");
				
			}
		});
		fenetre.getContentPane().add(titre);
		fenetre.getContentPane().add(b1);
		fenetre.getContentPane().add(b2);
		fenetre.getContentPane().add(b3);
		
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetre.setVisible(true);	
		}
	
	public static void main(String args[]) {
		Launcher l = new Launcher();
	}
}
