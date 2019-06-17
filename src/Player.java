import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;


public class Player {

	private static double x;
	private static double y;
	private int r = 30;
	
	private double dx;
	private double dy;
	private double moveSpeed;
	private int life;
	
	private boolean right;
	private boolean left;
	private boolean up;
	private boolean down;
		
	private Color color1;
	private Color color2;
	
	private boolean firing;
	private long firingTimer;
	private long firingDelay;
	
	private boolean recovering;
	private long recoveryTimer;
	private long recoveryDelay;
	
	private int power;
	private int currentPowerLevel;
	private int[] requiredPowerForLevel = {1, 2, 3};
	private boolean maxPowerLevel;
	
	public Player() {
		x = GamePanel.WIDTH / 2;
		y = GamePanel.HEIGHT / 2;
		
		dx = 0;
		dy = 0;
		
		moveSpeed = 3;
		life = 3;
		
		firingTimer = System.nanoTime();
		firingDelay = 200;
		
		recoveryTimer = 0;
		recoveryDelay = 1500;
		
		color1 = Color.WHITE;
		color2 = Color.RED;
	}
	
	public void setx(double x) { this.x = x;}
	public void sety(double y) { this.y= y;}
	
	
	public void setFiring(boolean b) { firing = b; }
	
	public int getx() { return (int)x; }
	public int gety() { return (int)y; }
	public int getr() { return (int)r; }
	
	public boolean isMaxPowerLevel() { return maxPowerLevel; }
	public int getPower() { return power; }
	public int getPowerLevel() { return currentPowerLevel; }
	
	public int getNumbersOfLifes() { return life; }
	
	public boolean isRecovering() { return recovering; }
	
	public void loseLife() {
		if(!recovering) {
			life--;
			if(life <= 0) {
				AvoidGame.gameover = true;
			}
			else {
				recovering = true;
				recoveryTimer = System.nanoTime();
			}
		}
	}
	
	public void gainLife() {
		life++;
	}
	
	public void GameOver() {
		AvoidGame.gameover = true;
	}
	
	public void increasePower(int i) {

	}
	
	
	public void update() {
		
		//move
		if (right) {
			dx = moveSpeed;
		}
		if (left) {
			dx = -moveSpeed;
		}
		if (up) {
			dy = -moveSpeed;
		}
		if (down) {
			dy = moveSpeed;
		}
		
		x += dx;
		y += dy;
		
		dx = 0;
		dy = 0;
		
		//border
		if (x < r) x = r;
		if (y < r) y = r;
		if (x > GamePanel.WIDTH - r) x = GamePanel.WIDTH - r;
		if (y > GamePanel.HEIGHT  - r) y = GamePanel.HEIGHT - r;
		
		//recovery code
		if(recovering) {
			long elapsed = (System.nanoTime() - recoveryTimer) / 1000000;
			if(elapsed > recoveryDelay) {
				recovering = false;
				recoveryTimer = 0;
			}
		}
		
		
	}
	
	public void draw(Graphics2D g) {
		if(recovering) {
			g.setColor(color2);
			g.fillOval((int) (x - r), (int) (y - r), r * 2, r * 2);
			g.setStroke(new BasicStroke(3));
			g.setColor(color2.darker());
			g.drawOval((int) (x - r), (int) (y - r), r * 2, r * 2);
			g.setStroke(new BasicStroke(1));
		}
		else {
			g.setColor(color1);
			g.fillOval((int) (x - r), (int) (y - r), r * 2, r * 2);
			g.setStroke(new BasicStroke(3));
			g.setColor(color1.darker());
			g.drawOval((int) (x - r), (int) (y - r), r * 2, r * 2);
			g.setStroke(new BasicStroke(1));
		}
	}
	
}
