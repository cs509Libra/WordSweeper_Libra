package client.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestPlayer {
	/**@author You Zhou, Qingquan Zhao, Han Bao, Ruochen Shi (Authors contribute equally)
	 * This is responsible for testing "Player" Entity class*/
	@Test
	public void testGetName() {
		/**the test for getting player's name function*/
		Player p = new Player();
		assertFalse(p.getName().equals("James"));
		p.setName("Nick");
		assertTrue(p.getName().equals("Nick"));
	}

	@Test
	public void testSetName() {
		/**the test for setting player's name function*/
		Player p = new Player();
		assertFalse(p.getName().equals("James"));
		p.setName("Nick");
		assertTrue(p.getName().equals("Nick"));
	}

	@Test
	public void testGetScore() {
		/**the test for getting player's score function*/
		Player p = new Player();
		assertFalse(p.getScore() == 2000);
		p.setScore(30);
		assertEquals(p.getScore(), 30);
	}

	@Test
	public void testSetScore() {
		/**the test for setting player's score function*/
		Player p = new Player();
		assertFalse(p.getScore() == 2000);
		p.setScore(30);
		assertEquals(p.getScore(), 30);
	}

	@Test
	public void testIsManager() {
		/**the test for checking if the player is manager function*/
		Player p = new Player();
		assertFalse(p.isManager());
		p.setAsManager();
		assertTrue(p.isManager());
	}

	@Test
	public void testSetAsManager() {
		/**the test for setting player as manager function*/
		Player p = new Player();
		assertFalse(p.isManager());
		p.setAsManager();
		assertTrue(p.isManager());
	}

	@Test
	public void testResetPlayerScore() {
		/**the test for restting player's score function*/
		Player p = new Player();
		p.setScore(100);
		assertEquals(100, p.getScore());
		p.resetPlayerScore();
		assertFalse(100 == p.getScore());
		assertEquals(0, p.getScore());
	}

	@Test
	public void testGetWordscore() {
		/**the test for getting word's score function*/
		Player p = new Player();
		assertFalse(p.getWordscore() == 2000);
		p.setWordscore(30);
		assertEquals(p.getWordscore(), 30);
	}

	@Test
	public void testSetWordscore() {
		/**the test for setting word's score function*/
		Player p = new Player();
		assertFalse(p.getWordscore() == 2000);
		p.setWordscore(30);
		assertEquals(p.getWordscore(), 30);
	}

	@Test
	public void testSetManager() {
		/**the test for setting player as a manager function*/
		Player p = new Player();
		assertFalse(p.isManager());
		p.setManager(true);
		assertTrue(p.isManager());
		p.setManager(false);
		assertFalse(p.isManager());
	}

}
//end of the test of Player entity