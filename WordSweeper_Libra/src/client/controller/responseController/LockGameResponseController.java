package client.controller.responseController;

import client.model.Model;
import client.view.Application;
import client.view.LeftBoardPanel;
import client.view.RightGameInfoBoard;
import xml.Message;

/**
 * A lock game response information sent from server should be received once the game manager locks the game. 
 * No new players are able to join the game.
 * 
 * The {@link #process(Message)} handles the lock game response information, and set the game locked to be true.
 * @author You Zhou, Qingquan Zhao, Han Bao, Ruochen Shi (Authors contribute equally)
 *
 */

public class LockGameResponseController extends ControllerChain {

	public Application app;
	public Model model;

	public LockGameResponseController(Application a, Model m) {
		super();
		this.app = a;
		this.model = m;
	}

	@Override
	public boolean process(Message response) {
		String type = response.contents.getFirstChild().getLocalName();
		if (!type.equals("lockGameResponse")) {
			return next.process(response);
		}

		model.getGame().setLocked(true);

		((LeftBoardPanel) app.getMultiGame().getLeftBoardPanel()).refreshBoard();
		((RightGameInfoBoard) app.getMultiGame().getRightGameInfoPanel()).updateGameInfoBoard();

		return true;
	}

}
