package model;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import controller.Game;
import controller.Handler;
import view.Animation;
import view.Texture;

public class Beam extends GameObject {

	Texture tex = Game.getInstance();
	private Animation beam;
	private Handler handler;
	private int time = 120;

	public Beam(float x, float y, Handler handler, ObjectId id, int velY) {
		super(x, y, id);
		this.velY = velY;
		this.handler = handler;
		if (handler != null) {
			beam = new Animation(1, tex.Bullet[1]);
		}
	}

	public void tick(LinkedList<GameObject> object) {
		y += velY;
		beam.runAnimation();
		if (time > 0) {
			time--;
		} else if (time == 0) {
			handler.setEnemyCanShoot(true);
			handler.removeObject(this);
		}
	}

	public void render(Graphics g) {
		beam.drawAnimation(g, (int) x, (int) y);
	}

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 16, 32);
	}

}
