import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;


public class Enemy {

	private double x;
	private double y;
	private int r;
	
	private double rad;
	
	private double dx;
	private double dy;
	
	private double moveSpeed;
	private int health;
	
	private boolean dead;
	private long hitTimer;
	private boolean hit;
	
	private int rank;
	private int type;
	
	private Color color1;
	private Color color2;
	
	private boolean slow;
	
	public Enemy(int type, int rank) {
		
		this.type = type;
		this.rank = rank;
		
		color2 = Color.WHITE;
		slow = false;
		
		//default enemy
		if (type == 1) {
			color1 = new Color(0, 0, 255, 128);
			if (rank == 1) {
				moveSpeed = 8;
				health = 2;
				r = 10;
			}
			else if (rank == 2) {
				moveSpeed = 2;
				r = 10;
				health = 3;
			}
			else if (rank == 3) {
				moveSpeed = 2;
				r = 20;
				health = 6;
			}			
			else if (rank == 4) {
				moveSpeed = 2;
				r = 45;
				health = 8;
			}
		}
		// stronger, faster enemy default
		if (type == 2) {
			color1 = new Color(255, 0, 0, 128);
			if (rank == 1) {
				moveSpeed = 3;
				r = 5;
				health = 2;
			}
			else if (rank == 2) {
				moveSpeed = 2.3;
				r = 10;
				health = 3;
			}
			else if (rank == 3) {
				moveSpeed = 2;
				r = 20;
				health = 6;
			}
		}
		// small, slow enemy default
		if (type == 3) {
			color1 = new Color(0, 255, 0, 128);
			if (rank == 1) {
				moveSpeed = 1;
				r = 2;
				health = 2;
			}
			else if (rank == 2) {
				moveSpeed = 1;
				r = 5;
				health = 3;
			}
			else if (rank == 3) {
				moveSpeed = 1;
				r = 10;
				health = 4;
			}
		}
		// small, fast enemy default
		if (type == 4) {
			color1 = new Color(0, 255, 255, 128);
			if (rank == 1) {
				moveSpeed = 5;
				r = 3;
				health = 1;
			}
			else if (rank == 2) {
				moveSpeed = 5;
				r = 5;
				health = 2;
			}
			else if (rank == 3) {
				moveSpeed = 5;
				r = 8;
				health = 3;
			}
		}

		
		x = Math.random() * GamePanel.WIDTH / 2 + GamePanel.WIDTH / 4;		
		y = -r;
		
		double angle = Math.random() * 140 + 20;
		rad = Math.toRadians(angle);
		
		dx = Math.cos(rad) * moveSpeed;
		dy = Math.cos(rad) * moveSpeed;
		
		dead = false;
	}
	
	public double getx() { return x; }
	public double gety() { return y; }
	public double getr() { return r; }
	
	public int getRank() { return rank; }
	public int getType() { return type; }
	
	public void update() {
		
		//move
		if(slow) {
			x += dx * 0.3;
			y += dy * 0.3;
		}
		else {
			x += dx;
			y += dy;
		}
		
		//border
		if(x < r && dx < 0) {
			x = r;
			dx = -dx;
		}
		if(y < r && dy < 0) {
			y = r;
			dy = -dy;
		}
		if(x > GamePanel.WIDTH - r && dx > 0) {
			x = GamePanel.WIDTH - r;
			dx = -dx;
		}
		if(y > GamePanel.HEIGHT - r && dy > 0) {
			y = GamePanel.HEIGHT - r;
			dy = -dy;
		}	
		
		//hit
		if(hit) {
			long elapsed = (System.nanoTime() - hitTimer) / 1000000;
			if(elapsed > 50) {
				hit = false;
				hitTimer = 0;
				elapsed = 0;
			}
		}
	}
	
	public void draw(Graphics2D g) {
		if(hit) {
			g.setColor(color2);
			g.fillOval((int) (x - r), (int) (y - r), 2 * r, 2 * r);
			g.setStroke(new BasicStroke(3));
			g.setColor(color2.darker());
			g.drawOval((int) (x - r), (int) (y - r), 2 * r, 2 * r);
			g.setStroke(new BasicStroke(1));
			
		}
		else {
			g.setColor(color1);
			g.fillOval((int) (x - r), (int) (y - r), 2 * r, 2 * r);
			g.setStroke(new BasicStroke(3));
			g.setColor(color1.darker());
			g.drawOval((int) (x - r), (int) (y - r), 2 * r, 2 * r);
			g.setStroke(new BasicStroke(1));
		}
	}
}
