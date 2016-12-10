package client.controller;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import client.MockServerAccess;
import client.controller.responseController.ResetGameResponseController;
import client.model.Model;
import client.view.Application;
import client.view.MultiGame;
import xml.Message;

public class TestResetGameResponseController {
	/**@author You Zhou, Qingquan Zhao, Han Bao, Ruochen Shi (Authors contribute equally)
	 * This is responsible for testing the controller of "List Game Response" controller */
		Model model = new Model();
		Application client = new Application(model);
		MockServerAccess mockServer = new MockServerAccess("localhost");

		@Before
		public void set() {
			/**this is the preparation before the test.*/
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
		
		@Test
		public void TestResetGameResponseProcess()
		{
			/**this is the test for the process of "Reset Game Response" controller*/
			String gameid="Game1";
			model.getGame().setGameID(gameid);
			String xml="<?xml version=\"1.0\" encoding=\"UTF-8\"?><response id=\"someMessageID\" success=\"true\">"
			           + "<resetGameResponse gameId=\"%s\"/></response>";
			xml=String.format(xml,gameid);
			Message m = new Message(xml);
			ResetGameResponseController rgrc=new ResetGameResponseController(client,model);
			assertTrue(rgrc.process(m));
		}

}
//end of TestResetGameResponseController