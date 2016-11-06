package client.controller.requestController;

import client.model.Model;
import client.view.Application;
import xml.Message;

public class LockGameController {
	Application app;
	Model model;

	public LockGameController(Application app, Model model) {
		this.app = app;
		this.model = model;
	}

	/** Make the request on the server and wait for response. */
	public void process() {
	
		String xmlString = Message.requestHeader() + String.format("<lockGameRequest gameId='%s'/></request>", model.getGame().getGameID());
		
		Message m = new Message (xmlString);
		app.getRequestArea().append(m.toString());
		app.getRequestArea().append("\n");
		app.getServerAccess().sendRequest(m);
	}
}
