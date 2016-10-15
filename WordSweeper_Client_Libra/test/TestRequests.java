import xml.Message;
import junit.framework.TestCase;


public class TestRequests extends TestCase {
	String requestHeader = null;
	String responseHeader = null;
	
	protected void setUp() {
		if (!Message.configure("wordsweeper.xsd")) {
			fail ("Unable to locate wordsweeper.xsd");
		}

		// create generic request header each time.
		requestHeader = Message.requestHeader();
		responseHeader = Message.responseHeader("someMessageID");
	}

	protected void tearDown() {

	}
	
	public void testCreateGameRequest() {
		Message m = new Message (
				requestHeader +
				  "<createGameRequest name='george'/>" +
				"</request>");
		System.out.println(m.toString());
	}
	

	public void testCreateGameRequestWithPassword() {
		Message m = new Message (
				requestHeader +
				  "<createGameRequest name='george' password='secret'/>" +
				"</request>");
	}
	
	/**
	 *  note 'Qu' gets encoded as a 'Q' and the u is assumed. In this game, 
	 *
	 * Board of each player is represented with the player. That is, for now not evern the total size
	 * of the board is encoded to the client.
	 * 
	 * Two players are on the board, and multiplier is at (4,3). Note that player2 is managing user.
	 */
	public void testBoardResponse() {
		Message m = new Message (
				responseHeader +
				  "<boardResponse gameId='hg12jhd' managingUser='player2' bonus='4,3'>" +
				      "<player name='player1' score='3897498374' position='4,6' board='A,F,E,R,K,S,O,E,R,O,I,E,R,P,O,R'/>" +
				      "<player name='player2' score='98127398' position='2,2' board='E,C,D,R,F,T,G,O,U,I,G,E,R,P,R,T'/>" +
				  "</boardResponse>" +
				"</response>");
		System.out.println(m.toString());
	}
	
	public void testJoinGameRequest() {
		Message m = new Message (
				requestHeader +
				  "<joinGameRequest name='george' gameId='specialGame'/>" +
				"</request>");
		System.out.println(m.toString());
	}
	
	public void testLockGameRequest() {
		Message m = new Message (
				requestHeader +
				  "<lockGameRequest gameId='specialGame'/>" +
				"</request>");
		System.out.println(m.toString());
	}
	
	public void testRepositionBoardRequestt() {
		Message m = new Message (
				requestHeader +
				  "<repositionBoardRequest name='player' rowChange='1' colChange='0' gameId='specialGame'/>" +
				"</request>");
		System.out.println(m.toString());
	}
	
	public void testFindWordRequest() {
		Message m = new Message (
				requestHeader +
				  "<findWordRequest gameId='specialGame' name='george' word='quartz'>" +
						"<cell position='2,2' letter='Qu'/>" +
						"<cell position='3,1' letter='a'/>" +
						"<cell position='2,1' letter='r'/>" +
						"<cell position='3,2' letter='t'/>" +
						"<cell position='2,3' letter='z'/>" +
				  "</findWordRequest>" +
				"</request>");
		System.out.println(m.toString());
	}
	
	/**
	 *  note 'Qu' gets encoded as a 'Q' and the u is assumed. In this game, 
	 *
	 * Board of each player is represented with the player. That is, for now not evern the total size
	 * of the board is encoded to the client.
	 * 
	 * Two players are on the board, and multiplier is at (4,3). Note that player2 is managing user.
	 */
	public void testBoardResponseWithBoardState() {
		Message m = new Message (
				responseHeader +
				  "<boardResponse gameId='hg12jhd' managingUser='player2' bonus='4,3' contents='A,B,C,G,B,C,J,D,H...H,D,J,H,J,D'>" +
				      "<player name='player1' score='392489038' position='4,6' board='A,F,E,R,K,S,O,E,R,O,I,E,R,P,O,R'/>" +
				      "<player name='player2' score='38974' position='2,2' board='E,C,D,R,F,T,G,O,U,I,G,E,R,P,R,T'/>" +
				  "</boardResponse>" +
				"</response>");
	}
	
	public void testLockGameResponse() {
		Message m = new Message (
				responseHeader +
				  "<lockGameResponse gameId='specialGame'/>" +
				"</response>");
		System.out.println(m.toString());
	}
	
	public void testFindWordResponse() {
		Message m = new Message (
				responseHeader +
				  "<findWordResponse gameId='specialGame' name='george' score='981232000'/>" +
				"</response>");
		System.out.println(m.toString());
	}
	
}
