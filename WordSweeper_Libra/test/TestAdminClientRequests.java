import xml.Message;
import junit.framework.TestCase;


public class TestAdminClientRequests extends TestCase {
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
	
	public void testGamesRequest() {
		Message m = new Message (
				requestHeader +
				  "<listGamesRequest/>" +
				"</request>");
		System.out.println(m.toString());
	}
	
	public void testShowGameStateRequest() {
		Message m = new Message (
				requestHeader +
				  "<showGameStateRequest gameId='skldfhk'/>" +
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
	public void testListGamesResponse() {
		Message m = new Message (
				responseHeader +
				  "<listGamesResponse>" +
				      "<gameBrief gameId='jsahdjk' players='17'/>" +
				      "<gameBrief gameId='dfgdg' players='5'/>" +
				      "<gameBrief gameId='fhfghs' players='3'/>" +
				      "<gameBrief gameId='adsafdjj' players='12'/>" +
				  "</listGamesResponse>" +
				"</response>");
		System.out.println(m.toString());
	}
	
	
}
