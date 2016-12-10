package util;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestCalculateLocalScore {

	@Test
	public void testCalculateLetterScoreString() {
		Integer score1 = CalculateLocalScore.calculateLetterScore("H");
		assertEquals((int)score1, 2);
		Integer score2 = CalculateLocalScore.calculateLetterScore("QU");
		assertEquals((int)score2, 11);
		Integer score3 = CalculateLocalScore.calculateLetterScore("");
		assertEquals((int)score3, 0);
	}

	@Test
	public void testCalculateLetterScoreChar() {
		Integer score1 = CalculateLocalScore.calculateLetterScore('H');
		assertEquals((int)score1, 2);
		Integer score2 = CalculateLocalScore.calculateLetterScore('Q');
		assertEquals((int)score2, 8);
	}

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
