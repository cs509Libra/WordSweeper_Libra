package client.model;

public class Model {

	private Game game;
	private Board board;
	private Player player;
	private PracticeGame practiceGame;
	public boolean existedGame;
	public boolean isWaitingResponse;

	public Model() {
		this.game = new Game();
		this.practiceGame = new PracticeGame();
		this.board = new Board();
		this.player = new Player();
		this.existedGame = false;
		this.isWaitingResponse = false;
	}

	public PracticeGame startPracticeGame() {
		return this.practiceGame;
	}

	public void updateInfo(String gameID, String managingUser, String playerName, int newStartingCol, int newStaringRow,
			String boardInfo, long score, String bonusCell) {
		this.game.setGameID(gameID);
		this.player.setName(playerName);
		if (score != this.player.getScore())
			this.player.setScore(score);
		this.game.setManagingUser(managingUser);
		if (managingUser.equals(this.player.getName())) {
			this.player.setAsManager();
		}
		this.board.updateBoard(newStartingCol, newStaringRow, boardInfo);
		this.board.setRequestColChange(0);
		this.board.setRequestRowChange(0);
		this.board.setBonusCell(bonusCell);
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

	public void resetGame() {
		this.getPlayer().resetPlayerScore();
		this.getBoard().empltyChosenCells();
	}
}
