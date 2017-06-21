package client;

import entity.ArrowManager;
import entity.Hero;
import graphics.Button;
import graphics.GraphicsManager;
import graphics.Texture;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;
import javax.swing.JPanel;

import block.Block;
import level.Level;
import level.LevelManager;

public class Game implements Runnable {
	
	private int width = 800;
	private int height = 600;
	
	private long desiredFPS = 60;
	private long desiredDeltaLoop = (1000 * 1000 * 1000) / desiredFPS;
	
	private boolean running = false;
	
	JFrame frame;
	Canvas canvas;
	BufferStrategy bufferStrategy;
	
	Font font1;
	Font font2;
	
	public static SceneManager sm;
	public static ButtonManager bm;
	public static LevelManager lm;
	public static GraphicsManager gm;
	public static ArrowManager am;
	public static Camera cam;
	public static Hero cavey;
	
	public Game() {
		
		if(!init()) {
			
			Logger.printError("Game failed to initialize.");
			
		} else {
			
			running = true;
			
			frame = new JFrame("Cavey Jones");
			
			JPanel panel = (JPanel) frame.getContentPane();
			panel.setPreferredSize(new Dimension(width, height));
			panel.setLayout(null);
			
			canvas = new Canvas();
			canvas.setBounds(0, 0, width, height);
			canvas.setIgnoreRepaint(true);
			
			panel.add(canvas);
			
			canvas.addMouseListener(new Mouse());
			canvas.addKeyListener(new Key());
			
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.pack();
			frame.setResizable(false);
			frame.setVisible(true);
			frame.setLocationRelativeTo(null);
			
			canvas.createBufferStrategy(2);
			bufferStrategy = canvas.getBufferStrategy();
			
			canvas.requestFocus();
		
		}
		
	}
	
	public void run() {

		lm.setCurrentLevel(Level.LEVEL_01_NAME);
		sm.setScene(SceneManager.Scene.MAINMENU);
		
		cam = new Camera(0, 0, width, height, lm.getCurrentLevel().getWidth() * 32, lm.getCurrentLevel().getHeight() * 32);
		
		cavey = new Hero(lm.getCurrentLevel().getSpawnX(), lm.getCurrentLevel().getSpawnY());

		long beginLoopTime;
		long endLoopTime;
		long currentUpdateTime = System.nanoTime();
		long lastUpdateTime;
		long deltaLoop;
		
		while(running) {
			
			beginLoopTime = System.nanoTime();
			
			render();
			
			lastUpdateTime = currentUpdateTime;
			currentUpdateTime = System.nanoTime();
			update((currentUpdateTime - lastUpdateTime) / (1000 * 1000));
			
			endLoopTime = System.nanoTime();
			deltaLoop = endLoopTime - beginLoopTime;
			
			if(deltaLoop > desiredDeltaLoop) {
				
			} else {

				try {

					Thread.sleep((desiredDeltaLoop - deltaLoop) / (1000 * 1000));
				
				} catch(InterruptedException e) {
					
				}
				
			}
			
		}
		
	}
	
	private void render() {
		
		Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
		
		g.clearRect(0, 0, width, height);
		render(g);
		g.dispose();
		bufferStrategy.show();
		
	}
	
	protected void update(float deltaTime) {
		
		if(cam != null)
			cam.tick(deltaTime);
		
		if(cavey != null)
			cavey.tick2(deltaTime);
		
		if(lm.getCurrentLevel() != null)
			lm.getCurrentLevel().tickLevel();
		
		am.tick(deltaTime);
		
	}
	
