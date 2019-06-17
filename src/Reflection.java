import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;


public class Reflection {
	
	private double x;
	private double y;
	private int r;
	
	private long timer;
	private long[] timerDiff;
	private int currTimerDiff;
	private long reflectionTimer;
	private long waitTimer;
	
	private Color colorNotEnabled;
	private Color colorHit;
	private Color colorEnabled;
	
	private boolean isHit;
	private boolean isEnabled;
	
	public Reflection() {
		
		r = GamePanel.WIDTH / 5;
		
		x = GamePanel.WIDTH / 2;
		y = GamePanel.HEIGHT / 2;		
		
		timerDiff = new long[5];
		currTimerDiff = 0;
		
		Random random = new Random(20);
		int a = random.nextInt(50);
		waitTimer = a * 500 + 1000;
		
		timer = System.nanoTime();
		
		colorNotEnabled = Color.RED;
		colorHit = Color.WHITE;
		colorEnabled = Color.GREEN;
		
		isHit = false;
		isEnabled = false;
	}
	
	public double getx() { return x; }
	public double gety() { return y; }
	
	public void setHit(boolean b) { isHit = b; }
	
	public void update() {
		
		if(isHit) {
			timerDiff[currTimerDiff] = (System.nanoTime() - timer) / 1000000;
			currTimerDiff++;
			isHit = false;
			Random random = new Random(20);
			int a = random.nextInt(50);
			waitTimer = a  * 500 + 1000;
		}
		
		long elapsed = (System.nanoTime() - timer) / 1000000;
		if(elapsed > waitTimer) {
			isEnabled = true;
			timer = System.nanoTime();
		}		
	}
	
	public void draw(Graphics2D g) {
		if(!isHit && !isEnabled) {
			g.setColor(colorNotEnabled);
		}
		else if(!isHit && isEnabled) {
			g.setColor(colorEnabled);
		}
		else if(isHit) {
			g.setColor(colorHit);
		}
		
		g.fillOval((int) (x - r), (int) (y - r), r * 2, r * 2);
		for(int i = 0; i < timerDiff.length; i++) {
			g.drawString(timerDiff[i] + "", 100, 100 * (i + 1));
		}
		
		g.drawString(isHit + "", 200, 500);
		
	}
}
