package client.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestPracticeGame {
	/**@author Ruochen Shi; 
	 * This is responsible for testing "PracticeGame" Entity class*/
	@Test
	public void testGenerateRandomCellInfo() {
		/**the test for generating a new practice game function*/
		PracticeGame strGenerator = new PracticeGame();
		String str = strGenerator.generateRandomCellInfo();
		assertEquals(16, str.length());
	}
}
