package client.model;

/**
 * Word entity class, which contains all the info and functions about the word.  
 * 
 * @author You Zhou, Qingquan Zhao, Han Bao, Ruochen Shi (Authors contribute equally)
 *
 */
public class Word {
	private String content;
    private int score;
    private int localExpectedScore;

    /**Word constructor*/
	public Word(){
		this.score = 0;
		this.content = "";
		this.localExpectedScore = 0;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public int getScore() {
		return score;
	}
	
	public void setlocalExpectedScore(Integer localExpectedScore){
		this.localExpectedScore = localExpectedScore;
	}
	
	public Integer getlocalExpectedScore(){
		return localExpectedScore;
	}

}