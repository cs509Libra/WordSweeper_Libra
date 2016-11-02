package server.controller;
import server.ClientState;

/**
 * Controller to detect when terminating client has something to be cleaned up.
 * That is, the exit appears to be system-related, not by user action.
 */
public class ClientDisconnectController {

	public void process(ClientState state) {
		System.out.println ("Client disconnected for client:" + state.id());
	}
}
