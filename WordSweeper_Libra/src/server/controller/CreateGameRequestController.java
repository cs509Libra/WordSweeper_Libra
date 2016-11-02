package server.controller;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import server.ClientState;
import server.IProtocolHandler;
import server.model.ServerModel;
import xml.Message;

/**
 * Controller on server to package up the current state of the model
 * as an updateResponse message and send it back to the client.
 */
public class CreateGameRequestController implements IProtocolHandler {

	ServerModel model;
	
	public CreateGameRequestController (ServerModel model) {
		this.model = model;
	}
	
	public Message process(ClientState client, Message request) {
		
		model.joinGame();  // HACK.
		
		// note you can retrieve information from the request...
		Node createRequest = request.contents.getFirstChild();
		NamedNodeMap map = createRequest.getAttributes();
		
		String pname = map.getNamedItem("name").getNodeValue();
		
		
		// Construct message reflecting state
		String xmlString = Message.responseHeader(request.id()) +
				"<boardResponse gameId='hg12jhd' managingUser='" + pname + "' bonus='4,3' contents='ABCGBCJDH...HDJHJD'>" +
			      "<player name='" + pname + "' score='392489038' position='4,6' board='AFERKSOEROIERPOR'/>" +
			  "</boardResponse>" +
			"</response>";
		
		// send this response back to the client which sent us the request.
		return new Message (xmlString);
	}
}
