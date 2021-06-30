package profiles;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProfileDatabase {

	private HashMap<String, Profile> profiles;
	private List<Score> scores;

	public ProfileDatabase() {
		profiles = Csv_Input_Output.processProfilesFile();
		scores = Csv_Input_Output.processHighscoresFile();
	}

	String getRecentScore(int stelle) {
		//name..................score
		try {
			scores.sort((s1, s2) -> s1.getDate().compareTo(s2.getDate()));
			var temp = scores.get(scores.size() - 1 - stelle);
			return temp.getName() + "....................." + temp.getScore();
		} catch (IndexOutOfBoundsException e) {
			return "N/A";
		}
	}

	String getTopScore(int stelle) {
		//name..................score
		try {
			scores.sort((s1, s2) -> Integer.compare(s1.getScore(), s2.getScore()));
			var temp = scores.get(scores.size() - 1 - stelle);
			return temp.getName() + "....................." + temp.getScore();
		} catch (IndexOutOfBoundsException e) {
			return "N/A";
		}
	}

	void addName(String input) {
		input = clean(input);
		if (profiles.containsKey(input)) {
			return;
		}
		profiles.put(input, new Profile(input));
	}

	boolean removeName(String input) {
		input = clean(input);
		if (!profiles.containsKey(input)) {
			return false;
		} else {
			profiles.remove(input);
			return true;
		}
	}

	private String clean(String input) {
		return input.replaceAll("\\s", "");
	}

	void resetScores() {
		scores = new ArrayList<Score>();
	}

	public String getScore(int order) {
		Score temp = scores.get(order);
		return temp.getName() + "......................" + temp.getScore();
	}

	void addScore(String name, int score) {
		if (!profiles.containsKey(name)) {
			profiles.put(name, new Profile(name));
			System.out.println("DEBUG-Name-exist-profiles.put");
		}
		scores.add(new ScoreImpl(name, score));
		System.out.println("DEBUG-Name-dont-exist-profiles.put");
	}

	List<Score> getScores() {
		return scores;
	}

	public void setScores(List<Score> list) {
		scores = list;
	}

	HashMap<String, Profile> getProfiles() {
		return profiles;
	}

}
