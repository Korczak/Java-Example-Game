import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;


public class MenuState extends GameState {
 
	
	private Background bg;
	
	private int currentChoice = 0;
	private String[] options = {"Start - Avoid Game", "Start - Reflection Game", "EXIT"};
	
	private Color titleColor;
	private Font titleFont;
	
	private Font font;
	
	public MenuState(GameStateManager gsm) {
		this.gsm = gsm;
		
		try {
			
			bg = new Background("/menubg.gif");
			
			titleColor = new Color(255,0,0);
			titleFont = new Font("Comic Sans MS", Font.BOLD + Font.ITALIC, 25);
			
			
			font = new Font("Comic Sans MS", Font.PLAIN, 40);
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void init() {
		Game.window.getContentPane().setCursor(Cursor.getDefaultCursor());
	}
	
	public void update() {
		bg.update();
	}
	
	public void draw(Graphics2D g) {
		//draw background
		bg.draw(g);
		
		
		//draw menu options
		for(int i = 0; i < options.length; i++) {
			g.setFont(font);
			if(i == currentChoice) {
				g.setColor(Color.BLACK);
			}
			else {
				g.setColor(Color.WHITE);
			}
			g.drawString(options[i], GamePanel.WIDTH / 2 + 150, 200 + 70 * i);
		}		
	}
	
	private void select() {
		if(currentChoice == 0) {
			gsm.setState(GameStateManager.AVOIDGAME);
		}
		if(currentChoice == 1) {
			gsm.setState(GameStateManager.REFLECTIONGAME);
		}
		if(currentChoice == 2) {
			System.exit(0);
		}
	}
	
	public void keyPressed(int k) {
		if(k == KeyEvent.VK_ENTER ) {
			select();
		}
		
		if(k == KeyEvent.VK_DOWN) {
			currentChoice--;
			if(currentChoice == -1) {
				currentChoice = options.length - 1;
			}
		}
		
		if(k == KeyEvent.VK_UP) {
			currentChoice++;
			if(currentChoice == options.length) {
				currentChoice = 0;
			}
		}
		
		if(k == KeyEvent.VK_ESCAPE) {
			System.exit(0);
		}
	}
	
	public void keyReleased(int k) {
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}
	
}
