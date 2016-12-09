package client.controller;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import client.MockServerAccess;
import client.controller.responseController.ConnectResponseController;
import client.model.Model;
import client.view.Application;
import xml.Message;

public class TestConnectResponseController {
	/**@author Ruochen Shi; 
	 * This is responsible for testing "connect response" Controller*/
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
	}
	
	@Test
	public void TestConnectResponseProcess(){
		/**this is the test for connect response process*/
		String xml= "<?xml version=\"1.0\" encoding=\"UTF-8\"?><response id=\"someMessageID\" success=\"true\">"
		    + "<connectResponse id:\"game1\""
		    +"</connectResponse></response>";
		xml =String.format(xml);
		Message m = new Message(xml);
		ConnectResponseController crc=new ConnectResponseController(client,model);
		assertTrue(crc.process(m));
	}

}
