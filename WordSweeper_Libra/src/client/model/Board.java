package client.model;

import java.util.ArrayList;

import util.CalculateLocalScore;

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

	public int getRow() {
		return this.globalStartingRow;
	}

	public int getCol() {
		return this.globalStartingCol;
	}

	public void updateBoard(int newStartingCol, int newStaringRow, String NewBoardInfo) {
		this.globalStartingCol = newStartingCol;
		this.globalStartingRow = newStaringRow;
		for (Cell cell : cells) {
			cell.setGlobalCol(this.globalStartingCol + cell.getLocalCol() - 1);
			cell.setGlobalRow(this.globalStartingRow + cell.getLocalRow() - 1);
		}
		updateBoardByAllLetters(NewBoardInfo);
	}

	private void updateBoardByAllLetters(String cellsLetters) {
		String[] cellInforList = cellsLetters.split(",");
		for (int i = 0; i < 16; i++) {
			if (cellInforList[i] == "Qu")
				cellInforList[i] = "Q";
			this.cells.get(i).setLetter(cellInforList[i]);
		}
	}

	public String getBoardInfo() {
		String allCellLetters = "";
		for (Cell cell : cells) {
			allCellLetters += cell.getLetter();
		}
		return allCellLetters;
	}

	/**
	 * Provide 16 letters String to view
	 * 
	 * @return
	 */

	public ArrayList<Cell> getCells() {
		return cells;
	}

	public String getChosenCellsXMLString() {
		String chosenCellsString = "";
		for (Cell cell : chosenCells) {
			chosenCellsString += String.format("<cell position='%s,%s' letter='%s'/>",
					String.valueOf(cell.getGlobalCol()), String.valueOf(cell.getGlobalRow()), cell.getLetter());
		}
		return chosenCellsString;
	}

	public Word getWord() {
		return this.word;
	}

	public void addToChosenCellsByCellIndex(int index) {
		cells.get(index).setAsChosen();
		chosenCells.add(cells.get(index));
		word.setContent(getChosenCellsLetters());
		word.setlocalExpectedScore(
				CalculateLocalScore.calculateWordScore(getChosenCellsLetters(), this.chosenCells.size()));
		System.out.println("word: " + word.getContent() + chosenCells.size());

	}

	public String getChosenCellsLetters() {
		String chosenLetters = "";
		for (Cell cell : chosenCells)
			chosenLetters += cell.getLetter();
		return chosenLetters;
	}

	public Integer getChosenLettersScore() {
		for (Cell cell : this.chosenCells) {
			this.chosenLettersScore += CalculateLocalScore.calculateLetterScore(cell.getLetter());
		}
		return this.chosenLettersScore;
	}

	public void clearChosenCells() {
		for (Cell cell : this.chosenCells) {
			cell.setAsUnchosen();
		}
		chosenCells.removeAll(chosenCells);
	}

	public Integer getRequestColChange() {
		return requestColChange;
	}

	public void setRequestColChange(Integer requestColChange) {
		this.requestColChange = requestColChange;
	}

	public Integer getRequestRowChange() {
		return requestRowChange;
	}

	public void setRequestRowChange(Integer requestRowChange) {
		this.requestRowChange = requestRowChange;
	}

	public void empltyChosenCells() {
		this.chosenCells.removeAll(chosenCells);
	}

	public int getGlobalStartingCol() {
		return globalStartingCol;
	}

	public int getGlobalStartingRow() {
		return globalStartingRow;
	}

	public String getBonusCell() {
		return this.bonusCell;
	}

	public void setBonusCell(String bonusCell) {
		this.bonusCell = bonusCell;
	}
}
