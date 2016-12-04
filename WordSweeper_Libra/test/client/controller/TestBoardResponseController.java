package client.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import client.MockServerAccess;
import client.controller.responseController.BoardResponseController;
import client.model.Board;
import client.model.Game;
import client.model.Model;
import client.view.Application;
import xml.Message;

public class TestBoardResponseController {
	
	Model model=new Model();
	Application client=new Application(model);
	MockServerAccess mockServer=new MockServerAccess("localhost");
	
	@Before
	public void set()
	{
		// FIRST thing to do is register the protocol being used.
		if (!Message.configure("wordsweeper.xsd")) {
			fail ("unable to configure protocol");
		}
		client.setVisible(true);
		client.setServerAccess(mockServer);
	}

	@Test
	public void testBoardResponseProcess() {
		String name1 = "player1", name2 = "player2";
		int col1 = 4, col2 = 2, row1 = 1, row2 = 2;
		String bonus = "4,3";
		String gameId = "some id";
		String managingUser = name2;
		String board1 = "AFERKSOEROIERPOR";
		String board2 = "ECDRFTGOUIGERPRT";
		String pos1 = col1 + "," + row1, pos2 = col2 + "," + row2;
		int score1 = 5, score2 = 10;
		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><response id=\"someMessageID\" success=\"true\">"
				+ "<boardResponse bonus=\"%s\" gameId=\"%s\" managingUser=\"%s\">"
				+ "<player board=\"%s\" name=\"%s\" position=\"%s\" score=\"%s\"/>"
				+ "<player board=\"%s\" name=\"%s\" position=\"%s\" score=\"%s\"/>"
				+ "</boardResponse></response>";
		xml = String.format(xml, bonus, gameId, managingUser, board1, name1, pos1, score1, board2, name2, pos2, score2);
		model.updateInfo("something different", "I don't know", "haha", 6, 7, "abcdefghijklmnop", 55, "6,6");
		model.getPlayer().setName(name1);
		
		Message m = new Message(xml);
		new BoardResponseController(client, model).process(m);
		
		
		Board b = model.getBoard();
		assertTrue(b.positions.contains(pos1));
		assertTrue(b.positions.contains(pos2));
		assertEquals(b.getGlobalStartingCol(), col1);
		assertEquals(b.getGlobalStartingRow(), row1);
		assertEquals(b.getBoardInfo(), board1);
		assertEquals(b.getBonusCell(), bonus);
		
		Game game = model.getGame();
		assertTrue(game.getPlayersInfoMap().containsKey(name1) && game.getPlayersInfoMap().get(name1) == score1);
		assertTrue(game.getPlayersInfoMap().containsKey(name2) && game.getPlayersInfoMap().get(name2) == score2);
		assertTrue(game.getPlayersInfoMap().size() == 2);
		
		assertTrue(game.getPlayersPositionMap().containsKey(name1) && game.getPlayersPositionMap().get(name1).equals(pos1));
		assertTrue(game.getPlayersPositionMap().containsKey(name2) && game.getPlayersPositionMap().get(name2).equals(pos2));
		assertTrue(game.getPlayersPositionMap().size() == 2);
		
		assertEquals(model.getGame().getGameID(), gameId);
		assertEquals(model.getGame().getManagingUser(), name2);
		
		
		assertEquals(model.getPlayer().getName(), name1);
		assertEquals(model.getPlayer().getScore(), score1);
		assertFalse(model.getPlayer().isManager());
	}

}
