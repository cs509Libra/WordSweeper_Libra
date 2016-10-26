package client.controller;

import java.util.ArrayList;

import client.MockServerAccess;
import client.model.Model;
import client.view.Application;
import xml.Message;
import junit.framework.TestCase;

/**
 * This test case is needed when the job of a controller is to send a Request to the server.
 * <P> 
 * To make this work we need to create a "mock" Server whose only purpose is to WAIT for requests to come
 * from the client being pressed into service here in this test case. 
 * 
 * @author heineman
 */
public class TestCreateGameController extends TestCase {
	
	// Mock server object that extends (and overrides) ServerAccess for its purposes
	MockServerAccess mockServer;
	
	// client to connect
	Application client;
	
	// model being maintained by client.
	Model model;
	
	protected void setUp() {
		// FIRST thing to do is register the protocol being used.
		if (!Message.configure("wordsweeper.xsd")) {
			fail ("unable to configure protocol");
		}
		
		// prepare client and connect to server.
		model = new Model();
		client = new Application (model);
		client.setVisible(true);
		
		// Create mockServer to simulate server, and install 'obvious' handler
		// that simply dumps to the screen the responses.
		mockServer = new MockServerAccess("localhost");
		
		// as far as the client is concerned, it gets a real object with which
		// to communicate.
		client.setServerAccess(mockServer);
	}
	
	/**
	 * The real test case whose purpose is to validate that selecting the Locked button
	 * sends a GrabLock request to the server.
	 */
	public void testCreateGameProcess() {
		 new CreateGameController(client, model).process();
		 
		 // validate from mockServer
		 
		 ArrayList<Message> reqs = mockServer.getAndClearMessages();
		 assertTrue (reqs.size() == 1);
		 Message r = reqs.get(0);
		 
		 // a lock request is sent out.
		 assertEquals ("createGameRequest", r.contents.getFirstChild().getLocalName());
		 
		 // make sure "grab" attribute is there, and true
		 System.out.println (r.toString());
		 assertEquals ("samplePlayer", r.contents.getFirstChild().getAttributes().getNamedItem("name").getNodeValue());
		 
	}
	

	/**
	 * The real test case whose purpose is to validate that selecting the Locked button
	 * sends a GrabLock request to the server.
	 */
	public void testJoinGameProcess() {
		 new JoinGameController(client, model).process();
		 
		 // validate from mockServer
		 
		 ArrayList<Message> reqs = mockServer.getAndClearMessages();
		 assertTrue (reqs.size() == 1);
		 Message r = reqs.get(0);
		 
		 // a lock request is sent out.
		 assertEquals ("joinGameRequest", r.contents.getFirstChild().getLocalName());
		 
		 // make sure "grab" attribute is there, and true
		 System.out.println (r.toString());
		 assertEquals ("nextOne", r.contents.getFirstChild().getAttributes().getNamedItem("name").getNodeValue());
	}
}
