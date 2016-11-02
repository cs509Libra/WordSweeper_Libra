package client.model;

public class Word {
	String content;
    int score;

   public Word(String content){
  	 this.content=content;
  	 // score from server
   }
   public String getContent() {
	   return content;
   }

   public void setContent(String content) {
	   this.content = content;
   }
}
