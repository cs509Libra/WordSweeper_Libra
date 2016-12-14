package client.model;


/**
 * Model entity class, which plays a role as a manager of entity.  
 * 
 * @author You Zhou, Qingquan Zhao, Han Bao, Ruochen Shi (Authors contribute equally)
 *
 */
public class Model {

	private Game game;
	private Board board;
	private Player player;
	private PracticeGame practiceGame;
	public boolean existedGame;
	public boolean isWaitingResponse;

	/**Model constructor*/
	public Model() {
		this.game = new Game();
		this.practiceGame = new PracticeGame();
		this.board = new Board();
		this.player = new Player();
		this.existedGame = false;
		this.isWaitingResponse = false;
	}

	/**get the PracticeGame info
	 *@return PracticeGame
	 */
	public PracticeGame startPracticeGame() {
		return this.practiceGame;
	}

	/**update all the info about Model class.
	 *@param gameID
	 *@param managingUser
	 *@param playerName
	 *@param newStartingCol
	 *@param newStaringRow
	 *@param boardInfo
	 *@param score
	 *@param bonusCell
	 */
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

	/**reset the game and all the data of the game.
	 */
	public void resetGame() {
		this.getPlayer().resetPlayerScore();
		this.getBoard().empltyChosenCells();
	}
}
