package client.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestWord {
	
	@Test
	public void testGetContent() {
		Word w = new Word();
		assertEquals("", w.getContent());
		w.setContent("HelloWorld");
		assertEquals("HelloWorld", w.getContent());
	}

	@Test
	public void testSetContent() {
		Word w = new Word();
		assertFalse("HelloWorld" == w.getContent());
		w.setContent("HelloWorld");
		assertTrue("HelloWorld" == w.getContent());
	}

	@Test
	public void testSetScore() {
		Word w = new Word();
		assertFalse(10 == w.getScore());
		w.setScore(20);
		assertTrue(20 == w.getScore());
	}

	@Test
	public void testGetScore() {
		Word w = new Word();
		assertFalse(10 == w.getScore());
		w.setScore(20);
		assertTrue(20 == w.getScore());
	}

	@Test
	public void testSetlocalExpectedScore() {
		Word w = new Word();
		assertFalse(10 == w.getlocalExpectedScore());
		w.setlocalExpectedScore(20);
		assertTrue(20 == w.getlocalExpectedScore());
	}

	@Test
	public void testGetlocalExpectedScore() {
		Word w = new Word();
		assertFalse(10 == w.getlocalExpectedScore());
		w.setlocalExpectedScore(20);
		assertTrue(20 == w.getlocalExpectedScore());
	}

}
