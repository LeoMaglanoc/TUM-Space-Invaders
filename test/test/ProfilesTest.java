package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import profiles.ProfileSubsystemFacade;

public class ProfilesTest {

	ProfileSubsystemFacade data;

	@Before
	public void setup() {
		data = new ProfileSubsystemFacade();
	}

	@Test
	public void testAddName() {
		data.addName("Julia");
		assertTrue("Name wurde nicht hinzugefügt", data.getProfiles().containsKey("Julia"));
	}

	@Test
	public void testRemoveName() {
		data.removeName("Louis");
		assertFalse("Name existiert noch", data.getProfiles().containsKey("Louis"));
	}

	@Test
	public void testAddScore() {
		data.addScore("Julia", 1000);
		var list = data.getScores();
		var score = list.get(list.size() - 1);
		assertTrue("Score wasnt added", score.getName().equals("Julia") && score.getScore() == 1000);
	}

	@Test
	public void testResetScore() {
		data.resetScores();
		assertTrue("Liste ist nicht leer", data.getScores().isEmpty());
	}

}
