package client.controller.requestController;

import client.model.Model;
import client.view.Application;
import xml.Message;

public class FindWordController {
	Application app;
	Model model;

	public FindWordController(Application app, Model model) {
		this.app = app;
		this.model = model;
	}

	/** Make the request on the server and wait for response. */
	public void process() {
		
		String chosenCellsInfoXMLString = model.getBoard().getChosenCellsXMLString();
		
		String xmlString = Message.requestHeader() + String.format("<findWordRequest gameId='%s' name='%s' word='%s'>",
																							model.getGame().getGameID(),
																							model.getPlayer().getName(), 
																							model.getBoard().getWord().getContent())
																	+
																	chosenCellsInfoXMLString
																	+
																	"</findWordRequest></request>";
		System.out.println(xmlString);
		Message m = new Message (xmlString);
		app.getRequestArea().append(m.toString());
		app.getRequestArea().append("\n");
		app.getServerAccess().sendRequest(m);
	}
}
