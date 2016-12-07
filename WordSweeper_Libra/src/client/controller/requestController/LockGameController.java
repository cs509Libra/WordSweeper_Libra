package client.controller.requestController;

import client.model.Model;
import client.view.Application;
import xml.Message;

/**
 * When the player clicks "lock" button, a lockGameRequest will be sent to the
 * server.
 * <p>
 * The {@link #process()} makes a lockGameRequest in XML format, and send it to
 * the server.
 * 
 * @author HanBao
 *
 */
public class LockGameController {
	Application app;
	Model model;

	public LockGameController(Application app, Model model) {
		this.app = app;
		this.model = model;
	}

	/** Make a lockGameRequest in XML format, and send it to the server. */
	public void process() {

		String xmlString = Message.requestHeader()
				+ String.format("<lockGameRequest gameId='%s'/></request>", model.getGame().getGameID());

		Message m = new Message(xmlString);
		app.getRequestArea().append(m.toString());
		app.getRequestArea().append("\n");
		app.getServerAccess().sendRequest(m);
	}
}
