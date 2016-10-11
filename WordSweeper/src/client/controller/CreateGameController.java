package client.controller;


import xml.Message;

import javax.security.auth.login.AppConfigurationEntry;

import client.model.Model;
import client.view.Application;

public class CreateGameController {

	Application app;
	Model model;

	public CreateGameController(Application app, Model model) {
		this.app = app;
		this.model = model;
	}

	/** Make the request on the server and wait for response. */
	public void process(String name) {
		// send the request to create the game.
		String xmlString = Message.requestHeader() + "<createGameRequest name='"+name+"'/></request>";
		Message m = new Message (xmlString);
        System.out.println(m);
		app.getServerAccess().sendRequest(m);
	}
}
