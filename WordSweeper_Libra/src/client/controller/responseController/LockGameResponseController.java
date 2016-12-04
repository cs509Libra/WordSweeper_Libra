package client.controller.responseController;

import client.model.Model;
import client.view.Application;
import client.view.LeftBoardPanel;
import client.view.RightGameInfoBoard;
import xml.Message;

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

		// app.getResponseArea().append(response.toString() + "\n");
		model.getGame().setLocked(true);

		((LeftBoardPanel) app.getMultiGame().getLeftBoardPanel()).refreshBoard();
		((RightGameInfoBoard) app.getMultiGame().getRightGameInfoPanel()).updateGameInfoBoard();

		return true;
	}

}
