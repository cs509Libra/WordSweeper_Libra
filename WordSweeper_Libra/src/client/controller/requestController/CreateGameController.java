package client.controller.requestController;

import client.model.Model;
import client.view.Application;
import xml.Message;

/**
 * When the player clicks "Create Game" button, a createGameRequest will be sent
 * to the server.
 * <p>
 * The {@link #process()} makes a createGameRequest in XML format, and send it
 * to the server.
 * 
 * @author You Zhou, Qingquan Zhao, Han Bao, Ruochen Shi (Authors contribute equally)
 *
 */
public class CreateGameController {

	Application app;
	Model model;

	public CreateGameController(Application app, Model model) {
		this.app = app;
		this.model = model;
	}

	/** Make a createGameRequest in XML format, and send it to the server. */
	public void process() {

		String xmlString;

		if (this.app.getPassword() == null) {// No password.
			xmlString = Message.requestHeader()
					+ String.format("<createGameRequest name='%s'/></request>", this.app.getPlayerName());
		} else {// There is a password.
			xmlString = Message.requestHeader()
					+ String.format("<createGameRequest name='%s' password='%s'/></request>", this.app.getPlayerName(),
							this.app.getPassword());
		}

		Message m = new Message(xmlString);
		app.getRequestArea().append(m.toString());
		app.getRequestArea().append("\n");
		app.getServerAccess().sendRequest(m);

		model.getGame().setLocked(false);// reset the lock flag

	}
}
