package client.controller.responseController;

import xml.Message;


/**
 * This takes care of the message that can not be handled by current registered handler. 
 * 
 * The {@link #process(Message)}} will print out a "Not handled" information to notify the player.
 * @author QQZhao
 *
 */

public final class EmptyHandler extends ControllerChain {

	@Override
	public boolean process(Message response) {
		System.err.println("Not Handled:" + response);
		return true;
	}

}
