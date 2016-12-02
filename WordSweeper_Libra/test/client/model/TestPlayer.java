package client.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestPlayer {

	@Test
	public void testGetName() {
		Player p = new Player();
		assertFalse(p.getName().equals("James"));
		p.setName("Nick");
		assertTrue(p.getName().equals("Nick"));
	}

	@Test
	public void testSetName() {
		Player p = new Player();
		assertFalse(p.getName().equals("James"));
		p.setName("Nick");
		assertTrue(p.getName().equals("Nick"));
	}

	@Test
	public void testGetScore() {
		Player p = new Player();
		assertFalse(p.getScore() == 2000);
		p.setScore(30);
		assertEquals(p.getScore(), 30);
	}

	@Test
	public void testSetScore() {
		Player p = new Player();
		assertFalse(p.getScore() == 2000);
		p.setScore(30);
		assertEquals(p.getScore(), 30);
	}

	@Test
	public void testIsManager() {
		Player p = new Player();
		assertFalse(p.isManager());
		p.setAsManager();
		assertTrue(p.isManager());
	}

	@Test
	public void testSetAsManager() {
		Player p = new Player();
		assertFalse(p.isManager());
		p.setAsManager();
		assertTrue(p.isManager());
	}

	@Test
	public void testResetPlayerScore() {
		Player p = new Player();
		p.setScore(100);
		assertEquals(100, p.getScore());
		p.resetPlayerScore();
		assertFalse(100 == p.getScore());
		assertEquals(0, p.getScore());
	}

	@Test
	public void testGetWordscore() {
		Player p = new Player();
		assertFalse(p.getWordscore() == 2000);
		p.setWordscore(30);
		assertEquals(p.getWordscore(), 30);
	}

	@Test
	public void testSetWordscore() {
		Player p = new Player();
		assertFalse(p.getWordscore() == 2000);
		p.setWordscore(30);
		assertEquals(p.getWordscore(), 30);
	}

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
