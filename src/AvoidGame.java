import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


public class AvoidGame extends GameState{
	
	private boolean running;
	
	
	
	public static Player player;
	public static ArrayList<Enemy> enemies;
	
	private int waveNumber;
	private int waveDelay = 20000;
	private long waveStartTimer;
	private long waveStartTimerDiff;
	private boolean waveStart;
	
	public static long gameTimer;
	
	public static boolean gameover = false;
	
	
	
	public AvoidGame(GameStateManager gsm) {
		this.gsm = gsm;
		init();
	}
	
	public void init() {
		
		BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
		
		Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImg, new Point(0, 0), "blank cursor");

		Game.window.getContentPane().setCursor(blankCursor);

		player = new Player();
		enemies = new ArrayList<Enemy>();
		
		gameTimer = System.nanoTime();
		
		waveNumber = 0;
		
	}
	
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public long getTimer() {
		long elapsed = (System.nanoTime() - gameTimer) / 1000000000;
		return elapsed;
	}
	
	public void update() {
		
		//update player
		player.update();
		
		//update new wave
		waveStartTimerDiff = (System.nanoTime() - waveStartTimer) / 1000000;
		if(waveStartTimerDiff > waveDelay) {
			waveStart = true;
			waveStartTimer = 0;
			waveStartTimerDiff = 0;
		}
		
		
		if(waveStart) {
			createNewWave();
			waveNumber++;
			waveStart = false;
		}
		
		//update enemies
		for(int i = 0; i < enemies.size(); i++) {
			enemies.get(i).update();
		}
		
		//check player-enemies collision
		for(int i = 0; i < enemies.size(); i++) {
			if(!player.isRecovering()) {
				Enemy e = enemies.get(i);
			
				double ex = e.getx();
				double ey = e.gety();
				double er = e.getr();
				
				double px = player.getx();
				double py = player.gety();
				double pr = player.getr();
				
				double dx = ex - px;
				double dy = ey - py;
				double dist = Math.sqrt(dx * dx + dy * dy); 
				
				if(dist < er + pr) {
					gsm.setState(GameStateManager.GAMEOVER);
				}
			}
		}
		
		//game over
		if(gameover) {
			running = false;
		}
		
	}
	
	public void draw(Graphics2D g) {
		
		//draw screen
		g.setColor(new Color(0, 100, 255));
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		
		//draw player
		player.draw(g);
		
		//draw enemies
		for(int i = 0; i < enemies.size(); i++) {
			enemies.get(i).draw(g);
		}
		
		//draw timer
		long elapsed = (System.nanoTime() - gameTimer) / 1000000000;
		g.drawString(elapsed + "", 10, 50);

	}
	
	
	private void createNewWave() {
		
		if(waveNumber == 1) {
			enemies.add(new Enemy(1, 1));
			enemies.add(new Enemy(1, 1));
			enemies.add(new Enemy(1, 1));
			enemies.add(new Enemy(1, 1));
			enemies.add(new Enemy(1, 1));
			enemies.add(new Enemy(1, 1));
		}
		if(waveNumber == 2) {
			enemies.add(new Enemy(1, 1));
			enemies.add(new Enemy(1, 1));
			enemies.add(new Enemy(1, 1));
			enemies.add(new Enemy(1, 1));
		}
		if(waveNumber == 3) {
			enemies.add(new Enemy(1, 1));
			enemies.add(new Enemy(1, 1));
			enemies.add(new Enemy(1, 1));
			enemies.add(new Enemy(1, 1));
		}
		if(waveNumber == 4) {
			enemies.add(new Enemy(1, 1));
			enemies.add(new Enemy(1, 1));
			enemies.add(new Enemy(1, 1));
			enemies.add(new Enemy(1, 1));
		}
		if(waveNumber == 5) {
			enemies.add(new Enemy(1, 1));
			enemies.add(new Enemy(1, 1));
			enemies.add(new Enemy(1, 1));
		}
		if(waveNumber == 6) {
			enemies.add(new Enemy(1, 1));
			enemies.add(new Enemy(1, 1));
		}
	}
	
	
	
	@Override
	public void mouseDragged(MouseEvent e) {
		
		int x = e.getX();
		player.setx(x);
		int y = e.getY();
		player.sety(y);
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		
		int x = e.getX();
		player.setx(x);
		int y = e.getY();
		player.sety(y);
		
	}

	@Override
	public void keyPressed(int k) {
		
	}

	@Override
	public void keyReleased(int k) {
		if(k == KeyEvent.VK_ESCAPE) {
			gsm.setState(GameStateManager.MENUSTATE);
		}
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
