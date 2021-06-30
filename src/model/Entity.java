package model;

public abstract class Entity extends GameObject {

	public Entity(float x, float y, ObjectId id) {
		super(x, y, id);
	}

	protected int facing = 1;
	protected int GegnerLives;
	protected static int lives = 3;
	protected boolean alive = true;
	protected static int score = 0;
	protected static int Level = 1;

	public static int getScore() {
		return score;
	}

	public static void setScore(int s) {
		score = s;
	}

	public static int getLevel() {
		return Level;
	}

	public static void setLevel(int lvl) {
		score = lvl;
	}

	public int getGegnerLives() {
		return GegnerLives;
	}

	public void setGegnerLives(int gegnerLives) {
		GegnerLives = gegnerLives;
	}

	public void SubGegnerLives() {
		GegnerLives--;
	}

	public int getFacing() {
		return facing;
	}

	public void setFacing(int facing) {
		this.facing = facing;
	}

	public int getLives() {
		return lives;
	}

}
