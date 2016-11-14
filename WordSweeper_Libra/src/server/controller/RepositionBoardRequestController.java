package server.controller;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import server.ClientState;
import server.IProtocolHandler;
import server.model.ServerModel;
import xml.Message;

public class RepositionBoardRequestController implements IProtocolHandler {

	ServerModel model;
	
	public RepositionBoardRequestController (ServerModel model) {
		this.model = model;
	}
	
	public Message process(ClientState client, Message request) {
		
		model.joinGame();  // HACK.
		
		// note you can retrieve information from the request...
		Node createRequest = request.contents.getFirstChild();
		NamedNodeMap map = createRequest.getAttributes();
		
		String pname = map.getNamedItem("name").getNodeValue();
		Integer rowChange = Integer.valueOf(map.getNamedItem("rowChange").getNodeValue());
		Integer colChange = Integer.valueOf(map.getNamedItem("colChange").getNodeValue());
		
		String xmlString;
		if(rowChange == -1 && colChange == 0){
			xmlString = Message.responseHeader(request.id()) +
					"<boardResponse gameId='hg12jhd' managingUser='" + pname + "' bonus='4,3' contents='ABCGBCJDH...HDJHJD'>" +
				      "<player name='" + pname + "' score='392489038' position='4,4' board='R,E,P,O,S,I,T,I,O,N,M,V,U,P,Q,Q'/>" +
				  "</boardResponse>" +
				"</response>";
		}else if(rowChange == 1 && colChange == 0){
			xmlString = Message.responseHeader(request.id()) +
					"<boardResponse gameId='hg12jhd' managingUser='" + pname + "' bonus='4,3' contents='ABCGBCJDH...HDJHJD'>" +
				      "<player name='" + pname + "' score='392489038' position='4,6' board='R,E,P,O,S,I,T,I,O,N,M,V,D,O,W,N'/>" +
				  "</boardResponse>" +
				"</response>";
		}else if(colChange == -1 && rowChange == 0){
			xmlString = Message.responseHeader(request.id()) +
					"<boardResponse gameId='hg12jhd' managingUser='" + pname + "' bonus='4,3' contents='ABCGBCJDH...HDJHJD'>" +
				      "<player name='" + pname + "' score='392489038' position='3,5' board='R,E,P,O,S,I,T,I,O,N,M,V,L,E,F,T'/>" +
				  "</boardResponse>" +
				"</response>";
		}else if(colChange == 1 && rowChange == 0){
			xmlString = Message.responseHeader(request.id()) +
					"<boardResponse gameId='hg12jhd' managingUser='" + pname + "' bonus='4,3' contents='ABCGBCJDH...HDJHJD'>" +
				      "<player name='" + pname + "' score='392489038' position='5,5' board='R,E,P,O,S,I,T,I,O,N,M,V,Q,R,I,T'/>" +
				  "</boardResponse>" +
				"</response>";
		}else{
			xmlString = Message.responseHeader(request.id()) +
					"<boardResponse gameId='hg12jhd' managingUser='" + pname + "' bonus='4,3' contents='ABCGBCJDH...HDJHJD'>" +
				      "<player name='" + pname + "' score='392489038' position='4,5' board='R,E,P,O,S,I,T,I,O,N,N,O,M,O,V,E'/>" +
				  "</boardResponse>" +
				"</response>";
		}
	
		// send this response back to the client which sent us the request.
		return new Message (xmlString);
	}
}
