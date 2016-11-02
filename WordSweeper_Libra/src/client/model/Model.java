package client.model;

public class Model {
	 Board board;
   	 Game game;
   	 Player player;
   	
   	public Model()
   	{
   	  board=new Board();
   	  game=new Game();
   // player=new Player();
   	}
   	
	public Board getBoard() {
		return board;
	}
	public void setBoard(Board board) {
		this.board = board;
	}
	public Game getGame() {
		return game;
	}
	public void setGame(Game game) {
		this.game = game;
	}
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
}
