package client.model;

import static org.junit.Assert.*;

import org.junit.Test;

/**@author You Zhou, Qingquan Zhao, Han Bao, Ruochen Shi (Authors contribute equally)
 * This is responsible for testing "Word" Entity class*/
public class TestWord {
	/**the test for getting content function*/
	@Test
	public void testGetContent() {
		Word w = new Word();
		assertEquals("", w.getContent());
		w.setContent("HelloWorld");
		assertEquals("HelloWorld", w.getContent());
	}

	/**the test for setting content function*/
	@Test
	public void testSetContent() {
		Word w = new Word();
		assertFalse("HelloWorld" == w.getContent());
		w.setContent("HelloWorld");
		assertTrue("HelloWorld" == w.getContent());
	}

	/**the test for setting word's score function*/
	@Test
	public void testSetScore() {
		Word w = new Word();
		assertFalse(10 == w.getScore());
		w.setScore(20);
		assertTrue(20 == w.getScore());
	}

	/**the test for getting word's score function*/
	@Test
	public void testGetScore() {
		Word w = new Word();
		assertFalse(10 == w.getScore());
		w.setScore(20);
		assertTrue(20 == w.getScore());
	}

	/**the test for setting local expected score function*/
	@Test
	public void testSetlocalExpectedScore() {
		Word w = new Word();
		assertFalse(10 == w.getlocalExpectedScore());
		w.setlocalExpectedScore(20);
		assertTrue(20 == w.getlocalExpectedScore());
	}

	/**the test for getting local expected score function*/
	@Test
	public void testGetlocalExpectedScore() {
		Word w = new Word();
		assertFalse(10 == w.getlocalExpectedScore());
		w.setlocalExpectedScore(20);
		assertTrue(20 == w.getlocalExpectedScore());
	}

}
//end of the test of Word entity