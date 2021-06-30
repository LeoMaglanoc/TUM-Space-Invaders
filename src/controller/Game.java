package controller;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.Random;

import model.Entity;
import model.ObjectId;
import view.Animation;
import view.BufferedImageLoader;
import view.Camera;
import view.Menu;
import view.Texture;
import view.Window;

public class Game extends Canvas implements Runnable {
	private static final long serialVersionUID = -69692312996969L;

	private boolean running = false;
	private Thread thread;
	private static Window window;
	public static boolean paused = false;

	private Menu menu;
	public static int WIDTH, HEIGHT;

	public BufferedImage level = null;
	public BufferedImage levelP2 = null;
	public BufferedImage background = null;

	// Object
	Handler handler;
	Camera Cam;
	static Texture tex;

	Random rand = new Random();

	public static int LEVEL = 0;
	private Animation Gegner1;
	private Animation Gegner2;

	public enum STATE {
		menu, Game, Game2P, Help, Profile, Credits, Add, Remove, Scores, RecSc, HighSc, End
	};

	public STATE gameState = STATE.menu;

	public void init() { // Initialisiert alle Start Values und Klassen

		Entity.setScore(0);
		WIDTH = getWidth();
		HEIGHT = getHeight();

		tex = new Texture();
		menu = new Menu(this, window);
		Cam = new Camera(0, 0);
		handler = new Handler(Cam, this);
		this.addMouseListener(menu);

		BufferedImageLoader loader = new BufferedImageLoader();
		level = loader.loadImage("/level.png"); // loading level
		levelP2 = loader.loadImage("/level2P.png");

		if (gameState == STATE.Game) {
			handler.LoadImageLevel(level);
		} else {
			if (gameState == STATE.Game2P) {
				handler.LoadImageLevel(levelP2);
			}
		}

		this.addKeyListener(new KeyInput(handler, this));
		Gegner1 = new Animation(6, tex.gegner[0], tex.gegner[1], tex.gegner[2], tex.gegner[3], tex.gegner[4],
				tex.gegner[5], tex.gegner[6], tex.gegner[7], tex.gegner[8]);
		Gegner2 = new Animation(6, tex.gegner[9], tex.gegner[10], tex.gegner[11], tex.gegner[12], tex.gegner[13],
				tex.gegner[14], tex.gegner[15], tex.gegner[16], tex.gegner[17]);
	}

	public synchronized void start() {
		if (running)
			return;

		running = true;
		thread = new Thread(this);
		thread.start();
	}

	public void run() // Spiel Clock
	{
		init(); // Handelt die Ticks, Frames --> Ticks und Frames werden Synchronisiert um Input
				// Lags zu vermeiden
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int updates = 0;
		int frames = 0;
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick(); // Ticks werden ausgeführt --> angestrebt 60 pro sekunde
				updates++;
				delta--;
			}
			render(); // Render method in allen objecten wird ausgeführt --> Frame rate kann variieren
						// je nach rechnerleistung
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println("FPS: " + frames + " TICKS: " + updates); // Frames und Ticks werden geprintet
				frames = 0;
				updates = 0;
			}
		}
	}

	private void render() {
		BufferStrategy bs = this.getBufferStrategy(); // Frame Buffer
		if (bs == null) {
			this.createBufferStrategy(3); // 3 Bilder werden gebuffert
			return;
		}

		Graphics g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D) g;

		if (paused) {

			g.drawString("PAUSED", 200, 200);

		}

		if (gameState == STATE.Game) {
			g.setColor(Color.black);
			g.fillRect(0, 0, getWidth(), getHeight());

		} else if (gameState == STATE.Game2P) {
			g.setColor(Color.black);
			g.fillRect(0, 0, getWidth(), getHeight());

		} else if (gameState == STATE.menu) {
			g.setColor(Color.black);

			g.fillRect(0, 0, getWidth(), getHeight());
			Gegner1.drawAnimation(g, 50, 550, 180, 180);
			Gegner2.drawAnimation(g, 590, 400, 180, 180);
			menu.render(g);
		} else if (gameState == STATE.Help) {
			g.setColor(Color.black);
			g.fillRect(0, 0, getWidth(), getHeight());

			menu.render(g);
		} else if (gameState == STATE.Credits) {
			g.setColor(Color.black);
			g.fillRect(0, 0, getWidth(), getHeight());

			menu.render(g);
		} else if (gameState == STATE.Profile) {

			g.setColor(Color.black);
			g.fillRect(0, 0, getWidth(), getHeight());
			menu.render(g);
		} else if (gameState == STATE.Add) {

			g.setColor(Color.black);
			g.fillRect(0, 0, getWidth(), getHeight());
			menu.render(g);
		} else if (gameState == STATE.Remove) {

			g.setColor(Color.black);
			g.fillRect(0, 0, getWidth(), getHeight());
			menu.render(g);
		} else if (gameState == STATE.Scores) {

			g.setColor(Color.black);
			g.fillRect(0, 0, getWidth(), getHeight());
			menu.render(g);
		} else if (gameState == STATE.HighSc) {

			g.setColor(Color.black);
			g.fillRect(0, 0, getWidth(), getHeight());

			menu.render(g);
		} else if (gameState == STATE.RecSc) {

			g.setColor(Color.black);
			g.fillRect(0, 0, getWidth(), getHeight());
			menu.render(g);
		} else if (gameState == STATE.End) {

			g.setColor(Color.black);
			g.fillRect(0, 0, getWidth(), getHeight());

			menu.render(g);
			handler = new Handler(Cam, this);
		}

		g2d.translate(Cam.getX(), Cam.getY()); // begin of cam

		handler.render(g);

		g2d.translate(-Cam.getX(), -Cam.getY());// end of cam

		g.dispose();
		bs.show();

	}

	private void tick() {

		if (gameState == STATE.Game) {

			if (!paused) {
				handler.tick();
				for (int i = 0; i < handler.object.size(); i++) {
					if (handler.object.get(i).getId() == ObjectId.Player) {
						Cam.tick(handler.object.get(i));
					}
				}
			}
		} else if (gameState == STATE.Game2P) {

			if (!paused) {
				handler.tick();
				for (int i = 0; i < handler.object.size(); i++) {
					if (handler.object.get(i).getId() == ObjectId.Player) {
						Cam.tick(handler.object.get(i));
					}
				}
			}
		} else if (gameState == STATE.menu) {
			menu.tick();
			handler.tick();
			Gegner1.runAnimation();
			Gegner2.runAnimation();

		}

		if (gameState == STATE.Credits) {
			menu.tick();
		}
	}

	public static Texture getInstance() {
		return tex;
	}

	public static void main(String args[]) {
		window = new Window(800, 800, "Space Invaders", new Game());
	}

	public STATE getGameState() {
		return gameState;
	}

	public void setGameState(STATE gs) {
		gameState = gs;
	}

	public void stop() {
		running = false;
	}

}
