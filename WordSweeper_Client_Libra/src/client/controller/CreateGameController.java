package client.controller;


import xml.Message;

import javax.security.auth.login.AppConfigurationEntry;

import client.model.Model;
import client.view.Log_in;

public class CreateGameController {

	Log_in login;
	Model model;

	public CreateGameController(Log_in login, Model model) {
		this.login = login;
		this.model = model;
	}

	/** Make the request on the server and wait for response. */
	public void process(String name) {
		// send the request to create the game.
		String xmlString = Message.requestHeader() + "<createGameRequest name='"+name+"'/></request>";
		Message m = new Message (xmlString);
        System.out.println(m);
		login.getServerAccess().sendRequest(m);
	}
}
