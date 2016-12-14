package client.controller.responseController;

import client.model.Model;
import client.view.Application;
import client.view.LeftBoardPanel;
import client.view.RightGameInfoBoard;
import xml.Message;

/**
 * A reset game response information sent from server should be received once the game manager reset the game.
 * 
 * The {@link #process(Message)} handles the reset game response message based on xml protocol, and reset board and player score in entities, and displays in the corresponding boundary GUIs.
 * @author You Zhou, Qingquan Zhao, Han Bao, Ruochen Shi (Authors contribute equally)
 *
 */

public class ResetGameResponseController extends ControllerChain {

	public Application app;
	public Model model;

	public ResetGameResponseController(Application a, Model m) {
		super();
		this.app = a;
		this.model = m;
	}

	@Override
	public boolean process(Message response) {
		String type = response.contents.getFirstChild().getLocalName();
		if (!type.equals("resetGameResponse")) {
			return next.process(response);
		}

		model.resetGame();

		((LeftBoardPanel) app.getMultiGame().getLeftBoardPanel()).refreshBoard();
		((RightGameInfoBoard) app.getMultiGame().getRightGameInfoPanel()).updateGameInfoBoard();

		return true;
	}
}
