package client.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import client.MockServerAccess;
import client.controller.requestController.ResetGameController;
import client.model.Model;
import client.view.Application;
import xml.Message;

public class TestResetGameController {
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
	public void TestResetGameProcess()
	{
		String game_id = "resetGame";
		model.getGame().setGameID(game_id);
		
		ResetGameController rgame=new ResetGameController(client,model);
		rgame.process();
		
		ArrayList<Message> reqs = mockServer.getAndClearMessages();
		assertTrue(reqs.size() == 1);
		Message r = reqs.get(0);
		assertEquals("resetGameRequest", r.contents.getFirstChild().getLocalName());
		assertEquals(game_id, r.contents.getFirstChild().getAttributes().getNamedItem("gameId").getNodeValue());		
	}


}
