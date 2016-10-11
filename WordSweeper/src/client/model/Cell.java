package client.model;

import javax.swing.JButton;

public class Cell extends JButton {   //¿ÉÒÔ¿¼ÂÇ
     int row;
     int colume;
     private String letter;
     boolean is_chosen;
     
     public Cell()       //int[][] location,String letter
     {
    	 this.is_chosen=false;
    //	 this.location=location;
     //  	 this.letter=letter;
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
