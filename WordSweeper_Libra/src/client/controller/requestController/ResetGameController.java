package client.controller.requestController;

import client.model.Model;
import client.view.Application;
import xml.Message;

/**
 * When the player clicks "reset" button, a resetGameRequest will be sent to the
 * server.
 * <p>
 * The {@link #process()} makes a resetGameRequest in XML format, and send it to
 * the server.
 * 
 * @author You Zhou, Qingquan Zhao, Han Bao, Ruochen Shi (Authors contribute equally)
 *
 */
public class ResetGameController {

	Application app;
	Model model;

	public ResetGameController(Application app, Model model) {
		this.app = app;
		this.model = model;
	}

	/** Make a resetGameRequest in XML format, and send it to the server. */
	public void process() {

		String xmlString = Message.requestHeader()
				+ String.format("<resetGameRequest gameId='%s'/></request>", model.getGame().getGameID());

		Message m = new Message(xmlString);
		app.getRequestArea().append(m.toString());
		app.getRequestArea().append("\n");
		app.getServerAccess().sendRequest(m);
	}
}
