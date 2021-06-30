package model;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import controller.Game;
import controller.Handler;
import view.Animation;
import view.Texture;

public class Bullet extends GameObject {

	Texture tex = Game.getInstance();
	private Animation Bullet;
	private Handler handler;
	private int time = 53;

	public Bullet(float x, float y, Handler handler, ObjectId id, int velY) {
		super(x, y, id);
		this.velY = velY;
		this.handler = handler;
		if (handler != null && velY != -99) {
			Bullet = new Animation(1, tex.Bullet[0]);
		}
	}

	public void tick(LinkedList<GameObject> object) {
		y += velY;
		Bullet.runAnimation();

		if (time > 0) {
			time--;
		} else if (time == 0) {
			handler.setCanShoot(true);
			handler.setCanShoot(true);
			handler.removeObject(this);
		}
		Collision2(object);
	}

	public void Collision2(LinkedList<GameObject> object) {
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);

			if (tempObject.getId() == ObjectId.Block) {

				if (getBounds().intersects(tempObject.getBounds())) {

					handler.setCanShoot(true);
					handler.setCanShootP2(true);
					if (handler.isEnemyCanShoot() == false) {
						handler.setEnemyCanShoot(true);
					}
					handler.removeObject(this);
				}
			}
		}
	}

	public void render(Graphics g) {
		Bullet.drawAnimation(g, (int) x - 90, (int) y + 20);
	}

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 16, 32);
	}

}
