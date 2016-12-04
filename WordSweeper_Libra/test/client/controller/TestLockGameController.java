package client.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import client.MockServerAccess;
import client.controller.requestController.LockGameController;
import client.model.Model;
import client.view.Application;
import xml.Message;

public class TestLockGameController {
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
	public void TestLockGameProcess()
	{
		String game_id = "lockGame";
		model.getGame().setGameID(game_id);
		
		LockGameController lgame=new LockGameController(client,model);
		lgame.process();
		
		ArrayList<Message> reqs = mockServer.getAndClearMessages();
		assertTrue(reqs.size() == 1);
		Message r = reqs.get(0);
		assertEquals("lockGameRequest", r.contents.getFirstChild().getLocalName());
		assertEquals(game_id, r.contents.getFirstChild().getAttributes().getNamedItem("gameId").getNodeValue());		
	}

}
