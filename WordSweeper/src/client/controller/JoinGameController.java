package client.controller;


import xml.Message;
import client.model.Model;
import client.view.Application;

public class JoinGameController {

	Application app;
	Model model;

	public JoinGameController(Application app, Model model) {
		this.app = app;
		this.model = model;
	}

	/** Make the request on the server and wait for response. */
	public void process(String gameId,String name) {
		// send the request to create the game.
		String xmlString = Message.requestHeader() + "<joinGameRequest gameId='"+gameId+"' name='"+name+"'/></request>";
		Message m = new Message (xmlString);
		System.out.println(m);
		// Request the lock (this might not succeed).
	//	app.getRequestArea().append(m.toString());
	//	app.getRequestArea().append("\n");
		app.getServerAccess().sendRequest(m);
	}
}
