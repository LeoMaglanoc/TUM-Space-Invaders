package profiles;

import java.io.BufferedWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Csv_Input_Output {

	private static Path directory = Paths.get("res");

	private static Path path_profiles;
	private static Path path_highscores;

	public static void setup() {
		try {
			URI url1 = Csv_Input_Output.class.getResource("/highscores.txt").toURI();
			URI url2 = Csv_Input_Output.class.getResource("/profiles.txt").toURI();

			Map<String, String> env1 = new HashMap<>();
			String[] array1 = url1.toString().split("!");
			FileSystem fs1 = FileSystems.newFileSystem(URI.create(array1[0]), env1);
			path_highscores = fs1.getPath(array1[1]);

			Map<String, String> env2 = new HashMap<>();
			String[] array2 = url2.toString().split("!");
			FileSystem fs2 = FileSystems.newFileSystem(URI.create(array2[0]), env2);
			path_profiles = fs2.getPath(array2[1]);

		} catch (IOException | URISyntaxException f) {
			System.out.println("fail in setup");

		}

	}

	public static void setup_old() {
		URL url1 = Csv_Input_Output.class.getResource("/highscores.txt");
		URL url2 = Csv_Input_Output.class.getResource("/profiles.txt");
		try {
			path_highscores = Paths.get(url1.toURI());
			path_profiles = Paths.get(url2.toURI());
		} catch (URISyntaxException e) {
			System.out.println("Failure in URI Method");
		}
	}

	static HashMap<String, Profile> processProfilesFile() {
		//setup();
		HashMap<String, Profile> profiles = new HashMap<String, Profile>();
		Stream<String> stream;
		try {
			stream = Files.lines(directory.resolve(Paths.get("profiles.txt"))); //directory.resolve(Paths.get("profiles.txt"))
		} catch (IOException e) {
			return null;
		}
		stream.forEach(s -> profiles.put(s, new Profile(s)));
		return profiles;
	}

	static List<Score> processHighscoresFile() {
		//setup();
		Stream<String> stream;
		try {
			stream = Files.lines(directory.resolve(Paths.get("highscores.txt"))); //directory.resolve(Paths.get("highscores.txt"))
		} catch (IOException e) {
			return null;
		}
		List<Score> scores = new ArrayList<Score>();
		stream.forEach(s -> {
			String[] split = s.split("\\|");
			String name = split[0];
			for (int i = 1; i < split.length; i++) {
				scores.add(new ScoreImpl(name, split[i]));
			}
		});
		return scores;
	}

	static void saveToFile(ProfileDatabase database) throws IOException {
		//setup();
		BufferedWriter writerProfiles_buf = Files.newBufferedWriter(directory.resolve(Paths.get("profiles.txt"))); //directory.resolve(Paths.get("profiles.txt"))
		BufferedWriter writerHighscores_buf = Files.newBufferedWriter(directory.resolve(Paths.get("highscores.txt"))); //directory.resolve(Paths.get("highscores.txt"))
		var iterator = database.getProfiles().entrySet().iterator();
		String profileFile = "";
		String highscoreFile = "";
		while (iterator.hasNext()) {
			String name = iterator.next().getValue().getName();
			profileFile += name + "\n";
			highscoreFile += name + "|";
			var list = database.getScores().stream().filter(x -> x.getName().equals(name)).collect(Collectors.toList());
			int size = list.size();
			for (int i = 0; i < size - 1; i++) {
				highscoreFile += list.get(i).toString() + "|";
			}
			if (list.size() > 0) {
				highscoreFile += list.get(size - 1).toString() + "\n";

			}
		}
		write_profile(profileFile);
		write_highscore(highscoreFile);
	}

	static void write_profile(String s) throws IOException {
		Files.writeString(directory.resolve(Paths.get("profiles.txt")), s); //directory.resolve(Paths.get("profiles.txt"))
	}

	static void write_highscore(String s) throws IOException {
		Files.writeString(directory.resolve(Paths.get("highscores.txt")), s); //directory.resolve(Paths.get("highscores.txt"))
	}
}
