package client.controller.requestController;

import client.model.Model;
import client.view.Application;
import xml.Message;

/**
 * When the player clicks "Join Game" button, a joinGameRequest will be sent to
 * the server.
 * <p>
 * The {@link #process()} makes a joinGameRequest in XML format, and send it to
 * the server.
 * 
 * @author You Zhou, Qingquan Zhao, Han Bao, Ruochen Shi (Authors contribute equally)
 *
 */
public class JoinGameController {

	Application app;
	Model model;

	public JoinGameController(Application app, Model model) {
		this.app = app;
		this.model = model;
	}

	/** Make a joinGameRequest in XML format, and send it to the server. */
	public void process() {

		String xmlString;
		if (this.app.getPassword() == null) {
			xmlString = Message.requestHeader() + String.format("<joinGameRequest gameId='%s' name='%s'/></request>",
					app.getGameNumber(), app.getPlayerName());

		} else {
			xmlString = Message.requestHeader()
					+ String.format("<joinGameRequest gameId='%s' name='%s' password='%s'/></request>",
							app.getGameNumber(), app.getPlayerName(), app.getPassword());
		}
		Message m = new Message(xmlString);
		app.getRequestArea().append(m.toString());
		app.getRequestArea().append("\n");
		app.getServerAccess().sendRequest(m);
	}
}
