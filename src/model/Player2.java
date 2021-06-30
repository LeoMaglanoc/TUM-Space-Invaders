package model;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;
import controller.Game;
import controller.Handler;
import view.Animation;
import view.Camera;
import view.Texture;

public class Player2 extends Entity {

	private float width = 40, height = 40;
	private boolean Leben = true;
	// 1 = Rechts
	// -1 = Links
	private Handler handler;
	Texture tex = Game.getInstance();
	private Animation playerIdle;
	private Animation playerlinks;
	private Animation playerrechts;

	public Player2(float x, float y, Handler handler, Camera Cam, ObjectId id) {
		super(x, y, id);
		this.handler = handler;
		if (Cam != null) {
			// Texturen
			playerIdle = new Animation(18, tex.playerP2[0], tex.playerP2[1], tex.playerP2[2], tex.playerP2[3]);
			playerlinks = new Animation(18, tex.playerP2[4], tex.playerP2[5], tex.playerP2[6], tex.playerP2[7]);
			playerrechts = new Animation(18, tex.playerP2[8], tex.playerP2[9], tex.playerP2[10], tex.playerP2[11]);
		}
	}

	public void tick(LinkedList<GameObject> object) {
		if (alive == true) {
			x += velX;
			y += velY;
			if (velX < 0)
				setFacing(1);
			else if (velX > 0)
				setFacing(-1);

			if (x > 800) {
				x = -50;
			}
			if (x < -50) {
				x = 800;

			}
		}
		// Level wird erhöht wenn alle Gegner getötet wurden
		if (handler.getEnemyList().size() == 0) {
			lives = 3;
			Level++;
			handler.restartLevel();
		}
		// Kollisionen werden überprüft
		Collision(object);
		playerIdle.runAnimation();
		playerlinks.runAnimation();
		playerrechts.runAnimation();
	}

	public void Collision(LinkedList<GameObject> object) {
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);

			if (tempObject.getId() == ObjectId.Gegner) {// Wenn spieler von gegnern getroffen wird

				if (getBoundsTop().intersects(tempObject.getBounds())
						|| getBoundsRight().intersects(tempObject.getBounds())
						|| getBoundsLeft().intersects(tempObject.getBounds())) {

					if (Leben == true) {
						if (GegnerLives > 1) {
							GegnerLives--;
							Leben = false;
						} else if (GegnerLives <= 1) {
							GegnerLives = 0;
							alive = false;
							System.out.println("Du bist Tod");
							Leben = false;
						}
					}
				}
			}

			if (tempObject.getId() == ObjectId.Beam) {// Wenn Spieler von beam getroffen wird

				if (getBoundsTop().intersects(tempObject.getBounds())
						|| getBoundsRight().intersects(tempObject.getBounds())
						|| getBoundsLeft().intersects(tempObject.getBounds())) {

					handler.removeObject(tempObject);
					if (Leben == true) {
						if (lives > 1) {
							lives--;

						} else if (lives <= 1) {
							lives = 0;
							alive = false;
							Leben = false;
						}
					}
					handler.setEnemyCanShoot(true);
				}
			}
		}
	}

	public void render(Graphics g) {

		if (alive == true) { // Spieler wird gerendert nur dnn wenn er lebt
			if (velX != 0) {
				if (getFacing() == 1) { // Wenn der spieler nach links fliegt
					playerlinks.drawAnimation(g, (int) x, (int) y, 70, 70);
				} else {// Wenn der Spieler nach rechts fliegt
					playerrechts.drawAnimation(g, (int) x, (int) y, 70, 70);
				}
			} else {// wenn der spieler still steht
				playerIdle.drawAnimation(g, (int) x, (int) y, 70, 70);
			}

		} else {
			lives = 3;
			handler.clearLevel();
			handler.endGame();
		}
		Font fnt2 = new Font("arial", 1, 13);
		g.setFont(fnt2);
		g.drawString("Spieler2", (int) (x + 9), (int) (y + 80));
	}

	public Rectangle getBounds() {

		return new Rectangle(0, 0, 0, 0);
	}

	public Rectangle getBoundsTop() {

		return new Rectangle((int) ((int) x + (width / 2) + 15 - ((width / 2) / 2)), (int) y + 2 - 10, (int) width / 2,
				(int) height / 2 + 18);
	}

	public Rectangle getBoundsRight() {

		return new Rectangle((int) ((int) x + width - 5), (int) y + 30, 35, 30);
	}

	public Rectangle getBoundsLeft() {

		return new Rectangle((int) ((int) x + width - 45) + 5, (int) y + 30, 35, 30);
	}

}
