package client.model;

import static org.junit.Assert.*;
import org.junit.Test;

/**@author You Zhou, Qingquan Zhao, Han Bao, Ruochen Shi (Authors contribute equally)
 * This is responsible for testing "PracticeGame" Entity class*/
public class TestPracticeGame {
	/**the test for generating a new practice game function*/
	@Test
	public void testGenerateRandomCellInfo() {
		PracticeGame strGenerator = new PracticeGame();
		String str = strGenerator.generateRandomCellInfo();
		assertEquals(16, str.length());
	}
}
//end of the test of PracticeGame entity