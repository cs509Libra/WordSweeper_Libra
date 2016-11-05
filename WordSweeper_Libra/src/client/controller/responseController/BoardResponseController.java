package client.controller.responseController;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import xml.Message;
import client.model.Model;
import client.view.Application;

/**
 * Tells the client whether the model is locked or not BY SOME OTHER CLIENT. This will never be returned to a client
 * to tell him that HE has the model locked (that is job of LockResponse).
 */
public class BoardResponseController extends ControllerChain {

	public Application app;
	public Model model;
	
	public BoardResponseController(Application a, Model m) {
		super();
		this.app = a;
		this.model = m;
	}
	
	public boolean process(Message response) {
		String type = response.contents.getFirstChild().getLocalName();
		if (!type.equals ("boardResponse")) {
			return next.process(response);
		}
		
		// this refers to the outer node of the Message DOM (in this case, updateResponse).
		Node boardResponse = response.contents.getFirstChild();
		NamedNodeMap map = boardResponse.getAttributes();
		
		String gameId = map.getNamedItem("gameId").getNodeValue();
		app.getResponseArea().append("Board Message received for game:" + gameId + "\n");
		app.getResponseArea().append("Players:\n");
		NodeList list = boardResponse.getChildNodes();
		for (int i = 0; i < list.getLength(); i++) {
			Node n = list.item(i);
			String pname = n.getAttributes().getNamedItem("name").getNodeValue();
			app.getResponseArea().append("  " + pname  + "\n");
		}
		
		// at this point, you would normally start processing this...
		app.getResponseArea().append(response.toString());
		app.getResponseArea().append("\n");
		
		// initateGame
		Node player = boardResponse.getFirstChild();
		NamedNodeMap playerMap = player.getAttributes();
		String playerName = playerMap.getNamedItem("name").getNodeValue();
		String boardInfo = playerMap.getNamedItem("board").getNodeValue();
		String colRow = playerMap.getNamedItem("position").getNodeValue();
		char[] corRowArray = colRow.toCharArray();
		Integer globalStartingCol = Integer.valueOf(String.valueOf(corRowArray[0]));
		Integer globalStaringRow = Integer.valueOf(String.valueOf(corRowArray[corRowArray.length - 1]));
		Long score = Long.valueOf(playerMap.getNamedItem("score").getNodeValue());

		app.model.updateInfo(gameId, "", playerName, (int)globalStartingCol, (int)globalStaringRow, boardInfo, score);
		
		return true;
	}

}
