package client.controller;

import static org.junit.Assert.fail;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import client.MockServerAccess;
import client.controller.responseController.LockGameResponseController;
import client.model.Model;
import client.view.Application;
import client.view.MultiGame;
import xml.Message;

/**@author You Zhou, Qingquan Zhao, Han Bao, Ruochen Shi (Authors contribute equally)
 * This is responsible for testing "Lock Game Response" Controller*/
public class TestLockGameResponseController {
		Model model = new Model();
		Application client = new Application(model);
		MockServerAccess mockServer = new MockServerAccess("localhost");
		
		/**this is the setting for the test*/
		@Before
		public void set() {
			// FIRST thing to do is register the protocol being used.
			if (!Message.configure("wordsweeper.xsd")) {
				fail("unable to configure protocol");
			}
			client.setVisible(true);
			client.setServerAccess(mockServer);
			
			create_init_MultiGame();
		}

		public void create_init_MultiGame() {
			String name="player1";
			model.updateInfo("something different", "I don't know", "haha", 6, 7, "A,B,C,D,F,E,E,G,J,I,J,O,P,B,I,M", 55,
					"6,6");
			model.getPlayer().setName(name);
			client.setMg(new MultiGame(model, client));
		}
		
		/**this is responsible for testing the process of "Lock Game Response" controller*/
		@Test
		public void TestLockGameResponseProcess(){
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
