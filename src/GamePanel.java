import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;



public class GamePanel extends JPanel implements Runnable, KeyListener, MouseMotionListener, MouseListener{
	
	//Dimension
	public static final int WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
	public static final int HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
	
	//game thread
	private static Thread thread;
	private boolean running;
	private int FPS = 300;
	private long targetTime = 1000 / FPS;
	
	//image
	private BufferedImage image;
	private Graphics2D g;
	
	//game state manager
	private GameStateManager gsm;
	
	//Constructor
	public GamePanel() {
		super();
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setFocusable(true);
		requestFocus();
	}
	
	public void addNotify() {
		super.addNotify();
		if (thread == null) {
			thread = new Thread(this);
			addKeyListener(this);
			thread.start();
		}
		addKeyListener(this);
		addMouseMotionListener(this);
		addMouseListener(this);
	}
	//Initialize
	private void init() {		
		running = true;
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) image.getGraphics();
		
		gsm = new GameStateManager();
		
		Game.window.getContentPane().setCursor(Cursor.getDefaultCursor());
		
	}
	
	public void run() {
		init();
		
		long start;
		long elapsed;
		long wait;
		
		//game loop
		while (running) {
			
			start = System.nanoTime();
			
			update();
			draw();
			drawToScreen();
			
			elapsed = System.nanoTime() - start;
			
			wait = targetTime - elapsed / 1000000;
			
			if (wait < 0) wait = 5;
			
			try {
				Thread.sleep(wait);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	//update
	public void update() {
		gsm.update();
	}
	
	//draw
	public void draw() {
		gsm.draw(g);
	}
	
	//draw to screen
	public void drawToScreen() {
		Graphics g2 = getGraphics();
		g2.drawImage(image, 0, 0, WIDTH, HEIGHT, null);
		g2.dispose();
	}
	
	//keys
	public void keyTyped(KeyEvent key) {}
	public void keyPressed(KeyEvent key) {
		gsm.keyPressed(key.getKeyCode());
	}
	public void keyReleased(KeyEvent key) {
		gsm.keyReleased(key.getKeyCode());
	}
	
	public void mouseDragged(MouseEvent e) {
		gsm.mouseDragged(e);
	}
	
	public void mouseMoved(MouseEvent e) {
		gsm.mouseMoved(e);
	}

	public void mouseClicked(MouseEvent e) {
		gsm.mouseMoved(e);
	}

	public void mousePressed(MouseEvent e) {
		gsm.mouseMoved(e);
	}

	public void mouseReleased(MouseEvent e) {
		gsm.mouseMoved(e);
	}

	public void mouseEntered(MouseEvent e) {
		gsm.mouseMoved(e);
	}

	public void mouseExited(MouseEvent e) {
		gsm.mouseMoved(e);
	}
}
