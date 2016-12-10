package client.view;

import static org.junit.Assert.*;

import javax.swing.JButton;

import org.junit.Test;

import client.model.Model;

public class TestApplication {

	Model model = new Model();
	
	@Test
	public void testApplication() {
		Application app = new Application(model);
	}
	
	
}
