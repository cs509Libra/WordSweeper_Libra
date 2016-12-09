package client.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestWord {
	/**@author Ruochen Shi; 
	 * This is responsible for testing "Word" Entity class*/
	@Test
	public void testGetContent() {
		/**the test for getting content function*/
		Word w = new Word();
		assertEquals("", w.getContent());
		w.setContent("HelloWorld");
		assertEquals("HelloWorld", w.getContent());
	}

	@Test
	public void testSetContent() {
		/**the test for setting content function*/
		Word w = new Word();
		assertFalse("HelloWorld" == w.getContent());
		w.setContent("HelloWorld");
		assertTrue("HelloWorld" == w.getContent());
	}

	@Test
	public void testSetScore() {
		/**the test for setting word's score function*/
		Word w = new Word();
		assertFalse(10 == w.getScore());
		w.setScore(20);
		assertTrue(20 == w.getScore());
	}

	@Test
	public void testGetScore() {
		/**the test for getting word's score function*/
		Word w = new Word();
		assertFalse(10 == w.getScore());
		w.setScore(20);
		assertTrue(20 == w.getScore());
	}

	@Test
	public void testSetlocalExpectedScore() {
		/**the test for setting local expected score function*/
		Word w = new Word();
		assertFalse(10 == w.getlocalExpectedScore());
		w.setlocalExpectedScore(20);
		assertTrue(20 == w.getlocalExpectedScore());
	}

	@Test
	public void testGetlocalExpectedScore() {
		/**the test for getting local expected score function*/
		Word w = new Word();
		assertFalse(10 == w.getlocalExpectedScore());
		w.setlocalExpectedScore(20);
		assertTrue(20 == w.getlocalExpectedScore());
	}

}
