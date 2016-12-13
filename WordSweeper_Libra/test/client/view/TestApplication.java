package client.view;

import org.junit.Test;

import client.model.Model;

/**
 * @author You Zhou, Qingquan Zhao, Han Bao, Ruochen Shi (Authors contribute
 *         equally) This is responsible for testing "Application" Boundary class
 */
public class TestApplication {

	Model model = new Model();

	/** the test for application construction */
	@Test
	public void testCreateGame() {
		Application app = new Application(model);
		app.playerNameField.setText("Player");
		app.passWordField.setText("password");
		app.btnCreateGame.doClick();
	}

	@Test
	public void testCreateGameNoPalyerName() {
		Application app = new Application(model);
		app.gameNumberField.setText("Room");
		app.passWordField.setText("password");
		app.btnJoinGame.doClick();
	}

	@Test
	public void testJoinGame() {
		Application app = new Application(model);
		app.gameNumberField.setText("Room");
		app.playerNameField.setText("Player");
		app.passWordField.setText("password");
		app.btnJoinGame.doClick();
	}

	@Test
	public void testJoinGameNoGameId() {
		Application app = new Application(model);
		app.playerNameField.setText("Player");
		app.passWordField.setText("password");
		app.btnJoinGame.doClick();
	}

	@Test
	public void testJoinGameNoPalyerName() {
		Application app = new Application(model);
		app.gameNumberField.setText("Room");
		app.passWordField.setText("password");
		app.btnJoinGame.doClick();
	}
}
