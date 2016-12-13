package client.controller.responseController;

import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import client.model.Model;
import client.view.Application;
import client.view.LeftBoardPanel;
import client.view.RightGameInfoBoard;
import xml.Message;

/**
 * Whenever a board response message is received from server, this class deals with it based on xml transmission protocol.
 * <p>
 * The {@link #process(Message)}} extract information from the broad response message from server, update the corresponding information in both the entity classes and boundary class accordingly.
 * @author You Zhou, Qingquan Zhao, Han Bao, Ruochen Shi (Authors contribute equally)
 *
 */
public class BoardResponseController extends ControllerChain {

	public Application app;
	public Model model;
	private boolean flag;

	public BoardResponseController(Application a, Model m) {
		super();
		this.app = a;
		this.model = m;
		this.flag = false;
	}

	@Override
	public boolean process(Message response) {
		String type = response.contents.getFirstChild().getLocalName();
		if (!type.equals("boardResponse")) {
			return next.process(response);
		}
		this.model.existedGame = true;

		// this refers to the outer node of the Message DOM (in this case,
		// updateResponse).
		Node boardResponse = response.contents.getFirstChild();
		NamedNodeMap map = boardResponse.getAttributes();

		String gameId = map.getNamedItem("gameId").getNodeValue();
		String managingUser = map.getNamedItem("managingUser").getNodeValue();
		String bonusCell = map.getNamedItem("bonus").getNodeValue();
		NodeList list = boardResponse.getChildNodes();

		Map<String, Integer> allPlayersInfo = new HashMap<String, Integer>();
		Map<String, String> allPlayersPositionInfo = new HashMap<String, String>();
		this.model.getBoard().positions.clear();

		for (int i = 0; i < list.getLength(); i++) {
			Node n = list.item(i);
			String pname = n.getAttributes().getNamedItem("name").getNodeValue();
			String pscore = n.getAttributes().getNamedItem("score").getNodeValue();
			String boardInfo = n.getAttributes().getNamedItem("board").getNodeValue();
			String plocation = n.getAttributes().getNamedItem("position").getNodeValue();
			char[] corRowArray = plocation.toCharArray();
			Integer globalStartingCol = Integer.valueOf(String.valueOf(corRowArray[0]));
			Integer globalStaringRow = Integer.valueOf(String.valueOf(corRowArray[corRowArray.length - 1]));
			Long score = Long.valueOf(n.getAttributes().getNamedItem("score").getNodeValue());
			allPlayersInfo.put(pname, Integer.valueOf(pscore));
			allPlayersPositionInfo.put(pname, plocation);
			if (list.getLength() == 1)
				model.updateInfo(gameId, managingUser, pname, globalStartingCol, globalStaringRow, boardInfo, score,
						bonusCell);
			else if (pname.equals(this.model.getPlayer().getName())) {
				model.updateInfo(gameId, managingUser, pname, globalStartingCol, globalStaringRow, boardInfo, score,
						bonusCell);
			}
			this.model.getBoard().positions.add(plocation);

			// app.getResponseArea().append(" " + pname + "\n");
		}

		// app.getResponseArea().append(response.toString());
		// app.getResponseArea().append("\n");

		model.getGame().setPlayersInfoMap(allPlayersInfo);
		model.getGame().setPlayersPositionMap(allPlayersPositionInfo);

		if (this.flag == true) {
			((LeftBoardPanel) app.getMultiGame().getLeftBoardPanel()).refreshBoard();
			((RightGameInfoBoard) app.getMultiGame().getRightGameInfoPanel()).updateGameInfoBoard();
		}
		this.flag = true;

		return true;
	}

}
