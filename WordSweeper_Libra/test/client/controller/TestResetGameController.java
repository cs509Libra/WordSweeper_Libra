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
	/**@author You Zhou, Qingquan Zhao, Han Bao, Ruochen Shi (Authors contribute equally)
	 * This is responsible for testing "Reset Game" Controller*/
	Model model=new Model(); 
	Application client=new Application(model); 
	MockServerAccess mockServer=new MockServerAccess("localhost"); 

	@Before 
	public void set() 
	{
		/**the setting for the test*/
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
		/**this is the test for the process of "Reset Game" controller*/
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
	// end of TestResetGameController
	
	
	
	
	
	
	
	
	
	
	
}

