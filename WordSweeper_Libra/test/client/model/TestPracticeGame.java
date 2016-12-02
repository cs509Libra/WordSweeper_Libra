package client.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestPracticeGame {

	@Test
	public void testGenerateRandomCellInfo() {
		PracticeGame strGenerator = new PracticeGame();
		String str = strGenerator.generateRandomCellInfo();
		assertEquals(16, str.length());
	}
}
