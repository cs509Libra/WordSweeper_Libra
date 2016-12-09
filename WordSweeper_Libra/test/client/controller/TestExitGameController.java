package client.controller;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import client.MockServerAccess;
import client.controller.requestController.ExitGameController;
import client.model.Model;
import client.view.Application;
import xml.Message;


public class TestExitGameController {
	/**@author Ruochen Shi; 
	 * This is responsible for testing "Exit Game" Controller*/

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
	public void TestExitGameProcess(){
		/**@author Ruochen Shi; 
		 * This is responsible for testing the process of "Exit Game" Controller*/
		// set up model info before actually process
		String player_name = "player1";
		String game_id = "exitGame";
		model.getPlayer().setName(player_name);
		model.getGame().setGameID(game_id);
		
		ExitGameController  exit1=new ExitGameController(client,model);
		// process
		exit1.process();
		
		// check server whether receiving correct message
		ArrayList<Message> reqs = mockServer.getAndClearMessages();
		
		// after getting messages, think of what to test
		//<-- 1. the number of messages should be 1, only one message is sent
		assertTrue(reqs.size() == 1);
		
		//<-- 2. the message label should be exitGameRequest
		//<--	we use get 0 because this is the first and only message should be received
		Message r = reqs.get(0);
		assertEquals("exitGameRequest", r.contents.getFirstChild().getLocalName());
		
		//<-- 3. the message label should have attribute name to be the same name which is set in the model
		assertEquals(player_name, r.contents.getFirstChild().getAttributes().getNamedItem("name").getNodeValue());
		//<-- 4. the message label should have attribute gameId to be the same name which is set in the model
		assertEquals(game_id, r.contents.getFirstChild().getAttributes().getNamedItem("gameId").getNodeValue());
	}

}
