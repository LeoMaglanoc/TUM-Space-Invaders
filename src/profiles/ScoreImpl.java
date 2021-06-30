package profiles;

import java.time.LocalDateTime;

public class ScoreImpl implements Score {
	private LocalDateTime date;
	private int score;
	private String name;

	public ScoreImpl(String name, String eintrag) {
		String[] split = eintrag.split("\\ ");
		score = Integer.parseInt(split[0]);
		date = LocalDateTime.parse(split[1]);
		this.name = name;
	}

	public ScoreImpl(String name, int score) {
		this.name = name;
		this.score = score;
		date = LocalDateTime.now();
	}

	public String toString() {
		return score + " " + date.toString();
	}

	public String getName() {
		return name;
	}

	public int getScore() {
		return score;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public static void main(String[] args) {
		System.out.println(LocalDateTime.now());
		String[] split = "lol kek".split("\\ ");
		for (int i = 0; i < split.length; i++) {
			System.out.println(split[i]);
		}
	}
}
