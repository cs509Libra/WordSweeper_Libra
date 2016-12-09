package client.controller;

import static org.junit.Assert.fail;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import client.MockServerAccess;
import client.controller.responseController.LockGameResponseController;
import client.model.Model;
import client.view.Application;
import xml.Message;

public class TestLockGameResponseController {
	/**@author Ruochen Shi; 
	 * This is responsible for testing "Lock Game Response" Controller*/
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
		public void TestLockGameResponseProcess(){
			/**this is responsible for testing the process of "Lock Game Response" controller*/
			String gameID="Game1";
			model.getGame().setGameID(gameID);
			model.getGame().setLocked(false);
			
			String xml="<?xml version=\"1.0\" encoding=\"UTF-8\"?><response id=\"someMessageID\" success=\"true\">"
			           + "<lockGameResponse gameId=\"%s\"/></response>";
			xml=String.format(xml,gameID);
			Message m = new Message(xml);

			boolean check=new LockGameResponseController(client,model).process(m);
			assertTrue(check);
			assertTrue(model.getGame().isLocked());	
		}
}
//end of TestLockGameResponseController
