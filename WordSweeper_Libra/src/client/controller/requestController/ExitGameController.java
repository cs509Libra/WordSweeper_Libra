package client.controller.requestController;

import client.model.Model;
import client.view.Application;
import xml.Message;

/**
 * When the player clicks "quit" button, an exitGameRequest will be sent to the
 * server.
 * <p>
 * The {@link #process()} makes an exitGameRequest in XML format, and send it to
 * the server.
 * 
 * @author HanBao
 *
 */
public class ExitGameController {

	Application app;
	Model model;

	public ExitGameController(Application app, Model model) {
		this.app = app;
		this.model = model;
	}

	/** Make an exitGameRequest in XML format, and send it to the server. */
	public void process() {

		String xmlString = Message.requestHeader() + String.format("<exitGameRequest name='%s' gameId='%s'/></request>",
				model.getPlayer().getName(), model.getGame().getGameID());

		Message m = new Message(xmlString);
		app.getRequestArea().append(m.toString());
		app.getRequestArea().append("\n");
		app.getServerAccess().sendRequest(m);
	}
}
