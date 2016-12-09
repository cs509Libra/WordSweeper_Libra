package client.controller;

import static org.junit.Assert.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import client.MockServerAccess;
import client.controller.responseController.FindWordResponseController;
import client.model.Model;
import client.view.Application;
import client.view.LeftBoardPanel;
import xml.Message;

public class TestFindWordResponseController {
	/**@author Ruochen Shi; 
	 * This is responsible for testing "Find Word Response" Controller*/
	Model model = new Model();
	Application client = new Application(model);
	MockServerAccess mockServer = new MockServerAccess("localhost");

	@Before
	public void set() {
		/**this is the setting for the test*/
		// FIRST thing to do is register the protocol being used.
		if (!Message.configure("wordsweeper.xsd")) {
			fail("unable to configure protocol");
		}
		client.setVisible(true);
		client.setServerAccess(mockServer);
	}

	@Test
	public void TestFindWordResponseProcess(){
		/**this is responsible for testing the process of "Find Word Response" controller*/
		String gameID="game";
		String name="player";
		int score= 5000;
		
		String xml="<?xml version=\"1.0\" encoding=\"UTF-8\"?><response id=\"someMessageID\" success=\"true\">"
		           + "<findWordResponse gameId=\"%s\" name=\"%s\" score=\"%d\"/></response>";
		xml=String.format(xml,gameID,name,score);
		Message m = new Message(xml);
		
		model.getGame().setGameID(gameID);
		model.getPlayer().setName(name);
		model.getPlayer().setScore(0);
		
		FindWordResponseController fwrc=new FindWordResponseController(client,model);
		assertTrue(fwrc.process(m));
	}
}
//end of TestFindWordResponseController