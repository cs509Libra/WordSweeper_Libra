package client.model;

import java.util.ArrayList;

public class Board {
		ArrayList<Cell> cells;
		
		public Board(){
			cells = new ArrayList<Cell>();
			for(int i = 0; i < 16 ; i++)
				cells.add(new Cell());
		}

		public ArrayList<Cell> getCells() {
			updateBoard();
			return cells;
		}

		public void setCells(ArrayList<Cell> cells) {
			this.cells = cells;
		}
		
		public void updateBoard(){
			fillCellsWithCellInfo(generateRandomCellInfo());
		}
		
		public String generateRandomCellInfo(){
			String[] LetterSources = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Qu","R","S","T","U","V","W","X","Y","Z"};
			String randomCellInfo = "";
			for(int i=0; i<16; i++){
				randomCellInfo += LetterSources[(int)(Math.random()*26)];
			}
			return randomCellInfo;
		}
		
		public void fillCellsWithCellInfo(String cellInfo){
			char[] cellInfoList = cellInfo.toCharArray();
			for(int i = 0; i < 16; i++){
				Character toBeAdd = (Character)(cellInfoList[i]);
				this.cells.get(i).setLetter(toBeAdd.toString());
			}
		}
		
}
