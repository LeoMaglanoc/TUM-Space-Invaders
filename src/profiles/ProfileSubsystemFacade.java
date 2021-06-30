package profiles;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class ProfileSubsystemFacade {

	private ProfileDatabase data;

	public ProfileSubsystemFacade() {
		data = new ProfileDatabase();
	}

	public void addName(String input) {
		data.addName(input);
	}

	public boolean removeName(String input) {
		return data.removeName(input);
	}

	public void resetScores() {
		data.resetScores();
	}

	public void addScore(String name, int score) {
		data.addScore(name, score);
		System.out.println("DEBUG-data.addscore");
	}

	public void saveToFile() {
		try {
			Csv_Input_Output.saveToFile(data);
		} catch (IOException e) {
			System.out.println("Fail beim Finden der Datei");
		}
	}

	public String getRecentScore(int stelle) {
		return data.getRecentScore(stelle);
	}

	public String getTopScore(int stelle) {
		return data.getTopScore(stelle);
	}

	public List<Score> getScores() {
		return data.getScores();
	}

	public HashMap<String, Profile> getProfiles() {
		return data.getProfiles();
	}

	public static void main(String[] args) {
		var data = new ProfileSubsystemFacade();
		data.addScore("Vicente", 5000);
		System.out.println(data.getRecentScore(0));
		data.saveToFile();
	}
}
