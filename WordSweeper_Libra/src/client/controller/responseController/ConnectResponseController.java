package client.controller.responseController;

import client.model.Model;
import client.view.Application;
import xml.Message;

/**
 * Connect response information sent from server should be received once a player is trying to connect to the server.
 * This class deals with the connect response information based on xml transmission protocol.
 * 
 * The {@link #process(Message)}} returns true if the connection is successful.
 * @author You Zhou, Qingquan Zhao, Han Bao, Ruochen Shi (Authors contribute equally)
 *
 */

public class ConnectResponseController extends ControllerChain {
	public Application app;
	public Model model;

	public ConnectResponseController(Application a, Model m) {
		super();
		this.app = a;
		this.model = m;
	}

	@Override
	public boolean process(Message response) {
		String type = response.contents.getFirstChild().getLocalName();
		if (!type.equals("connectResponse")) {
			return next.process(response);
		}
		return true;
	}

}
