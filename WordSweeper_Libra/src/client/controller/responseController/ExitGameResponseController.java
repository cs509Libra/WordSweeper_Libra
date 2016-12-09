package client.controller.responseController;

import client.model.Model;
import client.view.Application;
import xml.Message;


/**
 * An exit game response information should be received once a player is trying to exit the game.
 * This handles the exit game response information
 * 
 * The {@link #process(Message)}} updates the isWaitingResponse and existedGame information in entity classes, and returns the boundary from game GUI to the login GUI.
 * 
 * @author QQZhao
 *
 */

public class ExitGameResponseController extends ControllerChain {

	public Application app;
	public Model model;

	public ExitGameResponseController(Application a, Model m) {
		super();
		this.app = a;
		this.model = m;
	}

	@Override
	public boolean process(Message response) {

		System.out.println("Response::" + response.toString());
		app.getResponseArea().append(response.toString());
		app.getResponseArea().append("\n");

		this.model.isWaitingResponse = false;
		String type = response.contents.getFirstChild().getLocalName();
		if (!type.equals("exitGameResponse")) {
			return next.process(response);
		}
		this.model.existedGame = false;

		// <xs:attribute name='gameId' type='xs:string' use='required'/>
		app.getMg().dispose();
		app.enableInputs();

		// app.setVisible(true);

		// app.getResponseArea().append(response.toString());
		// app.getResponseArea().append("\n");
		return true;
	}
}
