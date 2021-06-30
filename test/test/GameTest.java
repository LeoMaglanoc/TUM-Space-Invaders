package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.awt.Rectangle;
import java.util.LinkedList;

import org.junit.Test;

import controller.Game;
import controller.Game.STATE;
import controller.Handler;
import model.Beam;
import model.Bullet;
import model.GameObject;
import model.Gegner1;
import model.ObjectId;
import model.Player;

public class GameTest {

	@Test
	public void testBoundsGegner1() {
		Gegner1 g = new Gegner1(100, 100, null, ObjectId.Gegner);
		assertEquals("Not equal",
		        g.getBounds(),
		        new Rectangle((int) ((int) 100 + (40 / 2) - ((40 / 2) / 2)) + 85,
		                (int) ((int) 100 + (40 / 2)),
		                (int) 40 / 2,
		                (int) 40 / 2));
	}

	@Test
	public void testObjectIdBullet() {
		Bullet b = new Bullet(100, 100, null, ObjectId.Bullet, 5);
		assertTrue("Not equal", ObjectId.Bullet == b.getId());
	}

	@Test
	public void testGameState() {
		Game g = new Game();
		Handler h = new Handler(null, g);
		h.endGame();
		assertTrue("Not equal", g.getGameState() == STATE.End);
	}

	@Test
	public void testPlayerColision() {
		Beam n = new Beam(100, 100, null, ObjectId.Beam, -5);
		Handler h = new Handler(null, null);
		Player p = new Player(100, 100, h, null, ObjectId.Player);
		h.object.add(n);
		LinkedList<GameObject> l = new LinkedList<>();
		l.add(p);
		int livesStart = p.getLives();
		p.Collision(l);
		assertTrue(livesStart - 1 == p.getLives());
	}

	@Test
	public void testScoreAdding() {
		Handler h = new Handler(null, null);
		Bullet n = new Bullet((int) ((int) 100 + (40 / 2) - ((40 / 2) / 2))
		        + 85, (int) 100 + 2, h, ObjectId.Bullet, -99);
		Player p = new Player(300, 300, h, null, ObjectId.Player);
		Gegner1 g1 = new Gegner1(100, 100, h, ObjectId.Block);
		h.object.add(n);
		LinkedList<GameObject> l = new LinkedList<>();
		l.add(g1);
		int scoreStart = p.getScore();
		g1.Collision(l);
		int scoreEnd = p.getScore();
		assertEquals(scoreStart + 200, scoreEnd);
	}

}
