package client.model;

import java.util.ArrayList;

import util.CalculateLocalScore;
import xml.Message;

/**
 * Board entity class, which contains all the info and functions about the board.  
 * 
 * @author You Zhou, Qingquan Zhao, Han Bao, Ruochen Shi (Authors contribute equally)
 *
 */
public class Board {
	private ArrayList<Cell> cells;
	private ArrayList<Cell> chosenCells;
	public ArrayList<String> positions;
	private int globalStartingCol;
	private int globalStartingRow;
	private Integer requestColChange;
	private Integer requestRowChange;
	private Word word;
	private Integer chosenLettersScore;
	private String bonusCell;

	/**Board constructor*/
	public Board() {
		cells = new ArrayList<Cell>();
		chosenCells = new ArrayList<Cell>();
		positions = new ArrayList<String>();
		globalStartingCol = 0;
		globalStartingRow = 0;
		requestColChange = 0;
		requestRowChange = 0;
		word = new Word();
		chosenLettersScore = 0;
		bonusCell = "";
		for (int i = 1; i < 5; i++) {
			for (int j = 1; j < 5; j++) {
				Cell currentCell = new Cell();
				currentCell.setLocalRow(i);
				currentCell.setLocalCol(j);
				currentCell.setGlobalRow(i + this.globalStartingRow);
				currentCell.setGlobalCol(j + this.globalStartingCol);
				this.cells.add(currentCell);
			}
		}
	}

	/**get the globalStartingRow attribute 
	 * @return int
	 */
	public int getRow() {
		return this.globalStartingRow;
	}
	
	/** get the globalStartingCol attribute 
	 * @return int
	 */
	public int getCol() {
		return this.globalStartingCol;
	}
	
	/** update the board information
  	 * @param newStartingCol
  	 * @param newStaringRow
	 * @param NewBoardInfo
	 */
	public void updateBoard(int newStartingCol, int newStaringRow, String NewBoardInfo) {
		this.globalStartingCol = newStartingCol;
		this.globalStartingRow = newStaringRow;
		for (Cell cell : cells) {
			cell.setGlobalCol(this.globalStartingCol + cell.getLocalCol() - 1);
			cell.setGlobalRow(this.globalStartingRow + cell.getLocalRow() - 1);
		}
		updateBoardByAllLetters(NewBoardInfo);
	}

	/** update the board information, especially on letters.
  	 * @param cellsLetters
	 */
	private void updateBoardByAllLetters(String cellsLetters) {
		String[] cellInforList = cellsLetters.split(",");
		for (int i = 0; i < 16; i++) {
			this.cells.get(i).setLetter("" + cellInforList[i]);
		}
	}

	/** get the board info 
	 * @return String
	 */
	public String getBoardInfo() {
		String allCellLetters = "";
		for (Cell cell : cells) {
			allCellLetters += cell.getLetter();
		}
		return allCellLetters;
	}

	/**
	 * the getter for cells attribute.
	 * 
	 * @return ArrayList<Cell>
	 */
	public ArrayList<Cell> getCells() {
		return cells;
	}

	/**
	 * get the XML format of String which consists of chosen cells.
	 * 
	 * @return String
	 */
	public String getChosenCellsXMLString() {
		String chosenCellsString = "";
		for (Cell cell : chosenCells) {
			chosenCellsString += String.format("<cell position='%s,%s' letter='%s'/>",
					String.valueOf(cell.getGlobalCol()), String.valueOf(cell.getGlobalRow()), cell.getLetter());
		}
		return chosenCellsString;
	}

	/**
	 * the getter for word attribute.
	 * 
	 * @return Word
	 */
	public Word getWord() {
		return this.word;
	}

	/**
	 * add the new chosen cell to chosen cell list.
	 * @param index 
	 */
	public void addToChosenCellsByCellIndex(int index) {
		cells.get(index).setAsChosen();
		chosenCells.add(cells.get(index));
		word.setContent(getChosenCellsLetters());
		word.setlocalExpectedScore(
				CalculateLocalScore.calculateWordScore(getChosenCellsLetters(), this.chosenCells.size()));
	}

	/**
	 * get the cell letters that has been chosen.
	 * @return String
	 */
	public String getChosenCellsLetters() {
		String chosenLetters = "";
		for (Cell cell : chosenCells)
			chosenLetters += cell.getLetter();
		return chosenLetters;
	}

	/**
	 * get the score, according to cell letters that has been chosen.
	 * @return Integer
	 */
	public Integer getChosenLettersScore() {
		for (Cell cell : this.chosenCells) {
			this.chosenLettersScore += CalculateLocalScore.calculateLetterScore(cell.getLetter());
		}
		return this.chosenLettersScore;
	}

	/**
	 * clear all the cells that has been chosen.
	 */
	public void clearChosenCells() {
		for (Cell cell : this.chosenCells) {
			cell.setAsUnchosen();
		}
		chosenCells.removeAll(chosenCells);
		word.setContent(getChosenCellsLetters());
	}

	/**
	 * the getter for RequestColChange attribute.
	 * 
	 * @return Integer
	 */
	public Integer getRequestColChange() {
		return requestColChange;
	}

	/**
	 * the setter for RequestColChange attribute.
	 * @param requestColChange
	 */
	public void setRequestColChange(Integer requestColChange) {
		this.requestColChange = requestColChange;
	}

	/**
	 * the getter for RequestRowChange attribute.
	 * 
	 * @return Integer
	 */
	public Integer getRequestRowChange() {
		return requestRowChange;
	}

	/**
	 * the setter for RequestRowChange attribute.
	 * @param requestRowChange
	 */
	public void setRequestRowChange(Integer requestRowChange) {
		this.requestRowChange = requestRowChange;
	}

	/**
	 * remove all the chosen cells.
	 */
	public void empltyChosenCells() {
		this.chosenCells.removeAll(chosenCells);
	}

	/**
	 * the getter for BonusCell attribute.
	 * 
	 * @return String
	 */
	public String getBonusCell() {
		return this.bonusCell;
	}

	/**
	 * the setter for BonusCell attribute.
	 * @param bonusCell
	 */
	public void setBonusCell(String bonusCell) {
		this.bonusCell = bonusCell;
	}
}
