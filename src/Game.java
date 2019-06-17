import java.awt.Frame;

import javax.swing.JFrame;


public class Game {
	
	public static JFrame window;

	public static void main(String[] args) {
		
		window = new JFrame("Arkanoid");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setContentPane(new GamePanel());
		window.setUndecorated(true);
		window.setVisible(true);
		window.pack();

		
	}

}
