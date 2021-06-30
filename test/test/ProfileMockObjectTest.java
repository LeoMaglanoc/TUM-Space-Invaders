package test;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.junit.Assert.assertEquals;

import java.util.LinkedList;

import org.easymock.EasyMockRunner;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Test;
import org.junit.runner.RunWith;

import profiles.ProfileDatabase;
import profiles.Score;

@RunWith(EasyMockRunner.class)
public class ProfileMockObjectTest {

	@TestSubject
	private ProfileDatabase database;

	@Mock
	private Score scoreMock;

	@Test
	public void testScoreSystem() {
		database = new ProfileDatabase();
		var list = new LinkedList<Score>();
		list.add(scoreMock);
		database.setScores(list);
		expect(scoreMock.getName()).andReturn("Leo");
		expect(scoreMock.getScore()).andReturn(1000);
		replay(scoreMock);
		String temp = database.getScore(0);
		assertEquals("Message isnt equal", temp, "Leo......................1000");
	}

}
