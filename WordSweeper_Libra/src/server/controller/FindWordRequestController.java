package server.controller;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import server.ClientState;
import server.IProtocolHandler;
import server.model.ServerModel;
import xml.Message;

public class FindWordRequestController implements IProtocolHandler {
	
	ServerModel model;
	
	public FindWordRequestController (ServerModel model) {
		this.model = model;
	}
	
	public Message process(ClientState client, Message request) {
		
		// note you can retrieve information from the request...
		Node createRequest = request.contents.getFirstChild();
		NamedNodeMap map = createRequest.getAttributes();
		
		String gameId = map.getNamedItem("gameId").getNodeValue();
		String pname = map.getNamedItem("name").getNodeValue();
		String xmlString = Message.responseHeader(request.id()) + String.format("<findWordResponse gameId='%s' name='' score='392489048'></findWordResponse></response>", gameId, pname);

		// send this response back to the client which sent us the request.
		return new Message (xmlString);
	}

}
