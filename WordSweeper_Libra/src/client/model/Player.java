package client.model;

public class Player {
    private String name;
    private long score;
    private boolean isManager;
    
	public Player(){
		this.name = "";
		this.score = 0;
		this.isManager = false;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public long getScore() {
		return score;
	}
	public void setScore(long score) {
		this.score = score;
	}
	
	public boolean isManager() {
		return isManager;
	}
	
	public void setAsManager(){
		this.isManager = true;
	}
	
	public void resetPlayerScore(){
		this.score = 0;
	}

}
