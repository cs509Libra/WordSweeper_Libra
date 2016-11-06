package client.controller.responseController;

import client.model.Model;
import client.view.Application;
import xml.Message;

public class ListGameResponseController extends ControllerChain {

	public Application app;
	public Model model;
	
	public ListGameResponseController(Application a, Model m) {
		super();
		this.app = a;
		this.model = m;
	}
	
	@Override
	public boolean process(Message response) {
		String type = response.contents.getFirstChild().getLocalName();
		if (!type.equals ("listGameResponse")) {
			return next.process(response);
		}
		
		
		
		return true;
	}


}
