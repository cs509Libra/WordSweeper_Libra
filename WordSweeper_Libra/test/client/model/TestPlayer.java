package client.model;

import static org.junit.Assert.*;

import org.junit.Test;

/**@author You Zhou, Qingquan Zhao, Han Bao, Ruochen Shi (Authors contribute equally)
 * This is responsible for testing "Player" Entity class*/
public class TestPlayer {
	/**the test for getting player's name function*/
	@Test
	public void testGetName() {
		Player p = new Player();
		assertFalse(p.getName().equals("James"));
		p.setName("Nick");
		assertTrue(p.getName().equals("Nick"));
	}

	/**the test for setting player's name function*/
	@Test
	public void testSetName() {
		Player p = new Player();
		assertFalse(p.getName().equals("James"));
		p.setName("Nick");
		assertTrue(p.getName().equals("Nick"));
	}

	/**the test for getting player's score function*/
	@Test
	public void testGetScore() {
		Player p = new Player();
		assertFalse(p.getScore() == 2000);
		p.setScore(30);
		assertEquals(p.getScore(), 30);
	}

	/**the test for setting player's score function*/
	@Test
	public void testSetScore() {
		Player p = new Player();
		assertFalse(p.getScore() == 2000);
		p.setScore(30);
		assertEquals(p.getScore(), 30);
	}

	/**the test for checking if the player is manager function*/
	@Test
	public void testIsManager() {
		Player p = new Player();
		assertFalse(p.isManager());
		p.setAsManager();
		assertTrue(p.isManager());
	}

	/**the test for setting player as manager function*/
	@Test
	public void testSetAsManager() {
		Player p = new Player();
		assertFalse(p.isManager());
		p.setAsManager();
		assertTrue(p.isManager());
	}

	/**the test for restting player's score function*/
	@Test
	public void testResetPlayerScore() {
		Player p = new Player();
		p.setScore(100);
		assertEquals(100, p.getScore());
		p.resetPlayerScore();
		assertFalse(100 == p.getScore());
		assertEquals(0, p.getScore());
	}

	/**the test for getting word's score function*/
	@Test
	public void testGetWordscore() {
		Player p = new Player();
		assertFalse(p.getWordscore() == 2000);
		p.setWordscore(30);
		assertEquals(p.getWordscore(), 30);
	}

	/**the test for setting word's score function*/
	@Test
	public void testSetWordscore() {
		Player p = new Player();
		assertFalse(p.getWordscore() == 2000);
		p.setWordscore(30);
		assertEquals(p.getWordscore(), 30);
	}

	/**the test for setting player as a manager function*/
	@Test
	public void testSetManager() {
		Player p = new Player();
		assertFalse(p.isManager());
		p.setManager(true);
		assertTrue(p.isManager());
		p.setManager(false);
		assertFalse(p.isManager());
	}
}
//end of the test of Player entity