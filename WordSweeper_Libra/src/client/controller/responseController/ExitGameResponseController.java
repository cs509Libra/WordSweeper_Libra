package client.controller.responseController;

import client.model.Model;
import client.view.Application;
import xml.Message;

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
		String type = response.contents.getFirstChild().getLocalName();
		if (!type.equals ("exitGameResponse")) {
			return next.process(response);
		}
		
		//<xs:attribute name='gameId' type='xs:string' use='required'/>
		
		return true;
	}

}

