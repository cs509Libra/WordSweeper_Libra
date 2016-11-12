package client.controller.responseController;

import client.model.Model;
import client.view.Application;
import client.view.LeftBoardPanel;
import client.view.RightGameInfoBoard;
import xml.Message;

public class ConnectResponseController extends ControllerChain {
	public Application app;
	public Model model;
	private boolean flag;
	
	public ConnectResponseController(Application a, Model m) {
		super();
		this.app = a;
		this.model = m;
		this.flag = false;
	}
	
	@Override
	public boolean process(Message response) {
		String type = response.contents.getFirstChild().getLocalName();
		if (!type.equals ("connectResponse")) {
			return next.process(response);
		}
		
		app.getResponseArea().append(response.toString() + "\n");

		if (this.flag == true) {
			((LeftBoardPanel) app.getMultiGame().getLeftBoardPanel()).refreshBoard();
			((RightGameInfoBoard) app.getMultiGame().getRightGameInfoPanel()).updateGameInfoBoard();
		}
		this.flag = true;

		return true;
	}

}
