package controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import controller.Game.STATE;
import model.Bullet;
import model.BulletP2;
import model.GameObject;
import model.ObjectId;

public class KeyInput extends KeyAdapter {
	Handler handler;
	Game game;

	public KeyInput(Handler handler, Game game) {
		this.handler = handler;
		this.game = game;
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);

			if (tempObject.getId() == ObjectId.Player) {
				if (key == KeyEvent.VK_D) {
					tempObject.setVelX(5);
				}

				if (key == KeyEvent.VK_A) {
					tempObject.setVelX(-5);
				}

				if (key == KeyEvent.VK_SPACE) {
					if (handler.isCanShoot()) {
						handler.addObject(new Bullet(tempObject.getX() + 110, tempObject.getY() - 50, handler,
								ObjectId.Bullet, -12));
						handler.setCanShoot(false);
					}
				}

				if (key == KeyEvent.VK_ESCAPE) {
					if (game.gameState == STATE.Game) {
						if (Game.paused == false) {
							Game.paused = true;
							System.out.println("Pause");
						} else {
							Game.paused = false;
						}
					}
				}
			}

			if (tempObject.getId() == ObjectId.Player2) {
				if (key == KeyEvent.VK_NUMPAD6 || key == KeyEvent.VK_RIGHT) {
					tempObject.setVelX(5);
				}

				if (key == KeyEvent.VK_NUMPAD4 || key == KeyEvent.VK_LEFT) {
					tempObject.setVelX(-5);
				}

				if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_UP) {
					if (handler.isCanShootP2()) {
						handler.addObject(new BulletP2(tempObject.getX() + 110, tempObject.getY() - 50, handler,
								ObjectId.BulletP2, -12));
						handler.setCanShootP2(false);
					}
				}
			}
		}
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);

			if (tempObject.getId() == ObjectId.Player) {
				if (key == KeyEvent.VK_D)
					tempObject.setVelX(0);
				if (key == KeyEvent.VK_A)
					tempObject.setVelX(0);

			}
			if (tempObject.getId() == ObjectId.Player2) {
				if (key == KeyEvent.VK_NUMPAD6)
					tempObject.setVelX(0);
				if (key == KeyEvent.VK_NUMPAD4)
					tempObject.setVelX(0);
				if (key == KeyEvent.VK_RIGHT)
					tempObject.setVelX(0);
				if (key == KeyEvent.VK_LEFT)
					tempObject.setVelX(0);
			}
		}
	}
}
