package client.model;

import java.util.ArrayList;

public class Board {
	//Cell[][] cells;
	ArrayList<Cell> cells;
	
	public Board(){
		/*
		cells= new Cell[4][4];
		String[] string = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Qu","R","S","T","U","V","W","X","Y","Z"};
		for(int i=0;i<4;i++)
			for(int j=0;j<4;j++)
			{
				cells[i][j]=new Cell();
				String str=string[(int)(Math.random()*26)];
				cells[i][j].setLetter(str);
			}*/
		
		cells=new ArrayList<Cell>();
		for(int i=0;i<16;i++)
			cells.add(new Cell());
		String[] string = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Qu","R","S","T","U","V","W","X","Y","Z"};
		for(Cell temp:cells)
		{
			String str=string[(int)(Math.random()*26)];
			temp.setLetter(str);
			//System.out.println(temp.getLetter());
		}
			}

	public ArrayList<Cell> getCells() {
		return cells;
	}

	public void setCells(ArrayList<Cell> cells) {
		this.cells = cells;
	}

/*
	public Cell[][] getCells() {
		return cells;
	}

	public void setCells(Cell[][] cells) {
		this.cells = cells;
	}
*/
}
