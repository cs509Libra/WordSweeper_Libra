package client.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import client.MockServerAccess;
import client.controller.requestController.CreateGameController;
import client.model.Model;
import client.view.Application;
import xml.Message;

public class TestCreateGameController {
	/**@author You Zhou, Qingquan Zhao, Han Bao, Ruochen Shi (Authors contribute equally) 
	 * This is responsible for testing "Create Game" Controller*/
	Model model=new Model();
	Application client=new Application(model);
	MockServerAccess mockServer=new MockServerAccess("localhost");
	
	@Before
	public void set()
	/**This is the setting for the test*/
	{
		// FIRST thing to do is register the protocol being used.
		if (!Message.configure("wordsweeper.xsd")) {
			fail ("unable to configure protocol");
		}
		client.setVisible(true);
		client.setServerAccess(mockServer);
	}

	@Test
	public void TestCreateGameProcess()
	{
		/**@author You Zhou, Qingquan Zhao, Han Bao, Ruochen Shi (Authors contribute equally) 
		 * This is responsible for testing the process "Create Game" Controller
		 * Has two parts: 
		 * 1.don't provide password
		 * 2.provide password*/
		//test create game process without password
		String player_name = "player1";
		model.getPlayer().setName(player_name);
		client.setPlayerName(player_name);
		CreateGameController cgame=new CreateGameController(client,model);
		cgame.process();
		ArrayList<Message> reqs = mockServer.getAndClearMessages();
		assertTrue(reqs.size() == 1);
		Message r = reqs.get(0);
		assertEquals("createGameRequest", r.contents.getFirstChild().getLocalName());
		assertEquals(player_name, r.contents.getFirstChild().getAttributes().getNamedItem("name").getNodeValue());
		
		//test create game process with password
		String password="abc";
		String name="player2";
		model.getPlayer().setName(name);
		client.setPlayerName(name);
		client.setPassword(password);
		CreateGameController cgame2=new CreateGameController(client,model);
		cgame2.process();
		ArrayList<Message> reqs2 = mockServer.getAndClearMessages();
		assertTrue(reqs2.size() == 1);
		Message r2 = reqs2.get(0);
		assertEquals("createGameRequest", r2.contents.getFirstChild().getLocalName());
		assertEquals(name, r2.contents.getFirstChild().getAttributes().getNamedItem("name").getNodeValue());
		assertEquals(password, r2.contents.getFirstChild().getAttributes().getNamedItem("password").getNodeValue());
	}
}
//end of TestCreateGameController