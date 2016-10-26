package server.controller;

import org.w3c.dom.NamedNodeMap;

import server.MockClient;
import server.Server;
import server.controller.CreateGameRequestController;
import server.controller.JoinGameRequestController;
import server.model.ServerModel;
import xml.Message;
import junit.framework.TestCase;

// validate that server generates appropriate modelResponse for each modelRequest.
public class TestCreateGameController extends TestCase {

	/** Become a placeholder for responses sent to the (only connected) client. */
	MockClient client1;
	MockClient client2;
	
	ServerModel model;
	
	protected void setUp() {
		// FIRST thing to do is register the protocol being used.
		if (!Message.configure("wordsweeper.xsd")) {
			fail ("unable to configure protocol");
		}
				
		client1 = new MockClient();
		client2 = new MockClient();
		Server.register("c1", client1);
		Server.register("c2", client2);
		model = new ServerModel();
	}
	
	protected void tearDown() {
		Server.unregister("c1");
		Server.unregister("c2");
	}
	
	public void testSimple() {
		// client1 creates game
		String xmlString = Message.requestHeader() + "<createGameRequest name='test'/></request>";
		Message request = new Message(xmlString);
		
		// get response after processing this request
		Message response = new CreateGameRequestController(model).process(client1, request);
				
		// make sure model is well-represented
		assertTrue (response.success());
		
		// get attributes of 'boardResponse' (firstChild) 
		NamedNodeMap map = response.contents.getFirstChild().getAttributes();
		assertEquals ("hg12jhd", map.getNamedItem("gameId").getNodeValue());
		
		// player 2 joins game
		xmlString = Message.requestHeader() + "<joinGameRequest gameId='hg12jhd' name='other'/></request>";
		request = new Message(xmlString);
		
		// get response after processing this request
		Message joinClient2Response = new JoinGameRequestController(model).process(client2, request);
		Message joinClient1Response = client1.getAndRemoveMessage(); 
		
		// get attributes of 'boardResponse' (firstChild) 
		map = joinClient1Response.contents.getFirstChild().getAttributes();
		assertEquals ("hg12jhd", map.getNamedItem("gameId").getNodeValue());
		map = joinClient2Response.contents.getFirstChild().getAttributes();
		assertEquals ("hg12jhd", map.getNamedItem("gameId").getNodeValue());
	}
}
