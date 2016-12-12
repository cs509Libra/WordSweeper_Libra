package client.model;

/**
 * Player entity class, which contains all the info and functions about the player.  
 * 
 * @author You Zhou, Qingquan Zhao, Han Bao, Ruochen Shi (Authors contribute equally)
 *
 */
public class Player {
    private String name;
    private long score;
    private long wordscore;
    private boolean isManager;
    
    /**Player constructor*/
	public Player(){
		this.name = "";
		this.score = 0;
		this.wordscore=0;
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
	
	/**set the player as manager.
	 */
	public void setAsManager(){
		this.isManager = true;
	}
	
	/**set the player as normal player.
	 */
	public void resetManager(){
		this.isManager = false;
	}
	
	/**set the player score as 0.
	 */
	public void resetPlayerScore(){
		this.score = 0;
	}

	public long getWordscore() {
		return wordscore;
	}

	public void setWordscore(long wordscore) {
		this.wordscore = wordscore;
	}

	public void setManager(boolean isManager) {
		this.isManager = isManager;
	}

}