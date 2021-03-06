package client;

import javax.swing.UIManager;
import javax.swing.plaf.metal.MetalLookAndFeel;

import client.controller.responseController.BoardResponseController;
import client.controller.responseController.ConnectResponseController;
import client.controller.responseController.ExitGameResponseController;
import client.controller.responseController.FindWordResponseController;
import client.controller.responseController.JoinGameResponseController;
import client.controller.responseController.LockGameResponseController;
import client.controller.responseController.ResetGameResponseController;
import client.controller.responseController.SampleClientMessageHandler;
import client.model.Model;
import client.view.Application;
import xml.Message;

/** Launch command-line Client to show minimal access needs. */
public class ClientLauncher {

	// If requested by ClientLauncher (pass in '-server' as argument).
	// public static final String serverHost = "72.249.186.243";
	public static final String serverHost = "cccwork3.wpi.edu";
	// public static final String serverHost = "rambo.wpi.edu";

	/**
	 * Note that to simplify the coding of this command-client, it declares that
	 * it can throw an Exception, which is typically the failed connection to a
	 * server.
	 */
	public static void main(String[] args) throws Exception {

		UIManager.setLookAndFeel(new MetalLookAndFeel());

		// FIRST thing to do is register the protocol being used. There will be
		// a single class protocol
		// that will be defined and which everyone will use. For now,
		// demonstrate with skeleton protocol.
		if (!Message.configure("wordsweeper.xsd")) {
			System.exit(0);
		}

		// select dedicated server with '-server' options
		// String host = "localhost";
		String host = "cccwork3.wpi.edu";
		// String host = "rambo.wpi.edu";

		if (args.length > 0 && args[0].equals("-server")) {
			host = serverHost;
		}

		// Initialize the client application and its corresponding model
		Model model = new Model();
		Application app = new Application(model);

		SampleClientMessageHandler handler = new SampleClientMessageHandler(app);

		handler.registerHandler(new BoardResponseController(app, model));
		handler.registerHandler(new FindWordResponseController(app, model));
		handler.registerHandler(new JoinGameResponseController(app, model));
		handler.registerHandler(new ConnectResponseController(app, model));
		handler.registerHandler(new LockGameResponseController(app, model));
		handler.registerHandler(new ResetGameResponseController(app, model));
		handler.registerHandler(new ExitGameResponseController(app, model));

		// try to connect to the server. Once connected, messages are going to
		// be processed by
		// SampleClientMessageHandler. For now we just continue on with the
		// initialization because
		// no message is actually sent by the connect method.
		ServerAccess sa = new ServerAccess(host, 11425);
		if (!sa.connect(handler)) {
			System.out.println("Unable to connect to server (" + host + "). Exiting.");
			System.exit(0);
		}
		System.out.println("Connected to " + host);

		// Should we on the client ever need to communicate with the server, we
		// need this ServerAccess
		// object.
		app.setServerAccess(sa);
		// send an introductory connect request now that we have created (but
		// not made visible!)
		// the GUI
		String xmlString = Message.requestHeader() + "<connectRequest/></request>";
		Message m = new Message(xmlString);
		sa.sendRequest(m);
		app.getRequestArea().append(m.toString() + "\n");

		// at this point, we need to make app visible, otherwise we would
		// terminate application
		app.setVisible(true);
	}
}
