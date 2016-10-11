package client.model;
//import java.util.*;

public class Player {
	int row;
    int colume;
    private String id;
    private String name;
    private int score;
    private boolean manager;
      
	public Player(boolean manager){
		this.score=0;
		this.manager=manager;
	}
	
    public Player(String name,boolean manager){
		this.name=name;
		this.score=0;
		this.manager=manager;
	}
    
    public void MoveLocation(String direction){
         
    }
    
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public boolean isManager() {
		return manager;
	}
	public void setManager(boolean manager) {
		this.manager = manager;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
