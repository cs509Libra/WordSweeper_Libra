package client.model;


/**
 * Cell entity class, which contains all the info and functions about the Cell.  
 * 
 * @author You Zhou, Qingquan Zhao, Han Bao, Ruochen Shi (Authors contribute equally)
 *
 */
public class Cell {
	private int localRow;
    private int localCol;
    private int globalRow;
    private int globalCol;
    private String letter;
    
    private boolean isChosen;
    private boolean isBonus;
    
    /**Cell constructor*/
    public Cell(){
    	localRow = 0;       
    	localCol = 0;
    	globalRow = 0;
    	globalCol = 0;
    	letter = "";
    	isBonus = false;
    	isChosen = false;
    }
    
	public boolean isBonus() {
		return isBonus;
	}

	public void setBonus(boolean isBonus) {
		this.isBonus = isBonus;
	}

	public int getLocalRow() {
		return localRow;
	}

	public void setLocalRow(int localRow) {
		this.localRow = localRow;
	}

	public int getLocalCol() {
		return localCol;
	}

	public void setLocalCol(int localCol) {
		this.localCol = localCol;
	}

	public int getGlobalRow() {
		return globalRow;
	}

	public void setGlobalRow(int globalRow) {
		this.globalRow = globalRow;
	}

	public int getGlobalCol() {
		return globalCol;
	}

	public void setGlobalCol(int globalCol) {
		this.globalCol = globalCol;
	}

	public String getLetter() {
		return letter;
	}
	
	public void setLetter(String letter) {
		this.letter = letter;
	}
	
	public void setAsChosen(){
		this.isChosen = true;
	}
	
	public boolean isChosen(){
		return this.isChosen;
	}
	
	public void setAsUnchosen(){
		this.isChosen = false;
	}
}
