package client.model;

public class Game {
	
	private String gameID;
	private String managingUser;
	private boolean isLocked;
	
	public Game() {
		gameID = "";
		managingUser = null;
		isLocked = false;
	}
	
	
	public String getGameID() {
		return gameID;
	}

	public void setGameID(String gameID) {
		this.gameID = gameID;
	}

	public String getManagingUser() {
		return managingUser;
	}

	public void setManagingUser(String managingUser) {
		this.managingUser = managingUser;
	}

	public boolean isLocked() {
		return isLocked;
	}

	public void setLocked(boolean isLocked) {
		this.isLocked = isLocked;
	}

}
