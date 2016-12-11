package client.controller;

import static org.junit.Assert.assertEquals; 
import static org.junit.Assert.assertTrue; 
import static org.junit.Assert.fail; 

import java.util.ArrayList;

import org.junit.Before; 
import org.junit.Test; 

import client.MockServerAccess; 
import client.controller.requestController.RepositionBoardController; 
import client.model.Model; 
import client.view.Application; 
import xml.Message; 

/**@author You Zhou, Qingquan Zhao, Han Bao, Ruochen Shi (Authors contribute equally)
 * This is responsible for testing "Reposition Board" Controller*/
public class TestRepositionBoardController {
	Model model=new Model(); 
	Application client=new Application(model); 
	MockServerAccess mockServer=new MockServerAccess("localhost"); 
	
	/**the setting for the test*/
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
	
	/**this is the testing for the process of "Reposition Board" controller*/
	@Test 
	public void TestRepositionBoardProcess() 
	{
		String game_id = "Game1"; 
		String name="Mike"; 
		model.getGame().setGameID(game_id); 
		model.getPlayer().setName(name); 

		int colchange=1; 
		int rowchange=2; 
		model.getBoard().setRequestColChange(colchange); 
		model.getBoard().setRequestRowChange(rowchange); 
		String colChange="1"; 
		String rowChange="2"; 
		
		RepositionBoardController rbc=new RepositionBoardController(client,model); 
		rbc.process(); 

		ArrayList<Message> reqs = mockServer.getAndClearMessages(); 
		assertTrue(reqs.size() == 1); 
		Message r = reqs.get(0); 
		assertEquals("repositionBoardRequest", r.contents.getFirstChild().getLocalName()); 
		assertEquals(game_id, r.contents.getFirstChild().getAttributes().getNamedItem("gameId").getNodeValue());		 
		assertEquals(name,r.contents.getFirstChild().getAttributes().getNamedItem("name").getNodeValue()); 
		assertEquals(colChange,r.contents.getFirstChild().getAttributes().getNamedItem("colChange").getNodeValue()); 
		assertEquals(rowChange,r.contents.getFirstChild().getAttributes().getNamedItem("rowChange").getNodeValue()); 	
	}
	//end of TestRepositionBoardController
}
