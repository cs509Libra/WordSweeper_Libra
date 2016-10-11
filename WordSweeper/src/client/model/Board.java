package client.model;

import java.util.ArrayList;

import javax.swing.JButton;

public class Board {
	Cell[][] cells;
	ArrayList<Cell> cell;
	
	public Board(){
		/*
		cells= new Cell[4][4];
		String[] string = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","Qu"};
		for(int i=0;i<4;i++)
			for(int j=0;j<4;j++)
			{
				cells[i][j]=new Cell();
				String str=string[(int)(Math.random()*27)];
				cells[i][j].setLetter(str);
			}*/
		
		cell=new ArrayList<Cell>();
		for(int i=0;i<16;i++)
			cell.add(new Cell());
		String[] string = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","Qu"};
		for(Cell temp:cell)
		{
			String str=string[(int)(Math.random()*27)];
			temp.setLetter(str);
			//System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"+temp.getLetter());
		}
			}

	public Cell[][] getCells() {
		return cells;
	}

	public void setCells(Cell[][] cells) {
		this.cells = cells;
	}

	public ArrayList<Cell> getCell() {
		return cell;
	}

	public void setCell(ArrayList<Cell> cell) {
		this.cell = cell;
	}
	}

