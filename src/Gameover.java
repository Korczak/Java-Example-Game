import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;


public class Gameover extends GameState {
 
	
	private Background bg;
	
	private int currentChoice = 0;
	private String back = "Back";
	
	private Color titleColor;
	private Font titleFont;
	
	private Font font;
	
	private boolean isScore = true;
	private long scoreTimer;
	
	public Gameover(GameStateManager gsm) {
		this.gsm = gsm;
		
		try {
			
			bg = new Background("/gameoverbg.gif");
			
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
		
		//draw SCORE
		if(isScore) {
			scoreTimer = (System.nanoTime() - AvoidGame.gameTimer) / 1000000000;
			isScore = false;
		}
		g.drawString("Your score is  " + scoreTimer, GamePanel.WIDTH / 2, GamePanel.HEIGHT - 200);
	}
	
	private void select() {
		gsm.setState(GameStateManager.MENUSTATE);
	}
	
	public void keyPressed(int k) {
		
	}
	
	public void keyReleased(int k) {
		if(k == KeyEvent.VK_ENTER) {
			isScore = true;
			select();
		}
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
