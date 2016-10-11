package client;

import java.util.ArrayList;
import java.util.Hashtable;

import xml.Message;
import client.IController;
import client.IMessageHandler;
import client.ServerAccess;

/**
 * Mock Server Access replaces the entire functionality of the ServerAccess
 * class and provides a "simulation of it". 
 * <p>
 * One cardinal rule regarding constructors is that the method should do as little
 * as possible; this is often violated in GUI programming. However, when it is the
 * case, note that this object states it offers the behavior but completely does
 * things without actually communicating to a server.
 * <p>
 * Use the {@link #getAndClearMessages()} and {@link #getAndClearWaitingMessages()}
 * methods to retrieve the requests that were "processed" by this mock server.
 * 
 * @author heineman
 */
public class MockServerAccess extends ServerAccess {

	/** Sent messages. */
	ArrayList<Message> sentMessages = new ArrayList<Message>();
	
	/** Special message requests to be process. */
	Hashtable<String, Message> waiting = new Hashtable<String,Message>();
	
	public MockServerAccess(String host) {
		super(host);
	}

	public MockServerAccess(String host, int port) {
		super(host, port);
	}
	
	/** To simulate connect, just grab onto callback handler object. */
	@Override
	public boolean connect(final IMessageHandler handler) {
		return true;
	}
	
	/** To simulate disconnect, clear out. */
	@Override	
	public void disconnect() {
		
	}
	
	/** Mock server is true to its name. */
	public String getHost() {
		return "mockServer";
	}
	
	/** Requests are held onto so they can be inspected later. */
	public synchronized boolean sendRequest(Message r) {
		sentMessages.add(r);
		return true;
	}
	
	/** Requests to be processed specially are held separately. */
	public synchronized boolean sendRequest(IController c, Message m) {
		waiting.put(m.id(), m);
		return true;
	}
	
	/** Get and clear sent Messages. */
	public ArrayList<Message> getAndClearMessages() {
		ArrayList<Message> al = sentMessages;
		sentMessages = new ArrayList<Message>();
		return al;
	}

	/** Get and clear waiting Messages. */
	public Hashtable<String, Message> getAndClearWaitingMessages() {
		Hashtable<String, Message> ht = new Hashtable<String, Message>();
		waiting.clear();
		return ht;
	}
	
}