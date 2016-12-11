package util;

import static org.junit.Assert.*;

import org.junit.Test;

/**@author You Zhou, Qingquan Zhao, Han Bao, Ruochen Shi (Authors contribute equally)
 * This is responsible for testing "CalculateLocalScore" Util class*/
public class TestCalculateLocalScore {

	/**test the letter score calculate function for String*/
	@Test
	public void testCalculateLetterScoreString() {
		Integer score1 = CalculateLocalScore.calculateLetterScore("H");
		assertEquals((int)score1, 2);
		Integer score2 = CalculateLocalScore.calculateLetterScore("QU");
		assertEquals((int)score2, 11);
		Integer score3 = CalculateLocalScore.calculateLetterScore("");
		assertEquals((int)score3, 0);
	}

	/**test the letter score calculate function for Char*/
	@Test
	public void testCalculateLetterScoreChar() {
		Integer score1 = CalculateLocalScore.calculateLetterScore('H');
		assertEquals((int)score1, 2);
		Integer score2 = CalculateLocalScore.calculateLetterScore('Q');
		assertEquals((int)score2, 8);
	}

	/**test the word score calculate function*/
	@Test
	public void testCalculateWordScore() {
		String word = "APPLE";
		Integer score1 = CalculateLocalScore.calculateWordScore(word, word.length());
		assertEquals((int)score1, 4480);
		String word2 = "";
		Integer score2 = CalculateLocalScore.calculateWordScore(word, 0);
		assertEquals((int)score2, 0);
	}

}