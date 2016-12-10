package client.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestPracticeGame {
	/**@author You Zhou, Qingquan Zhao, Han Bao, Ruochen Shi (Authors contribute equally)
	 * This is responsible for testing "PracticeGame" Entity class*/
	@Test
	public void testGenerateRandomCellInfo() {
		/**the test for generating a new practice game function*/
		PracticeGame strGenerator = new PracticeGame();
		String str = strGenerator.generateRandomCellInfo();
		assertEquals(16, str.length());
	}
}
//end of the test of PracticeGame entity