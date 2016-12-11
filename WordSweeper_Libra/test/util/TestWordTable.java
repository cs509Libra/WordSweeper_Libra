package util;

import junit.framework.TestCase;

/**@author You Zhou, Qingquan Zhao, Han Bao, Ruochen Shi (Authors contribute equally)
 * This is responsible for testing "WordTable" Util class*/
public class TestWordTable extends TestCase {

	/**test the judgement function for word*/
	public void testWordGood() {
		assertTrue (WordTable.isWord("sample"));
	}
	
	public void testWordBad() {
		assertFalse (WordTable.isWord("non*word"));
	}
}
