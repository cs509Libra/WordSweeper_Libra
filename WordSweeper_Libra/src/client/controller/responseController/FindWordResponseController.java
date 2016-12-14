package client.controller.responseController;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import client.model.Model;
import client.view.Application;
import client.view.LeftBoardPanel;
import client.view.RightGameInfoBoard;
import xml.Message;

/**
 * Find word response information sent from server should be received once a player submits a word.
 * 
 * The {@link #process(Message)} handles the find word response information. If the submission is successful, the entity classes will be updated. Otherwise, return false.
 * @author You Zhou, Qingquan Zhao, Han Bao, Ruochen Shi (Authors contribute equally)
 *
 */

public class FindWordResponseController extends ControllerChain {

	public Application app;
	public Model model;

	public FindWordResponseController(Application a, Model m) {
		super();
		this.app = a;
		this.model = m;
	}

	@Override
	public boolean process(Message response) {
		String type = response.contents.getFirstChild().getLocalName();
		if (!type.equals("findWordResponse")) {
			return next.process(response);
		}
		if (response.contents.getAttributes().getNamedItem("success").getNodeValue().equals("false")) {
			model.getPlayer().setWordscore(0);
			((LeftBoardPanel) app.getMultiGame().getLeftBoardPanel()).getMessageLabel()
					.setText(response.contents.getAttributes().getNamedItem("reason").getNodeValue());
			return false;
		}

		Node boardResponse = response.contents.getFirstChild();
		NamedNodeMap map = boardResponse.getAttributes();

		String gameId = map.getNamedItem("gameId").getNodeValue();
		String score = map.getNamedItem("score").getNodeValue();
		String pname = map.getNamedItem("name").getNodeValue();

		model.getPlayer().setWordscore(Integer.valueOf(score));
		((LeftBoardPanel) app.getMultiGame().getLeftBoardPanel()).getMessageLabel().setText("Good job!");
		((LeftBoardPanel) app.getMultiGame().getLeftBoardPanel()).refreshBoard();
		((RightGameInfoBoard) app.getMultiGame().getRightGameInfoPanel()).updateGameInfoBoard();

		return true;
	}
}
