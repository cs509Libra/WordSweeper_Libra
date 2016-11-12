package util;

import junit.framework.TestCase;

public class TestWordTable extends TestCase {

	public void testWordGood() {
		assertTrue (WordTable.isWord("sample"));
	}
	
	public void testWordBad() {
		assertFalse (WordTable.isWord("non*word"));
	}
}
