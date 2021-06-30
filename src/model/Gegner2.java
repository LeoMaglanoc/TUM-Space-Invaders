package model;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

//import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;

import controller.Game;
import controller.Handler;
import view.Animation;
import view.Texture;

public class Gegner2 extends Entity {

	private float width = 40, height = 40;
	private int Hit = 0;
	private boolean Leben = true;
	private boolean Alive = true;
	private int Deathtime = 0;
	private Handler handler;
	Texture tex = Game.getInstance();
	private Animation Gegner2;
	private Animation Gegner_tod;

	public Gegner2(float x, float y, Handler handler, ObjectId id) {
		super(x, y, id);
		this.handler = handler;

		GegnerLives = 1;
		velX = 2 + Level;
		Gegner2 = new Animation(6, tex.gegner[9], tex.gegner[10], tex.gegner[11], tex.gegner[12], tex.gegner[13],
				tex.gegner[14], tex.gegner[15], tex.gegner[16], tex.gegner[17]);
	}

	public void tick(LinkedList<GameObject> object) {

		if (Alive == true) {
			x += velX;
			y += velY;
			if (velX < 0)
				facing = 1;
			else if (velX > 0)
				facing = -1;
		} else if (Alive == false) {
			Deathtime++;

		}
		if (Alive == false && Deathtime >= 150) {
			handler.removeObject(this);
			Deathtime = 0;
		}

		Collision(object);
		Gegner2.runAnimation();
	}

	public void Collision(LinkedList<GameObject> object) {
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);

			if (tempObject.getId() == ObjectId.Bullet) {

				if (getBoundsTop().intersects(tempObject.getBounds())) {

					if (Leben == true) {
						if (GegnerLives > 1) {
							GegnerLives--;
							Leben = false;

						} else if (GegnerLives <= 1) {
							GegnerLives = 0;
							Alive = false;
							System.out.println("Du bist Tod");
							Leben = false;
							handler.removeEnemy(this);
						}
					}

					handler.setCanShoot(true);
					handler.removeObject(tempObject);
					Entity.setScore(score + 200);
					System.out.println(Entity.getScore());
					Hit = 400;
				}
			}
			if (tempObject.getId() == ObjectId.BulletP2) {

				if (getBoundsTop().intersects(tempObject.getBounds())) {

					if (Leben == true) {
						if (GegnerLives > 1) {
							GegnerLives--;
							Leben = false;

						} else if (GegnerLives <= 1) {
							GegnerLives = 0;
							Alive = false;
							System.out.println("Du bist Tod");
							Leben = false;
							handler.removeEnemy(this);
						}
					}

					handler.setCanShootP2(true);
					handler.removeObject(tempObject);
					Entity.setScore(score + 200);
					System.out.println(Entity.getScore());
					Hit = 400;
				}
			}
		}
	}

	public void render(Graphics g) {

		if (Alive == true) {
			Gegner2.drawAnimation(g, (int) x - 5, (int) y, 40, 40);
		}
		HitEnemy(g);
	}

	public void HitEnemy(Graphics g) {
		if (Hit > 0) {
			g.setColor(Color.yellow);
			Font fnt = new Font("arial", 1, 10);

			g.setFont(fnt);
			g.drawString("200", (int) x, (int) y);
			Hit--;
		}
	}

	public Rectangle getBounds() {
		if (Alive) {
			return new Rectangle((int) ((int) x + (width / 2) - ((width / 2) / 2)) + 85, (int) ((int) y + (height / 2)),
					(int) width / 2, (int) height / 2);
		}
		return new Rectangle(1, 1, 1, 1);

	}

	public Rectangle getBoundsTop() {
		if (Alive) {
			return new Rectangle((int) ((int) x + (width / 2) - ((width / 2) / 2)) + 85, (int) y + 2, (int) width / 2,
					(int) height / 2);
		}
		return new Rectangle(1, 1, 1, 1);
	}

	public Rectangle getBoundsRight() {
		if (Alive) {
			return new Rectangle((int) ((int) x + width - 5) + 85, (int) y + 5, (int) 5, (int) height - 10);
		}
		return new Rectangle(1, 1, 1, 1);
	}

	public Rectangle getBoundsLeft() {
		if (Alive) {
			return new Rectangle((int) x + 85, (int) y + 5, (int) 5, (int) height - 10);
		}
		return new Rectangle(1, 1, 1, 1);
	}

}
