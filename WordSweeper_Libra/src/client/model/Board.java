package client.model;

import java.util.ArrayList;

import util.CalculateLocalScore;

public class Board {

		private ArrayList<Cell> cells;
		private ArrayList<Cell> chosenCells;
		private int globalStartingCol;
		private int globalStartingRow;
		private Word word;
		private Integer chosenLettersScore;
		
		public Board(){
			cells = new ArrayList<Cell>();
			chosenCells = new ArrayList<Cell>();
			globalStartingCol = 0;
			globalStartingRow = 0;
			word = new Word();
			chosenLettersScore = 0;
			for(int i = 0; i < 4 ; i++){
				for(int j = 0; j < 4; j++){
					Cell currentCell = new Cell();
					currentCell.setLocalRow(i);
					currentCell.setLocalCol(j);
					currentCell.setGlobalRow(i + this.globalStartingRow);
					currentCell.setGlobalCol(j + this.globalStartingCol);
					this.cells.add(currentCell);
				}
			}
		}

		public void updateBoard(int newStartingCol, int newStaringRow, String NewBoardInfo){
			this.globalStartingCol = newStartingCol;
			this.globalStartingRow = newStaringRow;
			for(Cell cell : cells){
				cell.setGlobalCol(this.globalStartingCol + cell.getLocalCol());
				cell.setGlobalRow(this.globalStartingRow + cell.getLocalRow());
			}
			updateBoardByAllLetters(NewBoardInfo);
		}

		private void updateBoardByAllLetters(String cellsLetters){
			char[] cellInfoList = cellsLetters.toCharArray();
			for(int i = 0; i < 16; i++){
				Character toBeAdd = (Character)(cellInfoList[i]);
				this.cells.get(i).setLetter(toBeAdd.toString());
			}
		}
	
		public String getBoardInfo(){
			String allCellLetters = "";
			for(Cell cell : cells){
				allCellLetters += cell.getLetter();
			}
			return allCellLetters;
		}

		public String getChosenCellsLetters(){
			String chosenLetters = "";
			for(Cell cell : cells){
				if(cell.isChosen()){
					chosenLetters += cell.getLetter();
				}
			}
			return chosenLetters;
		}
		
		public ArrayList<Cell> getCells(){
			return cells;
		}
		
		public Cell getCellByIndex(int index){
			return cells.get(index);
		}
		
		public Word getWord(){
			this.word = new Word();
			this.word.setContent(getChosenCellsLetters());
			this.word.setlocalExpectedScore(CalculateLocalScore.calculateWordScore(getChosenCellsLetters(), this.chosenCells.size()));
			return word;
		}

		public void addToChosenCellsByCellIndex(int index){		
			this.chosenCells.add(getCellByIndex(index));
		}

		public Integer getChosenLettersScore(){
			for(Cell cell : this.chosenCells){
				this.chosenLettersScore += CalculateLocalScore.calculateLetterScore(cell.getLetter());
			}
			return this.chosenLettersScore;
		}
		
		
		
}
