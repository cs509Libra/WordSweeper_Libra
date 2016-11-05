package client.model;

public class Model {
	
	private Game game;
	private Board board;
	private Player player;
	private PracticeGame practiceGame;
   	
	public Model(){
	  this.game = new Game();
	  this.practiceGame = new PracticeGame();
	  this.board = new Board();
	  this.player = new Player();
	}

	public PracticeGame startPracticeGame() {
		return this.practiceGame;
	}
	
	public void updateInfo(String gameID, String managingUser, String playerName, int newStartingCol, int newStaringRow, 
							String boardInfo, long score)
	{
		this.game.setGameID(gameID);
		this.player.setName(playerName);
		this.player.setScore(score);
		this.game.setManagingUser(managingUser);
		if(managingUser.equals(this.player.getName())){
			this.player.setAsManager();
		}
		this.board.updateBoard(newStartingCol, newStaringRow, boardInfo);
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
	
	
}
