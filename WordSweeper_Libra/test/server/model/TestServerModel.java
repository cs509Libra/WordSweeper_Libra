package server.model;

import junit.framework.TestCase;

public class TestServerModel extends TestCase {

	public void testServerModel() {
		ServerModel m = new ServerModel();
		assertEquals (0, m.getNumPlayers());
		
		m.joinGame();
		assertEquals (1, m.getNumPlayers());
		
		m.joinGame();
		assertEquals (2, m.getNumPlayers());
	}
}
