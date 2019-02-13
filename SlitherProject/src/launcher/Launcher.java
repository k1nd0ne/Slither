package launcher;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import game.Game;


public class Launcher {
	private JMenuBar menuBar; 
	private JMenu menu; 
	private JMenuItem menuItem;
	private JFrame fenetre;  
	private JPanel panel; 
	private JButton b1;
	private JButton b2;
	private JButton b3;
	public Launcher() {
		menuBar = new JMenuBar();
		menuItem  = new JMenuItem("Graphe random");
		menu = new JMenu("Jeux");
		menu.add(menuItem);
		
		menuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new Thread(new Game()).start();
				
			}
		});
		
		
		
		fenetre = new JFrame("Menu principal");
		fenetre.setSize(200, 300);
		panel = new JPanel();
		fenetre.setContentPane(panel);
		menuBar.add(menu);
		fenetre.add(menuBar);
		JLabel titre = new JLabel("---SLITHER GAME---");
		fenetre.getContentPane().add(titre);
		fenetre.setVisible(true);	
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
	
	public static void main(String args[]) {
		Launcher l = new Launcher();
	}
}
