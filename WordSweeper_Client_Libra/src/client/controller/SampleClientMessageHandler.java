package client.controller;
import xml.Message;
import client.IMessageHandler;
import client.view.Log_in;

/**
 * Sample implementation of a protocol handler to respond to messages received from the server.
 * You should follow this template when designing YOUR message handler.
 * 
 * Note: This one does nothing more than dump the XML message into the client window. Nothing that special.
 */
public class SampleClientMessageHandler implements IMessageHandler {

	Log_in login;
	
	public SampleClientMessageHandler(Log_in login) {
		this.login = login;
	}
	
	@Override
	public void process(Message response) {
		String type = response.contents.getFirstChild().getLocalName();

		// process each response that comes in with its own controller.
		if (type.equals ("boardResponse")) {
			// What happens now that we are connected?
			new BoardResponseController(login, login.model).process(response);
		} else if (type.equals ("connectResponse")) {
//			app.getResponseArea().append(response.toString() + "\n");
		}
		
		// only here to show messages as they are received by the client
		// this isn't needed.
		System.out.println(response);
	}

}