	private void render(Graphics2D g) {
		
		switch(sm.getScene()) {
		
		case MAINMENU:
			
			g.drawImage(gm.getTexture(Texture.TITLE_SCREEN_NAME).getImage(), 0, 0, null);
			
			bm.getButton(1).render(g);
			bm.getButton(2).render(g);
			bm.getButton(6).render(g);
			bm.getButton(7).render(g);
			bm.getButton(8).render(g);
			bm.getButton(9).render(g);
			bm.getButton(10).render(g);
			bm.getButton(11).render(g);
			bm.getButton(13).render(g);
			bm.getButton(14).render(g);
			bm.getButton(15).render(g);
			bm.getButton(16).render(g);
			bm.getButton(17).render(g);
			bm.getButton(18).render(g);
			bm.getButton(19).render(g);
			
			break;
			
		case LEVEL:
			
			g.setColor(new Color(0, 20, 6, 255));
			g.fillRect(0, 0, width, height);
			
			if(lm.getCurrentLevel() != null)
				lm.getCurrentLevel().render(g, 0);
			
			am.render(g);
			
			if(cavey != null)
				cavey.render(g);
			
			if(lm.getCurrentLevel() != null)
				lm.getCurrentLevel().render(g, 1);
			
			if(lm.getCurrentLevel() != null)
				lm.getCurrentLevel().render(g, 2);
			
			bm.getButton(0).render(g);
			bm.getButton(5).render(g);
			
			g.setColor(Color.WHITE);
			g.setFont(font1);
			
			if(lm.getCurrentLevel().getCoins() > 0) {
				
				g.setColor(new Color(200, 200, 200, 180));
				g.fillRect(20, 20, lm.getCurrentLevel().getCoins() * 36 + 2, 40);
				g.setColor(Color.BLACK);
				g.drawRect(20, 20, lm.getCurrentLevel().getCoins() * 36 + 2, 40);
				for(int i = 0; i < lm.getCurrentLevel().getCoins(); i++) {
					
					g.drawImage(gm.getSprite(Block.COIN_00_NAME).getSprite(), 24 + 36 * i, 24, null);
					
				}
				
			}
			
			break;
			
		case LEVELDEAD:
			
			g.setColor(new Color(0, 20, 6, 255));
			g.fillRect(0, 0, width, height);
			
			if(lm.getCurrentLevel() != null)
				lm.getCurrentLevel().render(g, 0);
			
			am.render(g);
			
			if(cavey != null)
				cavey.render(g);
			
			if(lm.getCurrentLevel() != null)
				lm.getCurrentLevel().render(g, 1);
			
			g.setColor(new Color(90, 80, 80, 200));
			g.fillRect(0, 0, width, height);
			
			g.setFont(font2);
			g.setColor(new Color(0, 0, 0, 255));
			g.drawString("You died.", width / 2 - 60, height / 2 - 200);
			
			bm.getButton(3).render(g);
			bm.getButton(4).render(g);
			
			break;
			
		case LEVELCOMPLETE:
		
			g.setColor(new Color(0, 20, 6, 255));
			g.fillRect(0, 0, width, height);
			
			if(lm.getCurrentLevel() != null)
				lm.getCurrentLevel().render(g, 0);
			
			am.render(g);
			
			if(cavey != null)
				cavey.render(g);
			
			if(lm.getCurrentLevel() != null)
				lm.getCurrentLevel().render(g, 1);
			
			g.setColor(new Color(128, 128, 128, 200));
			g.fillRect(10, 10, width - 20, height - 20);
			
			g.setFont(font2);
			g.setColor(new Color(0, 0, 0, 255));
			g.drawString("Level complete!", width / 2 - 100, height / 2 - 200);
			
			bm.getButton(12).render(g);
			bm.getButton(4).render(g);
			
		}
		
	}
	
