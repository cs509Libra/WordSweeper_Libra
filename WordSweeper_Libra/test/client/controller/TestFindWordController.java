package client.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import client.MockServerAccess;
import client.controller.requestController.FindWordController;
import client.model.Model;
import client.view.Application;
import xml.Message;

/**@author You Zhou, Qingquan Zhao, Han Bao, Ruochen Shi (Authors contribute equally) 
 * This is responsible for testing "Find Word" Controller*/
public class TestFindWordController {
	Model model=new Model();
	Application client=new Application(model);
	MockServerAccess mockServer=new MockServerAccess("localhost");
	
	/**this is the setting for the test*/
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
	
	/**@author You Zhou, Qingquan Zhao, Han Bao, Ruochen Shi (Authors contribute equally) 
	 * This is responsible for testing the process of "Find Word" Controller*/
	@Test
	public void TestFindWordProcess()
	{
		String player_name = "player1";
		String game_id = "game1";
		String wordContent="SO";


		model.getPlayer().setName(player_name);
		model.getGame().setGameID(game_id);
		model.getBoard().getWord().setContent(wordContent);
		model.getBoard().updateBoard(2, 1, "S,A,B,C,O,A,A,B,I,E,N,F,J,K,O,L");
		model.getBoard().addToChosenCellsByCellIndex(0);
		model.getBoard().addToChosenCellsByCellIndex(4);

		
		FindWordController fwc=new FindWordController(client,model);
		
		fwc.process();
		
		ArrayList<Message> reqs = mockServer.getAndClearMessages();
		
		assertTrue(reqs.size() == 1);
		
		Message r = reqs.get(0);
		assertEquals("findWordRequest", r.contents.getFirstChild().getLocalName());
		assertEquals(player_name, r.contents.getFirstChild().getAttributes().getNamedItem("name").getNodeValue());
		assertEquals(game_id, r.contents.getFirstChild().getAttributes().getNamedItem("gameId").getNodeValue());
		assertEquals(wordContent,r.contents.getFirstChild().getAttributes().getNamedItem("word").getNodeValue());
	}
}
//end of TestFindWordController