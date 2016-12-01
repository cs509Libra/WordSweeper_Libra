package client.controller.requestController;

import client.model.Model;
import client.view.Application;
import xml.Message;

public class ExitGameController {

	Application app;
	Model model;

	public ExitGameController(Application app, Model model) {
		this.app = app;
		this.model = model;
	}

	/** Make the request on the server and wait for response. */
	public void process() {
		
		String xmlString = Message.requestHeader() + String.format("<exitGameRequest name='%s' gameId='%s'/></request>", 
																	model.getPlayer().getName(),
																	model.getGame().getGameID());		
		
		Message m = new Message (xmlString);
		// Request the lock (this might not succeed).
		app.getRequestArea().append(m.toString());
		app.getRequestArea().append("\n");
		app.getServerAccess().sendRequest(m);
	}
}
