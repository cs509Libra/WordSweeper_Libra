package client.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import client.MockServerAccess;
import client.controller.responseController.BoardResponseController;
import client.model.Board;
import client.model.Game;
import client.model.Model;
import client.view.Application;
import xml.Message;

/**@author You Zhou, Qingquan Zhao, Han Bao, Ruochen Shi (Authors contribute equally) 
 * This is responsible for testing "Board Response" Controller*/
public class TestBoardResponseController {
	Model model = new Model();
	Application client = new Application(model);
	MockServerAccess mockServer = new MockServerAccess("localhost");

	
	/**this is the setting for the test*/
	@Before
	public void set() {
		// FIRST thing to do is register the protocol being used.
		if (!Message.configure("wordsweeper.xsd")) {
			fail("unable to configure protocol");
		}
		client.setVisible(true);
		client.setServerAccess(mockServer);
	}

	/** This is responsible for testing the process of "Board Response" Controller
	 * the situation is that the list is more than 1*/
	@Test
	public void TestBoardResponseProcess1(){
		String name="player1";
		int col=2, row=1;
		String bonus = "3,3";
		String gameId= "idid";
		String managingUser =name;
		String board = "A,B,A,B,A,B,B,B,C,B,C,D,B,A,B,R";
		String boardm= "ABABABBBCBCDBABR";
		String pos=col+ "," + row;
		int score =100;
		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><response id=\"someMessageID\" success=\"true\">"
				+ "<boardResponse bonus=\"%s\" gameId=\"%s\" managingUser=\"%s\">"
				+ "<player board=\"%s\" name=\"%s\" position=\"%s\" score=\"%s\"/>"
				+ "</boardResponse></response>";
		xml = String.format(xml, bonus,gameId,managingUser,board,name,pos,score);
		model.updateInfo("something different", "I don't know", "haha", 6, 7, "A,B,C,D,F,E,E,G,J,I,J,O,P,B,I,M", 55,
				"6,6");
		model.getPlayer().setName(name);
		
		Message m = new Message(xml);
		new BoardResponseController(client,model).process(m);
		
		Board b = model.getBoard();
		assertTrue(b.positions.contains(pos));
		assertEquals(b.getCol(), col);
		assertEquals(b.getRow(), row);
		assertEquals(b.getBoardInfo(), boardm);
		assertEquals(b.getBonusCell(), bonus);

		Game game = model.getGame();
		assertTrue(game.getPlayersInfoMap().containsKey(name) && game.getPlayersInfoMap().get(name) == score);
		assertTrue(game.getPlayersInfoMap().size() == 1);

		assertTrue(game.getPlayersPositionMap().containsKey(name)
				&& game.getPlayersPositionMap().get(name).equals(pos));
		assertTrue(game.getPlayersPositionMap().size() == 1);

		assertEquals(model.getGame().getGameID(), gameId);
		assertEquals(model.getGame().getManagingUser(), name);

		assertEquals(model.getPlayer().getName(), name);
		assertEquals(model.getPlayer().getScore(), score);
		assertTrue(model.getPlayer().isManager());
		
	}
	
	
	/**This is responsible for testing the process of "Board Response" Controller
	 * the situation is that the list is more than 1*/
	@Test
	public void testBoardResponseProcess2() {
		String name1 = "player1", name2 = "player2";
		int col1 = 4, col2 = 2, row1 = 1, row2 = 2;
		String bonus = "4,3";
		String gameId = "some id";
		String managingUser = name2;
		String board1 = "A,F,E,R,K,S,O,E,R,O,I,E,R,P,O,R";
		String board2 = "E,C,D,R,F,T,G,O,M,I,G,E,R,P,R,T";
		String board11 = "AFERKSOEROIERPOR";
		String pos1 = col1 + "," + row1, pos2 = col2 + "," + row2;
		int score1 = 5, score2 = 10;
		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><response id=\"someMessageID\" success=\"true\">"
				+ "<boardResponse bonus=\"%s\" gameId=\"%s\" managingUser=\"%s\">"
				+ "<player board=\"%s\" name=\"%s\" position=\"%s\" score=\"%s\"/>"
				+ "<player board=\"%s\" name=\"%s\" position=\"%s\" score=\"%s\"/>" + "</boardResponse></response>";
		xml = String.format(xml, bonus, gameId, managingUser, board1, name1, pos1, score1, board2, name2, pos2, score2);
		model.updateInfo("something different", "I don't know", "haha", 6, 7, "A,B,C,D,F,E,E,G,J,I,J,O,P,B,I,M", 55,
				"6,6");
		model.getPlayer().setName(name1);

		Message m = new Message(xml);
		new BoardResponseController(client, model).process(m);

		Board b = model.getBoard();
		assertTrue(b.positions.contains(pos1));
		assertTrue(b.positions.contains(pos2));
		assertEquals(b.getCol(), col1);
		assertEquals(b.getRow(), row1);
		assertEquals(b.getBoardInfo(), board11);
		assertEquals(b.getBonusCell(), bonus);

		Game game = model.getGame();
		assertTrue(game.getPlayersInfoMap().containsKey(name1) && game.getPlayersInfoMap().get(name1) == score1);
		assertTrue(game.getPlayersInfoMap().containsKey(name2) && game.getPlayersInfoMap().get(name2) == score2);
		assertTrue(game.getPlayersInfoMap().size() == 2);

		assertTrue(game.getPlayersPositionMap().containsKey(name1)
				&& game.getPlayersPositionMap().get(name1).equals(pos1));
		assertTrue(game.getPlayersPositionMap().containsKey(name2)
				&& game.getPlayersPositionMap().get(name2).equals(pos2));
		assertTrue(game.getPlayersPositionMap().size() == 2);

		assertEquals(model.getGame().getGameID(), gameId);
		assertEquals(model.getGame().getManagingUser(), name2);

		assertEquals(model.getPlayer().getName(), name1);
		assertEquals(model.getPlayer().getScore(), score1);
		assertFalse(model.getPlayer().isManager());
	}

}
//end of TestBoardResponseController