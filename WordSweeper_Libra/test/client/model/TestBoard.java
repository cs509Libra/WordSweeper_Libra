package client.model;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

/**@author You Zhou, Qingquan Zhao, Han Bao, Ruochen Shi (Authors contribute equally)
 * This is responsible for testing "Board" Entity class*/
public class TestBoard {
	/**
	 * Test initialization of Board, including constructor, getCol(), getRow()
	 * and getBoardInfo()
	 */
	@Test
	public void testBoardInitialization() {
		/**the test for initializing a board*/
		Board board = new Board();
		board.updateBoard(2, 3, "A,B,C,D,A,B,C,D,A,B,C,D,A,B,C,D");

		assertEquals(2, board.getCol());
		assertEquals(3, board.getRow());
		assertEquals("ABCDABCDABCDABCD", board.getBoardInfo());
	}

	/**
	 * test the letter in every cell
	 */
	@Test
	public void testBoardCells() {
		/**the test for each cell in the board*/
		Board board = new Board();
		board.updateBoard(2, 3, "A,B,C,D,A,B,C,D,A,B,C,D,A,B,C,D");
		ArrayList<Cell> cells = board.getCells();
		for (Cell i : cells) {
			System.out.println(i.getLetter());
		}
	}

	/**
	 * test choosing cells , get chosen cells and clear chosen cells
	 */
	@Test
	public void testChosenCells() {
		/**the test for adding, clearing and checking chosen cells*/
		Board board = new Board();
		board.updateBoard(2, 3, "A,B,C,D,A,B,C,D,A,B,C,D,A,B,C,D");
		board.addToChosenCellsByCellIndex(0);
		board.addToChosenCellsByCellIndex(5);
		board.addToChosenCellsByCellIndex(10);
		board.addToChosenCellsByCellIndex(15);

		// index at 0, 5, 10, 15 should be A, B, C, D
		assertEquals("ABCD", board.getChosenCellsLetters());
		assertEquals("ABCD", board.getWord().getContent());

		// clear all chosen cells
		board.clearChosenCells();

		// should be empty String ""
		assertEquals("", board.getChosenCellsLetters());
		assertEquals("", board.getWord().getContent());

	}
}
//end of the test of Board entity