	public boolean init() {

		font1 = new Font("Calibri", 0, 16);
		font2 = new Font("Calibri", 0, 32);
		
		sm = new SceneManager();
		bm = new ButtonManager();
		lm = new LevelManager();
		gm = new GraphicsManager();
		am = new ArrowManager();
		
		bm.addButton(new Button(width - 120, 70, 100, 40, "Main Menu", font1, 0));
		bm.addButton(new Button(196, 207, 120, 50, "Level 1", font1, 2));
		bm.addButton(new Button(196, 277, 120, 50, "Level 2", font1, 2));
		bm.addButton(new Button(width / 2 - 50, height / 2 - 50, 100, 40, "Respawn", font1, 0));
		bm.addButton(new Button(width / 2 - 50, height / 2, 100, 40, "Main Menu", font1, 0));
		bm.addButton(new Button(width - 120, 20, 100, 40, "Respawn", font1, 0));
		bm.addButton(new Button(196, 347, 120, 50, "Level 3", font1, 2));
		bm.addButton(new Button(196, 417, 120, 50, "Level 4", font1, 2));
		bm.addButton(new Button(196, 487, 120, 50, "Level 5", font1, 2));
		bm.addButton(new Button(336, 207, 120, 50, "Level 6", font1, 2));
		bm.addButton(new Button(336, 277, 120, 50, "Level 7", font1, 2));
		bm.addButton(new Button(336, 347, 120, 50, "Level 8", font1, 2));
		bm.addButton(new Button(width / 2 - 50, height / 2 - 50, 100, 40, "Next Level", font1, 7));
		bm.addButton(new Button(336, 417, 120, 50, "Level 9", font1, 0));
		bm.addButton(new Button(336, 487, 120, 50, "Level 10", font1, 0));
		bm.addButton(new Button(476, 207, 120, 50, "Level 11", font1, 0));
		bm.addButton(new Button(476, 277, 120, 50, "Level 12", font1, 0));
		bm.addButton(new Button(476, 347, 120, 50, "Level 13", font1, 0));
		bm.addButton(new Button(476, 417, 120, 50, "Level 14", font1, 0));
		bm.addButton(new Button(476, 487, 120, 50, "Level 15", font1, 0));
		
		lm.addLevel(Level.LEVEL_00_FILE, Level.LEVEL_00_NAME);
		lm.addLevel(Level.LEVEL_01_FILE, Level.LEVEL_01_NAME);
		lm.addLevel(Level.LEVEL_02_FILE, Level.LEVEL_02_NAME);
		lm.addLevel(Level.LEVEL_03_FILE, Level.LEVEL_03_NAME);
		lm.addLevel(Level.LEVEL_04_FILE, Level.LEVEL_04_NAME);
		lm.addLevel(Level.LEVEL_05_FILE, Level.LEVEL_05_NAME);
		lm.addLevel(Level.LEVEL_06_FILE, Level.LEVEL_06_NAME);
		lm.addLevel(Level.LEVEL_07_FILE, Level.LEVEL_07_NAME);
		lm.addLevel(Level.LEVEL_08_FILE, Level.LEVEL_08_NAME);
		lm.addLevel(Level.LEVEL_09_FILE, Level.LEVEL_09_NAME);
		lm.addLevel(Level.LEVEL_10_FILE, Level.LEVEL_10_NAME);
		lm.addLevel(Level.LEVEL_11_FILE, Level.LEVEL_11_NAME);
		lm.addLevel(Level.LEVEL_12_FILE, Level.LEVEL_12_NAME);
		lm.addLevel(Level.LEVEL_13_FILE, Level.LEVEL_13_NAME);
		lm.addLevel(Level.LEVEL_14_FILE, Level.LEVEL_14_NAME);
		lm.addLevel(Level.LEVEL_FLYINGARROWS_FILE, Level.LEVEL_FLYINGARROWS_NAME);
		//lm.addLevel("res/levels/debug.txt", "debug");
		
		return lm.init() && gm.init();
		
	}
	
	public boolean isRunning() {
		
		return running;
		
	}
	
	public int getWidth() { return width; }
	public int getHeight() { return height; }
	
	public static void main(String[] args) {
		
		Game game = new Game();
		
		Logger.printDebug("Number of levels added: " + lm.addedSize());
		Logger.printDebug("Number of levels loaded: " + lm.loadedSize());
		
		if(game.isRunning()) 
			new Thread(game).start();
		
	}

}
