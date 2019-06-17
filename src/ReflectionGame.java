import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;


public class ReflectionGame extends GameState{
	
	private Reflection reflection;
	
	private boolean start;
	
	
	
	public ReflectionGame(GameStateManager gsm) {
		this.gsm = gsm;
		init();
	}
	
	public void init() {
		reflection = new Reflection();
		start = false;
	}
	
	public void update() {
		if(start) {
			reflection.update(); 
		}
		
	}
	
	public void draw(Graphics2D g) {
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		if(start) {
			reflection.draw(g);
		}
		else {
			g.setColor(Color.WHITE);
			g.drawString("Press 'K' to start", 200, 200);
		}
	}
	
	public void mouseDragged(MouseEvent e) {
		double x = e.getX();
		double y = e.getY(); 
		if(x == reflection.getx() && y == reflection.gety()) {
			if(e.getButton() != e.NOBUTTON) {
				reflection.setHit(true);
			}
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		
	}

	@Override
	public void keyPressed(int k) {
	}

	@Override
	public void keyReleased(int k) {
		if(k == KeyEvent.VK_K) start = true;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		reflection.setHit(true);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		reflection.setHit(true);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		reflection.setHit(true);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		reflection.setHit(true);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		reflection.setHit(true);
	}
	
}
