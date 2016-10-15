package client.model;

public class Cell{  
     int row;
     int colume;
     private String letter;
     boolean is_chosen;
     
     public Cell()      //row,colume,letter
     {
    	 this.is_chosen=false;
    //	 this.row=row;
    //	 this.colume=colume;
     //  this.letter=letter;
     }
     
     public void update(String letter,boolean is_chosen){
    	 this.letter=letter;
    	 this.is_chosen=is_chosen;
     }
     
	public String getLetter() {
		return letter;
	}
	public void setLetter(String letter) {
		this.letter = letter;
	}
}
