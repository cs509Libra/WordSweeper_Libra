package client.controller.responseController;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import client.model.Model;
import client.view.Application;
import xml.Message;

public class JoinGameResponseController extends ControllerChain {
	public Application app;
	public Model model;
	
	public JoinGameResponseController(Application a, Model m) {
		super();
		this.app = a;
		this.model = m;
	}
	@Override
	public boolean process(Message response) {
		
		String type = response.contents.getFirstChild().getLocalName();
		if (!type.equals ("joinGameResponse")) {
			return next.process(response);
		}
		
		Node boardResponse = response.contents.getFirstChild();
		NamedNodeMap map = boardResponse.getAttributes();
		
		String gameId = map.getNamedItem("gameId").getNodeValue();
		
		
		
		
		
		app.getResponseArea().append(response.toString() + "\n");
		model.getGame().setGameID(gameId);
		return true;
	}

}
