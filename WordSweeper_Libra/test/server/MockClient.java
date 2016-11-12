package server;

import java.util.ArrayList;

import server.ClientState;
import xml.Message;

/**
 * Represent "client" from the point of view of the server.
 */
public class MockClient implements ClientState {

	final String id;
	Object data;
	ArrayList<Message> messages = new ArrayList<Message>();
	
	// a default
	public MockClient() {
		this.id = "1";
	}
	
	// useful it you want multiple 'mock clients' to work with...
	public MockClient(String id) {
		this.id = id;
	}
	
	// "sends" message by holding onto it for later inspection 
	public boolean sendMessage(Message m) {
		messages.add(m);
		return true;
	}

	// returns the message that was first "sent" in the pipeline
	public Message getAndRemoveMessage() {
		if (messages.isEmpty()) { return null; }
		
		return messages.remove(0); 
	}
	
	// store objects for later use
	public Object setData(Object newData) {
		Object old = data;
		data = newData;
		return old;
	}
	
	// retrieve stored object (if any)
	public Object getData() {
		return data;
	}

	// our server-generated unique id.
	public String id() {
		return id;
	}

}
