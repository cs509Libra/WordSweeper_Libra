package server.model;

/** HACK: Replace with actual functionality! */

public class ServerModel {
	int numPlayers = 0;
	
	public void joinGame() {
		numPlayers++;
	}
	
	public int getNumPlayers() {
		return numPlayers;
	}
}
