package client.model;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

public class TestBoard {

	@Test
	/*
	 * Test initialization of Board, including constructor, getCol(), getRow()
	 * and getBoardInfo()
	 */
	public void testBoardInitialization() {
		Board board = new Board();
		board.updateBoard(2, 3, "A,B,C,D,A,B,C,D,A,B,C,D,A,B,C,D");

		assertEquals(2, board.getCol());
		assertEquals(3, board.getRow());
		assertEquals("ABCDABCDABCDABCD", board.getBoardInfo());
	}

	@Test
	/*
	 * test the letter in every cell
	 */
	public void testBoardCells() {
		Board board = new Board();
		board.updateBoard(2, 3, "A,B,C,D,A,B,C,D,A,B,C,D,A,B,C,D");
		ArrayList<Cell> cells = board.getCells();
		for (Cell i : cells) {
			System.out.println(i.getLetter());
		}
	}

	@Test
	/*
	 * test choosing cells , get chosen cells and clear chosen cells
	 */
	public void testChosenCells() {
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