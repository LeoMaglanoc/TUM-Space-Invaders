package controller;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

import controller.Game.STATE;
import model.Beam;
import model.GameObject;
import model.Gegner1;
import model.Gegner2;
import model.Gegner3;
import model.Gegner4;
import model.ObjectId;
import model.Player;
import model.Player2;
import view.BufferedImageLoader;
import view.Camera;

public class Handler {

	public LinkedList<GameObject> object = new LinkedList<GameObject>();
	private GameObject tempObject;
	private Camera Cam;
	private Game game;
	private BufferedImage level = null;
	private BufferedImage levelP2 = null;
	private boolean CanShoot = true;
	private boolean CanShootP2 = true;
	private boolean enemyCanShoot = true;
	private ArrayList<GameObject> enemyList = new ArrayList();
	Random r = new Random();

	private boolean SetupComplete = false;

	public Handler(Camera Cam, Game game) {
		this.Cam = Cam;
		this.game = game;

		BufferedImageLoader loader = new BufferedImageLoader();
		level = loader.loadImage("/level.png"); // loading level
		levelP2 = loader.loadImage("/level2P.png"); // loading levelp2

	}

	public void tick() {
		for (int i = 0; i < object.size(); i++) {
			tempObject = object.get(i);
			tempObject.tick(object);
		}
		if (SetupComplete && enemyList.size() > 0) {
			// Makes enemies move and change direction at borders
			if ((enemyList.get(enemyList.size() - 1).getX() + enemyList.get(enemyList.size() - 1).getVelX()) > 760
					|| (enemyList.get(0).getX() + enemyList.get(0).getVelX()) < 0) {
				for (int index = 0; index < enemyList.size(); index++) {
					enemyList.get(index).setVelX(enemyList.get(index).getVelX() * -1);
					enemyList.get(index).setY(enemyList.get(index).getY() + 10);
				}
			}
		}
		for (int input = 0; input < enemyList.size(); input++) {
			// If aliens exceed this X Position, you reset the level and lose a life
			if (enemyList.get(input).getY() + 50 >= 750) {
				clearLevel();
				endGame();
			}
		}

		// Generates random beams shot from enemies

		if (enemyCanShoot && SetupComplete) {
			int index = r.nextInt(enemyList.size());
			addObject(new Beam(enemyList.get(index).getX(), enemyList.get(index).getY(), this, ObjectId.Beam, 10));
			enemyCanShoot = false;
		}
	}

	public void render(Graphics g) {
		for (int i = 0; i < object.size(); i++) {
			tempObject = object.get(i);

			tempObject.render(g);
		}
	}

	public void setup() {
		// Sets enemies for normal levels

		// Six rows
		for (int row = 0; row < 6; row++) {
			// 5 columns
			for (int column = 0; column < 5; column++) {
				if (column == 0) {
					GameObject enemy = new Gegner1((int) (20 + (row * 100)), (int) (20 + (column * 60)), this,
							ObjectId.Gegner);
					addObject(enemy); // Enemy speed will increase each level
					enemyList.add(enemy);

				}
				if (column == 1) {
					GameObject enemy = new Gegner2((int) (20 + (row * 100)), (int) (20 + (column * 60)), this,
							ObjectId.Gegner);
					addObject(enemy); // Enemy speed will increase each level
					enemyList.add(enemy);
				}
				if (column == 2) {
					GameObject enemy = new Gegner3((int) (20 + (row * 100)), (int) (20 + (column * 60)), this,
							ObjectId.Gegner);
					addObject(enemy); // Enemy speed will increase each level
					enemyList.add(enemy);
				}
				if (column == 3) {
					GameObject enemy = new Gegner4((int) (20 + (row * 100)), (int) (20 + (column * 60)), this,
							ObjectId.Gegner);
					addObject(enemy); // Enemy speed will increase each level
					enemyList.add(enemy);
				}
				if (column == 4) {
					GameObject enemy = new Gegner1((int) (20 + (row * 100)), (int) (20 + (column * 60)), this,
							ObjectId.Gegner);
					addObject(enemy); // Enemy speed will increase each level
					enemyList.add(enemy);
				}
			}
		}
		SetupComplete = true;
	}

	public void LoadImageLevel(BufferedImage image) {
		setup();
		int w = image.getWidth();
		int h = image.getHeight();

		System.out.println("width, height: " + w + " " + h);

		for (int xx = 0; xx < h; xx++) {
			for (int yy = 0; yy < w; yy++) {
				int pixel = image.getRGB(xx, yy);
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;

				if (red == 0 && green == 0 & blue == 255)
					addObject(new Player(xx * 40, yy * 40, this, Cam, ObjectId.Player)); // Spawn player

				if (red == 255 && green == 0 & blue == 0)
					addObject(new Player2(xx * 40, yy * 40, this, Cam, ObjectId.Player2)); // Spawn player2
			}
		}
	}

	public void switchLevel() {
		clearLevel();
		Cam.setX(0);
		Game.LEVEL++;
	}

	public void endGame() {
		game.gameState = STATE.End;
	}

	public void restartLevel() {
		clearLevel();
		switch (Game.LEVEL) {
		case 0:
			if (game.gameState == STATE.Game) {
				LoadImageLevel(level);
			} else if (game.gameState == STATE.Game2P) {
				LoadImageLevel(levelP2);
			}
			break;
		}
	}

	public void startLevel() {
		LoadImageLevel(level);
	}

	public void clearLevel() {
		object.clear();
		enemyList.clear();
		setEnemyCanShoot(true);
		setCanShoot(true);
	}

	public void addObject(GameObject object) {
		this.object.add(object);
	}

	public void removeObject(GameObject object) {
		this.object.remove(object);
	}

	public void removeEnemy(GameObject object) {
		this.enemyList.remove(object);
	}

	public int enemyindex(GameObject object) {
		return this.enemyList.indexOf(object);
	}

	public boolean isCanShoot() {
		return CanShoot;
	}

	public ArrayList<GameObject> getEnemyList() {
		return enemyList;
	}

	public boolean isSetupComplete() {
		return SetupComplete;
	}

	public void setSetupComplete(boolean setupComplete) {
		SetupComplete = setupComplete;
	}

	public void setCanShoot(boolean canShoot) {
		CanShoot = canShoot;
	}

	public boolean isEnemyCanShoot() {
		return enemyCanShoot;
	}

	public void setEnemyCanShoot(boolean enemyCanShoot) {
		this.enemyCanShoot = enemyCanShoot;
	}

	public boolean isCanShootP2() {
		return CanShootP2;
	}

	public void setCanShootP2(boolean canShootP2) {
		CanShootP2 = canShootP2;
	}
}
