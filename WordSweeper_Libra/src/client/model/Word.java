package client.model;

public class Word {
	private String content;
    private int score;
    private int localExpectedScore;

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
