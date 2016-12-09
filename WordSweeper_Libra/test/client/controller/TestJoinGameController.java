package client.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import client.MockServerAccess;
import client.controller.requestController.JoinGameController;
import client.model.Model;
import client.view.Application;
import xml.Message;

public class TestJoinGameController {
	/**@author Ruochen Shi; 
	 * This is responsible for testing "Join Game" Controller*/
	Model model=new Model();
	Application client=new Application(model);
	MockServerAccess mockServer=new MockServerAccess("localhost");
	
	@Before
	public void set()
	{
		/**this is the setting for the test*/
		// FIRST thing to do is register the protocol being used.
		if (!Message.configure("wordsweeper.xsd")) {
			fail ("unable to configure protocol");
		}
		client.setVisible(true);
		client.setServerAccess(mockServer);
	}

	@Test
	public void TestJoinGameProcess()
	{
		/**@author Ruochen Shi; 
		 * This is responsible for testing the process of "Join Game" Controller
		 * Has two parts:
		 * 1.join game process without providing password
		 * 2.join game process by providing password*/
		//test join game process without password
		String gameid="game1";
		String player_name = "player1";
		model.getPlayer().setName(player_name);
		model.getGame().setGameID(gameid);
		client.setPlayerName(player_name);
		client.setGameNumber(gameid);
		JoinGameController jgame=new JoinGameController(client,model);
		jgame.process();
		ArrayList<Message> reqs = mockServer.getAndClearMessages();
		assertTrue(reqs.size() == 1);
		Message r = reqs.get(0);
		assertEquals("joinGameRequest", r.contents.getFirstChild().getLocalName());
		assertEquals(gameid, r.contents.getFirstChild().getAttributes().getNamedItem("gameId").getNodeValue());
		assertEquals(player_name, r.contents.getFirstChild().getAttributes().getNamedItem("name").getNodeValue());

		
/*		//test join game process with password
		String gameid2="game2";
		String name = "player2";
		String password ="abc";
		model.getPlayer().setName(name);
		model.getGame().setGameID(gameid2);
		client.setPlayerName(name);
		client.setGameNumber(gameid2);
		client.setPassword(password);
		JoinGameController jgame2=new JoinGameController(client,model);
		jgame2.process();
		ArrayList<Message> reqs2 = mockServer.getAndClearMessages();
		assertTrue(reqs2.size() == 1);
		Message r2 = reqs2.get(0);
		assertEquals("joinGameRequest", r2.contents.getFirstChild().getLocalName());
		assertEquals(gameid2, r2.contents.getFirstChild().getAttributes().getNamedItem("gameId").getNodeValue());
		assertEquals(name, r2.contents.getFirstChild().getAttributes().getNamedItem("name").getNodeValue());
		assertEquals(password, r2.contents.getFirstChild().getAttributes().getNamedItem("password").getNodeValue());
*/
	}
	
}
