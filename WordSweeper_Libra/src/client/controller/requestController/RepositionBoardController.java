package client.controller.requestController;

import client.model.Model;
import client.view.Application;
import xml.Message;

/**
 * When the player clicks any direction button around the board, a
 * repositionBoardRequest will be sent to the server.
 * <p>
 * The {@link #process()} makes a repositionBoardRequest in XML format, and send
 * it to the server.
 * 
 * @author HanBao
 *
 */
public class RepositionBoardController {
	Application app;
	Model model;

	public RepositionBoardController(Application app, Model model) {
		this.app = app;
		this.model = model;
	}

	/**
	 * Make a repositionBoardRequest in XML format, and send it to the server.
	 */
	public void process() {

		String xmlString = Message.requestHeader()
				+ String.format("<repositionBoardRequest name='%s' gameId='%s'", model.getPlayer().getName(),
						model.getGame().getGameID())
				+ " rowChange='" + model.getBoard().getRequestRowChange() + "' colChange='"
				+ model.getBoard().getRequestColChange() + "'/></request>";

		Message m = new Message(xmlString);
		app.getRequestArea().append(m.toString());
		app.getRequestArea().append("\n");
		app.getServerAccess().sendRequest(m);
	}
}
